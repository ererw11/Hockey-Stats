package com.android.stats.player_stats.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerStats {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("mStats")
    @Expose
    private List<Stat> mStats = null;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Stat> getStats() {
        return mStats;
    }

    public void setStats(List<Stat> stats) {
        this.mStats = stats;
    }

}
