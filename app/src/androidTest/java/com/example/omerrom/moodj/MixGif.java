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
//public class MixGif extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gif_mix);
//        Handler mHandler = new Handler();
//        mHandler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                if (DrawingActivity.whoCall.equals("frus")) {
//                    DrawingActivity.whoCall = "chill";
//                }
//                else if (DrawingActivity.whoCall.equals("un")) {
//                    DrawingActivity.whoCall = "chill";
//                }
//                else {
//                    //means it chill
//                    DrawingActivity.whoCall = "un";
//                }
//                Intent intent = new Intent(MixGif.this, playerMood.class);
//                startActivity(intent);
//            }
//
//        }, 3000L);
//
//    }
//}
