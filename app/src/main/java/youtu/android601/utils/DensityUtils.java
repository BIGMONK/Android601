package youtu.android601.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by liuenbao on 2/20/16.
 */
public class DensityUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, int dp) {
        DisplayMetrics dm = getDisplayMetrics(context);
//        float density = dm.density;
//        int densityDpi = dm.densityDpi;
//        float scaledDensity = dm.scaledDensity;
//        float xdpi = dm.xdpi;
//        float ydpi = dm.ydpi;
//        int heightPixels = dm.heightPixels;
//        int widthPixels = dm.widthPixels;
        return (int) (dp*( dm.densityDpi/160f));
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }

}
