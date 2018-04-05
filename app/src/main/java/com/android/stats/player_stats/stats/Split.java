package com.android.stats.player_stats.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Split {

    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("stat")
    @Expose
    private Stat_ stat;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Stat_ getStat() {
        return stat;
    }

    public void setStat(Stat_ stat) {
        this.stat = stat;
    }

}
