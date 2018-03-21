package com.android.stats;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created 3/18/2018.
 */

public class OpenJsonTeamData {

    public static String[] getSimpleTeamStringFromJson(Context context, String teamJsonString) throws JSONException {

        final String LIST = "list";

        String[] parsedTeamData = null;

        JSONObject teamJson = new JSONObject(teamJsonString);

        JSONArray teamArray = teamJson.getJSONArray(LIST);

        parsedTeamData = new String [teamArray.length()];

        for (int i =0; i < teamArray.length(); i++) {

            // These are the values to retrieve
            String id;
            String teamName;
            String locationName;
            String division;
            String officialSiteUrl;

            JSONObject teamInfo = teamArray.getJSONObject(i);

            // Division is in a child array called division
            // JSONObject divisionObject = teamInfo.getJSONArray("division").getJSONObject(1);

            id = teamJson.getString("id");
            teamName = teamJson.getString("teamName");
            locationName = teamJson.getString("locationName");
            officialSiteUrl = teamJson.getString("officialSiteUrl");

            parsedTeamData[i] = id + "-" + teamName + "-" + locationName + "-" + officialSiteUrl;
        }

        return parsedTeamData;
    }
}
