package com.example.jeeves.gesturebaseddrawing.Structures;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Line extends Shape {

    private CoordinateList coordinates;
    private int shapeColour;

    public Line(CoordinateList coordinates, int shapeColour) {
        this.coordinates = clone(coordinates);
        this.shapeColour = shapeColour;
    }

    private CoordinateList clone(CoordinateList coordinates) {
        CoordinateList clonedCoordinates = new CoordinateList();
        for (int i=0; i<coordinates.size(); i++) {
            clonedCoordinates.add(coordinates.get(i).clone());
        }

        return clonedCoordinates;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(shapeColour);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        if (coordinates.size() > 1) {
            Coordinate previousCoordinate = null;
            int step = 2;
            for (int i=0; i<coordinates.size(); i+=step) {

                Coordinate coordinate = coordinates.get(i);

                if (i == 0)
                {
                    path.moveTo(coordinate.getX(), coordinate.getY());
                }

                else
                {
                    float middleX = (previousCoordinate.getX() + coordinate.getX()) / 2;
                    float middleY = (previousCoordinate.getY() + coordinate.getY()) / 2;
                    if (i == step)
                    {
                        path.lineTo(middleX, middleY);
                    }
                    else
                    {
                        path.quadTo(previousCoordinate.getX(), previousCoordinate.getY(), middleX, middleY);
                    }
                }

                previousCoordinate = coordinate;
            }
            path.lineTo(previousCoordinate.getX(), previousCoordinate.getY());
            canvas.drawPath(path, paint);
        }
    }
}
