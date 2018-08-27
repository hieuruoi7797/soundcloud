package com.example.admin.scloud.screen.genre;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.s_cloud.R;
import com.example.admin.scloud.data.model.Genre;
import com.example.admin.scloud.data.repository.GenreRepository;
import com.example.admin.scloud.data.source.local.GenresLocalDataSource;
import com.example.admin.scloud.screen.genre.genre_detail.GenreDetailFragment;

import java.util.List;

public class GenreFragment extends Fragment implements GenreContract.View,
        GenreAdapter.OnGenreSelectedListener {
    private Context mContext;
    private GenreContract.Presenter mPresenter;
    private static final int AUDIO_POSITION = 0;
    private static final int LARGER = 2;
    private static final int SMALLER = 1;
    private RecyclerView mRecyclerGenres;
    public GenreAdapter mGenreAdapter;

    public GenreFragment() {
    }

    public GenreFragment newInstance() {
        GenreFragment genreFragment = new GenreFragment();
        return genreFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        mContext = getContext();
        mRecyclerGenres = view.findViewById(R.id.recycler_genres);
        setupUI(container);
        mPresenter = new GenrePresenter(this,
                GenreRepository.getInstance(new GenresLocalDataSource()));
        mPresenter.loadGenres();
        return view;
    }

    private void setupUI(ViewGroup container) {
        GridLayoutManager mGridLayoutManager;
        mGridLayoutManager = new GridLayoutManager(getContext(), 2,
                GridLayoutManager.VERTICAL, false);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == AUDIO_POSITION ? LARGER : SMALLER;
            }
        });
        mRecyclerGenres.setLayoutManager(mGridLayoutManager);
        mGenreAdapter = new GenreAdapter(container.getContext(), null);
        mRecyclerGenres.setAdapter(mGenreAdapter);
        mGenreAdapter.setOnGenreClickListener(this);
    }

    @Override
    public void onGenreSelected(String genre) {
        gotoDetailFragment(genre);
    }
    @Override
    public void showGenres(List<Genre> genres) {
        mGenreAdapter.setGenres(genres);
    }

    private void gotoDetailFragment(String genre) {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_genre, GenreDetailFragment.newInstance(genre))
                .addToBackStack(null)
                .commit();
    }
}
