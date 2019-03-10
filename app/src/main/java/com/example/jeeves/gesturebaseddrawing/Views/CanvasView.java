package com.example.jeeves.gesturebaseddrawing.Views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

import com.example.jeeves.gesturebaseddrawing.Activities.MainActivity;
import com.example.jeeves.gesturebaseddrawing.R;
import com.example.jeeves.gesturebaseddrawing.Structures.Circle;
import com.example.jeeves.gesturebaseddrawing.Structures.Coordinate;
import com.example.jeeves.gesturebaseddrawing.Structures.CoordinateList;
import com.example.jeeves.gesturebaseddrawing.Structures.Line;
import com.example.jeeves.gesturebaseddrawing.Structures.Rectangle;
import com.example.jeeves.gesturebaseddrawing.Structures.ShapeList;
import com.example.jeeves.gesturebaseddrawing.Structures.Triangle;

public class CanvasView extends View {

    private ShapeList shapeList;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private CoordinateList coordinates;
    private Switch fillToggle;
    private boolean userDrawing = false;
    private Path mPath;
    private Resources resources;
    public static Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    Context context;


    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.resources = getResources();

        shapeList = new ShapeList();
        coordinates = new CoordinateList();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(12f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);

        mBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    protected void onDraw(Canvas canvas){
        shapeList.draw(canvas, mPaint);
        mPaint.setStyle(Style.STROKE);
        mPaint.setColor(((MainActivity)context).getButtonColour());
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        fillToggle = ((MainActivity)context).getFillToggleView();

        if (!userDrawing && coordinates.size() > 0) {

            String currentlySelectedShape = ((MainActivity) context).getCurrentlySelectedShape();

            if (fillToggle.isChecked()) {
                mPaint.setStyle(Style.FILL);
            }
            else {
                mPaint.setStyle(Style.STROKE);
            }

            if (currentlySelectedShape.equals(resources.getString(R.string.RECTANGLE))) {
                saveRectangle();
            }
            else if (currentlySelectedShape.equals(resources.getString(R.string.TRIANGLE))) {
                saveTriangle();
            }
            else if (currentlySelectedShape.equals(resources.getString(R.string.CIRCLE))) {
                saveCircle();
            }
            else if (currentlySelectedShape.equals(resources.getString(R.string.LINE))) {
                saveLine();
            }

            clearCanvas();
            coordinates.clear();
        }
    }

    private void startTouch(float x, float y){
        mPath.moveTo(x,y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y){
        float dx = Math.abs(x-mX);
        float dy = Math.abs(y-mY);

        if(dx >= TOLERANCE || dy >= TOLERANCE){
            mPath.quadTo(mX,mY,(x+mX)/2, (y+mY)/2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas(){
        mPath.reset();
        invalidate();
    }

    public void refreshCanvas() {
        shapeList.clear();
        mPath.reset();
        invalidate();
    }

    private void upTouch() {
        mPath.lineTo(mX, mY);
    }

    public boolean onTouchEvent (MotionEvent event){

        float x = event.getX();
        float y = event.getY();
        coordinates.add(x, y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                userDrawing = true;
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                userDrawing = false;
                upTouch();
                invalidate();
                break;
        }

        return true;
    }

    /***
     * Custom assignment 2 functions which allow approximation drawing for the closest
     * representative shape given the user's selection from the palette
     */

    // Processes the coordinates and draws the closest approximation of a Rectangle
    private void saveRectangle() {
        Coordinate largestX = coordinates.getLargestX();
        Coordinate largestY = coordinates.getLargestY();
        Coordinate smallestX = coordinates.getSmallestX();
        Coordinate smallestY = coordinates.getSmallestY();

        Rectangle rectangle = new Rectangle(smallestX.getX(), smallestY.getY(), largestX.getX(), largestY.getY(), ((MainActivity)context).getButtonColour(), mPaint.getStyle());
        shapeList.add(rectangle);
    }

    // Processes the coordinates and draws the closest approximation of a Triangle
    private void saveTriangle() {

        Coordinate largestX = coordinates.getLargestX();
        Coordinate largestY = coordinates.getLargestY();
        Coordinate smallestX = coordinates.getSmallestX();
        Coordinate smallestY = coordinates.getSmallestY();

        Point a = new Point((int)smallestX.getX(), (int)largestY.getY());
        Point b = new Point((int)largestX.getX(), (int)largestY.getY());
        Point c = new Point(((int)smallestX.getX() + (int)largestX.getX()) / 2, (int)smallestY.getY());

        Triangle triangle = new Triangle(a, b, c, ((MainActivity)context).getButtonColour(), mPaint.getStyle());
        shapeList.add(triangle);
    }

    // Processes the coordinates and draws the closest approximation of a Circle
    private void saveCircle() {
        Coordinate largestX = coordinates.getLargestX();
        Coordinate largestY = coordinates.getLargestY();
        Coordinate smallestX = coordinates.getSmallestX();
        Coordinate smallestY = coordinates.getSmallestY();

        float x = (largestX.getX() + smallestX.getX()) / 2;
        float y = (largestY.getY() + smallestY.getY()) / 2;
        float radius = (x - smallestX.getX() + y - smallestY.getY()) / 2;

        Circle circle = new Circle(x, y, radius, ((MainActivity)context).getButtonColour(), mPaint.getStyle());
        shapeList.add(circle);
    }

    // Processes the coordinates and draws the closest approximation of a Line
    private void saveLine() {
        Coordinate first = coordinates.get(0);
        Coordinate last = coordinates.get(coordinates.size()-1);

        Point a = new Point((int)first.getX(), (int)first.getY());
        Point b = new Point((int)last.getX(), (int)last.getY());

        Line line = new Line(a, b, ((MainActivity)context).getButtonColour());
        shapeList.add(line);
    }
}
