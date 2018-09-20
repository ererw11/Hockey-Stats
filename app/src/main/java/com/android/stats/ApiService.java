package com.android.stats;

import com.android.stats.dashboard.standings.Standings;
import com.android.stats.dashboard.team.Team;
import com.android.stats.player_stats.skater_details.PlayerDetails;
import com.android.stats.player_stats.stats.PlayerStats;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    // Get teams
    @GET("https://statsapi.web.nhl.com/api/v1/teams")
    Call<Team> getTeams();

    // Get Standings
    @GET("https://statsapi.web.nhl.com/api/v1/standings/regularSeason")
    Call<Standings> getStandings();

    // Get player details
    @GET("https://statsapi.web.nhl.com/api/v1/people/{ID}")
    Call<PlayerDetails> getPlayerDetails(@Path("ID") String playerId);

    // Get player Stat details
    @GET("https://statsapi.web.nhl.com/api/v1/people/{ID}/stats?stats=statsSingleSeason&season=20182019")
    Call<PlayerStats> getPlayerStat(@Path("ID") String playerId);
}
