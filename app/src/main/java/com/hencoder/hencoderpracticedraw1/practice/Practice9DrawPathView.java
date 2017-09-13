package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    private Path path = new Path();
    private Paint paint = new Paint();
    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 练习内容：使用 canvas.drawPath() 方法画心形

        int x = getWidth() / 2, y = getHeight() / 2;
        int radius = 70;
        path.addArc(x - radius * 2, y - radius, x , y + radius, -225, 225);
        path.arcTo(x, y - radius, x + radius * 2, y + radius, 180, 225, false);
        path.lineTo(x, y + radius * 2.5f);
        path.close();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path, paint);
    }
}
