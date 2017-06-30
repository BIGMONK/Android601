package youtu.android601.view;

/**
 * Created by djf on 2016/8/29.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import youtu.android601.R;


public class RoundCornerImageView extends ImageView {

    /**
     * 圆角
     */
    private float round;
    private RectF rectF;
    private Path clipPath;
    private TypedArray typedArray;
    private int w;
    private int h;
    private Paint paint;
    /**
     * 圆环宽度
     */
    private float roundWidth;
    /**
     * 圆环颜色
     */
    private int roundColor;
    private boolean circled;

    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundCornerImageView);
        round = typedArray.getDimension(R.styleable.RoundCornerImageView_Round, 5);
        roundWidth = typedArray.getDimension(R.styleable.RoundCornerImageView_circleWidth, 0);
        roundColor = typedArray.getColor(R.styleable.RoundCornerImageView_circleColor, Color.RED);
        circled = typedArray.getBoolean(R.styleable.RoundCornerImageView_circled, false);
        typedArray.recycle();
        paint = new Paint();
    }

    public RoundCornerImageView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setRound(int round) {
        this.round = round;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = this.getWidth();
        h = this.getHeight();
        rectF = new RectF(0, 0, w, h);
        clipPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        clipPath.addRoundRect(new RectF(0, 0, w, h), 100, 100, Path.Direction.CW);
        if (round>w/2){
            round=w/2;
        }
        /**
         * 画最外层的大圆环
         */
        if (circled) {

            double r = w/2+(w/2-round)/2 ; //获取圆环半径
            paint.setColor(roundColor); //设置圆环的颜色
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);  //消除锯齿
            canvas.drawCircle(w / 2, h / 2, (float) (r), paint); //画出圆环
            System.out.println("w="+w+"  r="+r);
        }
        if (rectF != null && clipPath != null) {
            clipPath.reset();
//            clipPath.addRoundRect(rectF, round +roundWidth, round+roundWidth, Path.Direction.CW);
            clipPath.addRoundRect(rectF, round , round, Path.Direction.CW);
            canvas.clipPath(clipPath);
        }

        super.onDraw(canvas);
    }

}
