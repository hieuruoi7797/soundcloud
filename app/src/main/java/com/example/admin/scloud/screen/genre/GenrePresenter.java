package com.example.admin.scloud.screen.genre;

import com.example.admin.scloud.data.repository.GenreRepository;

public class GenrePresenter implements GenreContract.Presenter {
    private GenreContract.View mView;
    private GenreRepository mGenreRepository;

    public GenrePresenter(GenreContract.View view, GenreRepository genreRepository) {
        mView = view;
        mGenreRepository = genreRepository;
    }

    @Override
    public void loadGenres() {
        mView.showGenres(mGenreRepository.getGenres());
    }
}
