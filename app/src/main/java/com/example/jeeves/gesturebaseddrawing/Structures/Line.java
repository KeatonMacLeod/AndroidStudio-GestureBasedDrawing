package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class Line extends Shape {

    private Point a;
    private Point b;
    private int shapeColour;

    public Line(Point a, Point b, int shapeColour) {
        this.a = a;
        this.b = b;
        this.shapeColour = shapeColour;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(shapeColour);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.close();

        canvas.drawPath(path, paint);
    }
}
