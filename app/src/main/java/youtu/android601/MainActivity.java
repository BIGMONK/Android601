package youtu.android601;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import youtu.android601.db.BleBean;
import youtu.android601.db.DBManager;
import youtu.android601.utils.FileUtil;
import youtu.android601.utils.PermissionUtils;
import youtu.android601.view.CheckView;
import youtu.android601.view.Code;
import youtu.android601.view.PromptDialogFragment;
import youtu.android601.view.RoundBorImageView;

//import com.greendao.gen.BleBeanDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        PromptDialogFragment.PromptDialogFragmentListener {

    private String TAG = this.getClass().getSimpleName();
    private TextView tv;
    private TextView tv2;
    private RoundBorImageView iv1;
    private RoundBorImageView iv2;
    private RoundBorImageView iv3;
    private TextView tv3;
    private TextView move;
    Button btn1, btn2;
    private ImageView image;
    private PromptDialogFragment promptDialogFragment;
    private CheckView checkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Glide.with(this).
                load("https://ss0.bdstatic" +
                        ".com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000" +
                        "&sec=1495097451&di=a7468d381ba3fbd81e6ec240d04e0014&src=http://pic1" +
                        ".win4000.com/wallpaper/1/58816e7a790b0.jpg").into(iv1);
        Glide.with(this).
                load("https://ss0.bdstatic" +
                        ".com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000" +
                        "&sec=1495097451&di=a7468d381ba3fbd81e6ec240d04e0014&src=http://pic1" +
                        ".win4000.com/wallpaper/1/58816e7a790b0.jpg").into(iv2);
        Glide.with(this).
                load("https://ss0.bdstatic" +
                        ".com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000" +
                        "&sec=1495097451&di=a7468d381ba3fbd81e6ec240d04e0014&src=http://pic1" +
                        ".win4000.com/wallpaper/1/58816e7a790b0.jpg").into(iv3);


        tv.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        image.setImageBitmap(Code.getInstance().getBitmap());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(Code.getInstance().getBitmap());
            }
        });

        checkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.refresh();
            }
        });

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        110);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        try {
            if (("rk3288".equalsIgnoreCase(android.os.Build.DEVICE)) && PermissionUtils
                    .isSuSuccessedRun()) {

                final File file = new File("/system/media/bootanimation.zip");

                if (!file.exists()) {
                    Log.d(TAG,"文件不存在");
                } else

                    //修改开机log
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String filename = "bootanimation.zip";
                            String filepath = getFilesDir().getAbsolutePath();
                            File fil = new File(filepath + File.separator + filename);
                            if (!fil.exists()) {
                                FileUtil.copyFileFromAssets(getApplicationContext(), filename, fil
                                        .getAbsolutePath());
                            }
                            if (!(file.exists() && FileUtil.isFileEqual(file, fil))) {
                                PermissionUtils.runRootCommand("mount -o " + "remount,rw /system");
                                PermissionUtils.runRootCommand("chmod -R 777 /system/media");
                                PermissionUtils.runRootCommand("cp -f "
                                        + filepath + File.separator + filename + " " +
                                        file.getAbsolutePath());
                                PermissionUtils.runRootCommand("chmod -R 444 " + file
                                        .getAbsolutePath
                                                ());
                            }
                        }

                    }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 110: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initView() {
        iv1 = (RoundBorImageView) findViewById(R.id.iv1);
        iv2 = (RoundBorImageView) findViewById(R.id.iv2);
        iv3 = (RoundBorImageView) findViewById(R.id.iv3);
        tv = (TextView) findViewById(R.id.tv);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        move = (TextView) findViewById(R.id.move);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        image = (ImageView) findViewById(R.id.image);
        checkView = (CheckView) findViewById(R.id.checkview);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        int densityDpi = dm.densityDpi;
        float scaledDensity = dm.scaledDensity;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        int heightPixels = dm.heightPixels;
        int widthPixels = dm.widthPixels;

        tv3.setText("屏幕参数:" + dm.toString() +
                "\n  density=" + density +
                "  densityDpi=" + densityDpi +
                "  scaledDensity=" + scaledDensity +
                "  xdpi=" + xdpi +
                "  ydpi=" + ydpi +
                "  widthPixels=" + widthPixels +
                "  heightPixels=" + heightPixels +
                "\n " + "Product Model: " +
                "\n " + "Build.MODEL:" + Build.MODEL +
                "\n " + "Build.VERSION.SDK: " + Build.VERSION.SDK +
                "\n " + "Build.VERSION.SDK_INT:" + Build.VERSION.SDK_INT +
                "\n " + "Build.VERSION.RELEASE=" + Build.VERSION.RELEASE +
                "\n " + "Build.VERSION.CODENAME: " + Build.VERSION.CODENAME +
                "\n " + "Build.VERSION.INCREMENTAL:" + Build.VERSION.INCREMENTAL +
                ""
        );

        //获取数据库操作对象
        DBManager dbManager = DBManager.getInstance(this);
        dbManager.insertUser(new BleBean(null, "测试1", "asdfa", 2));
        dbManager.updateUser(new BleBean(4L, "测试1", "asdfa", 3));
        List<BleBean> list = dbManager.queryUserList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                BleBean bleBean = list.get(i);
                System.out.println("FFFFFFF:" + bleBean.getId() + "  " + bleBean.getName() + "  "
                        + bleBean.getMac() + "  " + bleBean.getType());
            }
            System.out.println("FFFFFFF list != null");
        } else
            System.out.println("FFFFFFF无数据");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("v7.app.AlertDialog");
                String[] choice = new String[]{"选项一", "选项二"};
                builder.setSingleChoiceItems(choice, 1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(TAG, "languageChange已选择：" + which + "  switched=" + 1);
                                switch (which) {
                                    case 0:
                                        break;
                                    case 1:
                                        break;
                                }
                                dialog.dismiss(); // 让窗口消失
                            }
                        }
                );
                builder.create().show();
                break;
            case R.id.tv2:
                android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder
                        (this);
                builder2.setTitle("android.app.AlertDialog");
                String[] choice2 = new String[]{"选项一", "选项二"};
                builder2.setSingleChoiceItems(choice2, 1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d(TAG, "languageChange已选择：" + which + "  switched=" + 1);
                                switch (which) {
                                    case 0:
                                        break;
                                    case 1:
                                        break;

                                }
                                dialog.dismiss(); // 让窗口消失
                            }
                        }
                );
                builder2.create().show();
                break;
            case R.id.tv3:
                if (promptDialogFragment == null)
                    promptDialogFragment = new PromptDialogFragment();
                promptDialogFragment.messageString = "exit_prompt";
                promptDialogFragment.show(getFragmentManager(), "骑行未结束");
                break;

            case R.id.btn1:
                move.scrollBy(10, 10);
                break;

            case R.id.btn2:
                move.scrollBy(0 - 10, 0 - 10);

                break;

        }
    }


    @Override
    public void onPromptListener(boolean prompt) {

    }
}
