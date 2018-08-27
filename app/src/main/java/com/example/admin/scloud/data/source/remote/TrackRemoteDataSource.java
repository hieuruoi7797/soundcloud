package com.example.admin.scloud.data.source.remote;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.source.TrackDataSource;
import com.example.admin.scloud.utils.StringUtil;

public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {
    private static TrackRemoteDataSource sTrackRemoteDataSource;

    public static TrackRemoteDataSource getInstance() {
        if (sTrackRemoteDataSource == null) {
            sTrackRemoteDataSource = new TrackRemoteDataSource();
        }
        return sTrackRemoteDataSource;
    }

    @Override
    public void getTracksRemote(
            String genre, int limit, int offSet, OnFetchDataListener<Track> listener) {
        new FetchGenresFromUrl(listener)
                .execute(StringUtil.convertUrlFetchMusicGenre(genre, limit, offSet));
    }

    @Override
    public void searchTracksRemote(
            String trackName,int limit, int offSet, OnFetchDataListener<Track> listener) {
        new SearchTrackFromUrl(listener)
                .execute(StringUtil.convertUrlSearchTrack(trackName,limit, offSet));
    }
}
