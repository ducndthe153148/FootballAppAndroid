package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

public class EachSeason {
    @SerializedName("name")
    private String nameSeason;

    @SerializedName("standings")
    private Standings standings;

    public String getNameSeason() {
        return nameSeason;
    }

    public void setNameSeason(String nameSeason) {
        this.nameSeason = nameSeason;
    }

    public Standings getStandings() {
        return standings;
    }

    public void setStandings(Standings standings) {
        this.standings = standings;
    }

    public EachSeason(String nameSeason, Standings standings) {
        this.nameSeason = nameSeason;
        this.standings = standings;
    }

    public EachSeason() {
    }
}
