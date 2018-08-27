package com.example.admin.scloud.data.source.local.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.utils.Constant;

public class TrackDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "track";
    private static TrackDatabaseHelper sTrackDatabaseHelper;

    private static final String SQL_CREATE_TRACK_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " ( "
            + Track.TrackEntity.ID + " INTEGER NOT NULL, "
            + Track.TrackEntity.TITLE + " TEXT, "
            + Track.TrackEntity.SONGURI + " TEXT, "
            + Track.TrackEntity.USERNAME + " TEXT, "
            + Track.TrackEntity.DURATION + " INTEGER, "
            + " PRIMARY KEY( "
            + Track.TrackEntity.ID
            + " )"
            + ");";

    private TrackDatabaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    public static TrackDatabaseHelper getInstance(@NonNull Context context) {
        if (sTrackDatabaseHelper == null) {
            sTrackDatabaseHelper = new TrackDatabaseHelper(context);
        }
        return sTrackDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TRACK_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
