package youtu.android601.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * 本地验证码
 * Created by djf on 2017/4/28.
 */

public class CheckView extends View {
    private String TAG = this.getClass().getSimpleName();
    //验证码图片
    private Bitmap bitmap = null;
    //随机生成所有的数组
    final String[] strContent = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final Context context;
    private int width;
    private int height;
    private String[] strRes;
    private int textSize;
    private Bitmap sourceBitmap;
    private Paint interferencePaint;
    private Canvas canvas;
    private Paint numPaint;

    public String[] getStrRes() {
        return strRes;
    }

    //构造函数
    public CheckView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //产生随机数
        strRes = generageRadom(strContent);
    }


    @Override
    public void draw(Canvas canvas) {
        Log.e(TAG, "draw");
        //传随机串和随机数
        bitmap = generateValidate(strContent, strRes);
        Paint paint = new Paint();
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
        } else {
            paint.setColor(Color.GRAY);
            paint.setTextSize(textSize / 2);
            canvas.drawColor(Color.BLUE);
            canvas.drawText("点击换一换", 10, textSize, paint);

        }
        super.draw(canvas);
    }

    public String[] refresh() {
        strRes = generageRadom(strContent);
        invalidate();
        return strRes;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        textSize = (width / 4) > height ? height : (width / 4);

        numPaint = new Paint();
        numPaint.setTextSize(textSize);
        numPaint.setFakeBoldText(true);
        numPaint.setColor(Color.BLACK);

        //设置绘制干扰的画笔
        interferencePaint = new Paint();
        interferencePaint.setAntiAlias(true);
        interferencePaint.setStrokeWidth(1);
        interferencePaint.setStyle(Paint.Style.FILL);    //设置paint的style
        Log.e(TAG, "onLayout" + "  width=" + width + "  height=" + height);

    }

    /**
     * 绘制验证码并返回
     *
     * @param strContent
     * @param strRes
     * @return
     */
    private Bitmap generateValidate(String[] strContent, String[] strRes) {
        int isRes = isStrContent(strContent);
        if (isRes == 0) {
            return null;
        }
        Log.e(TAG, "generateValidate textSiz= " + textSize);
        //创建图片和画布
        sourceBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        canvas = new Canvas(sourceBitmap);
        canvas.drawColor(Color.YELLOW);

        //设置每个字
        Matrix mMatrix = new Matrix();
        for (int i = 0; i < strRes.length; i++) {
            numPaint.setColor(getRandColor(255, 255, 255));
            //字体旋转
            numPaint.setTextSkewX((float) Math.random() * 0.5f*(float)Math.pow(-1,new Random().nextInt(100)));
//            mMatrix.setRotate((float) Math.random() * 10);
            canvas.drawText(strRes[i], ((width - textSize*2) / 3 * i +textSize/2), textSize+(height-textSize)/2 , numPaint);
            canvas.setMatrix(mMatrix);
        }

        //绘制直线
        int[] line;
        for (int i = 0; i < 4; i++) {
            line = CheckGetUtil.getLine(height, width);
            interferencePaint.setColor(getRandColor(255, 255, 255));
            canvas.drawLine(line[0], line[1], line[2], line[3], interferencePaint);
        }
        // 绘制小圆点
        int[] point;
        for (int i = 0; i < 100; i++) {
            point = CheckGetUtil.getPoint(height, width);
            canvas.drawCircle(point[0], point[1], 0.5f, interferencePaint);
        }
        canvas.save();
        return sourceBitmap;
    }

    private int isStrContent(String[] strContent) {
        if (strContent == null || strContent.length <= 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 从指定数组中随机取出4个字符(数组)
     *
     * @param strContent
     * @return
     */
    private String[] generageRadom(String[] strContent) {
        String[] str = new String[4];
        // 随机串的个数
        int count = strContent.length;
        // 生成4个随机数
        Random random = new Random();
        int randomResFirst = random.nextInt(count);
        int randomResSecond = random.nextInt(count);
        int randomResThird = random.nextInt(count);
        int randomResFourth = random.nextInt(count);

        str[0] = strContent[randomResFirst].toString().trim();
        str[1] = strContent[randomResSecond].toString().trim();
        str[2] = strContent[randomResThird].toString().trim();
        str[3] = strContent[randomResFourth].toString().trim();
        return str;
    }

    /**
     * 从指定数组中随机取出4个字符(字符串)
     *
     * @param strContent
     * @return
     */
    private String generageRadomStr(String[] strContent) {
        StringBuilder str = new StringBuilder();
        // 随机串的个数
        int count = strContent.length;
        // 生成4个随机数
        Random random = new Random();
        int randomResFirst = random.nextInt(count);
        int randomResSecond = random.nextInt(count);
        int randomResThird = random.nextInt(count);
        int randomResFourth = random.nextInt(count);

        str.append(strContent[randomResFirst].toString().trim());
        str.append(strContent[randomResSecond].toString().trim());
        str.append(strContent[randomResThird].toString().trim());
        str.append(strContent[randomResFourth].toString().trim());
        return str.toString();
    }

    /**
     * 给定范围获得随机颜色，未使用
     *
     * @param rc 0-255
     * @param gc 0-255
     * @param bc 0-255
     * @return colorValue 颜色值，使用setColor(colorValue)
     */
    public int getRandColor(int rc, int gc, int bc) {
        Random random = new Random();
        if (rc > 255)
            rc = 255;
        if (gc > 255)
            gc = 255;
        if (bc > 255)
            bc = 255;
        int r = rc + random.nextInt(rc);
        int g = gc + random.nextInt(gc);
        int b = bc + random.nextInt(bc);
        return Color.rgb(r, g, b);
    }
}
