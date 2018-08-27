package com.example.admin.scloud.screen.main;

import android.content.Context;

import com.example.admin.scloud.BasePresenter;
import com.example.admin.scloud.BaseView;
import com.example.admin.scloud.data.model.Track;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showTabLayout();

        void hideTabLayout();

        void onDownloadError();

        void onDownloading();

        void onDownloadSuccess();

        Context getContext();
    }

    interface Presenter extends BasePresenter {
        void onDownloadTrack(Track track);
    }

}

