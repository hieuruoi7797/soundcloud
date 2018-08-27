package com.example.admin.scloud.screen.genre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.s_cloud.R;
import com.example.admin.scloud.data.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolders> {
    private List<Genre> mGenreList;
    private Context mContext;
    private OnGenreSelectedListener mListener;
    private LayoutInflater mInflater;

    public GenreAdapter(Context context, List<Genre> genreList) {
        this.mContext = context;
        this.mGenreList = genreList;
    }

    public Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_genre, viewGroup, false);
        return new ViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders sampleViewHolders, int i) {
        final Genre genre = mGenreList.get(i);
        sampleViewHolders.bindView(genre, mListener);
    }

    @Override
    public int getItemCount() {
        return mGenreList != null ? mGenreList.size() : 0;
    }


    static class ViewHolders extends RecyclerView.ViewHolder {
        private TextView mGenreName;
        private ImageView mGenreImage;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            mGenreImage = itemView.findViewById(R.id.image_genre);
            mGenreName = itemView.findViewById(R.id.text_genre_name);
        }

        public void bindView(final Genre genre,
                             final OnGenreSelectedListener listener) {
            mGenreName.setText(genre.getTitleResource());
            Glide.with(mGenreImage).load(genre.getImagerResource()).into(mGenreImage);
            mGenreImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onGenreSelected(genre.getApiName());
                }
            });
        }
    }

    public void setGenres(List<Genre> genres) {
        this.mGenreList = genres;
        notifyDataSetChanged();
    }

    public void setOnGenreClickListener(OnGenreSelectedListener listener) {
        mListener = listener;
    }

    public interface OnGenreSelectedListener {
        void onGenreSelected(String genre);
    }

}
