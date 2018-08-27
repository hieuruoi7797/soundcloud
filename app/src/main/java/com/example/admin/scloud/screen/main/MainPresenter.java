package com.example.admin.scloud.screen.main;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.source.remote.TrackDownloadManager;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void onDownloadTrack(Track track) {
        new TrackDownloadManager(mView.getContext(), new TrackDownloadManager.DownloadListener() {
            @Override
            public void onDownloadError() {
                mView.onDownloadError();
            }

            @Override
            public void onDownloading() {
                mView.onDownloading();
            }

            @Override
            public void onDownloadSuccess() {
                mView.onDownloadSuccess();
            }

        }).downloadTrack(track);
    }
}
