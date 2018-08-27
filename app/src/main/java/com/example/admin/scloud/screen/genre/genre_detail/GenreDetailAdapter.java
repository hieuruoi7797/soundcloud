package com.example.admin.scloud.screen.genre.genre_detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.s_cloud.R;
import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.screen.TrackListener;

import java.util.ArrayList;
import java.util.List;

public class GenreDetailAdapter extends RecyclerView.Adapter<GenreDetailAdapter.GenreDetailViewHolder> {
    private TrackListener mTrackListener;
    private Context mContext;
    private List<Track> mTracks;
    private LayoutInflater mInflater;

    public GenreDetailAdapter(Context context, TrackListener trackListener) {
        mTrackListener = trackListener;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GenreDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_song, viewGroup, false);
        return new GenreDetailViewHolder(view, mTrackListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreDetailViewHolder genreDetailViewHolder, int i) {
        genreDetailViewHolder.bindData(mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    public void updateListTrack(List<Track> tracks) {
        if (tracks == null) return;
        if (mTracks == null) {
            mTracks = new ArrayList<>();
        }
        int startPosition = mTracks.size();
        mTracks.addAll(tracks);
        notifyItemRangeInserted(startPosition, tracks.size());
    }

    public void clearData() {
        if (mTracks != null) {
            mTracks.clear();
        }
        notifyDataSetChanged();
    }

    static class GenreDetailViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImvTrack;
        private TextView mTvTitle;
        private TextView mTvArtist;
        private TextView mTvLikeCount;
        private Track mCurrentTrack;
        private TrackListener mTrackListener;
        private Context mContext;
        private ImageButton mImbDownload;

        public GenreDetailViewHolder(@NonNull View itemView,
                                     TrackListener trackListener) {
            super(itemView);
            this.mTrackListener = trackListener;
            this.mContext = itemView.getContext();
            mImvTrack = itemView.findViewById(R.id.image_song);
            mTvTitle = itemView.findViewById(R.id.text_song_name);
            mTvArtist = itemView.findViewById(R.id.text_artist);
            mTvLikeCount = itemView.findViewById(R.id.text_like_count);
            mImbDownload = itemView.findViewById(R.id.button_download);

            mImbDownload.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_download:
                    mTrackListener.onDownloadTrack(mCurrentTrack);
                    //download
                    break;
                default:
                    break;
            }
        }

        public void bindData(Track track) {
            if (track == null) return;
            mCurrentTrack = track;
            Glide.with(mContext).
                    load(mCurrentTrack.getArtworkURL())
                    .into(mImvTrack);
            mTvTitle.setText(mCurrentTrack.getTitle());
            mTvArtist.setText(mCurrentTrack.getArtist().getName());
            mTvLikeCount.setText(String.valueOf(mCurrentTrack.getLikeCount()));
        }
    }
}
