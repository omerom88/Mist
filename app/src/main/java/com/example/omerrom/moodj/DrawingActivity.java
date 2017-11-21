package com.example.omerrom.moodj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * Created by omerrom on 08/06/2017.
 */
public class DrawingActivity extends Activity {

    public SignaturePad mSignaturePad;
    public SignaturePad mDoneButton;
    public boolean haveDraw;
    public int fingerOff, numberOfTouches;
    public float allPressures, avgPressures, touchVel, avgVel, maxHeight, workHeight, maxWidth, workWidth, minWidth, minHeight;
    public static float ret;
    public long startTime, totalTime;
    private VelocityTracker mVelocityTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mDoneButton = (SignaturePad) findViewById(R.id.signature_pad_done);
    }

    @Override
    public void onResume(){
        super.onResume();
        mSignaturePad.clear();
        mDoneButton.clear();

        mSignaturePad.setPenColor(Color.WHITE);
        mDoneButton.setPenColor(Color.BLACK);

        haveDraw = false;
        clearVars();

        mSignaturePad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                numberOfTouches++;
                allPressures += event.getPressure() * 1000;
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                }
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000, 10000);
                touchVel += Math.abs(VelocityTrackerCompat.getXVelocity(mVelocityTracker, 0));
                if (maxWidth < event.getX()) {
                    maxWidth = event.getX();
                }
                if (minWidth > event.getX()) {
                    minWidth = event.getX();
                }
                if (maxHeight < event.getY()) {
                    maxHeight = event.getY();
                }
                if (minHeight > event.getY()) {
                    minHeight = event.getY();
                }
                return false;
            }
        });

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                fingerOff++;
                if (fingerOff == 1) {
                    startTime = System.currentTimeMillis();
                }
            }

            @Override
            public void onSigned() {
                haveDraw = true;
            }

            @Override
            public void onClear() {
            }
        });


        mDoneButton.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                if (haveDraw) {
                    switch (drawAnalyzer()) {
                        case 0: {
                            Intent intent = new Intent(DrawingActivity.this, frustrated.class);
                            startActivity(intent);
                            break;
                        }
                        case 1: {
                            Intent intent = new Intent(DrawingActivity.this, chilled.class);
                            startActivity(intent);
                            break;
                        }
                        case 2: {
                            Intent intent = new Intent(DrawingActivity.this, unmotivated.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }
                else{
                    mDoneButton.clear();
                }

            }

            @Override
            public void onClear() {

            }
        });

    }

    public void clearVars(){
        fingerOff = 0;
        numberOfTouches = 0;
        allPressures = 0;
        avgPressures = 0;
        touchVel = 0;
        avgVel = 0;
        maxHeight = 0;
        workHeight = 0;
        maxWidth = 0;
        workWidth = 0;
        minWidth = 700;
        minHeight = 1000;
        startTime = System.currentTimeMillis();
    }


    public int drawAnalyzer(){
        // 0 = frus, 1 = chill, 2 = unm
        avgPressures = allPressures/numberOfTouches;
        totalTime = (System.currentTimeMillis() - startTime)/1000;
        avgVel = touchVel/numberOfTouches;
        workHeight = maxHeight - minHeight+30;
        workWidth = maxWidth - minWidth;

        float scaledAvgPress = 100*(avgPressures - 340)/380;
        int scaledNumOfTouch = 100*(numberOfTouches)/200;
        double scaledAvgVel = (100*(Math.pow(avgVel,0.5) - 4)/40) + 20;
        double scaledWorkHeight = 100*(workHeight - 30)/1070;
        double scaledWorkWidth = 100*(workWidth)/700;
        double scaledAvgWork = (scaledWorkHeight + scaledWorkWidth)/2;

        ret = (2*scaledAvgPress  + (100 - (float)scaledNumOfTouch) +
                2*(float)scaledAvgVel + (float)scaledAvgWork)/6;
        if (totalTime < 12.0f)
        {
            if (ret > 70){
                return 0;
            }
            if (ret < 55){
                return 2;
            }
            else
                return 1;
        }
        else{
            if (ret > 80){
                return 0;
            }
            if (ret < 20){
                return 2;
            }
            else
                return 1;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
