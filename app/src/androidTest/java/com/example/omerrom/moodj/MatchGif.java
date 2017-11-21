//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
///**
// * Created by omerrom on 04/06/2017.
// */
//public class MatchGif extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_drawing_gif);
////        GifImageView gifImageView = (GifImageView)findViewById(R.id.matchGif);
//        Handler mHandler = new Handler();
//        mHandler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //not change the whocall beacuse its match
//                    Intent intent = new Intent(MatchGif.this, playerMood.class);
//                    startActivity(intent);
//            }
//
//        }, 10000L);
//
//    }
//}