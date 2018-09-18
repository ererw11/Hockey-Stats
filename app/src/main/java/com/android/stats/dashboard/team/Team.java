
package com.android.stats.dashboard.team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {

    private String copyright;
    private List<Team_> teams = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Team_> getTeams() {
        return teams;
    }

    public void setTeams(List<Team_> teams) {
        this.teams = teams;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
