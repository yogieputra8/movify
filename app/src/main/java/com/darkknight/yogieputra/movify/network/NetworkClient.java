package com.darkknight.yogieputra.movify.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {
    private final static String BASE_API_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit;
    private static Gson gson = new GsonBuilder().create();

    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
    private static OkHttpClient okHttpClient = builder.build();

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }
}
