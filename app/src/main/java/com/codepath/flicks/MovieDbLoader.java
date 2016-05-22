package com.codepath.flicks;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ssunda1 on 5/21/16.
 */
public class MovieDbLoader {

    private Consumer<List<Movie>> consumer;

    public MovieDbLoader(Consumer<List<Movie>> consumer) {
        this.consumer = consumer;
        loadMovies();
    }

    private void loadMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List<Movie> movies = parse(response);
                    consumer.accept(movies);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Movie> parse(JSONObject json) throws JSONException {
        return parse(json.getJSONArray("results"));
    }

    private List<Movie> parse(JSONArray array) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);

            String title = json.getString("title");
            String overview = json.getString("overview");
            String posterPath = json.getString("poster_path");
            if (posterPath != null) {
                posterPath = String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
            }
            String backdropPath = json.getString("backdrop_path");
            String fullbackdropPath = null;
            if (backdropPath != null) {
                backdropPath = String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
                fullbackdropPath = String.format("https://image.tmdb.org/t/p/w1280/%s", backdropPath);
            }
            double voteAverage = json.getDouble("vote_average");

            Movie movie = new Movie();
            movie.setTitle(title);
            movie.setOverview(overview);
            movie.setPosterImageUrl(posterPath);
            movie.setBackdropImageUrl(backdropPath);
            movie.setFullBackdropImageUrl(fullbackdropPath);
            movie.setAvgVote(voteAverage);

            movies.add(movie);
        }
        return movies;
    }


}
