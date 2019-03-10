package com.example.jeeves.gesturebaseddrawing.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.jeeves.gesturebaseddrawing.R;
import com.example.jeeves.gesturebaseddrawing.Views.CanvasView;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    boolean initializing;
    private CanvasView canvasView;
    private Switch fillToggleView;
    private int buttonColour;
    private String currentlySelectedShape;
    private Resources resources;
    private ImageButton colorButton;
    private ImageButton clearButton;
    private ImageButton undoButton;
    private ImageButton rectangleButton;
    private ImageButton triangleButton;
    private ImageButton circleButton;
    private ImageButton lineButton;
    private int selectedColour;
    private int deselectedColour;
    private float DEVICE_HEIGHT;
    private float DEVICE_WIDTH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDeviceDisplayMetrics();
        setActivityVariables();
        setButtonListeners();
    }

    // Initialize all of the view variables
    public void setActivityVariables() {
        canvasView = (CanvasView) findViewById(R.id.canvas);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) canvasView.getLayoutParams();
        params.height = (int)DEVICE_HEIGHT + 700;
        canvasView.setLayoutParams(params);

        selectedColour = 0x80A4A4A4;
        deselectedColour = 0x80E8E8E8;
        initializing = true;

        resources = getResources();
        currentlySelectedShape = resources.getString(R.string.RECTANGLE);
        buttonColour = android.graphics.Color.BLACK;
        fillToggleView = (Switch) findViewById(R.id.fillToggle);
    }

    // Set up all the button listeners for this activity
    public void setButtonListeners() {

        colorButton = (ImageButton) findViewById(R.id.colourButton);
        clearButton = (ImageButton) findViewById(R.id.clearButton);
        undoButton = (ImageButton) findViewById(R.id.undoButton);
        rectangleButton = (ImageButton) findViewById(R.id.rectangleButton);
        triangleButton = (ImageButton) findViewById(R.id.triangleButton);
        circleButton = (ImageButton) findViewById(R.id.circleButton);
        lineButton = (ImageButton) findViewById(R.id.lineButton);

        if (initializing) {
            setSelectedButton(rectangleButton);
        }

        // Initialize the functionality for the colour-selection button
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColourPicker();
            }
        });

        // Initialize the functionality for the undo button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCanvas(canvasView);
            }
        });

        // Initialize the functionality for the undo button
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoCanvas(canvasView);
            }
        });

        // Initialize the functionality for the rectangle button
        rectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.RECTANGLE));
                setSelectedButton(rectangleButton);
            }
        });

        // Initialize the functionality for the triangle button
        triangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.TRIANGLE));
                setSelectedButton(triangleButton);
            }
        });

        // Initialize the functionality for the circle button
        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.CIRCLE));
                setSelectedButton(circleButton);
            }
        });

        // Initialize the functionality for the line button
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.LINE));
                setSelectedButton(lineButton);
            }
        });
    }

    public void openColourPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, buttonColour, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                buttonColour = color;
            }
        });
        colorPicker.show();
    }

    public void setDeviceDisplayMetrics() {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        DEVICE_HEIGHT = displayMetrics.heightPixels / displayMetrics.density;
        DEVICE_WIDTH = displayMetrics.widthPixels / displayMetrics.density;
    }

    public int getButtonColour() {
        return buttonColour;
    }

    public void undoCanvas(View v){
        canvasView.undoCanvas();
    }

    public void clearCanvas(View v){
        canvasView.clearCanvas();
    }

    public void setCurrentlySelectedShape(String s) {
        this.currentlySelectedShape = s;
    }

    public String getCurrentlySelectedShape() {
        return this.currentlySelectedShape;
    }

    public Switch getFillToggleView() {
        return fillToggleView;
    }

    public void setSelectedButton(ImageButton imageButton) {

        if (colorButton == imageButton) {
            colorButton.setBackgroundColor(selectedColour);
        }
        else {
            colorButton.setBackgroundColor(deselectedColour);
        }

        if (clearButton == imageButton) {
            clearButton.setBackgroundColor(selectedColour);
        }
        else {
            clearButton.setBackgroundColor(deselectedColour);
        }

        if (undoButton == imageButton) {
            undoButton.setBackgroundColor(selectedColour);
        }
        else {
            undoButton.setBackgroundColor(deselectedColour);
        }

        if (rectangleButton == imageButton) {
            rectangleButton.setBackgroundColor(selectedColour);
        }
        else {
            rectangleButton.setBackgroundColor(deselectedColour);
        }

        if (triangleButton == imageButton) {
            triangleButton.setBackgroundColor(selectedColour);
        }
        else {
            triangleButton.setBackgroundColor(deselectedColour);
        }

        if (circleButton == imageButton) {
            circleButton.setBackgroundColor(selectedColour);
        }
        else {
            circleButton.setBackgroundColor(deselectedColour);
        }

        if (lineButton == imageButton) {
            lineButton.setBackgroundColor(selectedColour);
        }
        else {
            lineButton.setBackgroundColor(deselectedColour);
        }
    }

}
