package com.example.footballapp.api;

import com.example.footballapp.models.MatchItem;
import com.example.footballapp.models.MatchResponses;
import com.example.footballapp.models.Responses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<Responses> getNews (
            @Query("category") String category,
            @Query("q") String keyword,
            @Query("apiKey") String apiKey
    );

    @GET("7b2b57cb-0f64-4680-89df-06b39730d506?fbclid=IwAR27Lt1RkEOtXVUF2IWRxgg2V7FwTtTJXbUmDqnZwbXbNtumMpMEXsVTZ80")
    Call<MatchResponses> getFullMatch();

    @GET("7b2b57cb-0f64-4680-89df-06b39730d506?fbclid=IwAR27Lt1RkEOtXVUF2IWRxgg2V7FwTtTJXbUmDqnZwbXbNtumMpMEXsVTZ80")
    Call<MatchItem> getLatestMatch();

    @GET("top-headlines")
    Call<Responses> getNewsSearch(
            @Query("category") String keyword,
            @Query("language") String language,
            @Query("apiKey") String apiKey
    );
}
