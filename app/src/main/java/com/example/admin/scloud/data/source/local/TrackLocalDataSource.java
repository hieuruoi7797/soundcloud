package com.example.admin.scloud.data.source.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.example.admin.s_cloud.R;
import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.source.TrackDataSource;
import com.example.admin.scloud.data.source.local.config.TrackDatabaseHelper;

import java.util.ArrayList;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {

    private static final String SORT_ORDER = " ASC";

    private static TrackLocalDataSource sTrackLocalDataSource;
    private Context mContext;
    private TrackDatabaseHelper mTrackDatabaseHelper;

    public static TrackLocalDataSource getInstance(Context context) {
        if (sTrackLocalDataSource == null) {
            sTrackLocalDataSource = new TrackLocalDataSource(context);
        }
        return sTrackLocalDataSource;
    }

    private TrackLocalDataSource(Context context) {
        mContext = context;
        mTrackDatabaseHelper = TrackDatabaseHelper.getInstance(context);
    }

    @Override
    public void getTrackLocal(OnFetchDataListener<Track> listener) {
        ArrayList<Track> tracks = new ArrayList<>();
        String[] projections = new String[]{
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID,
        };

        ContentResolver contentResolver = mContext.getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, projections, null, null,
                MediaStore.Audio.Media.TITLE + SORT_ORDER);

        if (cursor == null) {
            listener.onFectDataFailure(mContext.getString(R.string.msg_fetch_failed));
            return;
        }

        if (!cursor.moveToFirst()) {
            listener.onFectDataSuccess(tracks);
            return;
        }

        int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int filePathColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        int durationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
        int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
        int artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

        do {
            Track track = new Track();
            track.setTitle(cursor.getString(titleColumn));
            track.setSongURI(cursor.getString(filePathColumn));
            track.setDuration(cursor.getInt(durationColumn));
            track.setId(cursor.getInt(idColumn));
            track.setArtist(cursor.getString(artist));
            tracks.add(track);
        } while (cursor.moveToNext());

        listener.onFectDataSuccess(tracks);
    }
}
