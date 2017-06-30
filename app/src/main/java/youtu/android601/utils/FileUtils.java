package youtu.android601.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by djf on 2017/6/12.
 */

public class FileUtils {
    private static String TAG = "FileUtils";

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            Log.d(TAG, "复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    public static void copyFileFromAssetsa(Context context, String fullName, String newPath) {
        try {
            File file = new File(newPath + File.separator + fullName);
            if (!file.exists()) {
                InputStream is = context.getAssets().open(fullName);
                int bytesum = 0;
                int byteread = 0;
                if (is != null) { //文件存在时
                    FileOutputStream fs = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((byteread = is.read(buffer)) != -1) {
                        bytesum += byteread; //字节数 文件大小
                        System.out.println(bytesum);
                        fs.write(buffer, 0, byteread);
                    }
                    is.close();
                }
            } else {
                Log.d(TAG, "File exists!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void copyFileFromAssets(Context context, String assetsFileName, String newPath) {
        Log.d(TAG, "copyFileFromAssets");
        try {
            File file = new File(newPath + "/" + assetsFileName);
            FileOutputStream fs = new FileOutputStream(file);
            InputStream is = context.getAssets().open(assetsFileName);
            if (is != null) {
                copy(is, fs);
            } else {
                Log.d(TAG, "copyFileFromAssets  getAssets null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "copyFileFromAssets Exception");

        }
    }

    private static void copy(InputStream input, OutputStream output) {
        byte[] buffer = new byte[1024 * 8];
        BufferedInputStream in = new BufferedInputStream(input, 1024 * 8);
        BufferedOutputStream out = new BufferedOutputStream(output, 1024 * 8);
        long count = 0;
        int n = 0;
        try {
            while ((n = in.read(buffer, 0, 1024 * 8)) != -1) {
                out.write(buffer, 0, n);
                count += n;

            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean isFileEqual(File file1, File file2) {
        if (file1.exists() && file2.exists()) {
            if (file1.length() == file2.length()) {
                return true;
            }
        }
        return false;
    }

}
