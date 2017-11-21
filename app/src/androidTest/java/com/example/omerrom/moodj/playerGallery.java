//package com.example.omerrom.moodj;
//
//import android.app.Activity;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by omerrom on 14/06/2017.
// */
//public class playerGallery extends Activity {
//    private Button stopButton, pauseButton, playButton, nextBut, beforeBut;
//    private MediaPlayer mediaPlayer = new MediaPlayer();
//    public static long totalTime, startTime;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gallery);
//        stopButton = (Button) findViewById(R.id.stopButton);
//        pauseButton = (Button) findViewById(R.id.pauseButton);
//        playButton = (Button)findViewById(R.id.playButton);
//        beforeBut = (Button)findViewById(R.id.trackBefore);
//        nextBut = (Button)findViewById(R.id.trackAfter);
//
//
//        playButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick (View v){
//                mediaPlayer.start();
//                startTime = mediaPlayer.getCurrentPosition();
//                stopButton.setEnabled(true);
//                pauseButton.setEnabled(true);
//                playButton.setEnabled(false);
//            }
//        });
//
//        pauseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.pause();
//                pauseButton.setEnabled(false);
//                playButton.setEnabled(true);
//            }
//        });
//
//        stopButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick (View v){
//                stopButton.setEnabled(false);
//                pauseButton.setEnabled(false);
//                playButton.setEnabled(true);
//                mediaPlayer.pause();
//                mediaPlayer.seekTo(0);
//            }
//        });
//        beforeBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playerMood.songCounter--;
//                if (playerMood.songCounter < 0) {
//                    playerMood.songCounter = playerMood.thePlaylist.size() - 1;
//                }
//                playSong();
//            }
//        });
//
//
//        nextBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playerMood.songCounter++;
//                if (playerMood.songCounter > playerMood.thePlaylist.size() - 1) {
//                    playerMood.songCounter = 0;
//                }
//                playSong();
//            }
//        });
//
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                playerMood.songCounter++;
//                if (playerMood.songCounter > playerMood.thePlaylist.size() - 1) {
//                    playerMood.songCounter = 0;
//                }
//                playSong();
//            }
//        });
//    }
//
//    public void playSong(){
//        if (mediaPlayer.isPlaying()){
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        }
//
//        int playlistelemnt = playerMood.thePlaylist.get(playerMood.songCounter);
//        mediaPlayer = MediaPlayer.create(playerGallery.this, playlistelemnt);
//        mediaPlayer.start();
//        startTime = mediaPlayer.getCurrentPosition();
//        stopButton.setEnabled(true);
//        pauseButton.setEnabled(true);
//        playButton.setEnabled(false);
//    }
//
//}
