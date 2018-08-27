package com.example.admin.scloud.data.source.remote;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.utils.StringUtil;

public class TrackDownloadManager {
    private static final String FILE_EXTENTION = ".mp3";
    private Context mContext;
    private DownloadListener mListener;

    public TrackDownloadManager(Context context, DownloadListener listener) {
        mListener = listener;
        mContext = context;
    }

    public void downloadTrack(Track track) {
        if (mListener == null) return;
        if (track == null || mContext == null || track.getDownloadURL() == null
                || !track.isDownloadable()) {
            mListener.onDownloadError();
            return;
        }
        DownloadManager downloadManager =
                (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(StringUtil.convertUrlDownloadTrack(track.getDownloadURL())));
        request.setTitle(track.getTitle());
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                track.getTitle() + track.getId() + FILE_EXTENTION);

        downloadManager.enqueue(request);

        mListener.onDownloading();
    }

    public interface DownloadListener {
        void onDownloadError();

        void onDownloading();

        void onDownloadSuccess();
    }
}
