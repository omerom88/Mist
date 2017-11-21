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
//public class Question2 extends Activity {
//
//    public static int retVal = 0;
//    public static String whoCall = "";
//    Spinner dropdown;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_quest2);
//
//        Button first_but = (Button)findViewById(R.id.button_03);
//        Button second_but = (Button)findViewById(R.id.button_47);
//        Button third_but = (Button)findViewById(R.id.button_8);
//        Button setBut = (Button)findViewById(R.id.settingBut);
//
//        setBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Question2.this,setting.class);
//                startActivity(intent1);
//            }
//        });
//
//        first_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 10;
//                calcMood();
//            }
//
//        });
//
//        second_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 20;
//                calcMood();
//            }
//        });
//
//        third_but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retVal = 30;
//                calcMood();
//            }
//        });
//
//
//    }
//
//    public void calcMood(){
//        if (Question.retVal + retVal == 20){
//            Intent intent = new Intent(Question2.this,frustrated.class);
//            startActivity(intent);
//        }
//        else if (Question.retVal + retVal <= 40){
//            Intent intent = new Intent(Question2.this,unmotivated.class);
//            startActivity(intent);
//
//        }
//        else if (Question.retVal + retVal <= 60){
//            Intent intent = new Intent(Question2.this,chilled.class);
//            startActivity(intent);
//        }
//        //means its 70
//        else {
//            Intent intent = new Intent(Question2.this,exited.class);
//            startActivity(intent);
//        }
//    }
//
//}
