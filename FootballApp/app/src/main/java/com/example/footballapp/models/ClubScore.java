package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

public class ClubScore {
    @SerializedName("position")
    private int position;
    @SerializedName("team_name")
    private String team_name;
    @SerializedName("overall")
    private ScoreItem scoreItem;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public ScoreItem getScoreItem() {
        return scoreItem;
    }

    public void setScoreItem(ScoreItem scoreItem) {
        this.scoreItem = scoreItem;
    }

    public ClubScore(int position, String team_name, ScoreItem scoreItem) {
        this.position = position;
        this.team_name = team_name;
        this.scoreItem = scoreItem;
    }

    public ClubScore() {
    }
}
