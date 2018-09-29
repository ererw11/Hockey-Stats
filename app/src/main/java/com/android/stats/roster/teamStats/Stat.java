
package com.android.stats.roster.teamStats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("splits")
    @Expose
    private List<Split> splits = null;

    public Type getType() {
        return type;
    }

    public List<Split> getSplits() {
        return splits;
    }

}
