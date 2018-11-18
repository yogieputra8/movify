package com.darkknight.yogieputra.movify.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darkknight.yogieputra.movify.R;
import com.darkknight.yogieputra.movify.models.Result;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private Context context;
    private List<Result> movieList;
    private static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    public MoviesAdapter(Context context, List<Result> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_movies, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = movieList.get(position);
        holder.tvTitle.setText(result.getTitle());
        holder.tvOverview.setText(result.getOverview());
        holder.tvReleaseDate.setText(result.getReleaseDate());
        Glide.with(context).load(BASE_IMAGE_URL + result.getPosterPath()).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOverview, tvReleaseDate;
        ImageView ivMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverView);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tvReleaseDate);
            ivMovie = (ImageView) itemView.findViewById(R.id.ivMovie);
        }
    }
}
