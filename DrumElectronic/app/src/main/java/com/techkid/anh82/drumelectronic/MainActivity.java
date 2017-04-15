package com.techkid.anh82.drumelectronic;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.techkid.anh82.drumelectronic.touch.Touch;
import com.techkid.anh82.drumelectronic.touch.TouchManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

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
        SoundManager.loadSoundIntoList(this);

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
        List<Touch> touches = TouchManager.toTouches(event);

        if(touches.size() > 0){
            Touch firstTouch = touches.get(0);
            if(firstTouch.getAction() == ACTION_DOWN || firstTouch.getAction()==ACTION_POINTER_DOWN ){
                ImageView pressedKey = findPressedKey(firstTouch);
                if(pressedKey != null && !containsKeyInfoWith(pressedKey)){
                    pressedKeyInfos.add(new PressedKeyInfo(pressedKey,firstTouch.getId()));
                    setPressed(pressedKey,true);
                    SoundManager.playSound(pressedKey.getTag().toString());
                    // TODO: A PLAY NODE

                }
            } else if(firstTouch.getAction()== ACTION_UP ||firstTouch.getAction()== ACTION_POINTER_UP ){
                Iterator<PressedKeyInfo> iterator = pressedKeyInfos.iterator();

                while(iterator.hasNext()){
                    PressedKeyInfo pressedKeyInfo = iterator.next();
                    ImageView  ivKey = pressedKeyInfo.getIvKey();
                    if(firstTouch.getId() == pressedKeyInfo.getPointerId() ){
                        iterator.remove();
                        setPressed(ivKey, false);

                    }

                }
            } else if(firstTouch.getAction() == ACTION_MOVE) {

//                    for(Touch touch:touches){
//
//                    }
                for (Touch touch : touches) {
                    ImageView pressedKey = findPressedKey(touch);
                    if(pressedKey != null && !containsKeyInfoWith(pressedKey)){
                        pressedKeyInfos.add(new PressedKeyInfo(pressedKey, touch.getId()));
                        setPressed(pressedKey,true);
                        // TODO: A PLAY NODE
                        SoundManager.playSound(pressedKey.getTag().toString());
                    }

                    Iterator<PressedKeyInfo> iterator = pressedKeyInfos.iterator();

                    while(iterator.hasNext()){
                        PressedKeyInfo pressedKeyInfo = iterator.next();
                        ImageView  ivKey = pressedKeyInfo.getIvKey();
                        if(touch.getId() == pressedKeyInfo.getPointerId() && !touch.isInside(ivKey)){
                            iterator.remove();
                            setPressed(ivKey, false);

                        }

                    }


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
    private ImageView findPressedKey(Touch touch){
        for(ImageView v : listView){
            if(touch.isInside(v)){
                return v;
            }
        }


        return null;
    }

    private void setPressed(ImageView view , boolean isPressed){
        if(isPressed){
           view.setImageResource(R.drawable.press);

        } else {
            view.setImageResource(R.drawable.enter);
        }
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
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                MainActivity.this.finish();
                            }
                        }).setNegativeButton("No", null).show();
    }
}
