package com.example.admin.scloud.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.admin.scloud.OnPlayAtMainListener;
import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.utils.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service implements BaseMediaPlayer {

    private static final String ACTION_CHANGE_MEDIA_NEXT = "ACTION_NEXT";
    private static final String ACTION_CHANGE_MEDIA_PREVIOUS = "ACTION_PREVIOUS";
    private static final String ACTION_CHANGE_MEDIA_STATE = "ACTION_CHANGE_STATE";
    private static final String ACTION_CHANGE_MEDIA_CLEAR = "ACTION_CLEAR";
    private static final int DEFAULT_POSSITION_START = 0;
    private static final int ID_NOTIFICATION = 183;
    private List<Track> mTracks;
    private MediaPlayer mMediaPlayer;
    private int mPossition;
    private RemoteViews mRemoteViews;
    private Notification mNotification;
    private Intent mIntentBroadcast;
    private Settings mSettings;
    private OnPlayAtMainListener mMainListener;

    public static Intent getInstance(Context context, List<Track> tracks, int possition) {
        Intent intent = new Intent(context, MusicService.class);
        intent.setAction(Constant.ConstantIntent.ACTION_INIT_SONG_SERVICE);
        intent.putParcelableArrayListExtra(Constant.ConstantIntent.EXTRA_INIT_SONG_SERVICE,
                (ArrayList<? extends Parcelable>) tracks);
        intent.putExtra(Constant.ConstantIntent.EXTRA_INIT_POSITION_SONG_SERVICE, possition);
        return intent;
    }

    public void setTracks(List<Track> tracks, int possition) {
        mTracks = tracks;
        mPossition = possition;
        Log.d("mtrack.size", "setTracks: " + mTracks.size());
    }

    public void setMainListener(OnPlayAtMainListener mainListener) {
        mMainListener = mainListener;
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    public void setPossition(int possition) {
        mPossition = possition;
        Log.d("possition", "onCreate: " + mPossition);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMediaPlayer();
        mIntentBroadcast = new Intent();
        mIntentBroadcast.setAction(Constant.ConstantBroadcast.ACTION_STATE_MEDIA);
    }

    @Override
    public void onDestroy() {
        release();
        super.onDestroy();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        Log.d("111", "onStartCommand: " + intent.getAction());
        if (action == null) {
            return START_STICKY;
        }
        switch (action) {
            case Constant.ConstantIntent.ACTION_INIT_SONG_SERVICE:
                mTracks = intent.getParcelableArrayListExtra(Constant.ConstantIntent.EXTRA_INIT_SONG_SERVICE);
                List<Track> tracks = intent.getParcelableArrayListExtra(Constant.ConstantIntent.EXTRA_INIT_SONG_SERVICE);
                int pos = intent.getIntExtra(Constant.ConstantIntent.EXTRA_INIT_SONG_SERVICE, 0);
                Log.d("AMENNNN", "onStartCommand: " + mTracks);
                break;
            case ACTION_CHANGE_MEDIA_PREVIOUS:
                previous();
                break;
            case ACTION_CHANGE_MEDIA_STATE:
                if (isPlaying()) {
                    pause();
                } else {
                    resume();
                }
                break;
            case ACTION_CHANGE_MEDIA_NEXT:
                next();
                break;
            case ACTION_CHANGE_MEDIA_CLEAR:
                stopSelf();
                stopForeground(true);
                stop();
                break;
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MediaBinder();
    }

    @Override
    public void start() {
        mMediaPlayer.start();
        mIntentBroadcast.putExtra(Constant.ConstantBroadcast.EXTRA_STATE_MEDIA, true);
        sendBroadcast(mIntentBroadcast);
    }

    @Override
    public void play() {
        mMediaPlayer.reset();
        Log.d("cccc", "play: " + mTracks.size() + " " + mPossition);
        try {
            mMediaPlayer.setDataSource(mTracks.get(mPossition).getSongURI());
            mMediaPlayer.prepareAsync();
            Log.d("title", "play: " + mTracks.get(mPossition).getTitle());
            Log.d("listener", "play: " + mMainListener);
            mMainListener.setTitle(mTracks.get(mPossition).getTitle());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mIntentBroadcast.putExtra(Constant.ConstantBroadcast.EXTRA_STATE_MEDIA, false);
            sendBroadcast(mIntentBroadcast);
        }
        upDateNotification();
    }

    @Override
    public void resume() {
        if (isPlaying()) {
            return;
        }
        start();
        upDateNotification();
    }

    @Override
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mIntentBroadcast.putExtra(Constant.ConstantBroadcast.EXTRA_STATE_MEDIA, false);
            sendBroadcast(mIntentBroadcast);
        }
        upDateNotification();
    }

    @Override
    public void release() {
        stop();
        mMediaPlayer.release();
    }

    @Override
    public void seekTo(int index) {
        mMediaPlayer.seekTo(index);
    }

    @Override
    public void next() {
    }

    @Override
    public void previous() {

    }

    @Override
    public long getDuration() {
        return mMediaPlayer.getDuration();
    }

    @Override
    public long getCurrentDuration() {
        return mMediaPlayer.getCurrentPosition();
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    private MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            start();
        }
    };

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

        }
    };

    private void initMediaPlayer() {
        mPossition = -1;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(mPreparedListener);
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }

    public String getTitleSongPlaying() {
        return mTracks.get(mPossition).getTitle();
    }

    public Track getCurrentTrack(){
        Log.d("HIEU_possition", "getCurrentTrack: "+ mTracks.get(mPossition).getTitle());
        return mTracks.get(mPossition);
    }

    private void upDateNotification() {
//        if (isPlaying()) {
//            //mRemoteViews.setImageViewResource();
//        }
    }

    public class MediaBinder extends Binder {
        public MusicService getMediaService() {
            return MusicService.this;
        }
    }
}
