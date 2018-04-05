package com.android.stats;

/**
 * Created 3/19/2018.
 */

class Team {

    private int TeamId;
    private String TeamName;
    private String TeamLocation;
    private String TeamDivision;

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getTeamLocation() {
        return TeamLocation;
    }

    public void setTeamLocation(String teamLocation) {
        TeamLocation = teamLocation;
    }

    public String getTeamDivision() {
        return TeamDivision;
    }

    public void setTeamDivision(String teamDivision) {
        TeamDivision = teamDivision;
    }
}
