//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.Spinner;
//import android.widget.Switch;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by omerrom on 03/06/2017.
// */
//public class setting extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_setting);
//
//
//        Switch sw = (Switch)findViewById(R.id.automaticPlay);
//        sw.setChecked(MainActivity.autoPlay);
//        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//
//                if (isChecked) {
//                    MainActivity.autoPlay = true;
//                } else {
//                    MainActivity.autoPlay = false;
//                }
//
//            }
//        });
//        Switch sw1 = (Switch)findViewById(R.id.pressplay);
//        sw1.setChecked(MainActivity.pressPlay);
//        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//
//                if (isChecked) {
//                    MainActivity.pressPlay = true;
//                } else {
//                    MainActivity.pressPlay = false;
//                }
//
//            }
//        });
//        Button about = (Button)findViewById(R.id.about);
//        about.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(setting.this, AboutUs.class);
//                startActivity(intent);
//            }
//        });
//        Button help = (Button)findViewById(R.id.help);
//        help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(setting.this, Help.class);
//                startActivity(intent);
//            }
//        });
//
//        final Spinner numSong = (Spinner)findViewById(R.id.numSongs);
//        final Spinner numQuest = (Spinner)findViewById(R.id.numQuest);
//        List<String> list = new ArrayList<String>();
//        list.add("");
//        list.add("5 tracks");
//        list.add("3 tracks");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                R.layout.spinner_items, list);
//        numSong.setAdapter(dataAdapter);
//
//        List<String> list2 = new ArrayList<String>();
//        list2.add("");
//        list2.add("one question");
//        list2.add("two questions");
//        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
//                R.layout.spinner_items, list2);
//        numQuest.setAdapter(dataAdapter2);
//
//        numQuest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                               @Override
//                                               public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//                                                   String selected_val = numQuest.getSelectedItem().toString();
//                                                   if (selected_val.equals("one question")) {
//                                                       numQuest.setBackgroundColor(Color.parseColor("#FF4D9594"));
//                                                       MainActivity.twoQuest = false;
//                                                   } else if (selected_val.equals("two questions")) {
//                                                       numQuest.setBackgroundColor(Color.parseColor("#FF4D9594"));
//                                                       MainActivity.twoQuest = true;
//                                                   } else {
//                                                       numQuest.setBackgroundColor(Color.parseColor("#00ffffff"));
//                                                   }
//                                               }
//
//
//                                               @Override
//                                               public void onNothingSelected(AdapterView<?> arg0) {
//                                                   // TODO Auto-generated method stub
//
//                                               }
//                                           }
//
//        );
//
//        numSong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                               @Override
//                                               public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
//                                                   String selected_val = numSong.getSelectedItem().toString();
//                                                   if (selected_val.equals("5 tracks")) {
//                                                       numSong.setBackgroundColor(Color.parseColor("#FF4D9594"));
//
//                                                   } else if (selected_val.equals("3 tracks")) {
//                                                       numSong.setBackgroundColor(Color.parseColor("#FF4D9594"));
//
//                                                   } else {
//                                                       numSong.setBackgroundColor(Color.parseColor("#00ffffff"));
//                                                   }
//                                               }
//
//
//                                               @Override
//                                               public void onNothingSelected(AdapterView<?> arg0) {
//                                                   // TODO Auto-generated method stub
//
//                                               }
//                                           }
//
//        );
//
//
//        Button startBut = (Button) findViewById(R.id.startBut);
//        startBut.setOnClickListener(new View.OnClickListener()
//
//                                    {
//                                        @Override
//                                        public void onClick(View v) {
//                                            Intent intent = new Intent(setting.this, opening.class);
//                                            startActivity(intent);
//                                        }
//                                    }
//
//        );
//
//    }
//}
