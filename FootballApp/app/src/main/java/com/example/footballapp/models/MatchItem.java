package com.example.footballapp.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchItem {
    @SerializedName("home_team")
    private Teams team_football_1;

    @SerializedName("away_team")
    private Teams team_football_;

    @SerializedName("match_stadium")
    private String team_stadium;

    @SerializedName("match_time")
    private String time_start;

    @SerializedName("image_back")
    private String image_back;

    public MatchItem() {
    }

    public MatchItem(Teams team_football_1, Teams team_football_, String team_stadium, String time_start, String image_back) {
        this.team_football_1 = team_football_1;
        this.team_football_ = team_football_;
        this.team_stadium = team_stadium;
        this.time_start = time_start;
        this.image_back = image_back;
    }

    public Teams getTeam_football_1() {
        return team_football_1;
    }

    public void setTeam_football_1(Teams team_football_1) {
        this.team_football_1 = team_football_1;
    }

    public Teams getTeam_football_() {
        return team_football_;
    }

    public void setTeam_football_(Teams team_football_) {
        this.team_football_ = team_football_;
    }

    public String getTeam_stadium() {
        return team_stadium;
    }

    public void setTeam_stadium(String team_stadium) {
        this.team_stadium = team_stadium;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getImage_back() {
        return image_back;
    }

    public void setImage_back(String image_back) {
        this.image_back = image_back;
    }
}
