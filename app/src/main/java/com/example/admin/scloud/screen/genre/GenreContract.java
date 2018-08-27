package com.example.admin.scloud.screen.genre;

import com.example.admin.scloud.data.model.Genre;

import java.util.List;

public interface GenreContract {
    interface Presenter {
        void loadGenres();
    }

    interface View {
        void showGenres(List<Genre> genres);
    }
}
