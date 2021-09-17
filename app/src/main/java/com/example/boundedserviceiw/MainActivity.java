package com.example.boundedserviceiw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mPlay;
    private Button mPause;
    private Button mStop;

    private MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        startService();
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicService!=null)
                    musicService.play();
            }
        });
        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicService!=null)
                    musicService.pause();
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicService!=null)
                    musicService.stop();
            }
        });
    }


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.ServiceBinder serviceBinder=(MusicService.ServiceBinder) service;
            musicService=serviceBinder.getMusicService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void initViews() {
        mPlay=findViewById(R.id.btnPlay);
        mPause=findViewById(R.id.btnPause);
        mStop=findViewById(R.id.btnStop);
    }
    private void startService()
    {
        Intent intent=new Intent(MainActivity.this,MusicService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
}