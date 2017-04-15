package com.techkid.anh82.drumelectronic;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anh82 on 4/11/2017.
 */

public class SoundManager {
    private static int NUMNER_OF_NODES =9;
    public static SoundPool soundPool = new SoundPool(NUMNER_OF_NODES, AudioManager.STREAM_MUSIC,0);

    public static ArrayList<Integer> soundList = new ArrayList<>();

    public static void loadSoundIntoList(Context context){
        for(int i=1; i<= NUMNER_OF_NODES ; i++ ){
            int realIDSound = context.getResources().getIdentifier("chungta" + i, "raw", context.getPackageName());
            int soundPoolID = soundPool.load(context, realIDSound, 1);
            soundList.add(soundPoolID);

        }
    }
    static HashMap<String,Integer> listSoundId = new HashMap<>();
    static {
        listSoundId.put("A",0);
        listSoundId.put("B",1);
        listSoundId.put("C",2);
        listSoundId.put("D",3);
        listSoundId.put("E",4);
        listSoundId.put("F",5);
        listSoundId.put("G",6);
        listSoundId.put("H",7);
        listSoundId.put("J",8);




    }

    public static void playSound(String string){
        soundPool.play(soundList.get(listSoundId.get(string)), 1.0F , 1.0F, 1,0, 1.0F);
    }


}
