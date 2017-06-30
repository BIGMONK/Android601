package youtu.android601;

import android.app.Application;

import java.io.File;

import youtu.android601.utils.FileUtils;
import youtu.android601.utils.PermissionUtils;

/**
 * Created by djf on 2017/6/12.
 */

public class MyApplication extends Application {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

//
//     Log.d(TAG, "hasRootPermission ：" + PermissionUtils.hasRootPermission());
//
//      Log.d(TAG, "runRootCommand Permission 1：" + PermissionUtils.runRootCommand("mount -o " +
//                "remount,rw /system"));//先挂载
//        Log.d(TAG, "upgradeRootPermission  2：" + PermissionUtils.upgradeRootPermission
//                ("/system/media", "-R 777"));//再修改权限
//
////        Log.d(TAG, "runRootCommand Permission 3：" +PermissionUtils.runRootCommand("rm -f
//// /system/media/bootanimation.zip"));//
//        Log.d(TAG, "runRootCommand Permission 3：" +PermissionUtils.runRootCommand("touch /system/media/bootanimation.zip"));//
////        Log.d(TAG, "upgradeRootPermission  ：4" + PermissionUtils.upgradeRootPermission
//// ("/system/media/bootanimation.zip","777"));//再修改权限
////
////        Log.d(TAG, "getPackageResourcePath=" + getPackageResourcePath());
////        Log.d(TAG, "getFilesDir=" + getFilesDir().getAbsolutePath());
////        Log.d(TAG, "getPackageResourcePath=" + getPackageResourcePath());
////        Log.d(TAG, "getCacheDir=" + getCacheDir().getAbsolutePath());
////        Log.d(TAG, "getExternalCacheDir=" + getExternalCacheDir().getAbsolutePath());
//
//        FileUtils.copyFileFromAssets(getApplicationContext(), "bootanimation.zip", getFilesDir()
//                .getAbsolutePath());
//        Log.d(TAG, "runRootCommand Permission 4444：" + PermissionUtils.runRootCommand("cp -f  " +
//                getFilesDir().getAbsolutePath() + "/bootanimation.zip" + " " +
//                "/system/media/bootanimation.zip"));//
//
////        FileUtils.copyFileFromAssets(getApplicationContext(),"bootanimation.zip","/system/media");

        if (PermissionUtils.isSuSuccessedRun()) {
            String filename = "bootanimation.zip";
            String filepath = getFilesDir().getAbsolutePath();
            File fil=new File(filepath + File.separator + filename);
            if (!fil.exists()) {
                FileUtils.copyFileFromAssets(getApplicationContext(), filename, filepath);
            }
            File file = new File("/system/media/bootanimation.zip");
            if (!(file.exists()&&FileUtils.isFileEqual(file,fil))) {
                PermissionUtils.runRootCommand("mount -o " + "remount,rw /system");
                PermissionUtils.runRootCommand("chmod -R 777 /system/media");
                PermissionUtils.runRootCommand("cp -f "
                        + filepath + File.separator + filename +" "+
                        file.getAbsolutePath());
                PermissionUtils.runRootCommand("chmod -R 444 "+file.getAbsolutePath());

            }
        }

    }

}
