package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Rectangle extends Shape {

    private float left;
    private float top;
    private float right;
    private float bottom;
    private int shapeColour;
    private Style style;

    public Rectangle(float left, float top, float right, float bottom, int shapeColour, Style style) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.shapeColour = shapeColour;
        this.style = style;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(shapeColour);
        paint.setStyle(style);
        canvas.drawRect(this.left, this.top, this.right, this.bottom, paint);
    }
}
