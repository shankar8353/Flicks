package com.codepath.flicks;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements Consumer<List<Movie>> {

    private List<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ActionBar menu = getSupportActionBar();
        menu.setLogo(R.mipmap.flix_launcher);
        menu.setDisplayUseLogoEnabled(true);

        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);
        moviesAdapter = new MoviesAdapter(this, movies);

        if (lvMovies != null) {
            // Link adapter to listView
            lvMovies.setAdapter(moviesAdapter);
        }

        // Start loading the movies
        new MovieDbLoader(this);
    }

    @Override
    public void accept(List<Movie> movies) {
        this.movies.addAll(movies);
        moviesAdapter.notifyDataSetChanged();
    }
}
