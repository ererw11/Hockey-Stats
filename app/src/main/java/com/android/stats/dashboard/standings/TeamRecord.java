
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

    public void setTeam(Team team) {
        this.team = team;
    }

    public LeagueRecord getLeagueRecord() {
        return leagueRecord;
    }

    public void setLeagueRecord(LeagueRecord leagueRecord) {
        this.leagueRecord = leagueRecord;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDivisionRank() {
        return divisionRank;
    }

    public void setDivisionRank(String divisionRank) {
        this.divisionRank = divisionRank;
    }

    public String getConferenceRank() {
        return conferenceRank;
    }

    public void setConferenceRank(String conferenceRank) {
        this.conferenceRank = conferenceRank;
    }

    public String getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(String leagueRank) {
        this.leagueRank = leagueRank;
    }

    public String getWildCardRank() {
        return wildCardRank;
    }

    public void setWildCardRank(String wildCardRank) {
        this.wildCardRank = wildCardRank;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Streak getStreak() {
        return streak;
    }

    public void setStreak(Streak streak) {
        this.streak = streak;
    }

    public String getClinchIndicator() {
        return clinchIndicator;
    }

    public void setClinchIndicator(String clinchIndicator) {
        this.clinchIndicator = clinchIndicator;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
