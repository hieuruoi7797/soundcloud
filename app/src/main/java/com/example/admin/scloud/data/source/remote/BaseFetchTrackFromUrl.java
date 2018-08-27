package com.example.admin.scloud.data.source.remote;

import android.os.AsyncTask;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.source.TrackDataSource;
import com.example.admin.scloud.utils.ConstantNetwork;
import com.example.admin.scloud.utils.ConstantString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFetchTrackFromUrl extends AsyncTask<String, Void, List<Track>> {
    protected TrackDataSource.OnFetchDataListener<Track> mListener;
    protected Exception mException;

    protected BaseFetchTrackFromUrl(TrackDataSource.OnFetchDataListener<Track> listener) {
        mListener = listener;
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        try {
            JSONObject jsonObject = new JSONObject(getJSONStringFromURL(strings[0]));
            return getTracksFromJsonObject(jsonObject);
        } catch (JSONException e) {
            mException = e;
        } catch (IOException e) {
            mException = e;
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<Track> tracks) {
        if (mListener == null) {
            return;
        }
        if (mException != null) {
            mListener.onFectDataFailure(mException.getMessage());
            return;
        }
        mListener.onFectDataSuccess(tracks);
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

    private String getJSONStringFromURL(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod(ConstantNetwork.REQUEST_METHOD_GET);
        httpURLConnection.setReadTimeout(ConstantNetwork.READ_TIME_OUT);
        httpURLConnection.setConnectTimeout(ConstantNetwork.CONNECT_TIME_OUT);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(ConstantString.BREAK_LINE);
        }
        br.close();
        httpURLConnection.disconnect();
        return sb.toString();
    }

    protected String parseArtworkUrlToBetter(String artworkUrl) {
        if (artworkUrl != null) {
            return artworkUrl.replace(Track.TrackEntity.LARGE_IMAGE_SIZE,
                    Track.TrackEntity.BETTER_IMAGE_SIZE);
        }
        return null;
    }

    protected Track parseJsonObjectToTrackObject(JSONObject jsonTrack) {
        Track track = new Track();
        JSONObject trackJSON = jsonTrack.optJSONObject(Track.TrackEntity.TRACK);
        JSONObject jsonUser = trackJSON.optJSONObject(Track.TrackEntity.USER);
        String artworkUrl = trackJSON.optString(Track.TrackEntity.ARTWORK_URL);
        // Null artwork is replaced by user's avatar
        if (artworkUrl.equals(ConstantNetwork.NULL_RESULT)) {
            artworkUrl = trackJSON.optJSONObject(Track.TrackEntity.USER)
                    .optString(Track.TrackEntity.AVATAR_URL);
        }
        track.setArtworkURL(parseArtworkUrlToBetter(artworkUrl));
        track.setDownloadable(trackJSON.optBoolean(Track.TrackEntity.DOWNLOADABLE));
        track.setDownloadURL(trackJSON.optString(Track.TrackEntity.DOWNLOAD_URL));
        track.setDuration(trackJSON.optInt(Track.TrackEntity.DURATION));
        track.setId(trackJSON.optInt(Track.TrackEntity.ID));
        track.setSongURI(trackJSON.optString(Track.TrackEntity.SONGURI));
        track.setTitle(trackJSON.optString(Track.TrackEntity.TITLE));
        track.setLikeCount(trackJSON.optInt(Track.TrackEntity.LIKES_COUNT));

        if (jsonUser != null) {
            track.setArtist(jsonUser.optString(Track.TrackEntity.USERNAME));
        }
        return track;
    }


}
