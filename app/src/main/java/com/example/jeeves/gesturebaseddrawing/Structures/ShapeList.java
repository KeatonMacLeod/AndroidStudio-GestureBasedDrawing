package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class ShapeList {

    private ArrayList<Shape> shapeList;

    public ShapeList() {
        shapeList = new ArrayList<>();
    }

    public void add(Shape shape) {
        shapeList.add(shape);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Shape shape: shapeList) {
            shape.draw(canvas, paint);
        }
    }

    public void clear() {
        shapeList.clear();
    }
}
