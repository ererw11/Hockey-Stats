
package com.android.stats.roster.teamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Split {

    @SerializedName("stat")
    @Expose
    private Stat_ stat;
    @SerializedName("team")
    @Expose
    private Team team;

    public Stat_ getStat() {
        return stat;
    }

    public Team getTeam() {
        return team;
    }

}
