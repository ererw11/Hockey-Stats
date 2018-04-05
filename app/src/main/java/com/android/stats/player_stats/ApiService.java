package com.android.stats.player_stats;

import com.android.stats.player_stats.player_details.PlayerDetails;
import com.android.stats.player_stats.stats.PlayerStats;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created 3/26/2018.
 */

public interface ApiService {

    // Get player details
    @GET("https://statsapi.web.nhl.com/api/v1/people/{ID}")
    Call<PlayerDetails> getPlayerDetails(@Path("ID") String playerId);

    // Get player Stat details
    @GET("https://statsapi.web.nhl.com/api/v1/people/{ID}/stats?stats=statsSingleSeason&season=20172018")
    Call<PlayerStats> getPlayerStat(@Path("ID") String playerId);
}
