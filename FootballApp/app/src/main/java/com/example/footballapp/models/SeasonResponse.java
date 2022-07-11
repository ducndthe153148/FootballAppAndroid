package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeasonResponse {
    @SerializedName("data")
    private List<EachSeason> list;

    public SeasonResponse() {
    }

    public List<EachSeason> getList() {
        return list;
    }

    public void setList(List<EachSeason> list) {
        this.list = list;
    }

    public SeasonResponse(List<EachSeason> list) {
        this.list = list;
    }
}
