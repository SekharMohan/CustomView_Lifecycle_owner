package com.example.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
    private Rect r = new Rect();
    int parentWidth;
    int parentHeight;
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTextCanvas(canvas);
    }

    private void drawTextCanvas(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawPaint(paint);

        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        int max = parentWidth/20;
int pos = max/2;

        for(int i=0; i<20; i++) {

          //  canvas.drawText(String.valueOf(i), max*i/2,max*i,max*i, 50, paint);
            drawText(canvas, paint, pos, ""+i);
            pos = pos+max;
        }

        canvas.drawLine(0,60,parentWidth,60,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    public static void drawText(Canvas canvas, Paint paint, int parentWidth, String text) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int x = (parentWidth / 2) - (bounds.width() / 2);
        int y = (canvas.getHeight() / 2) - (bounds.height() / 2);
        canvas.drawText(text, x, 50, paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
