package singerstone.com.superapp.v2sign;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ApkSignatureV2ChannelTool {

    /**
     * 自定义放置渠道号 Pair 的 ID 值，不能与 { APK_SIGNATURE_SCHEME_V2_BLOCK_ID} 0x7109871a 冲突
     */
    private static final int YYB_CHANNEL_BLOCK_ID = 0x79646e61;
    
    public static boolean isSignatureV2Apk(String apkFile) throws IOException {
        return ApkSignatureSchemeV2Verifier.hasSignature(apkFile);
    }
    
    /**
     * 写渠道号，会覆盖原有 YYB_CHANNEL_BLOCK_ID 中的 channel value 值
     * @param apkFile
     * @param channel
     * @throws IOException
     * @throws SignatureNotFoundException
     */
    public static void wirteChannel(String apkFile, String channel) throws Exception {
        updateApkWithPair(apkFile, YYB_CHANNEL_BLOCK_ID, channel);
    }
    
    /**
     * 读取存储在 APK Signing Block 中的渠道号
     * @param apkFile
     * @return 渠道号
     * @throws IOException 
     * @throws SignatureNotFoundException 按 V2 签名方式没有找到渠道号（1.非V2签名包，2.V2签名包，但未打渠道号）
     */
    public static String readChannel(String apkFile) throws Exception {
        return readPairValueWithId(apkFile, YYB_CHANNEL_BLOCK_ID);
    }

    /**
     * 向 APK Signing Block 更新/增加 iID-value pairs
     * https://source.android.com/security/apksigning/v2.html
     * Sequence of uint64-length-prefixed ID-value pairs:
     *  ID (uint32)
     *  value (variable-length: length of the pair - 4 bytes)
     * @param apkFile
     * @param id
     * @param value
     * @throws IOException 
     * @throws SignatureNotFoundException
     */
    static void updateApkWithPair(String apkFile, int id, String value) throws Exception {
        try (RandomAccessFile apk = new RandomAccessFile(apkFile, "rw")) {
            Pair<ByteBuffer, Long> eocdAndOffsetInFile = ApkSignatureSchemeV2Verifier.getEocd(apk);
            ByteBuffer eocd = eocdAndOffsetInFile.first;
            long eocdOffset = eocdAndOffsetInFile.second;
            if (ZipUtils.isZip64EndOfCentralDirectoryLocatorPresent(apk, eocdOffset)) {
                throw new SignatureNotFoundException("ZIP64 APK not supported");
            }

            // Find the APK Signing Block. The block immediately precedes the Central Directory.
            long centralDirOffset = ApkSignatureSchemeV2Verifier.getCentralDirOffset(eocd, eocdOffset);
            
            ByteBuffer cdfh = getCdfh(apk, centralDirOffset, (int)(eocdOffset - centralDirOffset));
            
            Pair<ByteBuffer, Long> apkSigningBlockAndOffsetInFile =
                    ApkSignatureSchemeV2Verifier.findApkSigningBlock(apk, centralDirOffset);
            ByteBuffer apkSigningBlock = apkSigningBlockAndOffsetInFile.first;
            long apkSigningBlockOffset = apkSigningBlockAndOffsetInFile.second;

            // update APK Signing Block with pair(id, value)
            Pair<ByteBuffer, Long> apkSigningBlockAndChnageSize = genApkSigningBlockWithNewPair(apkSigningBlock, id, value);
            ByteBuffer newApkSigningBlock = apkSigningBlockAndChnageSize.first;
            long changeSize = apkSigningBlockAndChnageSize.second;

            // update the offset of the start of the ZIP Central Directory
            ZipUtils.setZipEocdCentralDirectoryOffset(eocd, centralDirOffset + changeSize);

            apk.seek(apkSigningBlockOffset);
            apk.write(newApkSigningBlock.array(),
                    newApkSigningBlock.arrayOffset() + newApkSigningBlock.position(),
                    newApkSigningBlock.remaining());
            apk.write(cdfh.array(), cdfh.arrayOffset() + cdfh.position(), cdfh.remaining());
            apk.write(eocd.array(), eocd.arrayOffset() + eocd.position(), eocd.remaining());
            if (changeSize < 0) {
                apk.setLength(apk.length() + changeSize);
            }
        }
    }
    
    /**
     * 读取 APK Signing Block 中 id 对应的 value 值
     * @param apkFile
     * @param id
     * @return APK Signing Block 中 id 对应的 value
     * @throws IOException IO异常
     * @throws SignatureNotFoundException 非V2签名包，或者V2签名包但 APK Signing Block 中没 id，会抛出这个异常
     */
    static String readPairValueWithId(String apkFile, int id) throws Exception {
        try (RandomAccessFile apk = new RandomAccessFile(apkFile, "r")) {
            Pair<ByteBuffer, Long> eocdAndOffsetInFile = ApkSignatureSchemeV2Verifier.getEocd(apk);
            ByteBuffer eocd = eocdAndOffsetInFile.first;
            long eocdOffset = eocdAndOffsetInFile.second;
            if (ZipUtils.isZip64EndOfCentralDirectoryLocatorPresent(apk, eocdOffset)) {
                throw new SignatureNotFoundException("ZIP64 APK not supported");
            }

            // Find the APK Signing Block. The block immediately precedes the Central Directory.
            long centralDirOffset = ApkSignatureSchemeV2Verifier.getCentralDirOffset(eocd, eocdOffset);
            Pair<ByteBuffer, Long> apkSigningBlockAndOffsetInFile =
                    ApkSignatureSchemeV2Verifier.findApkSigningBlock(apk, centralDirOffset);
            ByteBuffer apkSigningBlock = apkSigningBlockAndOffsetInFile.first;

            // Find the channel Block from the APK Signing Block.
            ByteBuffer channelBlock = ApkSignatureSchemeV2Verifier.findApkSigningBlockWithId(apkSigningBlock, id);
            
            byte [] bytes = new byte[channelBlock.remaining()];
            channelBlock.get(bytes, 0, bytes.length);
            
            return new String(bytes);
        }
    }
    
    private static ByteBuffer getCdfh(RandomAccessFile apk, long centralDirOffset, int centralDirSize) throws IOException {
        ByteBuffer cdfh = ByteBuffer.allocate(centralDirSize);
        cdfh.order(ByteOrder.LITTLE_ENDIAN);
        apk.seek(centralDirOffset);
        apk.readFully(cdfh.array(), cdfh.arrayOffset(), cdfh.capacity());
        return cdfh;
    }
    
    private static Pair<ByteBuffer, Long> genApkSigningBlockWithNewPair(ByteBuffer apkSigningBlock,
            int pairId, String pairValue)
            throws SignatureNotFoundException, UnsupportedEncodingException {
        ApkSignatureSchemeV2Verifier.checkByteOrderLittleEndian(apkSigningBlock);
        // FORMAT:
        // OFFSET       DATA TYPE  DESCRIPTION
        // * @+0  bytes uint64:    size in bytes (excluding this field)
        // * @+8  bytes pairs
        // * @-24 bytes uint64:    size in bytes (same as the one above)
        // * @-16 bytes uint128:   magic
        ByteBuffer pairs = ApkSignatureSchemeV2Verifier.sliceFromTo(apkSigningBlock, 8, apkSigningBlock.capacity() - 24);

        byte[] value = pairValue.getBytes("UTF-8");
        int pairSize = 8 + 4 + value.length;
        long changeSize = 0;
        ByteBuffer newApkSigningBlock = ByteBuffer.allocate(apkSigningBlock.capacity() + pairSize);
        newApkSigningBlock.order(ByteOrder.LITTLE_ENDIAN);
        newApkSigningBlock.position(8);
        
        int entryCount = 0;
        while (pairs.hasRemaining()) {
            entryCount++;
            if (pairs.remaining() < 8) {
                throw new SignatureNotFoundException(
                        "Insufficient data to read size of APK Signing Block entry #" + entryCount);
            }
            long lenLong = pairs.getLong();
            if ((lenLong < 4) || (lenLong > Integer.MAX_VALUE)) {
                throw new SignatureNotFoundException(
                        "APK Signing Block entry #" + entryCount
                                + " size out of range: " + lenLong);
            }

            int len = (int) lenLong;
            int nextEntryPos = pairs.position() + len;
            if (len > pairs.remaining()) {
                throw new SignatureNotFoundException(
                        "APK Signing Block entry #" + entryCount + " size out of range: " + len
                                + ", available: " + pairs.remaining());
            }
            
            int id = pairs.getInt();
            if (id == pairId) {
                changeSize = value.length + 4 - len;
                newApkSigningBlock.putLong(value.length + 4);
                newApkSigningBlock.putInt(id);
                newApkSigningBlock.put(value);
                pairs.position(nextEntryPos);
                newApkSigningBlock.put(pairs.array(), pairs.arrayOffset(), pairs.remaining());
                pairs.position(pairs.limit());
                break;
            } else {
                newApkSigningBlock.putLong(lenLong);
                newApkSigningBlock.putInt(id);
                newApkSigningBlock.put(ApkSignatureSchemeV2Verifier.getByteBuffer(pairs, len - 4));
                pairs.position(nextEntryPos);
            }
        }
        
        if (changeSize == 0) {
            newApkSigningBlock.putLong(value.length + 4);
            newApkSigningBlock.putInt(pairId);
            newApkSigningBlock.put(value);
            changeSize = pairSize;
        }
        
        long oldSize = apkSigningBlock.getLong();
        long newSize = oldSize + changeSize;
        newApkSigningBlock.putLong(newSize);
        newApkSigningBlock.put(apkSigningBlock.array(), apkSigningBlock.array().length - 16, 16);
        
        newApkSigningBlock.position(0);
        newApkSigningBlock.putLong(newSize);
        
        newApkSigningBlock.position(0);
        newApkSigningBlock.limit((int)newSize + 8);
        
        return Pair.create(newApkSigningBlock, changeSize);
    }
    
}
