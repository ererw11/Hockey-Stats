
package com.android.stats.dashboard.standings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Record {

    private String standingsType;
    private League league;
    private Division division;
    private Conference conference;
    private List<TeamRecord> teamRecords = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getStandingsType() {
        return standingsType;
    }

    public League getLeague() {
        return league;
    }

    public Division getDivision() {
        return division;
    }

    public Conference getConference() {
        return conference;
    }

    public List<TeamRecord> getTeamRecords() {
        return teamRecords;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
