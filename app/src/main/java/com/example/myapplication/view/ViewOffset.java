package com.example.myapplication.view;

public class ViewOffset {
    double startX;
    double endX;
    int offSetValue;
    public ViewOffset(double startX, double endX, int offSetValue) {
        this.startX = startX;
        this.endX = endX;
        this.offSetValue = offSetValue;
    }


    public double getStartX() {
        return startX;
    }

    public double getEndX() {
        return endX;
    }

    public int getOffSetValue() {
        return offSetValue;
    }
}
