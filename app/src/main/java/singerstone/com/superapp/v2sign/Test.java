package singerstone.com.superapp.v2sign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


public class Test {

    private static String suffix = ".apk";

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        String apkDir = System.getProperty("user.dir") +
                File.separator + "app" +
                File.separator + "build" +
                File.separator + "outputs" +
                File.separator + "apk" +
                File.separator + "debug";
        String file = apkDir + File.separator + "app-debug";
        try {
            System.out.println(file + suffix + " isSignatureV2Apk = " + ApkSignatureV2ChannelTool.isSignatureV2Apk(file + suffix));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /**
         * 测试新写渠道号
         */
        String target = testChannelTool(file, "channel");
        /**
         * 测试中文渠道支持
         */
        testChannelTool(target, "中文渠道号");
        /**
         * 测试长渠道号改短渠道号
         */
        testChannelTool(target, "short");
        /**
         * 测试短渠道号改长渠道号
         */
        testChannelTool(target, "longlonglong");

        /**
         * 测试 ChannelTool 内部方法的正确性，在已经写入渠道号的包中，再写入另外一对 id-value
         */
        testChannelToolInnerFunction(target, 0xaabbccdd, "andy-test-inner-func");
    }

    private static void nioTransferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
            try {
                in.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
            try {
                outStream.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
            try {
                out.close();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
    }

    private static String testChannelTool(String source, String channelValue) {
        String target = source + "_" + channelValue;
        nioTransferCopy(new File(source + suffix), new File(target + suffix));
        try {
            ApkSignatureV2ChannelTool.wirteChannel(target + suffix, channelValue);
            String channel = ApkSignatureV2ChannelTool.readChannel(target + suffix);
            System.out.println(target + suffix + " channel is " + channel);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return target;
    }

    private static String testChannelToolInnerFunction(String source, int id, String value) {
        String target = source + "_" + value;
        nioTransferCopy(new File(source + suffix), new File(target + suffix));
        try {
            ApkSignatureV2ChannelTool.updateApkWithPair(target + suffix, id, value);
            String channel = ApkSignatureV2ChannelTool.readPairValueWithId(target + suffix, id);
            System.out.println(target + suffix + " channel is " + channel);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return target;
    }

}
