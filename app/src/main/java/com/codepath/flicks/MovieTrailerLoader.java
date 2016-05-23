package com.codepath.flicks;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by ssunda1 on 5/23/16.
 */
public class MovieTrailerLoader {

    private Consumer<MovieTrailer> consumer;

    public MovieTrailerLoader(int movieId, Consumer<MovieTrailer> consumer) {
        this.consumer = consumer;
        loadMovieTrailer(movieId);
    }

    private void loadMovieTrailer(int id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = String.format("https://api.themoviedb.org/3/movie/%d/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", id);
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    MovieTrailer trailer = parse(response);
                    consumer.accept(trailer);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private MovieTrailer parse(JSONObject json) throws JSONException {
        return parse(json.getJSONArray("youtube"));
    }

    private MovieTrailer parse(JSONArray array) throws JSONException {
        MovieTrailer trailer = null;
        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);

            String type = json.getString("type");
            if (!"Trailer".equalsIgnoreCase(type)) {
                continue;
            }
            String source = json.getString("source");
            String name = json.getString("name");

            return new MovieTrailer(name, source);
        }
        return trailer;
    }

}
