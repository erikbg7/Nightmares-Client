package com.example.nightmares.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by erikb on 1/14/2019.
 */

public class ScoreTemplate {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("score")
    @Expose
    private int score;

    public ScoreTemplate(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
