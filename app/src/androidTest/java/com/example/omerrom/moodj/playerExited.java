//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by omerrom on 03/06/2017.
// */
//public class playerExited extends Activity {
//    private Button stopButton, pauseButton, playButton, doneBut;
//    private MediaPlayer mediaPlayer = new MediaPlayer();
//    private double startTime = 0;
//    private double finalTime = 0;
//    private Handler myHandler = new Handler();
//    private TextView nameTxt,artistTxt,startSec,endSec,track1,track2,track3,track4,track5,track6;
//    private boolean mediaPlayerInit = false;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_record_player);
//        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Avenir_Next_Condensed.ttc");
//        final View themeLayout = findViewById(R.id.themeLayout);
//
//        stopButton = (Button) findViewById(R.id.stopButton);
//        pauseButton = (Button) findViewById(R.id.pauseButton);
//        playButton = (Button)findViewById(R.id.playButton);
//        Button setBut = (Button) findViewById(R.id.settingBut);
//
//        setBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                Intent intent1 = new Intent(playerExited.this,setting.class);
//                startActivityForResult(intent1, 1);
//            }
//        });
//
//        track1=(TextView)findViewById(R.id.track1);
//        track2=(TextView)findViewById(R.id.track2);
//        track3=(TextView)findViewById(R.id.track3);
//        track4=(TextView)findViewById(R.id.track4);
//        track5=(TextView)findViewById(R.id.track5);
//        track6=(TextView)findViewById(R.id.track6);
//
//        startSec=(TextView)findViewById(R.id.runSec);
//        endSec=(TextView)findViewById(R.id.endSec);
//
//
//        startSec.setTypeface(type);
//        endSec.setTypeface(type);
//
//
//        mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.goodvibrations);
//        endSec.setText("3:37");
//        themeLayout.setBackground(getResources().getDrawable(R.drawable.e1));
//
//        track1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.goodvibrations);
//                endSec.setText("3:37");
//                themeLayout.setBackground(getResources().getDrawable(R.drawable.e1));
//                if (MainActivity.pressPlay) {
//                    mediaPlayer.start();
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
//                }
//            }
//        });
//        track2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.bicyclerace);
//                endSec.setText("3:02");
//                themeLayout.setBackground(getResources().getDrawable(R.drawable.e2));
//                if (MainActivity.pressPlay) {
//                    mediaPlayer.start();
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
//                }
//            }
//        });
//        track3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.edwardsharpehome);
//                endSec.setText("5:01");
//                themeLayout.setBackground(getResources().getDrawable(R.drawable.e3));
//                if (MainActivity.pressPlay) {
//                    mediaPlayer.start();
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
//                }
//            }
//        });
//        track4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.feelgood);
//                endSec.setText("4:14");
//                themeLayout.setBackground(getResources().getDrawable(R.drawable.e4));
//                if (MainActivity.pressPlay) {
//                    mediaPlayer.start();
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
//                }
//            }
//        });
//        track5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.likeaprayer);
//                endSec.setText("5:42");
//                themeLayout.setBackground(getResources().getDrawable(R.drawable.e5));
//                if (MainActivity.pressPlay) {
//                    mediaPlayer.start();
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
//                }
//            }
//        });
//        track6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                mediaPlayer.release();
//                mediaPlayer = MediaPlayer.create(playerExited.this, R.raw.stopthefeeling);
//                endSec.setText("4:19");
//                themeLayout.setBackground(getResources().getDrawable(R.drawable.e6));
//                if (MainActivity.pressPlay) {
//                    mediaPlayer.start();
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
//                }
//            }
//        });
//
//
//
//        mediaPlayerInit = true;
//        stopButton.setEnabled(false);
//        pauseButton.setEnabled(false);
//
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayerInit) {
//                    mediaPlayer.start();
//
//                    startTime = mediaPlayer.getCurrentPosition();
//                    startSec.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                    myHandler.postDelayed(UpdateSongTime, 50);
//                    stopButton.setEnabled(true);
//                    pauseButton.setEnabled(true);
//                    playButton.setEnabled(false);
////                }
//                }
//            }
//        });
//
//        pauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayerInit) {
//                    mediaPlayer.pause();
//                    pauseButton.setEnabled(false);
//                    playButton.setEnabled(true);
//                }
//            }
//        });
//
//        stopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayerInit) {
//                    stopButton.setEnabled(false);
//                    pauseButton.setEnabled(false);
//                    playButton.setEnabled(true);
//                    mediaPlayer.pause();
//                    mediaPlayer.seekTo(0);
//                }
//            }
//        });
//    }
//
//    private Runnable UpdateSongTime = new Runnable() {
//        public void run() {
//            if (mediaPlayerInit) {
//                startTime = mediaPlayer.getCurrentPosition();
//                startSec.setText(String.format("%d:%d",
//                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//                myHandler.postDelayed(UpdateSongTime, 50);
//            }
//        }
//    };
//
//    @Override
//    public void onBackPressed()
//    {
//        mediaPlayerInit = false;
//        mediaPlayer.stop();
//        mediaPlayer.release();
//        super.onBackPressed();  // optional depending on your needs
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//        }
//    }
//}
//
//
//
