package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

public class ScoreItem {
    @SerializedName("games_played")
    private int games_played;
    @SerializedName("won")
    private int won;
    @SerializedName("draw")
    private int draw;
    @SerializedName("lost")
    private int lost;
    @SerializedName("goals_scored")
    private int goals_scored;
    @SerializedName("goals_against")
    private int goals_against;
    @SerializedName("points")
    private int points;

    public ScoreItem() {
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public void setGoals_scored(int goals_scored) {
        this.goals_scored = goals_scored;
    }

    public int getGoals_against() {
        return goals_against;
    }

    public void setGoals_against(int goals_against) {
        this.goals_against = goals_against;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ScoreItem(int games_played, int won, int draw, int lost, int goals_scored, int goals_against, int points) {
        this.games_played = games_played;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.goals_scored = goals_scored;
        this.goals_against = goals_against;
        this.points = points;
    }
}
