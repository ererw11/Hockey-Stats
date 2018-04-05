package com.android.stats.player_stats.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat_ {

    @SerializedName("timeOnIce")
    @Expose
    private String timeOnIce;
    @SerializedName("assists")
    @Expose
    private Integer assists;
    @SerializedName("goals")
    @Expose
    private Integer goals;
    @SerializedName("pim")
    @Expose
    private Integer pim;
    @SerializedName("shots")
    @Expose
    private Integer shots;
    @SerializedName("games")
    @Expose
    private Integer games;
    @SerializedName("hits")
    @Expose
    private Integer hits;
    @SerializedName("powerPlayGoals")
    @Expose
    private Integer powerPlayGoals;
    @SerializedName("powerPlayPoints")
    @Expose
    private Integer powerPlayPoints;
    @SerializedName("powerPlayTimeOnIce")
    @Expose
    private String powerPlayTimeOnIce;
    @SerializedName("evenTimeOnIce")
    @Expose
    private String evenTimeOnIce;
    @SerializedName("penaltyMinutes")
    @Expose
    private String penaltyMinutes;
    @SerializedName("faceOffPct")
    @Expose
    private Double faceOffPct;
    @SerializedName("shotPct")
    @Expose
    private Double shotPct;
    @SerializedName("gameWinningGoals")
    @Expose
    private Integer gameWinningGoals;
    @SerializedName("overTimeGoals")
    @Expose
    private Integer overTimeGoals;
    @SerializedName("shortHandedGoals")
    @Expose
    private Integer shortHandedGoals;
    @SerializedName("shortHandedPoints")
    @Expose
    private Integer shortHandedPoints;
    @SerializedName("shortHandedTimeOnIce")
    @Expose
    private String shortHandedTimeOnIce;
    @SerializedName("blocked")
    @Expose
    private Integer blocked;
    @SerializedName("plusMinus")
    @Expose
    private Integer plusMinus;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("shifts")
    @Expose
    private Integer shifts;
    @SerializedName("timeOnIcePerGame")
    @Expose
    private String timeOnIcePerGame;
    @SerializedName("evenTimeOnIcePerGame")
    @Expose
    private String evenTimeOnIcePerGame;
    @SerializedName("shortHandedTimeOnIcePerGame")
    @Expose
    private String shortHandedTimeOnIcePerGame;
    @SerializedName("powerPlayTimeOnIcePerGame")
    @Expose
    private String powerPlayTimeOnIcePerGame;

    public String getTimeOnIce() {
        return timeOnIce;
    }

    public void setTimeOnIce(String timeOnIce) {
        this.timeOnIce = timeOnIce;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getPim() {
        return pim;
    }

    public void setPim(Integer pim) {
        this.pim = pim;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getPowerPlayGoals() {
        return powerPlayGoals;
    }

    public void setPowerPlayGoals(Integer powerPlayGoals) {
        this.powerPlayGoals = powerPlayGoals;
    }

    public Integer getPowerPlayPoints() {
        return powerPlayPoints;
    }

    public void setPowerPlayPoints(Integer powerPlayPoints) {
        this.powerPlayPoints = powerPlayPoints;
    }

    public String getPowerPlayTimeOnIce() {
        return powerPlayTimeOnIce;
    }

    public void setPowerPlayTimeOnIce(String powerPlayTimeOnIce) {
        this.powerPlayTimeOnIce = powerPlayTimeOnIce;
    }

    public String getEvenTimeOnIce() {
        return evenTimeOnIce;
    }

    public void setEvenTimeOnIce(String evenTimeOnIce) {
        this.evenTimeOnIce = evenTimeOnIce;
    }

    public String getPenaltyMinutes() {
        return penaltyMinutes;
    }

    public void setPenaltyMinutes(String penaltyMinutes) {
        this.penaltyMinutes = penaltyMinutes;
    }

    public Double getFaceOffPct() {
        return faceOffPct;
    }

    public void setFaceOffPct(Double faceOffPct) {
        this.faceOffPct = faceOffPct;
    }

    public Double getShotPct() {
        return shotPct;
    }

    public void setShotPct(Double shotPct) {
        this.shotPct = shotPct;
    }

    public Integer getGameWinningGoals() {
        return gameWinningGoals;
    }

    public void setGameWinningGoals(Integer gameWinningGoals) {
        this.gameWinningGoals = gameWinningGoals;
    }

    public Integer getOverTimeGoals() {
        return overTimeGoals;
    }

    public void setOverTimeGoals(Integer overTimeGoals) {
        this.overTimeGoals = overTimeGoals;
    }

    public Integer getShortHandedGoals() {
        return shortHandedGoals;
    }

    public void setShortHandedGoals(Integer shortHandedGoals) {
        this.shortHandedGoals = shortHandedGoals;
    }

    public Integer getShortHandedPoints() {
        return shortHandedPoints;
    }

    public void setShortHandedPoints(Integer shortHandedPoints) {
        this.shortHandedPoints = shortHandedPoints;
    }

    public String getShortHandedTimeOnIce() {
        return shortHandedTimeOnIce;
    }

    public void setShortHandedTimeOnIce(String shortHandedTimeOnIce) {
        this.shortHandedTimeOnIce = shortHandedTimeOnIce;
    }

    public Integer getBlocked() {
        return blocked;
    }

    public void setBlocked(Integer blocked) {
        this.blocked = blocked;
    }

    public Integer getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Integer plusMinus) {
        this.plusMinus = plusMinus;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getShifts() {
        return shifts;
    }

    public void setShifts(Integer shifts) {
        this.shifts = shifts;
    }

    public String getTimeOnIcePerGame() {
        return timeOnIcePerGame;
    }

    public void setTimeOnIcePerGame(String timeOnIcePerGame) {
        this.timeOnIcePerGame = timeOnIcePerGame;
    }

    public String getEvenTimeOnIcePerGame() {
        return evenTimeOnIcePerGame;
    }

    public void setEvenTimeOnIcePerGame(String evenTimeOnIcePerGame) {
        this.evenTimeOnIcePerGame = evenTimeOnIcePerGame;
    }

    public String getShortHandedTimeOnIcePerGame() {
        return shortHandedTimeOnIcePerGame;
    }

    public void setShortHandedTimeOnIcePerGame(String shortHandedTimeOnIcePerGame) {
        this.shortHandedTimeOnIcePerGame = shortHandedTimeOnIcePerGame;
    }

    public String getPowerPlayTimeOnIcePerGame() {
        return powerPlayTimeOnIcePerGame;
    }

    public void setPowerPlayTimeOnIcePerGame(String powerPlayTimeOnIcePerGame) {
        this.powerPlayTimeOnIcePerGame = powerPlayTimeOnIcePerGame;
    }

}
