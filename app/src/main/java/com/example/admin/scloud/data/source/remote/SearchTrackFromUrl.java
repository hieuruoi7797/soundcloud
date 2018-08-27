package com.example.admin.scloud.data.source.remote;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.source.TrackDataSource;
import com.example.admin.scloud.utils.ConstantNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class SearchTrackFromUrl extends BaseFetchTrackFromUrl {
    public SearchTrackFromUrl(TrackDataSource.OnFetchDataListener<Track> listener) {
        super(listener);
    }

    protected List<Track> getTracksFromJsonObject(JSONObject jsonObject) throws JSONException {
        ArrayList<Track> trackArrayList = new ArrayList<>();
        JSONArray jsonCollection = jsonObject.getJSONArray(Track.TrackEntity.COLLECTION);
        for (int i = 0; i < jsonCollection.length(); i++) {
            JSONObject jsonTrack = jsonCollection.getJSONObject(i);
            Track track = parseJsonObjectToTrackObject(jsonTrack);
            if (track != null) {
                trackArrayList.add(track);
            }
        }
        return trackArrayList;
    }

    protected Track parseJsonObjectToTrackObject(JSONObject jsonTrack) {
        Track track = new Track();

        JSONObject jsonUser = jsonTrack.optJSONObject(Track.TrackEntity.USER);
        String artworkUrl = jsonTrack.optString(Track.TrackEntity.ARTWORK_URL);
        // Null artwork is replaced by user's avatar
        if (artworkUrl.equals(ConstantNetwork.NULL_RESULT)) {
            artworkUrl = jsonTrack.optJSONObject(Track.TrackEntity.USER)
                    .optString(Track.TrackEntity.AVATAR_URL);
        }
        track.setArtworkURL(parseArtworkUrlToBetter(artworkUrl));
        track.setSongURI(jsonTrack.optString(Track.TrackEntity.SONGURI));
        track.setDownloadURL(jsonTrack.optString(Track.TrackEntity.DOWNLOAD_URL));
        track.setDuration(jsonTrack.optInt(Track.TrackEntity.DURATION));
        track.setId(jsonTrack.optInt(Track.TrackEntity.ID));
        track.setDownloadable(jsonTrack.optBoolean(Track.TrackEntity.DOWNLOADABLE));
        track.setTitle(jsonTrack.optString(Track.TrackEntity.TITLE));
        track.setLikeCount(jsonTrack.optInt(Track.TrackEntity.LIKES_COUNT));
        if (jsonUser != null) {
            track.setArtist(jsonUser.optString(Track.TrackEntity.USERNAME));
        }
        return track;
    }
}
