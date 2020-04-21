package singerstone.com.superapp.log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogUtil {
    public static final String TAG = "LogUtil";

    public static final Comparator<File> FILEMODIFYTIMECOMPARETOR = (lhs, rhs) -> {
        if (lhs.lastModified() - rhs.lastModified() > 0) {
            return -1;
        } else if (lhs.lastModified() - rhs.lastModified() < 0) {
            return 1;
        } else {
            return 0;
        }

    };

    public static final FilenameFilter FILE_NAME_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".xlog");
        }
    };

    public static List<File> selectLatestLogFile(String path) {
        final File folder = new File(path);
        if (folder.exists()) {
            File[] files = folder.listFiles(FILE_NAME_FILTER);
            if (files != null) {
                Arrays.sort(files, FILEMODIFYTIMECOMPARETOR);
                return Arrays.asList(files);
            }
        }
        return null;
    }

    public static void writeLogPackage(OutputStream os, boolean withLogcat, List<File> fileList, long maxLogLength) throws IOException {
        ZipOutputStream zipOutputStream = null;
        try {
            if (isEmpty(fileList) || withLogcat) {
                zipOutputStream = new ZipOutputStream(os);
            }

            final byte[] temps = new byte[4096];
            if (!isEmpty(fileList)) {
                android.util.Log.i(TAG, "writeLogPackage() fileList begin");
                long totalSize = 0;
                for (File file : fileList) {
                    if (maxLogLength > 0 && totalSize >= maxLogLength) {
                        break;
                    }
                    long fileLength = file.length();
                    if (fileLength > 0) {
                        totalSize += fileLength;
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zipEntry.setTime(file.lastModified());
                        zipEntry.setSize(file.length());
                        zipOutputStream.putNextEntry(zipEntry);
                        try {
                            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                            try {
                                int size;
                                while ((size = inputStream.read(temps)) != -1) {
                                    zipOutputStream.write(temps, 0, size);
                                }
                            } finally {
                                try {
                                    inputStream.close();
                                } catch (Exception ignored) {
                                }
                            }
                        } catch (Exception e) {
                            android.util.Log.e(TAG, e.toString(), e);
                        } finally {
                            try {
                                zipOutputStream.closeEntry();
                            } catch (IOException ignored) {
                            }
                        }
                    }
                }
                android.util.Log.i(TAG, "writeLogPackage() fileList end");
            }
            if (withLogcat) {
                android.util.Log.i(TAG, "writeLogPackage() logcat begin");
                ZipEntry zipEntry = new ZipEntry("logcat.log");
                byte[] bytes = getLogcat(true).getBytes();
                zipEntry.setSize(bytes.length);
                zipOutputStream.putNextEntry(zipEntry);
                try {
                    zipOutputStream.write(bytes);
                } catch (Exception e) {
                    android.util.Log.e(TAG, e.toString(), e);
                } finally {
                    try {
                        zipOutputStream.closeEntry();
                    } catch (Exception ignored) {
                    }
                }

                android.util.Log.i(TAG, "writeLogPackage() logcat end");
            }
        } finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    zipOutputStream.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            android.util.Log.i(TAG, "writeLogPackage() end");
        }
    }

    public static boolean write2File(byte[] data, String dest) {
        if (data == null) {
            return false;
        }
        File file = new File(dest);
        if (file.exists()) {
            //如果文件存在，先删除再写入
            deleteFile(dest);
        }
        FileOutputStream fos = null;
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            fos.write(data);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // }
        return false;
    }

    public static boolean deleteFile(String path) {
        File dir = new File(path);
        if (dir.exists() && dir.isFile()) {
            return dir.delete();
        }
        return false;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    static String getLogcat(boolean full) throws IOException {
        Process process = Runtime.getRuntime().exec(full ? "logcat -d -v threadtime" : "logcat -d -v time");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder log = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            log.append(line);
            log.append("\n");
        }
        bufferedReader.close();
        return log.toString();
    }
}
