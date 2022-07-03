package com.example.footballapp.models;

import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("mem_id")
    private int id;
    @SerializedName("mem_name")
    private String name;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
