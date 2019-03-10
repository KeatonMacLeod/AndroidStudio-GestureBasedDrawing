package com.example.jeeves.gesturebaseddrawing.Structures;

public class Coordinate {

    private float x;
    private float y;

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate clone() {
        return new Coordinate(this.getX(), this.getY());
    }
}
