package lzh.dongruan.com.news.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import lzh.dongruan.com.news.R;

/**
 * Created by lzh220 on 2016/7/21.
 */
public class MyService extends Service {
    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        mediaPlayer=MediaPlayer.create(this, R.raw.music);
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle=intent.getExtras();
        int a=bundle.getInt("number");
        switch (a){
            case 1:
                if(mediaPlayer!=null&&!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case 2:
                if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
