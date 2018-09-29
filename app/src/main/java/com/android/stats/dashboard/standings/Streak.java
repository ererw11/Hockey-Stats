
package com.android.stats.dashboard.standings;

import java.util.HashMap;
import java.util.Map;

public class Streak {

    private String streakType;
    private int streakNumber;
    private String streakCode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStreakType() {
        return streakType;
    }

    public int getStreakNumber() {
        return streakNumber;
    }

    public String getStreakCode() {
        return streakCode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
