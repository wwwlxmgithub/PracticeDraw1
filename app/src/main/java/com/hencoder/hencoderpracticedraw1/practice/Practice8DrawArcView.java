package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    private Path path = new Path();
    private Paint paint = new Paint();

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(21)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形
        int x = getWidth() / 2, y = getHeight() / 2;
        int w = 200, h = 150;
        RectF rectF = new RectF(x - w, y - h, x + w, y + h);
        path.addArc(rectF, 200, 300);
        path.close();

        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        canvas.drawArc(rectF,  15, 150, false, paint);
        canvas.drawArc(rectF, 250, 100, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, 180, 60, false, paint);

    }
}
