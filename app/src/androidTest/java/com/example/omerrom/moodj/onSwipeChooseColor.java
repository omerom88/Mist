//package com.example.omerrom.moodj;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
///**
// * Created by omerrom on 13/06/2017.
// */
//public class onSwipeChooseColor   implements View.OnTouchListener {
//
//    private final GestureDetector gestureDetector;
//
//    public onSwipeChooseColor(Context ctx){
//        gestureDetector = new GestureDetector(ctx, new GestureListener());
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return gestureDetector.onTouchEvent(event);
//    }
//
//    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        private static final int SWIPE_THRESHOLD = 100;
//        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//        @Override
//        public boolean onDown(MotionEvent e) {
//            return true;
//        }
//
//        @Override
//        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            boolean result = true;
//            try {
//                Log.e("start X: ", Float.toString(e1.getX()));
//                Log.e("end X: ", Float.toString(e2.getX()));
//                if (e1.getX() < 260 && e1.getX() > 220){
//                    onGreenPressed();
//                }
//                if (e1.getX() < 370 && e1.getX() > 330){
//                    onBluePressed();
//                }
//                if (e1.getX() < 500 && e1.getX() > 460){
//                    onRedPressed();
//                }
////                float firstX = e1.getX();
////                float firstY = e1.getY();
////                float newX = e2.getX();
////                float newY = e2.getY();
////                float diffX = newX - firstX;
////                float diffY = newY - firstY;
////
////                if (Math.abs(diffX) > 10 || Math.abs(diffY) > 10){
////                    onRoundDraw();
////                    result = true;
////                }
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//            return result;
//        }
//
//    }
//    public void onGreenPressed() {
//    }
//    public void onBluePressed() {
//    }
//    public void onRedPressed() {
//    }
//
//}
//
//
