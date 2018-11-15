package com.darkknight.yogieputra.movify.ui;

import com.darkknight.yogieputra.movify.models.MovieResponse;

public interface MainViewInterface {

    void showToast(String s);
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);

}
