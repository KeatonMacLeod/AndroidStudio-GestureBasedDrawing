package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape {

    private float x;
    private float y;
    private float radius;

    public Circle(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawCircle(this.x, this.y, this.radius, paint);
    }
}
