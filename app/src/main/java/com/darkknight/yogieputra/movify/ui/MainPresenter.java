package com.darkknight.yogieputra.movify.ui;

import android.util.Log;

import com.darkknight.yogieputra.movify.models.MovieResponse;
import com.darkknight.yogieputra.movify.network.NetworkClient;
import com.darkknight.yogieputra.movify.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainPresenterInterface {

    private MainViewInterface mvi;
    private String TAG = MainPresenter.class.getSimpleName();

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }

    @Override
    public void getMovies() {
        getObservable().subscribeWith(getObserver());
    }

    private Observable<MovieResponse> getObservable() {
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovies("004cbaf19212094e32aa9ef6f6577f22")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<MovieResponse> getObserver() {
        return new DisposableObserver<MovieResponse>() {
            @Override
            public void onNext(@NonNull MovieResponse movieResponse) {
                Log.d(TAG, "OnNext" + movieResponse.getTotalResults());
                mvi.displayMovies(movieResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
                mvi.displayError("Error fetching Movie data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }

}
