package com.android.stats.dashboard.teams;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created 3/19/2018.
 */

class TeamFetcher {

    private static final String TAG = TeamFetcher.class.getSimpleName();

        private static final String TEAMS_URL =
                "https://statsapi.web.nhl.com/api/v1/teams";

    private final static String T_ID = "id";
    private final static String T_TEAM_NAME = "teamName";
    private final static String T_LOCATION_NAME = "locationName";
    private final static String T_WEBSITE = "officialSiteUrl";
    private final static String T_DIVISION = "division";
    private final static String T_DIVISION_NAME = "name";

    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    private String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    List<Team> fetchTeams() {

        List<Team> teams = new ArrayList<>();

        try {
            String builtUri = Uri.parse(TEAMS_URL).buildUpon().build().toString();
            String jsonString = getUrlString(builtUri);
            Log.i(TAG, "Received Team JSON " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            JSONArray teamsJson = jsonBody.getJSONArray("teams");
            parseTeams(teams, teamsJson);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        // Sort the teams by the TeamLocation
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getTeamLocation().compareToIgnoreCase(o2.getTeamLocation());
            }
        });

        return teams;
    }

    private void parseTeams(List<Team> teams, JSONArray jsonBody) throws JSONException {

        for (int i = 0; i < jsonBody.length(); i++) {

            JSONObject currentTeam = jsonBody.getJSONObject(i);

            Team team = new Team();
            team.setTeamId(currentTeam.getInt(T_ID));
            team.setTeamName(currentTeam.getString(T_TEAM_NAME));
            team.setTeamLocation(currentTeam.getString(T_LOCATION_NAME));

            JSONObject divisionObject = currentTeam.getJSONObject(T_DIVISION);
            team.setTeamDivision(divisionObject.getString(T_DIVISION_NAME));

            teams.add(team);
        }
    }
}
