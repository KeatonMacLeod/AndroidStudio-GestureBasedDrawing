package com.example.jeeves.gesturebaseddrawing.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.jeeves.gesturebaseddrawing.R;
import com.example.jeeves.gesturebaseddrawing.Views.CanvasView;

public class ColourSelectionActivity extends Activity {
    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorpopup);

        DisplayMetrics dm = new  DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width =dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout(width,(int)(height*.5));

        //red button
        Button red= (Button) findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.RED);
            }
        });

        //black
        Button black= (Button) findViewById(R.id.black);
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.BLACK);
            }
        });

        //blue
        Button blue= (Button) findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.BLUE);
            }
        });
        //yellow
        Button yellow= (Button) findViewById(R.id.yellow);
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.YELLOW);
            }
        });

        //purple
        Button purple= (Button) findViewById(R.id.purple);
        purple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.MAGENTA);
            }
        });
        //green
        Button green= (Button) findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.GREEN);
            }
        });
        //Orange
        Button Orange= (Button) findViewById(R.id.orange);
        Orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.YELLOW);
            }
        });

        //grey
        Button grey= (Button) findViewById(R.id.grey);
        grey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvasView.mPaint.setColor(android.graphics.Color.CYAN);
            }
        });
    }
}
