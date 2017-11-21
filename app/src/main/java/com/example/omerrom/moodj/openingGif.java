package com.example.omerrom.moodj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by omerrom on 10/06/2017.
 */
public class openingGif extends Activity {
    public GifDrawable gifDrawableFrus;
    private GifImageView im;
    public SignaturePad ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_gif);
        im = (GifImageView) findViewById(R.id.matchGif);
        ly = (SignaturePad)findViewById(R.id.gifLayoutPad);
    }

    @Override
    public void onResume(){
        super.onResume();
        ly.clear();

        final Intent intent = new Intent(openingGif.this, pickColorPre.class);
        try {
            gifDrawableFrus = new GifDrawable(getResources(), R.drawable.giff_start);
            gifDrawableFrus.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        im.setBackground(gifDrawableFrus);

        ly.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
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
