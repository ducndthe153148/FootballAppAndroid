package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchResponses {
    @SerializedName("match")
    private List<MatchItem> matches;

    public MatchResponses(List<MatchItem> matches) {
        this.matches = matches;
    }

    public MatchResponses() {
    }

    public List<MatchItem> getMatches() {
        return matches;
    }

    public void setMatches(List<MatchItem> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "MatchResponses{" +
                "matches=" + matches +
                '}';
    }
}
