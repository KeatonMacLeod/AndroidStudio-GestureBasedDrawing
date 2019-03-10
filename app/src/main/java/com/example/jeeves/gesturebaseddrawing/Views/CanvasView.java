package com.example.jeeves.gesturebaseddrawing.Views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.jeeves.gesturebaseddrawing.Activities.MainActivity;
import com.example.jeeves.gesturebaseddrawing.R;
import com.example.jeeves.gesturebaseddrawing.Structures.Circle;
import com.example.jeeves.gesturebaseddrawing.Structures.Coordinate;
import com.example.jeeves.gesturebaseddrawing.Structures.CoordinateList;
import com.example.jeeves.gesturebaseddrawing.Structures.Rectangle;
import com.example.jeeves.gesturebaseddrawing.Structures.ShapeList;

public class CanvasView extends View {

    private ShapeList shapeList;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private CoordinateList coordinates;
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
        mPaint.setStyle(Paint.Style.STROKE);
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
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
        shapeList.draw(canvas, mPaint);

        if (!userDrawing && coordinates.size() > 0) {

            String currentlySelectedShape = ((MainActivity) context).getCurrentlySelectedShape();

            if (currentlySelectedShape.equals(resources.getString(R.string.RECTANGLE))) {
                saveRectangle(canvas);
            }
            else if (currentlySelectedShape.equals(resources.getString(R.string.TRIANGLE))) {
                //DO THE THING
            }
            else if (currentlySelectedShape.equals(resources.getString(R.string.CIRCLE))) {
                saveCircle(canvas);
            }
            else if (currentlySelectedShape.equals(resources.getString(R.string.LINE))) {
                //DO THE THING
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
    private void saveRectangle(Canvas canvas) {
        Coordinate largestX = coordinates.getLargestX();
        Coordinate largestY = coordinates.getLargestY();
        Coordinate smallestX = coordinates.getSmallestX();
        Coordinate smallestY = coordinates.getSmallestY();

        Rectangle rectangle = new Rectangle(smallestX.getX(), smallestY.getY(), largestX.getX(), largestY.getY());
        shapeList.add(rectangle);
    }

    private void saveCircle(Canvas canvas) {
        Coordinate largestX = coordinates.getLargestX();
        Coordinate largestY = coordinates.getLargestY();
        Coordinate smallestX = coordinates.getSmallestX();
        Coordinate smallestY = coordinates.getSmallestY();

        float x = coordinates.getMeanX();
        float y = coordinates.getMeanY();
        float radius = (largestX.getX() - smallestX.getX() + largestY.getY() - smallestY.getY()) / 2;

        Circle circle = new Circle(x, y, radius);
        shapeList.add(circle);
    }


    // Processes the coordinates and draws the closest approximation of a Rectangle
    private void drawTriangle() {

    }

    // Processes the coordinates and draws the closest approximation of a Rectangle
    private void drawCircle() {

    }

    // Processes the coordinates and draws the closest approximation of a Rectangle
    private void drawLine() {

    }
}
