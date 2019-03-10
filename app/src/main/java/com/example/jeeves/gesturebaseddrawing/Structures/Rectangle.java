package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle extends Shape {

    private float left;
    private float top;
    private float right;
    private float bottom;

    public Rectangle(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(this.left, this.top, this.right, this.bottom, paint);
    }
}
