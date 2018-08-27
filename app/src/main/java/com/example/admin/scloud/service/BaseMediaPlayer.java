package com.example.admin.scloud.service;

public interface BaseMediaPlayer {

    void start();

    void play();

    void pause();

    void resume();

    void stop();

    void release();

    void seekTo(int index);

    void next();

    void previous();

    long getDuration();

    long getCurrentDuration();

    boolean isPlaying();
}
