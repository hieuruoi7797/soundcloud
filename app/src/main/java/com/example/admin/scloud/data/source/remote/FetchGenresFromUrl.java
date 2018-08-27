package com.example.admin.scloud.data.source.remote;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.source.TrackDataSource;

import org.json.JSONException;
import org.json.JSONObject;

public class FetchGenresFromUrl extends BaseFetchTrackFromUrl {
    protected FetchGenresFromUrl(TrackDataSource.OnFetchDataListener<Track> listener) {
        super(listener);
    }


    protected JSONObject getJsonTrack(JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject(Track.TrackEntity.TRACK);
    }
}
