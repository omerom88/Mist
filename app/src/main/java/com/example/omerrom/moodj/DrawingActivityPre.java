package com.example.omerrom.moodj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * Created by omerrom on 13/06/2017.
 */
public class DrawingActivityPre extends Activity {

    public SignaturePad ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawingpre);
        ly = (SignaturePad)findViewById(R.id.drawingLayoutPre);
    }

    @Override
    public void onResume(){
        super.onResume();
        ly.clear();

        ly.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                Intent intent = new Intent(DrawingActivityPre.this, DrawingActivity.class);
                startActivity(intent);
            }

            @Override
            public void onClear() {

            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
