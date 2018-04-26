package com.android.stats.player_stats.skater_details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerDetails {

    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("people")
    @Expose
    private List<Person> people = null;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

}
