package com.example.admin.scloud.utils;

import android.Manifest;
import android.os.Environment;

public class ConstantString {
    private ConstantString() {
    }

    public static final String BREAK_LINE = "\n";
    public static final String DOWNLOAD_FILE_PATH =
            Environment.getExternalStorageDirectory().toString() + "/SoundCloud13";
    public static final String[] PERMISSIONS =
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
