package com.example.admin.scloud.screen.genre.genre_detail;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.repository.TrackRepository;
import com.example.admin.scloud.data.source.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class GenreDetailPresenter implements GenreDetailContract.Presenter,
        TrackDataSource.OnFetchDataListener<Track> {
    private GenreDetailContract.View mView;
    private TrackRepository mTrackRepository;

    public GenreDetailPresenter(GenreDetailContract.View view,
                                TrackRepository trackRepository) {
        mView = view;
        mTrackRepository = trackRepository;
    }

    @Override
    public void loadTrack(String genre, int limit, int offSet) {
        mView.showLoadingIndicator();
        mTrackRepository.getTracksRemote(genre, limit, offSet, this);
    }

    @Override
    public void onFectDataSuccess(List<Track> data) {
        mView.hideLoadingIndicator();
        if (data == null || data.isEmpty()) {
            mView.showNoTracks();
            return;
        }
        mView.showTracks((ArrayList<Track>) data);
    }

    @Override
    public void onFectDataFailure(String messenger) {
        mView.hideLoadingIndicator();
        mView.showLoadingTracksError(messenger);
    }

    @Override
    public void start() {
    }
}
