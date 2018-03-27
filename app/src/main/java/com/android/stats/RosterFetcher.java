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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created 3/25/2018.
 */

public class RosterFetcher {

    private static final String TAG = RosterFetcher.class.getSimpleName();

    private static final String ROSTER_BASE_URL =
            "https://statsapi.web.nhl.com/api/v1/teams/";
    private static final String ROSTER_MODIFIER =
            "/roster";

    private final static String R_ROSTER = "roster";
    private final static String R_PERSON = "person";
    private final static String R_ID = "id";
    private final static String R_NAME ="fullName";
    private final static String R_NUMBER = "jerseyNumber";
    private final static String R_POSITION = "position";
    private final static String R_POSITION_NAME = "name";
    private final static String R_POSITION_TYPE = "type";

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

    List<Player> fetchPlayers(String playerId) {

        List<Player> roster = new ArrayList<>();

        try {
            String builtUri = Uri.parse(ROSTER_BASE_URL +
                    playerId +
                    ROSTER_MODIFIER)
                    .buildUpon()
                    .build()
                    .toString();
            String jsonString = getUrlString(builtUri);
            Log.i(TAG, "Received Roster JSON " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            JSONArray teamJson = jsonBody.getJSONArray(R_ROSTER);
            parseRoster(roster, teamJson);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        // Sort the roster by position
        Collections.sort(roster, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getPlayerType().compareToIgnoreCase(o2.getPlayerType());
            }
        });

        return roster;
    }

    private void parseRoster(List<Player> roster, JSONArray jsonBody) throws JSONException {

        for (int i = 0; i < jsonBody.length(); i++) {

            JSONObject currentPlayer = jsonBody.getJSONObject(i);

            Player player = new Player();

            JSONObject personObject = currentPlayer.getJSONObject(R_PERSON);
            player.setPlayerId(personObject.getInt(R_ID));
            player.setPlayerFullName(personObject.getString(R_NAME));

            player.setPlayerNumber(currentPlayer.getInt(R_NUMBER));

            JSONObject positionObject = currentPlayer.getJSONObject(R_POSITION);
            player.setPlayerPosition(positionObject.getString(R_POSITION_NAME));
            player.setPlayerType(positionObject.getString(R_POSITION_TYPE));

            roster.add(player);
        }
    }
}
