package com.example.admin.scloud.screen.search;

import com.example.admin.scloud.BasePresenter;
import com.example.admin.scloud.BaseView;
import com.example.admin.scloud.data.model.Track;

import java.util.ArrayList;

public interface SearchContract {
    interface Presenter extends BasePresenter {
        void searchTracks(String name, int limit, int offset);
    }

    interface View extends BaseView<Presenter> {
        void showNoTrackAvailable();

        void showTracks(ArrayList<Track> trackArrayList);

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showLoadingTrackError(String err);
    }
}
