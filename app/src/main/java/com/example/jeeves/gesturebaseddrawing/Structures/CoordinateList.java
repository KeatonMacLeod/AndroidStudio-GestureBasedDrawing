package com.example.jeeves.gesturebaseddrawing.Structures;

import java.util.ArrayList;
import java.util.Collections;

public class CoordinateList {

    private ArrayList<Coordinate> coordinates;

    public CoordinateList() {
        coordinates = new ArrayList<>();
    }

    public void add(float x, float y) {
        coordinates.add(new Coordinate(x, y));
    }

    public void add(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public void clear() {
        coordinates.clear();
    }

    public int size() {
        return coordinates.size();
    }

    public Coordinate get(int index) {
        return coordinates.get(index);
    }

    // Finds the coordinate in the list of coordinates with the largest X value and returns it
    public Coordinate getLargestX() {
        Coordinate coordinateLargestX = null;
        for (Coordinate coordinate: coordinates) {
            if (coordinateLargestX == null || coordinate.getX() > coordinateLargestX.getX())
                coordinateLargestX = coordinate;
        }
        return coordinateLargestX;
    }

    // Finds the coordinate in the list of coordinates with the smallest X value and returns it
    public Coordinate getSmallestX() {
        Coordinate coordinateSmallestX = null;
        for (Coordinate coordinate: coordinates) {
            if (coordinateSmallestX == null || coordinate.getX() < coordinateSmallestX.getX())
                coordinateSmallestX = coordinate;
        }
        return coordinateSmallestX;
    }

    // Finds the coordinate in the list of coordinates with the largest Y value and returns it
    public Coordinate getLargestY() {
        Coordinate coordinateLargestY = null;
        for (Coordinate coordinate: coordinates) {
            if (coordinateLargestY == null || coordinate.getY() > coordinateLargestY.getY())
                coordinateLargestY = coordinate;
        }
        return coordinateLargestY;
    }

    // Finds the coordinate in the list of coordinates with the largest X value and returns it
    public Coordinate getSmallestY() {
        Coordinate coordinateSmallestY = null;
        for (Coordinate coordinate: coordinates) {
            if (coordinateSmallestY == null || coordinate.getY() < coordinateSmallestY.getY())
                coordinateSmallestY = coordinate;
        }
        return coordinateSmallestY;
    }

    // Finds the coordinate in the list of coordinates with the middle X value and returns it
    public float getMeanX() {
        float lastX = 0;
        float meanX = 0;
        for (Coordinate coordinate: coordinates) {
            if (Math.abs(coordinate.getX() - lastX) > 10)
                meanX += coordinate.getX();
            lastX = coordinate.getX();
        }
        return meanX/coordinates.size();
    }

    // Finds the coordinate in the list of coordinates with the middle X value and returns it
    public float getMeanY() {
        float lastY = 0;
        float meanY = 0;
        for (Coordinate coordinate: coordinates) {
            if (Math.abs(coordinate.getY() - lastY) > 10)
                meanY += coordinate.getY();
            lastY = coordinate.getX();
        }

        return meanY/coordinates.size();
    }
}
