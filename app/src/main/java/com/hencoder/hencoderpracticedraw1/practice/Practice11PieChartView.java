package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {
    private float[] datas = {1, 5, 5, 40, 60, 80, 35};

    private String[] titles = {"Froyo", "Gingerbread", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};

    private int[] colors = {0xff3C3F41, 0xff9C27B0, 0xff9E9E9E, 0xff009688, 0xff2196F3, 0xffF44336, 0xffFFC107};
    private Paint whitePaint = new Paint(), piePaint = new Paint(), linePaint = new Paint();
    private String head = "饼图";

    private Path path = new Path();
    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setTextSize(27);

        linePaint.setAntiAlias(true);
        linePaint.setColor(0xffbbbbbb);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5);

        piePaint.setAntiAlias(true);
        piePaint.setStyle(Paint.Style.FILL);
        float sum = 0;
        for (float x : datas) {
            sum += x;
        }

        int x = getWidth() / 2 - 100, y = getHeight() / 2 - 40;
        int radius = 280;

        float startDegrees = 0;
        int offset = 20;
        int lineStartX, lineStartY;
        for (int i = 0; i < datas.length; i++) {
            String title = titles[i];
            float val = datas[i];
            float degrees = 360 * val / sum - 2;
            float midDregreesRad = (float) ((startDegrees + (degrees ) / 2) / 360 * 2 * Math.PI);
            piePaint.setColor(colors[i]);
            if (i == datas.length - 2){
                lineStartX = (int) (x - offset + Math.cos(midDregreesRad) * radius);
                lineStartY = (int) (y - offset + Math.sin(midDregreesRad) * radius);
                canvas.drawArc(x - radius - offset, y - radius - offset, x + radius - offset, y + radius - offset, startDegrees, degrees, true, piePaint);
            }else {
                lineStartX = (int) (x + Math.cos(midDregreesRad) * radius);
                lineStartY = (int) (y + Math.sin(midDregreesRad) * radius);
                canvas.drawArc(x - radius, y - radius, x + radius, y + radius, startDegrees, degrees, true, piePaint);
            }

            System.out.println("title : " + title + " x : " + lineStartX + ", y : " + lineStartY + ", midDegreex : " + Math.cos(midDregreesRad) + "  " + midDregreesRad +  " y " + Math.sin(midDregreesRad));

            path.reset();
            // 伸长部分的半径
            int r = 30;
            float offsetX = (float) (Math.cos(midDregreesRad) * r), offsetY = (float)(Math.sin(midDregreesRad) * r);
            path.moveTo(lineStartX, lineStartY);
            path.rLineTo(offsetX, offsetY);
            if (midDregreesRad > 0.5 * Math.PI && midDregreesRad < 1.5 * Math.PI){
                path.lineTo((float) (x - radius - offset - 15), lineStartY + offsetY);
                whitePaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(title, x -offset - radius + offsetX - 10, lineStartY + offsetY, whitePaint);
            }else {
                path.lineTo((float) (x + radius + 80), lineStartY + offsetY);
                whitePaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(title, x + radius + 80 + offsetX, lineStartY + offsetY, whitePaint);
            }
            canvas.drawPath(path, linePaint);
            startDegrees += degrees + 2;
        }

        whitePaint.setTextSize(50);
        canvas.drawText(head, getWidth() / 2 - 120, getHeight() - 50, whitePaint);
    }
}
