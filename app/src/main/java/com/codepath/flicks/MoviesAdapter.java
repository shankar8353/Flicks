package com.codepath.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by ssunda1 on 5/17/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    static class ViewHolder {
        @BindView(R.id.ivPoster) ImageView ivPoster;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvOverview) TextView tvOverview;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class PopularMovieViewHolder {
        @BindView(R.id.ivPoster) ImageView ivPoster;

        public PopularMovieViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public MoviesAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = getItem(position);
        return movie.isPopular() ? 0 : 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = getItem(position);

        if (movie.isPopular()) {
            PopularMovieViewHolder viewHolder;

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.popular_item_movie, parent, false);
                viewHolder = new PopularMovieViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (PopularMovieViewHolder) convertView.getTag();
            }

            String imageUrl = isLandscape() ? movie.getFullBackdropImageUrl() : movie.getBackdropImageUrl();
            Picasso.with(getContext()).load(imageUrl).
                    error(R.mipmap.flix_launcher).
                    transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.ivPoster);

            // Return the completed view to render on screen
            return convertView;
        }
        else {
            ViewHolder viewHolder;

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            String imageUrl = isLandscape() ? movie.getBackdropImageUrl() : movie.getPosterImageUrl();
            Picasso.with(getContext()).load(imageUrl).
                    error(R.mipmap.flix_launcher).
                    transform(new RoundedCornersTransformation(10, 10)).into(viewHolder.ivPoster);
            viewHolder.tvTitle.setText(movie.getTitle());
            viewHolder.tvOverview.setText(movie.getOverview());

            // Return the completed view to render on screen
            return convertView;
        }
    }

    private boolean isLandscape() {
        return getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
