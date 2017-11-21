//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by omerrom on 03/06/2017.
// */
//public class exited extends Activity {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_exited);
//
//        final Button mixBut = (Button)findViewById(R.id.excited_mix);
//        Button matchBut = (Button)findViewById(R.id.excited_match);
//        Button setBut = (Button) findViewById(R.id.settingBut);
//
//        setBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(exited.this,setting.class);
//                startActivityForResult(intent1, 1);
//            }
//        });
//
//
//        mixBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(exited.this,MixGif.class);
//                Question2.whoCall = "frus";
//                startActivity(intent);
//            }
//        });
//
//        matchBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(exited.this,MatchGif.class);
//                Question2.whoCall = "ex";
//                startActivity(intent);
//            }
//        });
//    }
//
//}
