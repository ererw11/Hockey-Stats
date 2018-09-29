
package com.android.stats.dashboard.standings;

import java.util.HashMap;
import java.util.Map;

public class LeagueRecord {

    private int wins;
    private int losses;
    private int ot;
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getOt() {
        return ot;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
