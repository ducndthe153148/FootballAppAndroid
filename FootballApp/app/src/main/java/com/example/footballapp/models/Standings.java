package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

public class Standings {
    @SerializedName("standings")
    private ClubResponses data;

    public Standings(ClubResponses data) {
        this.data = data;
    }

    public ClubResponses getData() {
        return data;
    }

    public void setData(ClubResponses data) {
        this.data = data;
    }

    public Standings() {
    }
}
