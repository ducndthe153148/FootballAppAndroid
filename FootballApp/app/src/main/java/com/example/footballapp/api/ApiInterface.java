package com.example.footballapp.api;

import com.example.footballapp.models.ClubResponses;
import com.example.footballapp.models.ClubScore;
import com.example.footballapp.models.MatchItem;
import com.example.footballapp.models.MatchResponses;
import com.example.footballapp.models.Responses;
import com.example.footballapp.models.SeasonResponse;

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

    @GET("bd1b18c7-7234-44b3-bf2e-eccfcaa3b588")
    Call<MatchResponses> getFullMatch();

    @GET("5a0b5c45-a49d-4f52-b40f-d2f60f6f8500")
    Call<MatchItem> getLatestMatch();

    @GET("ffef36cc-3c06-4034-a6f4-8fa8676bb59c")
    Call<ClubResponses> getClub();

    @GET("top-headlines")
    Call<Responses> getNewsSearch(
            @Query("category") String keyword,
            @Query("language") String language,
            @Query("apiKey") String apiKey
    );
}
