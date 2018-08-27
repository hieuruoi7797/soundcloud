package com.example.admin.scloud.utils;

public class Constant {

    private Constant() {
    }

    // Int
    public static final int MAX_SEEK_BAR = 100;

    // Bundle
    public static final String ARGUMENT_TRACK_LIST_LISTENER = "ARGUMENT_TRACK_LIST_LISTENER";
    public static final String ARGUMENT_NEXT_UP_LISTENER = "ARGUMENT_NEXT_UP_LISTENER";
    public static final String ARGUMENT_CURRENT_TRACK_POSITION = "ARGUMENT_CURRENT_TRACK_POSITION";

    // SQL
    public static final String DATABASE_NAME = "MySoundCloud.db";
    public static final int DATABASE_VERSION = 1;

    // Action
    public static final String ACTION_ADD_TRACK_TO_PLAYLIST = "ACTION_ADD_TRACK_TO_PLAYLIST";

    public class ConstantIntent {
        public static final String EXTRA_ID_SONG_ADD_TO_ALBUM =
                "com.example.admin.scloud.extra_id_song_to_album";
        public static final String ACTION_ID_SONG_ADD_TO_ALBUM =
                "com.example.admin.scloud.action_id_song_to_album";
        public static final String EXTRA_ALBUM = "com.framgia.music5.extra_album";
        public static final String EXTRA_NAME_ALBUM_DETAIL =
                "com.example.admin.scloud.extra_name_album_detail";
        public static final String ACTION_ID_ALBUM_DETAIL =
                "com.example.admin.scloud.action_id_album_detail";
        public static final String ACTION_INIT_SONG_SERVICE =
                "com.example.admin.scloud.action_init_song_service";
        public static final String EXTRA_INIT_SONG_SERVICE =
                "com.example.admin.scloud.extra_init_song_service";
        public static final String EXTRA_INIT_POSITION_SONG_SERVICE =
                "com.example.admin.scloud.extra_init_position_song_service";
    }

    public class ConstantBroadcast {
        public static final String ACTION_STATE_MEDIA = "com.example.admin.scloud.action_state_media";
        public static final String EXTRA_STATE_MEDIA = "com.example.admin.scloud.extra_state_media";
    }

    /**
     * Constant Shared Preference
     */

    public class ConstantSharePrefs {
        public static final String PREF_SHUFFLE_MEDIA = "com.example.admin.scloud.pref_shuffle_media";
        public static final String PREF_REPEAT_MEDIA = "com.example.admin.scloud.pref_repeat_media";
    }
}
