package com.example.omerrom.moodj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by omerrom on 03/06/2017.
 */
public class chilled extends Activity {

    public GifDrawable gifDrawableFrus;
    private GifImageView im;
    public SignaturePad mSignaturePad;
    public Handler mHandler;
    public RelativeLayout mainLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frus_layout);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_frus);
        im = (GifImageView)findViewById(R.id.gifViewMood);
        mHandler = new Handler();
        mainLay = (RelativeLayout)findViewById(R.id.mainLayFrus);
    }

    @Override
    public void onResume(){
        super.onResume();
        mSignaturePad.clear();
        mainLay.setBackground(getResources().getDrawable(R.drawable.layout27));


        mSignaturePad.setPenColor(Color.parseColor("#05aa4b"));
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                MainActivity.moodChoosen = "chill";
                Intent intent = new Intent(chilled.this,playerMood.class);
                startActivity(intent);
            }

            @Override
            public void onClear() {

            }
        });


        try {
            gifDrawableFrus = new GifDrawable(getResources(), R.drawable.chilledgif);
            gifDrawableFrus.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    gifDrawableFrus.stop();
                    gifDrawableFrus.seekToFrame(0);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gifDrawableFrus.start();
                        }

                    }, 3000L);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setBackground(gifDrawableFrus);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
