package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private float[] datas = {1, 5, 5, 40, 60, 80, 35};

    private String[] titles = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};

    private int count = 7;
    private float space = 20;
    private String head = "直方图";

    private float maxValue = 100;

    private Paint whitePaint = new Paint(), greenPaint = new Paint();
    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setTextSize(50);
        whitePaint.setStrokeWidth(2);

        greenPaint.setAntiAlias(true);
        greenPaint.setColor(0xff72B916);
        greenPaint.setStyle(Paint.Style.FILL);


//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        int width = getWidth(), height = getHeight();

        int centerX = width / 2, centerY = height / 2;

        int axisXLen = (int) (width * 0.8), axisYLen = (int) (height * 0.6);

        int itemWidth = (int) ((axisXLen - (count + 1) * space) / count);

        int startX = centerX - axisXLen / 2, startY = centerY + axisYLen /4;

        canvas.drawLine(startX, startY, startX + axisXLen, startY, whitePaint);

        canvas.drawLine(startX, startY, startX, startY - axisYLen, whitePaint);

        canvas.drawText(head, centerX - 50, startY + 200, whitePaint);

        int stX = (int) (startX + space);
        whitePaint.setTextSize(30);
        for (int i = 0;i < titles.length; i++){
            String title = titles[i];
            float val = datas[i];

            int itemHeight = (int) (val / maxValue * axisYLen);
            canvas.drawRect(stX, startY - itemHeight, stX + itemWidth, startY, greenPaint);
            canvas.drawText(title, stX + 10, startY + 40, whitePaint);
            stX += space + itemWidth;

        }
    }
}
