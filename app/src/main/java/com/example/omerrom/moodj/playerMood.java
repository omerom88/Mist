package com.example.omerrom.moodj;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by omerrom on 10/06/2017.
 */
public class playerMood extends Activity {
    private Button stopButton, pauseButton, playButton, nextBut, beforeBut, changeBut;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public static int songCounter = 0;
    private SignaturePad mSignaturePad, mSaveButton, mClearButton;
    public static int numberOfTouches = 0;
    public static float allPressures = 0;
    public static float avgPressures = 0;
    public static float touchVel = 0;
    public static float avgVel = 0;
    public static float maxHeight = 0;
    public static int fingerOff = 0;
    public static long startTime,totalTime;
    public static float workHeight = 0;
    public static float maxWidth = 0;
    public static float workWidth = 0;
    public static File photo;
    public static float minWidth = 700;
    public static float minHeight = 1000;
    private VelocityTracker mVelocityTracker = null;
    public static Drawable photoDrawable;
    public static ArrayList<Integer> thePlaylist;
    public static ArrayList<Drawable> theFrusGallery = new ArrayList<>();
    public static ArrayList<Drawable> theChillGallery = new ArrayList<>();
    public static ArrayList<Drawable> theUnGallery = new ArrayList<>();
    public int gallerySwipeCounter = 0;
    public RelativeLayout gallerPic;
    public float drawScore;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public AnimationDrawable animation, animFrus, animChill, animUn;
    public String whoToChangeMood = "";
    public GifDrawable gifDrawableFrus;
    private GifImageView im;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_record_player);
        clearAllVars();
        stopButton = (Button) findViewById(R.id.stopButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        changeBut = (Button) findViewById(R.id.change);
        playButton = (Button) findViewById(R.id.playButton);
        beforeBut = (Button) findViewById(R.id.trackBefore);
        nextBut = (Button) findViewById(R.id.trackAfter);
        mSaveButton = (SignaturePad) findViewById(R.id.playerSave);
        mClearButton = (SignaturePad) findViewById(R.id.playerClear);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad_player);
        im = (GifImageView)findViewById(R.id.imageViewPerfect);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.allButLayout);
        final RelativeLayout saveClearLay = (RelativeLayout) findViewById(R.id.clearDoneLayout);
        gallerPic = (RelativeLayout)findViewById(R.id.galleryPics);
        final FrameLayout mainLay = (FrameLayout) findViewById(R.id.themeLayout);

        setBrushColor();
        initList();
        playSong();
        setChangeMood();

        try {
            gifDrawableFrus = new GifDrawable(getResources(), R.drawable.perfectmatch);
            gifDrawableFrus.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    im.setVisibility(View.INVISIBLE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        changeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClearButton.clear();
                mSignaturePad.clear();
                clearAllVars();
                if (whoToChangeMood.equals("frus")) {
                    moveToFrus();
                } else if (whoToChangeMood.equals("chill")) {
                    moveToChill();
                } else {
                    moveToUn();
                }

            }
        });

        rl.setOnTouchListener(new OnSwipeTouchListener(playerMood.this) {
            public void onSwipeRight() {
                gallerySwipeCounter++;
                if (gallerySwipeCounter % 2 == 1) {
                    mSignaturePad.clear();
                    mainLay.setBackground(getResources().getDrawable(R.drawable.layout29));
                    saveClearLay.setEnabled(false);
                    clearAllVars();
                    mSignaturePad.setEnabled(false);
                    mSaveButton.setEnabled(false);
                    mClearButton.setEnabled(false);
                    changeBut.setEnabled(false);
                    moveToGallery();

                } else {
                    mainLay.setBackground(getResources().getDrawable(R.drawable.layout28));
                    saveClearLay.setEnabled(true);
                    mSignaturePad.setEnabled(true);
                    mSaveButton.setEnabled(true);
                    mClearButton.setEnabled(true);
                    changeBut.setEnabled(true);
                    gallerPic.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    animation.stop();
                }
            }

            public void onSwipeLeft() {
                gallerySwipeCounter++;
                if (gallerySwipeCounter % 2 == 1) {
                    mSignaturePad.clear();
                    mainLay.setBackground(getResources().getDrawable(R.drawable.layout29));
                    saveClearLay.setEnabled(false);
                    clearAllVars();
                    mSignaturePad.setEnabled(false);
                    mSaveButton.setEnabled(false);
                    mClearButton.setEnabled(false);
                    changeBut.setEnabled(false);
                    moveToGallery();


                } else {
                    mainLay.setBackground(getResources().getDrawable(R.drawable.layout28));
                    saveClearLay.setEnabled(true);
                    mSignaturePad.setEnabled(true);
                    mSaveButton.setEnabled(true);
                    mClearButton.setEnabled(true);
                    changeBut.setEnabled(true);
                    gallerPic.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    animation.stop();
                }
            }
        });


        mSignaturePad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                numberOfTouches++;
                float newTime = (System.currentTimeMillis() - startTime)/1000;
                if (numberOfTouches % 80 == 0 || newTime > 10.0f) {
                    int ret = drawAnalyzer();
                    clearAllVars();
                    checkRet(ret);
                } else {
                    allPressures += event.getPressure() * 1000;
                    if (mVelocityTracker == null) {
                        mVelocityTracker = VelocityTracker.obtain();
                    }
                    mVelocityTracker.addMovement(event);
                    mVelocityTracker.computeCurrentVelocity(1000, 10000);
                    touchVel += Math.abs(VelocityTrackerCompat.getXVelocity(mVelocityTracker, 0));
                    if (maxWidth < event.getX()) {
                        maxWidth = event.getX();
                    }
                    if (minWidth > event.getX()) {
                        minWidth = event.getX();
                    }
                    if (maxHeight < event.getY()) {
                        maxHeight = event.getY();
                    }
                    if (minHeight > event.getY()) {
                        minHeight = event.getY();
                    }
                }
                return false;
            }
        });

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                fingerOff++;
                if (fingerOff == 1) {
                    startTime = System.currentTimeMillis();
                }

            }
            @Override
            public void onSigned() {
            }

            @Override
            public void onClear() {
            }
        });

        mClearButton.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                mClearButton.clear();
                mSignaturePad.clear();
                clearAllVars();
            }

            @Override
            public void onClear() {

            }
        });

        mSaveButton.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
                if (fingerOff > 0) {
                    save();
                    mSignaturePad.clear();
                    mSaveButton.clear();
                    clearAllVars();
                }
                else{
                    mSaveButton.clear();
                }
            }

            @Override
            public void onClear() {
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                stopButton.setEnabled(true);
                pauseButton.setEnabled(true);
                playButton.setEnabled(false);
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopButton.setEnabled(false);
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
        });

        beforeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songCounter--;
                if (songCounter < 0) {
                    songCounter = thePlaylist.size() - 1;
                }
                playSong();
            }
        });


        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songCounter++;
                if (songCounter > thePlaylist.size() - 1) {
                    songCounter = 0;
                }
                playSong();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                songCounter++;
                if (songCounter > thePlaylist.size() - 1) {
                    songCounter = 0;
                }
                playSong();
            }
        });
    }

    public void setChangeMood(){
        animFrus = new AnimationDrawable();
        Drawable d;
        d = getResources().getDrawable(R.drawable.switchred3);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred2);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred1);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred2);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred3);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred2);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred1);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred2);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred3);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred3);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred2);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred1);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred2);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchred3);
        animFrus.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchnone);
        animFrus.addFrame(d,500);
        animFrus.setOneShot(true);

        animChill = new AnimationDrawable();
        d = getResources().getDrawable(R.drawable.switchgreen3);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen2);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen1);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen2);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen3);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen2);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen1);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen2);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen3);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen3);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen2);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen1);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen2);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchgreen3);
        animChill.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchnone);
        animChill.addFrame(d,500);
        animChill.setOneShot(true);

        animUn = new AnimationDrawable();
        d = getResources().getDrawable(R.drawable.switchblue3);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue2);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue1);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue2);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue3);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue2);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue1);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue2);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue3);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue3);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue2);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue1);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue2);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchblue3);
        animUn.addFrame(d,500);
        d = getResources().getDrawable(R.drawable.switchnone);
        animUn.addFrame(d,500);
        animUn.setOneShot(true);
    }


    public void moveToGallery(){
        animation = new AnimationDrawable();
        if (MainActivity.moodChoosen.equals("frus")){
            for (Drawable d:theFrusGallery){
                animation.addFrame(d, 500);
            }
        }
        else if (MainActivity.moodChoosen.equals("chill")) {
            for (Drawable d : theChillGallery) {
                animation.addFrame(d, 1000);
            }
        }
        else{
            for (Drawable d : theUnGallery) {
                animation.addFrame(d, 3000);
            }
        }
        animation.setOneShot(false);
        gallerPic.setBackgroundDrawable(animation);
        animation.start();
    }


    public void initList(){
        if (MainActivity.moodChoosen.equals("frus")){
            thePlaylist = new ArrayList<>(MainActivity.frusPlaylist);
            MainActivity.moodChoosen = "frus";
        }
        else if (MainActivity.moodChoosen.equals("chill")){
            thePlaylist = new ArrayList<>(MainActivity.chillPlaylist);
            MainActivity.moodChoosen = "chill";
        }
        else {
            thePlaylist = new ArrayList<>(MainActivity.unPlaylist);
            MainActivity.moodChoosen = "un";
        }
    }


    public void setBrushColor(){
        if (MainActivity.moodChoosen.equals("un")){
            mSignaturePad.setPenColor(Color.parseColor("#4b5eaa"));
        }
        else if (MainActivity.moodChoosen.equals("chill")){
            mSignaturePad.setPenColor(Color.parseColor("#05aa4b"));
        }
        else{
            mSignaturePad.setPenColor(Color.parseColor("#e41f26"));
        }
    }


    public void clearAllVars(){
        numberOfTouches = 0;
        allPressures = 0;
        touchVel = 0;
        maxWidth = 0;
        maxHeight = 0;
        workHeight = 0;
        workWidth = 0;
        minWidth = 700;
        minHeight = 1000;
        fingerOff = 0;
        startTime = System.currentTimeMillis();

    }


    public void save(){
        Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
        if (addJpgSignatureToGallery(signatureBitmap)) {
            Toast.makeText(playerMood.this, "Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(playerMood.this, "Unable to store the draw", Toast.LENGTH_SHORT).show();
        }
    }


    public void checkRet(int ret){
        if (MainActivity.moodChoosen.equals("un")) {
            switch (ret) {
                // 0 = frus, 1 = chill, 2 = unm
                case 0: {
                    clearAllVars();
                    mSignaturePad.clear();
                    changeBut.setBackgroundDrawable(animFrus);
                    animFrus.start();
                    whoToChangeMood = "frus";
                    break;
                }
                case 1: {
                    clearAllVars();
                    mSignaturePad.clear();
                    changeBut.setBackgroundDrawable(animChill);
                    animChill.start();
                    whoToChangeMood = "chill";
                    break;
                }
                case 2: {
                    clearAllVars();
                    break;
                }
            }
        }
        else if (MainActivity.moodChoosen.equals("chill")){
            switch (ret) {
                // 0 = frus, 1 = chill, 2 = unm
                case 0: {
                    clearAllVars();
                    mSignaturePad.clear();
                    changeBut.setBackgroundDrawable(animFrus);
                    animFrus.start();
                    whoToChangeMood = "frus";
                    break;
                }
                case 1: {
                    clearAllVars();
                    break;
                }
                case 2: {
                    clearAllVars();
                    mSignaturePad.clear();
                    changeBut.setBackgroundDrawable(animUn);
                    animUn.start();
                    whoToChangeMood = "un";
                    break;
                }
            }

        }
        else if (MainActivity.moodChoosen.equals("frus")){
            switch (ret) {
                // 0 = frus, 1 = chill, 2 = unm
                case 0: {
                    clearAllVars();
                    break;
                }
                case 1: {
                    clearAllVars();
                    mSignaturePad.clear();
                    changeBut.setBackgroundDrawable(animChill);
                    animChill.start();
                    whoToChangeMood = "chill";
                    break;
                }
                case 2: {
                    clearAllVars();
                    mSignaturePad.clear();
                    changeBut.setBackgroundDrawable(animUn);
                    animUn.start();
                    whoToChangeMood = "un";
                    break;
                }
            }

        }

    }

    public void deleteFromPlayList(String who){
        int nowPlaying = thePlaylist.get(songCounter);
        if (MainActivity.moodChoosen.equals("frus")){
            for (int i=0;i<MainActivity.frusPlaylist.size();i++){
                if (nowPlaying == MainActivity.frusPlaylist.get(i)){
                    MainActivity.frusPlaylist.remove(i);
                }
            }
        }
        else if (MainActivity.moodChoosen.equals("chill")){
            for (int i=0;i<MainActivity.chillPlaylist.size();i++){
                if (nowPlaying == MainActivity.chillPlaylist.get(i)){
                    MainActivity.chillPlaylist.remove(i);
                }
            }
        }
        else{
            for (int i=0;i<MainActivity.unPlaylist.size();i++){
                if (nowPlaying == MainActivity.unPlaylist.get(i)){
                    MainActivity.unPlaylist.remove(i);
                }
            }
        }

        if (who.equals("frus")){
            MainActivity.frusPlaylist.add(nowPlaying);
        }
        else if (who.equals("chill")){
            MainActivity.chillPlaylist.add(nowPlaying);
        }
        else{
            MainActivity.unPlaylist.add(nowPlaying);
        }

    }


    public void moveToFrus(){
        deleteFromPlayList("frus");
        thePlaylist = new ArrayList<>(MainActivity.frusPlaylist);
        MainActivity.moodChoosen = "frus";
        mediaPlayer.stop();
        songCounter = 0;
        playSong();
        setBrushColor();
    }


    public void moveToChill(){
        deleteFromPlayList("chill");
        thePlaylist = new ArrayList<>(MainActivity.chillPlaylist);
        MainActivity.moodChoosen = "chill";
        mediaPlayer.stop();
        songCounter = 0;
        playSong();
        setBrushColor();
    }


    public void moveToUn(){
        deleteFromPlayList("un");
        thePlaylist = new ArrayList<>(MainActivity.unPlaylist);
        MainActivity.moodChoosen = "un";
        mediaPlayer.stop();
        songCounter = 0;
        playSong();
        setBrushColor();
    }


    public int drawAnalyzer(){
        // 0 = frus, 1 = chill, 2 = unm
        avgPressures = allPressures/numberOfTouches;
        avgVel = touchVel/numberOfTouches;
        workHeight = maxHeight - minHeight+30;
        workWidth = maxWidth - minWidth;
        totalTime = (System.currentTimeMillis() - startTime)/1000;

        float scaledAvgPress = 100*(avgPressures - 340)/380;
        int scaledNumOfTouch = 100*(numberOfTouches)/200;
        double scaledAvgVel = (100*(Math.pow(avgVel,0.5) - 4)/40) + 20;
        double scaledWorkHeight = 100*(workHeight - 30)/1070;
        double scaledWorkWidth = 100*(workWidth)/700;
        double scaledAvgWork = (scaledWorkHeight + scaledWorkWidth)/2;

        drawScore = (2*scaledAvgPress  + (100 - (float)scaledNumOfTouch) +
                2*(float)scaledAvgVel + (float)scaledAvgWork)/6;

        if (Math.abs(drawScore - DrawingActivity.ret) < 2) {
            im.setBackground(gifDrawableFrus);
        }
        if (totalTime < 12.0f)
        {
            if (drawScore > 60){
                return 0;
            }
            if (drawScore < 45){
                return 2;
            }
            else
                return 1;
        }
        else{
            if (drawScore > 80){
                return 0;
            }
            if (drawScore < 20){
                return 2;
            }
            else
                return 1;
        }
    }


    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("Mist", "Directory not created");
        }
        return file;
    }


    public Bitmap saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth()-20, bitmap.getHeight()-20, bitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.RED, PorterDuff.Mode.DARKEN);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
        return newBitmap;
    }


    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            photo = new File(getAlbumStorageDir("Mist"), String.format("Draw_%d.jpg", System.currentTimeMillis()));
            Bitmap newOne =  saveBitmapToJPG(signature, photo);
            photoDrawable = new BitmapDrawable(getResources(),newOne);
            scanMediaFile(photo);
            result = true;
            if (MainActivity.moodChoosen.equals("frus")){
                theFrusGallery.add(photoDrawable);
            }
            else if (MainActivity.moodChoosen.equals("chill")){
                theChillGallery.add(photoDrawable);
            }
            else{
                theUnGallery.add(photoDrawable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        playerMood.this.sendBroadcast(mediaScanIntent);
    }


    public void playSong(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        int playlistelemnt = thePlaylist.get(songCounter);
        mediaPlayer = MediaPlayer.create(playerMood.this, playlistelemnt);
        mediaPlayer.start();
        stopButton.setEnabled(true);
        pauseButton.setEnabled(true);
        playButton.setEnabled(false);
    }


    @Override
    public void onBackPressed()
    {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onBackPressed();  // optional depending on your needs
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(playerMood.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearAllVars();
        mSaveButton.clear();
        mClearButton.clear();
        mSignaturePad.clear();
        mediaPlayer.release();


    }

}
