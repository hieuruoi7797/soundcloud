package com.example.admin.scloud.utils;


import android.annotation.SuppressLint;

import com.example.admin.s_cloud.BuildConfig;

public class StringUtil {
    @SuppressLint("DefaultLocale")
    public static String convertUrlFetchMusicGenre(String genre, int limit, int offset) {
        return String.format("%s%s%s&%s=%s&%s=%d&%s=%d", ConstantNetwork.BASE_URL,
                ConstantNetwork.PARA_MUSIC_GENRE, genre, ConstantNetwork.CLIENT_ID,
                BuildConfig.API_KEY, ConstantNetwork.LIMIT, limit,
                ConstantNetwork.PARA_OFFSET, offset);
    }

    public static String getUrlStreamTrack(String uriTrack) {
        return String.format("%s/%s?%s=%s", uriTrack, ConstantNetwork.PARA_STREAM,
                ConstantNetwork.CLIENT_ID, BuildConfig.API_KEY);
    }

    public static String convertUrlDownloadTrack(String url) {
        return String.format("%s?%s=%s", url, ConstantNetwork.CLIENT_ID, BuildConfig.API_KEY);
    }

    @SuppressLint("DefaultLocale")
    public static String convertUrlSearchTrack(String trackName, int limit, int offset) {
        return String.format("%s%s%s&%s=%d&%s=%d&%s=%s", ConstantNetwork.BASE_URL,
                ConstantNetwork.PARA_SEARCH_TRACK, trackName, ConstantNetwork.LIMIT, limit,
                ConstantNetwork.PARA_OFFSET, offset, ConstantNetwork.CLIENT_ID,
                BuildConfig.API_KEY);
    }
}
