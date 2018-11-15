package com.darkknight.yogieputra.movify.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.darkknight.yogieputra.movify.R;
import com.darkknight.yogieputra.movify.adapters.MoviesAdapter;
import com.darkknight.yogieputra.movify.models.MovieResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    private String TAG = MainActivity.class.getSimpleName();
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;
//    RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        setupMVP();
        setupViews();
        getMovieList();
    }

    private void getMovieList() {
        mainPresenter.getMovies();
    }

    private void setupViews() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovies(MovieResponse movieResponse) {
        if (movieResponse != null){
            Log.d(TAG, movieResponse.getResults().get(1).getTitle());
            adapter = new MoviesAdapter(MainActivity.this, movieResponse.getResults());
            rvMovies.setAdapter(adapter);
        } else {
            Log.d(TAG, "Movies response null");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }
}
