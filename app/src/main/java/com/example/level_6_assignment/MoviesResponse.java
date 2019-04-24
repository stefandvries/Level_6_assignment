package com.example.level_6_assignment;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesResponse {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResults;

    @SerializedName("results")
    @Expose
    private List<Movie> movies;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getPage() {
        return page;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public List<Movie> getMovies() {
        return movies;
    }


    public int getTotalPages() {
        return totalPages;
    }

}
