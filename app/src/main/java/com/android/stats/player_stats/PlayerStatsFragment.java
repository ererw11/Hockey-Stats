package com.android.stats.player_stats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.stats.ApiService;
import com.android.stats.ApiUtils;
import com.android.stats.R;
import com.android.stats.player_stats.skater_details.Person;
import com.android.stats.player_stats.skater_details.PlayerDetails;
import com.android.stats.player_stats.stats.PlayerStats;
import com.android.stats.player_stats.stats.Split;
import com.android.stats.player_stats.stats.Stat;
import com.android.stats.player_stats.stats.Stat_;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerStatsFragment extends Fragment {

    private static final String TAG = PlayerStatsFragment.class.getSimpleName();
    private static final String ARG_PLAYER_ID =
            "com.android.stats.player_stats.player_id";

    @BindView(R.id.player_head_shot)
    ImageView player_head_shot;
    @BindView(R.id.player_games)
    TextView player_games;
    @BindView(R.id.player_goals)
    TextView player_goals;
    @BindView(R.id.player_points)
    TextView player_points;
    @BindView(R.id.player_assist)
    TextView player_assists;
    @BindView(R.id.player_first_name)
    TextView player_first_name;
    @BindView(R.id.player_last_name)
    TextView player_last_name;
    @BindView(R.id.player_number)
    TextView player_number;
    @BindView(R.id.player_team)
    TextView player_team;
    @BindView(R.id.player_position)
    TextView player_position;
    @BindView(R.id.player_stat_one_label)
    TextView player_stat_label_one;
    @BindView(R.id.player_stat_two_label)
    TextView player_stat_label_two;
    @BindView(R.id.player_stat_three_label)
    TextView player_stat_label_three;

    private String playerId;
    private ApiService mApiService;

    public PlayerStatsFragment() {
        // Required empty public constructor
    }

    public static PlayerStatsFragment newInstance(String playerId) {
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_PLAYER_ID, playerId);

        PlayerStatsFragment fragment = new PlayerStatsFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerId = getArguments().getString(ARG_PLAYER_ID);

        mApiService = ApiUtils.getApiService();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_stats, container, false);
        ButterKnife.bind(this, v);

        Picasso.get()
                .load(String.format("%s%s%s", getString(R.string.nhl_head_shot_url), playerId, ".jpg"))
                .placeholder(R.drawable.no_skater_image)
                .into(player_head_shot);
        loadPlayerDetails(playerId);

        loadPlayerStats(playerId);

        return v;
    }

    private void loadPlayerDetails(String playerId) {
        mApiService.getPlayerDetails(playerId).enqueue(new Callback<PlayerDetails>() {
            @Override
            public void onResponse(@NonNull Call<PlayerDetails> call, @NonNull Response<PlayerDetails> response) {
                Log.d(TAG, getString(R.string.player_details_successful));
                bindPlayerDetails(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PlayerDetails> call, @NonNull Throwable t) {

            }
        });
    }

    private void bindPlayerDetails(PlayerDetails playerDetailsResponse) {
        List<Person> persons = playerDetailsResponse.getPeople();
        Person person = persons.get(0);
        Log.d(TAG, person.getFirstName());
        player_first_name.setText(person.getFirstName());
        player_last_name.setText(person.getLastName());
        player_number.setText(person.getPrimaryNumber());
        player_position.setText(person.getPrimaryPosition().getName());
        player_team.setText(person.getCurrentTeam().getName());
    }


    private void loadPlayerStats(String playerId) {
        mApiService.getPlayerStat(playerId).enqueue(new Callback<PlayerStats>() {
            @Override
            public void onResponse(@NonNull Call<PlayerStats> call, @NonNull Response<PlayerStats> response) {
                Log.d(TAG, getString(R.string.player_stats_successful));
                bindPlayerStats(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<PlayerStats> call, @NonNull Throwable t) {

            }
        });
    }

    private void bindPlayerStats(PlayerStats playerStatsResponse) {
        Stat_ playerStats = getStat_(playerStatsResponse);

        if (playerStats.getGoals() != null && playerStats.getAssists() != null) {
            // This player is a skater
            addSkaterStatLabels();
            player_games.setText(playerStats.getGames().toString());
            player_goals.setText(playerStats.getGoals().toString());
            player_assists.setText(playerStats.getAssists().toString());
            int points = playerStats.getGoals() + playerStats.getAssists();
            player_points.setText(Integer.toString(points));
        }

        if (playerStats.getWins() != null && playerStats.getSavePercentage() != null) {
            // This player is a goalie
            addGoalieStatLabels();
            player_games.setText(playerStats.getGames().toString());
            player_goals.setText(playerStats.getWins().toString());
            player_assists.setText(playerStats.getLosses().toString());
            player_points.setText(playerStats.getSavePercentage().toString());

        }
    }

    private void addGoalieStatLabels() {
        player_stat_label_one.setText(R.string.wins);
        player_stat_label_two.setText(R.string.loss);
        player_stat_label_three.setText(R.string.save_percentage);
    }

    private void addSkaterStatLabels() {
        player_stat_label_one.setText(R.string.goals);
        player_stat_label_two.setText(R.string.assists);
        player_stat_label_three.setText(R.string.points);
    }

    private Stat_ getStat_(PlayerStats playerStatsResponse) {
        List<Stat> statsList = playerStatsResponse.getStats();
        List<Split> splits = statsList.get(0).getSplits();
        return splits.get(0).getStat();
    }
}
