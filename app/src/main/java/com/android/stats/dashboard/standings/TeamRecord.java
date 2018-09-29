
package com.android.stats.dashboard.standings;

import java.util.HashMap;
import java.util.Map;

public class TeamRecord {

    private Team team;
    private LeagueRecord leagueRecord;
    private int goalsAgainst;
    private int goalsScored;
    private int points;
    private String divisionRank;
    private String conferenceRank;
    private String leagueRank;
    private String wildCardRank;
    private int row;
    private int gamesPlayed;
    private Streak streak;
    private String clinchIndicator;
    private String lastUpdated;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Team getTeam() {
        return team;
    }

    public LeagueRecord getLeagueRecord() {
        return leagueRecord;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getPoints() {
        return points;
    }

    public String getDivisionRank() {
        return divisionRank;
    }

    public String getConferenceRank() {
        return conferenceRank;
    }

    public String getLeagueRank() {
        return leagueRank;
    }

    public String getWildCardRank() {
        return wildCardRank;
    }

    public int getRow() {
        return row;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public Streak getStreak() {
        return streak;
    }

    public String getClinchIndicator() {
        return clinchIndicator;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
