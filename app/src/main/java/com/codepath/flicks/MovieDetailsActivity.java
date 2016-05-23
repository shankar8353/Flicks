package com.codepath.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ivPoster) ImageView ivPoster;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.imButton) ImageButton imButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        final Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        String imageUrl = movie.getBackdropImageUrl();
        Picasso.with(this).load(imageUrl).
                error(R.mipmap.flix_launcher).
                transform(new RoundedCornersTransformation(10, 10)).into(ivPoster);
        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        ratingBar.setRating((float)movie.getAvgVote());
        tvOverview.setText(movie.getOverview());

        imButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), QuickPlayActivity.class);
                i.putExtra("movie", movie);
                v.getContext().startActivity(i);
            }
        });
    }

}
