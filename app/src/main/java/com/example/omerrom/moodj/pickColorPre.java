package com.example.omerrom.moodj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * Created by omerrom on 13/06/2017.
 */
public class pickColorPre extends Activity {
    public SignaturePad mSignaturePad, mSignaturePadSwipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickcolorpre);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_pickColorPre);
        mSignaturePadSwipe = (SignaturePad) findViewById(R.id.signature_pad_pickColorPreSwipe);
    }
    @Override
    public void onResume(){
        super.onResume();
        mSignaturePad.clear();
        mSignaturePadSwipe.clear();

        mSignaturePadSwipe.setPenColor(Color.WHITE);
        mSignaturePad.setPenColor(Color.BLACK);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                Intent intent = new Intent(pickColorPre.this, pickColor1.class);
                startActivity(intent);
            }

            @Override
            public void onClear() {

            }
        });

        mSignaturePadSwipe.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                Intent intent = new Intent(pickColorPre.this, DrawingActivityPre.class);
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
