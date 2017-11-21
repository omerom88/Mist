//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.graphics.Color;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RelativeLayout;
//
//import com.github.gcacace.signaturepad.views.SignaturePad;
//
///**
// * Created by omerrom on 14/06/2017.
// */
//public class pickColor extends Activity {
//
//    private SignaturePad mSignaturePad;
//    private MediaPlayer mediaPlayer = new MediaPlayer();
//    private boolean pickedColor = false;
//    private int pickColorLayoutCouter = 0;
//    private int pickColorSongsCouter = 0;
//    private String choosed = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pickcolor);
//        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_pickColor);
//        mediaPlayer = MediaPlayer.create(pickColor.this, MainActivity.rawPlaylist[pickColorSongsCouter]);
//        mediaPlayer.start();
//        final RelativeLayout mainLay = (RelativeLayout)findViewById(R.id.layoutColorPick);
//        Button frusPen = (Button)findViewById(R.id.frusPenBut);
//        Button chillPen = (Button)findViewById(R.id.chillPenBut);
//        Button unPen = (Button)findViewById(R.id.unPenBut);
//        mainLay.setBackground(getResources().getDrawable(MainActivity.layoutList[pickColorLayoutCouter]));
//
//
//        frusPen.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                choosed = "frus";
//                pickColorLayoutCouter++;
//                mSignaturePad.setPenColor(Color.parseColor("#e41f26"));
//                mainLay.setBackground(getResources().getDrawable(MainActivity.layoutList[pickColorLayoutCouter]));
//                MainActivity.frusPlaylist.add(MainActivity.rawPlaylist[pickColorSongsCouter]);
//                pickedColor = true;
//                return true;
//            }
//        });
//        chillPen.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                choosed = "chill";
//                pickColorLayoutCouter+=3;
//                mSignaturePad.setPenColor(Color.parseColor("#05aa4b"));
//                mainLay.setBackground(getResources().getDrawable(MainActivity.layoutList[pickColorLayoutCouter]));
//                MainActivity.frusPlaylist.add(MainActivity.rawPlaylist[pickColorSongsCouter]);
//                pickedColor = true;
//                return true;
//            }
//        });
//        unPen.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                choosed = "un";
//                pickColorLayoutCouter+=2;
//                mSignaturePad.setPenColor(Color.parseColor("#4b5eaa"));
//                mainLay.setBackground(getResources().getDrawable(MainActivity.layoutList[pickColorLayoutCouter]));
//                MainActivity.frusPlaylist.add(MainActivity.rawPlaylist[pickColorSongsCouter]);
//                pickedColor = true;
//                return true;
//            }
//        });
//        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
//            @Override
//            public void onStartSigning() {
//            }
//
//            @Override
//            public void onSigned() {
//                if (pickedColor) {
//                    pickColorSongsCouter++;
//                    mediaPlayer.stop();
//                    mediaPlayer = MediaPlayer.create(pickColor.this, MainActivity.rawPlaylist[pickColorSongsCouter]);
//                    mediaPlayer.start();
//                    if (choosed.equals("frus")){
//                        pickColorLayoutCouter+=3;
//                    }
//                    else if (choosed.equals("chill")){
//                        pickColorLayoutCouter++;
//                    }
//                    else{
//                        pickColorLayoutCouter+=2;
//                    }
//                    mainLay.setBackground(getResources().getDrawable(MainActivity.layoutList[pickColorLayoutCouter]));
//                } else {
//                    mSignaturePad.clear();
//                }
//            }
//
//            @Override
//            public void onClear() {
//            }
//        });
//    }
//}
