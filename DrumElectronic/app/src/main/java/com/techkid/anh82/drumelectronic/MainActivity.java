package com.techkid.anh82.drumelectronic;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    List<ImageView> listView;
    List<MediaPlayer> listMedia;

    // key nao dc nhan thi minh them vao, nha ra thi xoa
    private List<PressedKeyInfo> pressedKeyInfos;
    class PressedKeyInfo{
        private ImageView ivKey;
        private int pointerId;

        public PressedKeyInfo(ImageView ivKey, int pointerId) {
            this.ivKey = ivKey;
            this.pointerId = pointerId;
        }

        public ImageView getIvKey() {
            return ivKey;
        }

        public int getPointerId() {
            return pointerId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ArrayList<>();
        listMedia = new ArrayList<>();
        pressedKeyInfos = new ArrayList<>();

        setContentView(R.layout.activity_main);
        setRelativeLayOut();


        //Toast.makeText(MainActivity.this , "Hello", Toast.LENGTH_LONG).show();
    }

    void checkMedia(MediaPlayer mp){
        if(mp != null){
            mp.release();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //  Log.d(TAG , String.format("  BLABLA %s %s" ,event.getX() , event.getY()) );
        // get index moi nhat
        for (int pointerIndex = 0; pointerIndex < event.getPointerCount(); pointerIndex++) {
            int pointerId = event.getPointerId(pointerIndex);
            float pointerX = event.getX(pointerIndex);
            float pointerY = event.getY(pointerIndex);
            int pointerAction = event.getActionMasked();
            if (pointerAction == MotionEvent.ACTION_MOVE) {
                for (int i = 0; i < pressedKeyInfos.size(); i++) {
                    PressedKeyInfo pressKeyInfo = pressedKeyInfos.get(i);
                    if (pressKeyInfo.getPointerId() == pointerId && !isInside(pointerX, pointerY, pressKeyInfo.getIvKey())) {
                        //touch moved outside view
                        pressedKeyInfos.remove(i);
                        setPressed(pressKeyInfo.getIvKey(), false);

           }



                }
            }

            ImageView pressedKey = findPressedKey(pointerX, pointerY);
            if (pressedKey != null) {
                if (pointerAction == MotionEvent.ACTION_DOWN || pointerAction == MotionEvent.ACTION_POINTER_DOWN || pointerAction == MotionEvent.ACTION_MOVE) {
                    if (!containsKeyInfoWith(pressedKey)) {
                        pressedKeyInfos.add(new PressedKeyInfo(pressedKey, pointerId));
                       // setPressed(pressedKey, true);
                    }
                    setPressed(pressedKey, true);

                }
                if (pointerAction == MotionEvent.ACTION_UP || pointerAction == MotionEvent.ACTION_POINTER_UP) {
                    for (int i = 0; i < pressedKeyInfos.size(); i++) {
                        PressedKeyInfo pressKeyInfo = pressedKeyInfos.get(i);
                        if (pressKeyInfo.getPointerId() == pointerId) {
                            pressedKeyInfos.remove(i);
                        }
                    }
                    setPressed(pressedKey, false);
                }
            }
        }
        return super.onTouchEvent(event);
    }


    private boolean containsKeyInfoWith(ImageView iv){
        for(PressedKeyInfo pressedKeyInfo: pressedKeyInfos){
            if(pressedKeyInfo.getIvKey() == iv){
                return true;
            }
        }
        return false;
    }
    private ImageView findPressedKey(float pointX , float pointY){
        for(ImageView v : listView){
            if(isInside(pointX, pointY, v)){
                return v;
            }
        }
        return null;
    }

    private void setPressed(ImageView view , boolean isPressed){
        if(isPressed){
           view.setImageResource(R.drawable.press);
            listMedia.get(listView.indexOf(view)).start();
        } else {
            view.setImageResource(R.drawable.enter);
        }
    }

    private boolean isInside(float x , float y , View v){
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + v.getWidth();
        int bottom = top + v.getHeight();
        return x > left && x < right && y > top && y < bottom ;
    }

    void setRelativeLayOut(){
        listView.add( (ImageView) findViewById(R.id.imageView1));
        listView.add( (ImageView) findViewById(R.id.imageView2));
        listView.add( (ImageView) findViewById(R.id.imageView3));
        listView.add( (ImageView) findViewById(R.id.imageView4));
        listView.add( (ImageView) findViewById(R.id.imageView5));
        listView.add( (ImageView) findViewById(R.id.imageView6));
        listView.add( (ImageView) findViewById(R.id.imageView7));
        listView.add( (ImageView) findViewById(R.id.imageView8));
        listView.add( (ImageView) findViewById(R.id.imageView9));

        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta1));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta2));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta3));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta4));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta5));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta6));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta7));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta8));
        listMedia.add(MediaPlayer.create(MainActivity.this , R.raw.chungta9));

        // Set size  relativeLayout
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;
       // Toast.makeText(MainActivity.this , width + " " + height, Toast.LENGTH_LONG).show();
        int temp  = 0;
        if(width > height){
            temp = height;
        } else {
            temp = width;
        }
       relativeLayout.getLayoutParams().width = temp;
       relativeLayout.getLayoutParams().height = temp;



    }
}
