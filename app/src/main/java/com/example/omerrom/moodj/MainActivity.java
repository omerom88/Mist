package com.example.omerrom.moodj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static String moodChoosen = "";
    public static ArrayList<Integer> frusPlaylist = new ArrayList<>();
    public static ArrayList<Integer> chillPlaylist = new ArrayList<>();
    public static ArrayList<Integer> unPlaylist = new ArrayList<>();
    public static int[] rawPlaylist = new int[5];
    public static int[] layoutList = new int[20];
    public static boolean pickingColors = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frusPlaylist.add(0,R.raw.takemeout);
        frusPlaylist.add(0,R.raw.power);
        frusPlaylist.add(0,R.raw.money);
        frusPlaylist.add(0,R.raw.gougeaway);
        chillPlaylist.add(0,R.raw.thousandeyes);
        chillPlaylist.add(0,R.raw.arabesqueno1);
        chillPlaylist.add(0,R.raw.tango);
        chillPlaylist.add(0,R.raw.kissme);
        unPlaylist.add(0,R.raw.ifitbeyourwill);
        unPlaylist.add(0,R.raw.backtoblack);
        unPlaylist.add(0,R.raw.stan);
        unPlaylist.add(0,R.raw.spaceoddity);
        rawPlaylist[0] = R.raw.nosurprises;
        rawPlaylist[1] = R.raw.runtheworld;
        rawPlaylist[2] = R.raw.dontworrybehappy;
        rawPlaylist[3] = R.raw.beckwow;
        rawPlaylist[4] = R.raw.feelgood;
        layoutList[0] = R.drawable.layout3;
        layoutList[1] = R.drawable.layout4;
        layoutList[2] = R.drawable.layout5;
        layoutList[3] = R.drawable.layout6;
        layoutList[4] = R.drawable.layout7;
        layoutList[5] = R.drawable.layout8;
        layoutList[6] = R.drawable.layout9;
        layoutList[7] = R.drawable.layout10;
        layoutList[8] = R.drawable.layout11;
        layoutList[9] = R.drawable.layout12;
        layoutList[10] = R.drawable.layout13;
        layoutList[11] = R.drawable.layout14;
        layoutList[12] = R.drawable.layout15;
        layoutList[13] = R.drawable.layout16;
        layoutList[14] = R.drawable.layout17;
        layoutList[15] = R.drawable.layout18;
        layoutList[16] = R.drawable.layout19;
        layoutList[17] = R.drawable.layout20;
        layoutList[18] = R.drawable.layout21;
        layoutList[19] = R.drawable.layout22;

        Intent intent = new Intent(MainActivity.this, openingGif.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        frusPlaylist.clear();
        chillPlaylist.clear();
        unPlaylist.clear();
    }
}
