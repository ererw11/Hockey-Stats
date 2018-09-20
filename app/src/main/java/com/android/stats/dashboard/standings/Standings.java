
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

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
