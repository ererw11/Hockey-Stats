
package com.android.stats.roster.teamStats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat_ {

    @SerializedName("gamesPlayed")
    @Expose
    private Integer gamesPlayed;
    @SerializedName("wins")
    @Expose
    private String wins;
    @SerializedName("losses")
    @Expose
    private String losses;
    @SerializedName("ot")
    @Expose
    private String ot;
    @SerializedName("pts")
    @Expose
    private String pts;
    @SerializedName("ptPctg")
    @Expose
    private String ptPctg;
    @SerializedName("goalsPerGame")
    @Expose
    private String goalsPerGame;
    @SerializedName("goalsAgainstPerGame")
    @Expose
    private String goalsAgainstPerGame;
    @SerializedName("evGGARatio")
    @Expose
    private String evGGARatio;
    @SerializedName("powerPlayPercentage")
    @Expose
    private String powerPlayPercentage;
    @SerializedName("powerPlayGoals")
    @Expose
    private String powerPlayGoals;
    @SerializedName("powerPlayGoalsAgainst")
    @Expose
    private String powerPlayGoalsAgainst;
    @SerializedName("powerPlayOpportunities")
    @Expose
    private String powerPlayOpportunities;
    @SerializedName("penaltyKillPercentage")
    @Expose
    private String penaltyKillPercentage;
    @SerializedName("shotsPerGame")
    @Expose
    private String shotsPerGame;
    @SerializedName("shotsAllowed")
    @Expose
    private String shotsAllowed;
    @SerializedName("winScoreFirst")
    @Expose
    private String winScoreFirst;
    @SerializedName("winOppScoreFirst")
    @Expose
    private String winOppScoreFirst;
    @SerializedName("winLeadFirstPer")
    @Expose
    private String winLeadFirstPer;
    @SerializedName("winLeadSecondPer")
    @Expose
    private String winLeadSecondPer;
    @SerializedName("winOutshootOpp")
    @Expose
    private String winOutshootOpp;
    @SerializedName("winOutshotByOpp")
    @Expose
    private String winOutshotByOpp;
    @SerializedName("faceOffsTaken")
    @Expose
    private String faceOffsTaken;
    @SerializedName("faceOffsWon")
    @Expose
    private String faceOffsWon;
    @SerializedName("faceOffsLost")
    @Expose
    private String faceOffsLost;
    @SerializedName("faceOffWinPercentage")
    @Expose
    private String faceOffWinPercentage;
    @SerializedName("shootingPctg")
    @Expose
    private Double shootingPctg;
    @SerializedName("savePctg")
    @Expose
    private Double savePctg;
    @SerializedName("penaltyKillOpportunities")
    @Expose
    private String penaltyKillOpportunities;
    @SerializedName("savePctRank")
    @Expose
    private String savePctRank;
    @SerializedName("shootingPctRank")
    @Expose
    private String shootingPctRank;

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public String getWins() {
        return wins;
    }

    public String getLosses() {
        return losses;
    }

    public String getOt() {
        return ot;
    }

    public String getPts() {
        return pts;
    }

    public String getPtPctg() {
        return ptPctg;
    }

    public String getGoalsPerGame() {
        return goalsPerGame;
    }

    public String getGoalsAgainstPerGame() {
        return goalsAgainstPerGame;
    }

    public String getEvGGARatio() {
        return evGGARatio;
    }

    public String getPowerPlayPercentage() {
        return powerPlayPercentage;
    }

    public String getPowerPlayGoals() {
        return powerPlayGoals;
    }

    public String getPowerPlayGoalsAgainst() {
        return powerPlayGoalsAgainst;
    }

    public String getPowerPlayOpportunities() {
        return powerPlayOpportunities;
    }

    public String getPenaltyKillPercentage() {
        return penaltyKillPercentage;
    }

    public String getShotsPerGame() {
        return shotsPerGame;
    }

    public String getShotsAllowed() {
        return shotsAllowed;
    }

    public String getWinScoreFirst() {
        return winScoreFirst;
    }

    public String getWinOppScoreFirst() {
        return winOppScoreFirst;
    }


    public String getWinLeadFirstPer() {
        return winLeadFirstPer;
    }

    public String getWinLeadSecondPer() {
        return winLeadSecondPer;
    }

    public String getWinOutshootOpp() {
        return winOutshootOpp;
    }

    public String getWinOutshotByOpp() {
        return winOutshotByOpp;
    }

    public String getFaceOffsTaken() {
        return faceOffsTaken;
    }

    public String getFaceOffsWon() {
        return faceOffsWon;
    }

    public String getFaceOffsLost() {
        return faceOffsLost;
    }

    public String getFaceOffWinPercentage() {
        return faceOffWinPercentage;
    }

    public Double getShootingPctg() {
        return shootingPctg;
    }

    public Double getSavePctg() {
        return savePctg;
    }

    public String getPenaltyKillOpportunities() {
        return penaltyKillOpportunities;
    }

    public String getSavePctRank() {
        return savePctRank;
    }

    public String getShootingPctRank() {
        return shootingPctRank;
    }

}
