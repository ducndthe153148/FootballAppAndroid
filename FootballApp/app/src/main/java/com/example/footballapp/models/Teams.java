package com.example.footballapp.models;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Teams {

    @SerializedName("team_name")
    private String team_name;

    @SerializedName("team_image")
    private String team_image;

    @SerializedName("team_member")
    private List<Member> memberList;

    @SerializedName("team_history")
    private String team_history;

    public Teams() {
    }

    public Teams(String team_name, String team_image, List<Member> memberList, String team_history) {
        this.team_name = team_name;
        this.team_image = team_image;
        this.memberList = memberList;
        this.team_history = team_history;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_image() {
        return team_image;
    }

    public void setTeam_image(String team_image) {
        this.team_image = team_image;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public String getTeam_history() {
        return team_history;
    }

    public void setTeam_history(String team_history) {
        this.team_history = team_history;
    }
}
