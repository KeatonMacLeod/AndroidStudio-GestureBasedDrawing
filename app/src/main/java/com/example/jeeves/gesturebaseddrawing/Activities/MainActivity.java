package com.example.jeeves.gesturebaseddrawing.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.jeeves.gesturebaseddrawing.R;
import com.example.jeeves.gesturebaseddrawing.Views.CanvasView;

public class MainActivity extends AppCompatActivity {

    private CanvasView canvasView;
    private String currentlySelectedShape;
    private Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActivityVariables();
        setButtonListeners();

    }

    // Initialize all of the view variables
    public void setActivityVariables() {
        canvasView = (CanvasView) findViewById(R.id.canvas);
        resources = getResources();
        currentlySelectedShape = resources.getString(R.string.RECTANGLE);
    }

    // Set up all the button listeners for this activity
    public void setButtonListeners() {

        // Initialize the functionality for the colour-selection button
        ImageButton color = (ImageButton) findViewById(R.id.colourButton);
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ColourSelectionActivity.class));
            }
        });

        // Initialize the functionality for the refresh button
        ImageButton refreshButton = (ImageButton) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCanvas(canvasView);
            }
        });

        // Initialize the functionality for the rectangle button
        ImageButton rectangleButton = (ImageButton) findViewById(R.id.rectangleButton);
        rectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.RECTANGLE));
            }
        });

        // Initialize the functionality for the triangle button
        ImageButton triangleButton = (ImageButton) findViewById(R.id.triangleButton);
        triangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.TRIANGLE));
            }
        });

        // Initialize the functionality for the circle button
        ImageButton circleButton = (ImageButton) findViewById(R.id.circleButton);
        circleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.CIRCLE));
            }
        });

        // Initialize the functionality for the line button
        ImageButton lineButton = (ImageButton) findViewById(R.id.lineButton);
        lineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentlySelectedShape(resources.getString(R.string.LINE));
            }
        });
    }

    public void refreshCanvas(View v){
        canvasView.refreshCanvas();
    }

    public void setCurrentlySelectedShape(String s) {
        this.currentlySelectedShape = s;
    }

    public String getCurrentlySelectedShape() {
        return this.currentlySelectedShape;
    }

}
