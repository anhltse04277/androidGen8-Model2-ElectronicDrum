package com.techkid.anh82.drumelectronic;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    MediaPlayer mp1;
    MediaPlayer mp2;
    MediaPlayer mp3;
    MediaPlayer mp4;
    MediaPlayer mp5;
    MediaPlayer mp6;
    MediaPlayer mp7;
    MediaPlayer mp8;
    MediaPlayer mp9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        set();
        //Toast.makeText(MainActivity.this , "Hello", Toast.LENGTH_LONG).show();
    }

    void checkMedia(MediaPlayer mp){
        if(mp != null){
            mp.release();
        }
    }
    void setEnterButton(final ImageView imageView , final String mp3String ){

        imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               //checkMedia();
                switch(mp3String){
                    case "chungta1":{
                        checkMedia(mp1);
                          mp1  = MediaPlayer.create(MainActivity.this , R.raw.chungta1);
                        mp1.start();
                        break;
                    }
                    case "chungta2":{
                        checkMedia(mp2);
                         mp2  = MediaPlayer.create(MainActivity.this , R.raw.chungta2);
                        mp2.start();
                        break;
                    }
                    case "chungta3":{
                        checkMedia(mp3);
                         mp3  = MediaPlayer.create(MainActivity.this , R.raw.chungta3);
                        mp3.start();
                        break;
                    }
                    case "chungta4":{
                        checkMedia(mp4);
                         mp4  = MediaPlayer.create(MainActivity.this , R.raw.chungta4);
                        mp4.start();
                        break;
                    }
                    case "chungta5":{
                        checkMedia(mp5);
                         mp5  = MediaPlayer.create(MainActivity.this , R.raw.chungta5);
                        mp5.start();
                        break;
                    }
                    case "chungta6":{
                        checkMedia(mp6);
                          mp6  = MediaPlayer.create(MainActivity.this , R.raw.chungta6);
                        mp6.start();
                        break;
                    }
                    case "chungta7":{
                        checkMedia(mp7);
                         mp7  = MediaPlayer.create(MainActivity.this , R.raw.chungta7);
                        mp7.start();
                        break;
                    }
                    case "chungta8":{
                        checkMedia(mp8);
                        mp8  = MediaPlayer.create(MainActivity.this , R.raw.chungta8);
                        mp8.start();
                        break;
                    }
                    case "chungta9":{
                        checkMedia(mp9);
                        mp9  = MediaPlayer.create(MainActivity.this , R.raw.chungta9);
                        mp9.start();
                        break;
                    }
                }



                imageView.setImageResource(R.drawable.press);

                new CountDownTimer(100,100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        imageView.setImageResource(R.drawable.enter);
                    }
                }.start() ;
            }
        });

    }

    void set(){
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        setEnterButton(imageView1 , "chungta1");
        setEnterButton(imageView2, "chungta2");
        setEnterButton(imageView3,  "chungta3");
        setEnterButton(imageView4, "chungta4");
        setEnterButton(imageView5 , "chungta5");
        setEnterButton(imageView6, "chungta6");
        setEnterButton(imageView7 , "chungta7");
        setEnterButton(imageView8, "chungta8");
        setEnterButton(imageView9, "chungta9");


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
