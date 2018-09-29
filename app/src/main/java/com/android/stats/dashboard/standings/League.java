
package com.android.stats.dashboard.standings;

import java.util.HashMap;
import java.util.Map;

public class League {

    private int id;
    private String name;
    private String link;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
