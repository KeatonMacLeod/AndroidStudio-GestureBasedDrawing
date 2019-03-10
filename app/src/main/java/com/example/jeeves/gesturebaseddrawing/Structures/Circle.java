package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Circle extends Shape {

    private float x;
    private float y;
    private float radius;
    private int shapeColour;
    private Style style;

    public Circle(float x, float y, float radius, int shapeColour, Style style) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.shapeColour = shapeColour;
        this.style = style;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(shapeColour);
        paint.setStyle(style);
        canvas.drawCircle(this.x, this.y, this.radius, paint);
    }
}
