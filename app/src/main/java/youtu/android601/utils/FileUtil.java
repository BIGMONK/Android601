package youtu.android601.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by liuenbao on 1/25/16.
 */
public class FileUtil {


    private static final String TAG = FileUtil.class.getSimpleName();

    public static String getJsonFromAssets(Context context, String filename) {
        InputStream mInputStream = null;
        String resultString = "";
        try {
            mInputStream = context.getAssets().open(filename);
            byte[] buffer = new byte[mInputStream.available()];
            mInputStream.read(buffer);
            resultString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString.toString();
    }

    public static Bitmap getBitmapFromAssets(Context context, String fileName) {
        Bitmap bitmap = null;
        InputStream imageStream = null;
        try {
            imageStream = context.getAssets().open(fileName);
            bitmap = BitmapFactory.decodeStream(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (imageStream != null) {
                try {
                    imageStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }

    public static boolean CopyRawResource(Context context, int id, String path) {
        Log.d(TAG, "copy -> " + path);
        try {
            InputStream ins = context.getResources().openRawResource(id);
            int size = ins.available();

            // Read the entire resource into a local byte buffer.
            byte[] buffer = new byte[size];
            ins.read(buffer);
            ins.close();

            FileOutputStream fos = new FileOutputStream(path);
            fos.write(buffer);
            fos.close();

            return true;
        } catch (Exception e) {
            Log.d(TAG, "public void createBinary() error! : " + e.getMessage());
        }

        return false;
    }

    public static void copyFileFromAssets(Context context, String assetsFileName, String newPath) {
        try {
            File file = new File(newPath);
            FileOutputStream fs = new FileOutputStream(file);
            InputStream is = context.getAssets().open(assetsFileName);
            if (is != null) {
                copy(is, fs);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    /**
     * json文件转成字符串
     *
     * @param file
     * @return
     */
    public static String getStringFromJsonFile(File file) {
        StringBuffer sb = null;
        try {
            //            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream
                    (file)));
            //            BufferedInputStream bis = new BufferedInputStream(new FileInputStream
            // (file));
            String readline = "";
            sb = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                sb.append(readline);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
