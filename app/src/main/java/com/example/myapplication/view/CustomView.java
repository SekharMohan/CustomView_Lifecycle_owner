package com.example.myapplication.view;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import java.util.ArrayList;

public class CustomView extends View {
    private Rect r = new Rect();
    int parentWidth;
    int parentHeight;
    int viewWidth;
    int barsWidth;
    int nBars;
    int viewOffset;
    private MutableLiveData<Integer> mOffsetValueLiveData;
    ArrayList<ViewOffset> viewOffsets;

    private int mSelectedValue;
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewOffsets = new ArrayList();
        mOffsetValueLiveData = new MutableLiveData();
        mOffsetValueLiveData.setValue(mSelectedValue);

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
        paint.setTextSize(48);
         nBars = (20-0)/5 ;

         barsWidth = parentWidth - 32 - 32;


        for (int i = 0; i<4 ; i++){
           viewOffsets.add(new ViewOffset(barsWidth/nBars*i, barsWidth/nBars*(i+1), i*5));
            int numberPosX = (int)(8 + (barsWidth/nBars) * (i + 0.5));

            canvas.drawText(String.valueOf(i * 5), numberPosX, 150, paint);

        }
        canvas.drawLine(32, 170, parentWidth-32, 170, paint);
       ViewOffset viewOffset = viewOffsets.get(mOffsetValueLiveData.getValue()/5);


      paint.setColor(Color.RED);
        canvas.drawLine((float) viewOffset.getStartX() , 170, (float)viewOffset.getEndX(), 170, paint);


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                return true;

            case MotionEvent.ACTION_UP:
                System.out.println("VCC touch event"+event.getX());
                for(int i = 0; i< 4; i++) {
                    ViewOffset offset = viewOffsets.get(i);
                    if(event.getX() > offset.getStartX() && event.getX() < offset.getEndX()) {
                       if(mOffsetValueLiveData.getValue() != offset.getOffSetValue()) {
                           mOffsetValueLiveData.setValue(offset.getOffSetValue());
                           invalidate();
                       }
                    }
                }
                return true;

            default:
                return false;

        }

    }
}
