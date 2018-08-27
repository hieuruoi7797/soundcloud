package com.example.admin.scloud.screen.genre.genre_detail;

import com.example.admin.scloud.BasePresenter;
import com.example.admin.scloud.BaseView;
import com.example.admin.scloud.data.model.Track;

import java.util.ArrayList;

public interface GenreDetailContract {

    interface View extends BaseView<Presenter> {
        void showTracks(ArrayList<Track> trackList);

        void showNoTracks();

        void showLoadingTracksError(String message);

        void showLoadingIndicator();

        void hideLoadingIndicator();
    }

    interface Presenter extends BasePresenter {
        void loadTrack(String genre, int limit, int offSet);
    }
}
