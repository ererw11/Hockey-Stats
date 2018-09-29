
package com.android.stats.roster.teamStats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamStats {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;

    public String getCopyright() {
        return copyright;
    }

    public List<Stat> getStats() {
        return stats;
    }

}
