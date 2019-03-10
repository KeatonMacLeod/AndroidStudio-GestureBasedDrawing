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

    public void remove (int index) {
        if (index >= 0 && index < shapeList.size())
            shapeList.remove(index);
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Shape shape: shapeList) {
            shape.draw(canvas, paint);
        }
    }

    public int size() {
        return shapeList.size();
    }

    public void clear() {
        shapeList.clear();
    }
}
