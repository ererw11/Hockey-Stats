
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

    public void setStreakType(String streakType) {
        this.streakType = streakType;
    }

    public int getStreakNumber() {
        return streakNumber;
    }

    public void setStreakNumber(int streakNumber) {
        this.streakNumber = streakNumber;
    }

    public String getStreakCode() {
        return streakCode;
    }

    public void setStreakCode(String streakCode) {
        this.streakCode = streakCode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
