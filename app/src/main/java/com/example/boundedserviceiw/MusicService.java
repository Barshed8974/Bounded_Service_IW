package com.example.boundedserviceiw;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.halloween_theme);
    }

    public void play()
    {
        if(!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }

    public void pause()
    {
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    public void stop()
    {
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new ServiceBinder();
    }
    public class ServiceBinder extends Binder{
        public MusicService getMusicService()
        {
            return MusicService.this;//return the service object
        }
    }
}