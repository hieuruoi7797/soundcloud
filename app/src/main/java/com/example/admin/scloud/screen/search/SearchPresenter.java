package com.example.admin.scloud.screen.search;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.repository.TrackRepository;
import com.example.admin.scloud.data.source.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchContract.Presenter,
        TrackDataSource.OnFetchDataListener<Track> {
    private SearchContract.View mView;


    public SearchPresenter(SearchContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void onFectDataSuccess(List<Track> data) {
        mView.hideLoadingIndicator();
        if (data == null || data.isEmpty()) {
            mView.showNoTrackAvailable();
            return;
        }
        mView.showTracks((ArrayList<Track>) data);
    }

    @Override
    public void onFectDataFailure(String message) {
        mView.hideLoadingIndicator();
        mView.showLoadingTrackError(message);
    }

    @Override
    public void searchTracks(String name, int limit, int offset) {
        mView.showLoadingIndicator();
        TrackRepository.getInstanceRemote().searchTracksRemote(name, limit, offset, this);
    }
}
