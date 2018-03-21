package com.android.stats;

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
import java.util.List;

/**
 * Created 3/19/2018.
 */

public class TeamFetcher {

    private static final String TAG = TeamFetcher.class.getSimpleName();

    private static final String TEAMS_URL = "https://statsapi.web.nhl.com/api/v1/teams";

    final static String T_ID = "id";
    final static String T_TEAM_NAME = "teamName";
    final static String T_LOCATION_NAME = "locationName";
    final static String T_WEBSITE = "officialSiteUrl";
    final static String T_DIVISION = "division";
    final static String T_DIVISION_NAME = "name";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
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
            int bytesRead = 0;
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

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<Team> fetchTeams() {
        List<Team> teams = new ArrayList<>();

        try {
            String builtUri = Uri.parse(TEAMS_URL).buildUpon().build().toString();

            String jsonString = getUrlString(builtUri);
            Log.i(TAG, "Received JSON " + jsonString);
            JSONArray jsonBody = new JSONArray(jsonString);

            parseTeams(teams, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }
        return teams;
    }

    private void parseTeams(List<Team> teams, JSONArray jsonBody) throws IOException, JSONException {

        for (int i = 0; i < jsonBody.length(); i++) {

            JSONObject currentTeam = jsonBody.getJSONObject(i);

            Team team = new Team();
            team.setTeamId(currentTeam.getInt(T_ID));
            team.setTeamName(currentTeam.getString(T_TEAM_NAME));
            team.setTeamLocation(currentTeam.getString(T_LOCATION_NAME));
            team.setTeamWebSite(currentTeam.getString(T_WEBSITE));

            teams.add(team);
        }
    }


}
