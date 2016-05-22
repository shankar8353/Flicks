package com.codepath.flicks;

/**
 * Created by ssunda1 on 5/17/16.
 */
public class Movie {

    private String title;
    private String overview;
    private String posterImageUrl;
    private String backdropImageUrl;
    private String fullBackdropImageUrl;
    private double avgVote;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }

    public String getBackdropImageUrl() {
        return backdropImageUrl;
    }

    public void setBackdropImageUrl(String backdropImageUrl) {
        this.backdropImageUrl = backdropImageUrl;
    }

    public String getFullBackdropImageUrl() {
        return fullBackdropImageUrl;
    }

    public void setFullBackdropImageUrl(String fullBackdropImageUrl) {
        this.fullBackdropImageUrl = fullBackdropImageUrl;
    }

    public double getAvgVote() {
        return avgVote;
    }

    public void setAvgVote(double avgVote) {
        this.avgVote = avgVote;
    }

    public boolean isPopular() {
        return avgVote > 5.0;
    }
}
