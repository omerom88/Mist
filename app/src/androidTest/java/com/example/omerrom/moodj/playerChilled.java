//package com.example.omerrom.moodj;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.view.VelocityTrackerCompat;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.VelocityTracker;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.github.gcacace.signaturepad.views.SignaturePad;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by omerrom on 03/06/2017.
// */
//public class playerChilled extends Activity {
//    private Button stopButton, pauseButton, playButton, nextBut, beforeBut, mSaveButton ,mClearButton;
//    private LinearLayout clearDoneLay;
//    private MediaPlayer mediaPlayer = new MediaPlayer();
//    private double startTime = 0;
//    private Handler myHandler = new Handler();
//    private TextView startSec,endSec;
//    public static ArrayList<Integer> songsArray = new ArrayList<>();
//    public static ArrayList<String> songsTime = new ArrayList<>();
//    private int songCounter = 0;
//    private SignaturePad mSignaturePad;
//    public static int fingerOff = 0;
//    public static int numberOfTouches = 0;
//    public static float allPressures = 0;
//    public static float avgPressures = 0;
//    public static long startTimeDrawing;
//    //    public static long totalTime;
//    public static float touchVel = 0;
//    public static float avgVel = 0;
//    public static float maxHeight = 0;
//    public static float workHeight = 0;
//    public static float maxWidth = 0;
//    public static float workWidth = 0;
//    public static File photo;
//    public static float minWidth = 700;
//    public static float minHeight = 1000;
//    private VelocityTracker mVelocityTracker = null;
//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//    public static Drawable photoDrawable;
//    public AlertDialog.Builder alertDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        verifyStoragePermissions(this);
//        setContentView(R.layout.activity_record_player);
//        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Avenir_Next_Condensed.ttc");
//        clearDoneLay = (LinearLayout)findViewById(R.id.clearDoneLayout);
//        clearDoneLay.setVisibility(View.INVISIBLE);
//        stopButton = (Button) findViewById(R.id.stopButton);
//        pauseButton = (Button) findViewById(R.id.pauseButton);
//        playButton = (Button)findViewById(R.id.playButton);
//        beforeBut = (Button)findViewById(R.id.trackBefore);
//        nextBut = (Button)findViewById(R.id.trackAfter);
//        mSaveButton = (Button)findViewById(R.id.button5);
//        mClearButton = (Button)findViewById(R.id.button4);
//        Button setBut = (Button) findViewById(R.id.settingBut);
//        final View themeLayout = findViewById(R.id.themeLayout);
//        themeLayout.setBackground(DrawingActivity.photoDrawable);
//        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_player);
//
//        startSec=(TextView)findViewById(R.id.runSec);
//        endSec=(TextView)findViewById(R.id.endSec);
//        startSec.setTypeface(type);
//        endSec.setTypeface(type);
//
//
//        DrawingActivity.whoIsPlaying = "chill";
//        mSignaturePad.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                numberOfTouches++;
//                if (numberOfTouches == 1) {
//                    themeLayout.setBackgroundColor(Color.WHITE);
//                    clearDoneLay.setVisibility(View.VISIBLE);
//                }
//                else if (numberOfTouches%60 == 0){
//                    int ret = drawAnalyzer();
//                    clearAllVars();
//                    checkRet(ret);
//                }
//                else {
//                    allPressures += event.getPressure() * 1000;
//                    if (mVelocityTracker == null) {
//                        mVelocityTracker = VelocityTracker.obtain();
//                    }
//                    mVelocityTracker.addMovement(event);
//                    mVelocityTracker.computeCurrentVelocity(1000, 10000);
//                    touchVel += Math.abs(VelocityTrackerCompat.getXVelocity(mVelocityTracker, 0));
//                    if (maxWidth < event.getX()) {
//                        maxWidth = event.getX();
//                    }
//                    if (minWidth > event.getX()) {
//                        minWidth = event.getX();
//                    }
//                    if (maxHeight < event.getY()) {
//                        maxHeight = event.getY();
//                    }
//                    if (minHeight > event.getY()) {
//                        minHeight = event.getY();
//                    }
//                }
//                return false;
//            }
//        });
//
//        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
//            @Override
//            public void onStartSigning() {
//                fingerOff++;
//                if (fingerOff == 1) {
//                    startTimeDrawing = System.currentTimeMillis();
//                }
//
//            }
//
//            @Override
//            public void onSigned() {
//                mSaveButton.setEnabled(true);
//                mClearButton.setEnabled(true);
//
//            }
//
//            @Override
//            public void onClear() {
//                mSaveButton.setEnabled(false);
//                mClearButton.setEnabled(false);
//            }
//        });
//
//        mClearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mSignaturePad.clear();
//                clearAllVars();
//            }
//        });
//
//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
//                if (addJpgSignatureToGallery(signatureBitmap)) {
//                    Toast.makeText(playerChilled.this, "Saved your draw in your phone gallery", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(playerChilled.this, "Unable to store the draw", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//
//
//
////        setBut.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mediaPlayer.stop();
////                mediaPlayer.release();
////                Intent intent1 = new Intent(plyerUnmotiv.this, setting.class);
////                startActivityForResult(intent1, 1);
////            }
////        });
//
//        playButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.start();
//                startTime = mediaPlayer.getCurrentPosition();
//                startSec.setText(String.format("%d:%d",
//                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//
//                myHandler.postDelayed(UpdateSongTime, 50);
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
//        stopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                stopButton.setEnabled(false);
//                pauseButton.setEnabled(false);
//                playButton.setEnabled(true);
//                mediaPlayer.pause();
//                mediaPlayer.seekTo(0);
//            }
//        });
//
//        beforeBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (songCounter == 0) {
//                    songCounter = 5;
//                }
//                else{
//                    songCounter--;
//                }
//                controllPlaylist(songCounter);
//            }
//        });
//
//        nextBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (songCounter == 5) {
//                    songCounter = 0;
//                }
//                else{
//                    songCounter++;
//                }
//                controllPlaylist(songCounter);
//            }
//        });
//
//        buildPlaylistArray();
//        buildTimeArray();
//        controllPlaylist(0);
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                if (songCounter == 5) {
//                    songCounter = 0;
//                } else {
//                    songCounter++;
//                }
//                controllPlaylist(songCounter);
//            }
//        });
//
//    }
//
//    public void clearAllVars(){
//        fingerOff = 0;
//        numberOfTouches = 0;
//        allPressures = 0;
//        touchVel = 0;
//        maxWidth = 0;
//        maxHeight = 0;
//        workHeight = 0;
//        workWidth = 0;
//        minWidth = 700;
//        minHeight = 1000;
//
//    }
//
//    public void openDialog(final ArrayList<Integer> songAr, final ArrayList<String> timeAr, final String who){
//        alertDialog = new AlertDialog.Builder(playerChilled.this);
//        alertDialog.setTitle("Yo boy");
//        alertDialog.setMessage("I can see your mood has changed\n do you wanna change music?");
//        alertDialog.setNegativeButton("No i'm good", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        alertDialog.setPositiveButton("Yha why not", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                DrawingActivity.whoIsPlaying = who;
//                songsArray = songAr;
//                songsTime = timeAr;
//                controllPlaylist(0);
//            }
//        });
//
//        alertDialog.show();
//    }
//
//    public void checkRet(int ret){
//        if (DrawingActivity.whoIsPlaying.equals("un")) {
//            switch (ret) {
//                // 0 = frus, 1 = chill, 2 = unm
//                case 0: {
//                    openDialog(playerFrus.buildPlaylistArray(),playerFrus.buildTimeArray(),"frus");
//                    break;
//                }
//                case 1: {
//                    openDialog(playerChilled.buildPlaylistArray(), playerChilled.buildTimeArray(),"chill");
//                    break;
//                }
//                case 2: {
//                    break;
//                }
//            }
//        }
//        else if (DrawingActivity.whoIsPlaying.equals("chill")){
//            switch (ret) {
//                // 0 = frus, 1 = chill, 2 = unm
//                case 0: {
//                    openDialog(playerFrus.buildPlaylistArray(),playerFrus.buildTimeArray(),"frus");
//                    break;
//                }
//                case 1: {
//                    break;
//                }
//                case 2: {
//                    openDialog(plyerUnmotiv.buildPlaylistArray(),plyerUnmotiv.buildTimeArray(),"un");
//                    break;
//                }
//            }
//
//        }
//        else if (DrawingActivity.whoIsPlaying.equals("frus")){
//            switch (ret) {
//                // 0 = frus, 1 = chill, 2 = unm
//                case 0: {
//                    break;
//                }
//                case 1: {
//                    openDialog(playerChilled.buildPlaylistArray(),playerChilled.buildTimeArray(),"chill");
//                    break;
//                }
//                case 2: {
//                    openDialog(plyerUnmotiv.buildPlaylistArray(),plyerUnmotiv.buildTimeArray(),"un");
//                    break;
//                }
//            }
//
//        }
//
//    }
//
//    public int drawAnalyzer(){
//        // 0 = frus, 1 = chill, 2 = unm
//        avgPressures = allPressures/numberOfTouches;
////        totalTime = (System.currentTimeMillis() - startTimeDrawing)/1000;
//        avgVel = touchVel/numberOfTouches;
//        workHeight = maxHeight - minHeight+30;
//        workWidth = maxWidth - minWidth;
//
//        float scaledAvgPress = 100*(avgPressures - 340)/380;
//        int scaledNumOfTouch = 100*(numberOfTouches)/200;
//        double scaledAvgVel = (100*(Math.pow(avgVel,0.5) - 4)/40) + 20;
//        double scaledWorkHeight = 100*(workHeight - 30)/1070;
//        double scaledWorkWidth = 100*(workWidth)/700;
//        double scaledAvgWork = (scaledWorkHeight + scaledWorkWidth)/2;
//
//        float ret = (2*scaledAvgPress  + (100 - (float)scaledNumOfTouch) +
//                2*(float)scaledAvgVel + (float)scaledAvgWork)/6;
////        if ((float)totalTime < 12)
////        {
//        if (ret > 60){
//            return 0;
//        }
//        if (ret < 40){
//            return 2;
//        }
//        else {
//            return 1;
//        }
////        }
////        else{
////            if (ret > 80){
////                return 0;
////            }
////            if (ret < 20){
////                return 2;
////            }
////            else
////                return 1;
////        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String permissions[], @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case REQUEST_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length <= 0
//                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(playerChilled.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
//
//    public File getAlbumStorageDir(String albumName) {
//        // Get the directory for the user's public pictures directory.
//        File file = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), albumName);
//        if (!file.mkdirs()) {
//            Log.e("SignaturePad", "Directory not created");
//        }
//        return file;
//    }
//
//    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
//        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(newBitmap);
//        canvas.drawColor(Color.WHITE);
//        canvas.drawBitmap(bitmap, 0, 0, null);
//        OutputStream stream = new FileOutputStream(photo);
//        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
//        stream.close();
//    }
//
//    public boolean addJpgSignatureToGallery(Bitmap signature) {
//        boolean result = false;
//        try {
//            photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
//            saveBitmapToJPG(signature, photo);
//            scanMediaFile(photo);
//            result = true;
//            photoDrawable = new BitmapDrawable(getResources(),signature);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    private void scanMediaFile(File photo) {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri contentUri = Uri.fromFile(photo);
//        mediaScanIntent.setData(contentUri);
//        playerChilled.this.sendBroadcast(mediaScanIntent);
//    }
//
//    public static void verifyStoragePermissions(Activity activity) {
//        // Check if we have write permission
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }
//
//
//    public void controllPlaylist(int i){
//        if (mediaPlayer.isPlaying()){
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        }
//        mediaPlayer = MediaPlayer.create(playerChilled.this, songsArray.get(i));
//        endSec.setText(songsTime.get(i));
//        mediaPlayer.start();
//        startTime = mediaPlayer.getCurrentPosition();
//        startSec.setText(String.format("%d:%d",
//                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//
//        myHandler.postDelayed(UpdateSongTime, 50);
//        stopButton.setEnabled(true);
//        pauseButton.setEnabled(true);
//        playButton.setEnabled(false);
//
//    }
//
//    private Runnable UpdateSongTime = new Runnable() {
//        public void run() {
//            startTime = mediaPlayer.getCurrentPosition();
//            startSec.setText(String.format("%d:%d",
//                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
//                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
//                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
//            myHandler.postDelayed(this, 50);
//        }
//    };
//
//    @Override
//    public void onBackPressed()
//    {
//        mediaPlayer.stop();
//        mediaPlayer.release();
//        super.onBackPressed();  // optional depending on your needs
//    }
//
//
//
//    public static ArrayList<Integer> buildPlaylistArray(){
//        songsArray.add(0,R.raw.dontworrybehappy);
//        songsArray.add(1,R.raw.beckwow);
//        songsArray.add(2,R.raw.thousandeyes);
//        songsArray.add(3,R.raw.arabesqueno1);
//        songsArray.add(4,R.raw.tango);
//        songsArray.add(5,R.raw.kissme);
//        return songsArray;
//    }
//    public static ArrayList<String> buildTimeArray(){
//        songsTime.add(0,"3:54");
//        songsTime.add(1,"3:45");
//        songsTime.add(2,"4:10");
//        songsTime.add(3,"4:24");
//        songsTime.add(4,"4:10");
//        songsTime.add(5,"3:11");
//        return songsTime;
//    }
//
//
//
//}
//
//
//
//
//
//
//
////
////
////
////
////
////    private Button stopButton, pauseButton, playButton, nextBut, beforeBut;
////    private MediaPlayer mediaPlayer = new MediaPlayer();
////    private double startTime = 0;
////    private double finalTime = 0;
////    private Handler myHandler = new Handler();
////    private TextView startSec,endSec;
////    private ArrayList<Integer> songsArray = new ArrayList<>();
////    private ArrayList<String> songsTime = new ArrayList<>();
////    private int songCounter = 0;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_record_player);
////        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Avenir_Next_Condensed.ttc");
////
////        stopButton = (Button) findViewById(R.id.stopButton);
////        pauseButton = (Button) findViewById(R.id.pauseButton);
////        playButton = (Button)findViewById(R.id.playButton);
////        beforeBut = (Button)findViewById(R.id.trackBefore);
////        nextBut = (Button)findViewById(R.id.trackAfter);
////        final View themeLayout = findViewById(R.id.themeLayout);
////        themeLayout.setBackground(DrawingActivity.photoDrawable);
////
////        startSec=(TextView)findViewById(R.id.runSec);
////        endSec=(TextView)findViewById(R.id.endSec);
////        startSec.setTypeface(type);
////        endSec.setTypeface(type);
////
////
////
////        Button setBut = (Button) findViewById(R.id.settingBut);
////        setBut.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mediaPlayer.stop();
////                mediaPlayer.release();
////                Intent intent1 = new Intent(playerChilled.this, setting.class);
////                startActivityForResult(intent1, 1);
////            }
////        });
////
////        playButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mediaPlayer.start();
////                startTime = mediaPlayer.getCurrentPosition();
////                startSec.setText(String.format("%d:%d",
////                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
////                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
////                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
////
////                myHandler.postDelayed(UpdateSongTime, 50);
////                stopButton.setEnabled(true);
////                pauseButton.setEnabled(true);
////                playButton.setEnabled(false);
////            }
////        });
////
////        pauseButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mediaPlayer.pause();
////                pauseButton.setEnabled(false);
////                playButton.setEnabled(true);
////            }
////        });
////
////        stopButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                stopButton.setEnabled(false);
////                pauseButton.setEnabled(false);
////                playButton.setEnabled(true);
////                mediaPlayer.pause();
////                mediaPlayer.seekTo(0);
////            }
////        });
////
////        beforeBut.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (songCounter == 0) {
////                    songCounter = 5;
////                }
////                else{
////                    songCounter--;
////                }
////                controllPlaylist(songCounter);
////            }
////        });
////
////        nextBut.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (songCounter == 5) {
////                    songCounter = 0;
////                }
////                else{
////                    songCounter++;
////                }
////                controllPlaylist(songCounter);
////            }
////        });
////
////        buildPlaylistArray();
////        controllPlaylist(0);
////        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
////            @Override
////            public void onCompletion(MediaPlayer mp) {
////                if (songCounter == 5) {
////                    songCounter = 0;
////                } else {
////                    songCounter++;
////                }
////                controllPlaylist(songCounter);
////            }
////        });
////
////    }
////
////    public void buildPlaylistArray(){
////        songsArray.add(0,R.raw.dontworrybehappy);
////        songsTime.add(0,"3:54");
////        songsArray.add(1,R.raw.beckwow);
////        songsTime.add(1,"3:45");
////        songsArray.add(2,R.raw.thousandeyes);
////        songsTime.add(2,"4:10");
////        songsArray.add(3,R.raw.arabesqueno1);
////        songsTime.add(3,"4:24");
////        songsArray.add(4,R.raw.tango);
////        songsTime.add(4,"4:10");
////        songsArray.add(5,R.raw.kissme);
////        songsTime.add(5,"3:11");
////    }
////
////    public void controllPlaylist(int i){
////        mediaPlayer = MediaPlayer.create(playerChilled.this, songsArray.get(i));
////        endSec.setText(songsTime.get(i));
////        mediaPlayer.start();
////        startTime = mediaPlayer.getCurrentPosition();
////        startSec.setText(String.format("%d:%d",
////                TimeUnit.MILLISECONDS.toMinutes((long) startTime),
////                TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
////                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
////
////        myHandler.postDelayed(UpdateSongTime, 50);
////        stopButton.setEnabled(true);
////        pauseButton.setEnabled(true);
////        playButton.setEnabled(false);
////
////    }
////
////    private Runnable UpdateSongTime = new Runnable() {
////        public void run() {
////            startTime = mediaPlayer.getCurrentPosition();
////            startSec.setText(String.format("%d:%d",
////                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
////                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
////                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
////            myHandler.postDelayed(this, 50);
////        }
////    };
////
////    @Override
////    public void onBackPressed()
////    {
////        mediaPlayer.stop();
////        mediaPlayer.release();
////        super.onBackPressed();  // optional depending on your needs
////    }
////
////}
////
