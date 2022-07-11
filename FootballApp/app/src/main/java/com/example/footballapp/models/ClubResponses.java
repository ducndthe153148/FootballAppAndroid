package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClubResponses {
    @SerializedName("data")
    private List<ClubScore>  clubScores;

    public ClubResponses() {
    }

    public List<ClubScore> getClubScores() {
        return clubScores;
    }

    public void setClubScores(List<ClubScore> clubScores) {
        this.clubScores = clubScores;
    }

    public ClubResponses(List<ClubScore> clubScores) {
        this.clubScores = clubScores;
    }
}
