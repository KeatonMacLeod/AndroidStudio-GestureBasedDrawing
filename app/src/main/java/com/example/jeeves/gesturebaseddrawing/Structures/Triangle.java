package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Point;


public class Triangle extends Shape {

    private Point a;
    private Point b;
    private Point c;
    private int shapeColour;
    private Style style;

    public Triangle(Point a, Point b, Point c, int shapeColour, Style style) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.shapeColour = shapeColour;
        this.style = style;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(shapeColour);
        paint.setStyle(style);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        if (style == Style.STROKE)
        {
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.moveTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.moveTo(c.x, c.y);
            path.lineTo(a.x, a.y);
        }

        else
        {
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
        }

        path.close();
        canvas.drawPath(path, paint);
    }
}
