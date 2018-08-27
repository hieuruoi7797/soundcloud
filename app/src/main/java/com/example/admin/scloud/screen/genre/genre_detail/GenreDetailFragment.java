package com.example.admin.scloud.screen.genre.genre_detail;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.s_cloud.R;
import com.example.admin.scloud.data.model.Track;
import com.example.admin.scloud.data.repository.TrackRepository;
import com.example.admin.scloud.screen.TrackListener;
import com.example.admin.scloud.utils.ConstantNetwork;
import com.example.admin.scloud.utils.EndlessScrollListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class GenreDetailFragment extends android.support.v4.app.Fragment implements
        GenreDetailContract.View {

    public static final String BUNDLE_GENRE_TYPE = "GENRE_TYPE";
    private GenreDetailContract.Presenter mPresenter;
    private GenreDetailAdapter mGenreDetailAdapter;
    private TrackListener mTrackListener;
    private ProgressBar mProgressLoading;
    private String mGenre;

    @SuppressLint("ValidFragment")
    public GenreDetailFragment() {
        // Required empty public constructor
    }

    public static GenreDetailFragment newInstance(String genre) {
        GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_GENRE_TYPE, genre);
        genreDetailFragment.setArguments(bundle);
        return genreDetailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new GenreDetailPresenter(this,
                TrackRepository.getInstance(getContext()));
        mGenre = getArguments().getString(BUNDLE_GENRE_TYPE);
        setupComponents(view);
        mPresenter.loadTrack(mGenre, ConstantNetwork.LIMIT_DEFAULT, ConstantNetwork.OFFSET_DEFAULT);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TrackListener) {
            mTrackListener = (TrackListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTrackListener = null;
    }

    @Override
    public void showTracks(ArrayList<Track> trackList) {
        mGenreDetailAdapter.updateListTrack(trackList);
    }

    @Override
    public void showNoTracks() {
        Toast.makeText(getContext(), R.string.message_no_track, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingTracksError(String message) {

    }

    @Override
    public void showLoadingIndicator() {
        mProgressLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressLoading.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(GenreDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void setupComponents(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mGenreDetailAdapter = new GenreDetailAdapter(getContext(), mTrackListener);
        RecyclerView trackRecycler = view.findViewById(R.id.recycler_genres_detail);
        mProgressLoading = view.findViewById(R.id.progress_loading);
        trackRecycler.setLayoutManager(linearLayoutManager);
        trackRecycler.setHasFixedSize(true);
        trackRecycler.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL)
        );
        trackRecycler.setAdapter(mGenreDetailAdapter);
        trackRecycler.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            protected void onLoadMore(int offset) {
                mPresenter.loadTrack(mGenre, ConstantNetwork.LIMIT_DEFAULT, offset);
            }
        });
    }
}
