package com.example.omerrom.moodj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by omerrom on 13/06/2017.
 */
public class pickColor5 extends Activity {
    public SignaturePad mSignaturePad;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private boolean pickedColor = false;
    public GifDrawable gifDrawableFrus;
    private GifImageView im;
    private String choosen = "";
    public RelativeLayout mainLay;
    public Button frusPen,chillPen,unPen,mute;
    public Handler mHandler;
    public int muteCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickcolor);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_pickColor);
        mainLay = (RelativeLayout)findViewById(R.id.layoutColorPick);
        frusPen = (Button)findViewById(R.id.frusPenBut);
        chillPen = (Button)findViewById(R.id.chillPenBut);
        unPen = (Button)findViewById(R.id.unPenBut);
        im = (GifImageView)findViewById(R.id.gifView);
        mute = (Button)findViewById(R.id.mute);
        mHandler = new Handler();
    }

    @Override
    public void onResume(){
        super.onResume();
        mSignaturePad.clear();
        MainActivity.pickingColors = true;
        mainLay.setBackground(getResources().getDrawable(R.drawable.layout19));

        mediaPlayer = MediaPlayer.create(pickColor5.this, R.raw.feelgood);
        mediaPlayer.start();


        frusPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gifDrawableFrus = new GifDrawable(getResources(), R.drawable.feelred);//// TODO: 15/06/2017 change to RED!!
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
                mainLay.setBackground(getResources().getDrawable(R.drawable.layout20));
                mSignaturePad.setPenColor(Color.parseColor("#e41f26"));
                choosen = "frus";
                pickedColor = true;
            }
        });

        chillPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gifDrawableFrus = new GifDrawable(getResources(), R.drawable.feelgreen);//// TODO: 15/06/2017 change to RED!!
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
                mainLay.setBackground(getResources().getDrawable(R.drawable.layout22));
                mSignaturePad.setPenColor(Color.parseColor("#05aa4b"));
                choosen = "chill";
                pickedColor = true;
            }
        });

        unPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gifDrawableFrus = new GifDrawable(getResources(), R.drawable.feelblue);//// TODO: 15/06/2017 change to RED!!
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
                mainLay.setBackground(getResources().getDrawable(R.drawable.layout21));
                mSignaturePad.setPenColor(Color.parseColor("#4b5eaa"));
                choosen = "frus";
                pickedColor = true;
            }
        });

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                if (pickedColor) {
                    if (choosen.equals("frus")) {
                        MainActivity.frusPlaylist.add(0, R.raw.feelgood);
                    } else if (choosen.equals("frus")) {
                        MainActivity.chillPlaylist.add(0, R.raw.feelgood);
                    } else {
                        MainActivity.unPlaylist.add(0, R.raw.feelgood);
                    }
                    mediaPlayer.stop();
                    mediaPlayer.seekTo(0);
                    Intent intent = new Intent(pickColor5.this, DrawingActivityPre.class);
                    startActivity(intent);
                } else {
                    mSignaturePad.clear();
                }
            }

            @Override
            public void onClear() {
            }
        });

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muteCounter++;
                if (muteCounter % 2 != 0) {
                    mediaPlayer.setVolume(0.0f, 0.0f);
                } else {
                    mediaPlayer.setVolume(1.0f, 1.0f);
                }

            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
