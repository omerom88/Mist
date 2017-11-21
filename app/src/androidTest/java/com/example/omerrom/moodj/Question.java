//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Spinner;
//
///**
// * Created by omerrom on 27/05/2017.
// */
//
//
//public class Question extends Activity {
//
//    public static int retVal = 0;
//    Spinner dropdown;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_quest);
//
//        final Intent intent = new Intent(Question.this,Question2.class);
//
//        Button white_but = (Button)findViewById(R.id.button_white);
//        Button blue_but = (Button)findViewById(R.id.button_blue);
//        Button green_but = (Button)findViewById(R.id.button_green);
//        Button red_but = (Button)findViewById(R.id.button_red);
//        Button set_but = (Button)findViewById(R.id.settingBut);
//
//
//        white_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 40;
//                if (MainActivity.twoQuest){
//                    startActivity(intent);
//                }
//                else{
//                    Intent intent = new Intent(Question.this,exited.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        blue_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 20;
//                if (MainActivity.twoQuest) {
//                    startActivity(intent);
//                }
//                else{
//                    Intent intent = new Intent(Question.this,unmotivated.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        green_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 30;
//                if (MainActivity.twoQuest) {
//                    startActivity(intent);
//                }
//                else{
//                    Intent intent = new Intent(Question.this,chilled.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//
//        red_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 10;
//                if (MainActivity.twoQuest) {
//                    startActivity(intent);
//                }
//                else{
//                    Intent intent = new Intent(Question.this,frustrated.class);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        set_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Question.this,setting.class);
//                startActivity(intent1);
//            }
//        });
//    }
//
//}
