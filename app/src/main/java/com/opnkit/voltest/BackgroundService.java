package com.opnkit.voltest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {
    private Handler handler;
    private Runnable runnable;
    private AudioManager mAudioManager;
    public BackgroundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Opnkit"," in the service");
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_MUTE,AudioManager.FLAG_PLAY_SOUND);
                handler.postDelayed(this,5000);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.postDelayed(runnable,0);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}