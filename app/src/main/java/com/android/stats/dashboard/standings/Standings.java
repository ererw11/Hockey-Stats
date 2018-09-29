
package com.android.stats.dashboard.standings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Standings {

    private String copyright;
    private List<Record> records = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCopyright() {
        return copyright;
    }

    public List<Record> getRecords() {
        return records;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
