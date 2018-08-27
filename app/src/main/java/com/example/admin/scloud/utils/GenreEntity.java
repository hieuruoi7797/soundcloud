package com.example.admin.scloud.utils;

import android.support.annotation.StringDef;

/**
 * Created by Hades on 8/8/2018.
 */

@StringDef({
        GenreEntity.ALL_MUSIC,
        GenreEntity.ALL_AUDIO,
        GenreEntity.ALTERNATIVE,
        GenreEntity.AMBIENT,
        GenreEntity.CLASSICAL,
        GenreEntity.COUNTRY }
)

public @interface GenreEntity {

    String ALL_MUSIC = "all-music";
    String ALL_AUDIO = "all-audio";
    String ALTERNATIVE = "alternativerock";
    String AMBIENT = "ambient";
    String CLASSICAL = "classical";
    String COUNTRY = "country";
}
