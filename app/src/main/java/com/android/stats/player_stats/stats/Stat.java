package com.android.stats.player_stats.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stat {

    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("mSplits")
    @Expose
    private List<Split> mSplits = null;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Split> getSplits() {
        return mSplits;
    }

    public void setSplits(List<Split> splits) {
        this.mSplits = splits;
    }

}
