package com.android.stats.player_stats;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerStatsFragment extends Fragment {

    private static final String TAG = PlayerStatsFragment.class.getSimpleName();
    private static final String ARG_PLAYER_ID =
            "com.android.stats.player_stats.player_id";

    private ImageView player_head_shot;
    private TextView player_games;
    private TextView player_first_name;
    private TextView player_last_name;
    private TextView player_number;
    private TextView player_team;
    private TextView player_position;

    private TextView player_stat_label_one;
    private TextView player_stat_label_two;
    private TextView player_stat_label_three;
    private TextView player_stat_label_four;
    private TextView player_stat_label_five;
    private TextView player_stat_label_six;
    private TextView player_stat_label_seven;
    private TextView player_stat_label_eight;
    private TextView player_stat_label_nine;
    private TextView player_stat_label_ten;
    private TextView player_stat_label_eleven;

    private TextView player_stats_one;
    private TextView player_stats_two;
    private TextView player_stats_three;
    private TextView player_stats_four;
    private TextView player_stats_five;
    private TextView player_stats_six;
    private TextView player_stats_seven;
    private TextView player_stats_eight;
    private TextView player_stats_nine;
    private TextView player_stats_ten;
    private TextView player_stats_eleven;

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
        playerId = Objects.requireNonNull(getArguments()).getString(ARG_PLAYER_ID);

        mApiService = ApiUtils.getApiService();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_stats, container, false);

        player_head_shot = v.findViewById(R.id.player_head_shot);
        player_first_name = v.findViewById(R.id.player_first_name);
        player_last_name = v.findViewById(R.id.player_last_name);
        player_number = v.findViewById(R.id.player_number);
        player_team = v.findViewById(R.id.player_team);
        player_position = v.findViewById(R.id.player_position);

        player_games = v.findViewById(R.id.player_games);
        player_stats_one = v.findViewById(R.id.player_stat_one);
        player_stats_two = v.findViewById(R.id.player_stat_two);
        player_stats_three = v.findViewById(R.id.player_stat_three);
        player_stats_four = v.findViewById(R.id.player_stat_four);
        player_stats_five = v.findViewById(R.id.player_stat_five);
        player_stats_six = v.findViewById(R.id.player_stat_six);
        player_stats_seven = v.findViewById(R.id.player_stat_seven);
        player_stats_eight = v.findViewById(R.id.player_stat_eight);
        player_stats_nine = v.findViewById(R.id.player_stat_nine);
        player_stats_ten = v.findViewById(R.id.player_stat_ten);
        player_stats_eleven = v.findViewById(R.id.player_stat_eleven);

        player_stat_label_one = v.findViewById(R.id.player_stat_one_label);
        player_stat_label_two = v.findViewById(R.id.player_stat_two_label);
        player_stat_label_three = v.findViewById(R.id.player_stat_three_label);
        player_stat_label_four = v.findViewById(R.id.player_stat_four_label);
        player_stat_label_five = v.findViewById(R.id.player_stat_five_label);
        player_stat_label_six = v.findViewById(R.id.player_stat_six_label);
        player_stat_label_six = v.findViewById(R.id.player_stat_seven_label);
        player_stat_label_eight = v.findViewById(R.id.player_stat_eight_label);
        player_stat_label_nine = v.findViewById(R.id.player_stat_nine_label);
        player_stat_label_ten = v.findViewById(R.id.player_stat_ten_label);
        player_stat_label_eleven = v.findViewById(R.id.player_stat_eleven_label);

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
                bindPlayerDetails(Objects.requireNonNull(response.body()));
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
            player_stats_one.setText(playerStats.getGoals().toString());
            player_stats_two.setText(playerStats.getAssists().toString());
            int points = playerStats.getGoals() + playerStats.getAssists();
            player_stats_three.setText(Integer.toString(points));
            player_stats_four.setText(playerStats.getPlusMinus().toString());
            player_stats_five.setText(playerStats.getPim().toString());
            player_stats_six.setText(playerStats.getPowerPlayGoals().toString());
            player_stats_seven.setText(playerStats.getPowerPlayPoints().toString());
            player_stats_eight.setText(playerStats.getGameWinningGoals().toString());
            player_stats_nine.setText(playerStats.getOverTimeGoals().toString());
            player_stats_ten.setText(playerStats.getShots().toString());
            player_stats_eleven.setText(playerStats.getShotPct().toString());
        }

        if (playerStats.getWins() != null && playerStats.getSavePercentage() != null) {
            // This player is a goalie
            addGoalieStatLabels();
            player_games.setText(playerStats.getGames().toString());
            player_stats_one.setText(playerStats.getWins().toString());
            player_stats_two.setText(playerStats.getLosses().toString());
            player_stats_three.setText(playerStats.getSavePercentage().toString());
            player_stats_four.setText(playerStats.getShotsAgainst().toString());
            player_stats_five.setText(playerStats.getGoalsAgainst().toString());
            player_stats_six.setText(playerStats.getGoalAgainstAverage().toString());
            player_stats_seven.setText(playerStats.getSaves().toString());
            player_stats_eight.setText(playerStats.getSavePercentage().toString());
            player_stats_nine.setText(playerStats.getShutouts().toString());
            player_stats_ten.setText(playerStats.getTimeOnIce());
            player_stats_eleven.setText(" ");
        }
    }

    private void addSkaterStatLabels() {
        player_stat_label_one.setText(R.string.goals);
        player_stat_label_two.setText(R.string.assists);
        player_stat_label_three.setText(R.string.points);
        player_stat_label_four.setText("+/-");
        player_stat_label_five.setText("PIM");
        player_stat_label_six.setText("PPG");
        player_stat_label_seven.setText("PPP");
        player_stat_label_eight.setText("GWG");
        player_stat_label_nine.setText("OTG");
        player_stat_label_ten.setText("S");
        player_stat_label_eleven.setText("S%");
    }

    private void addGoalieStatLabels() {
        player_stat_label_one.setText(R.string.wins);
        player_stat_label_two.setText(R.string.loss);
        player_stat_label_three.setText(R.string.save_percentage);
        player_stat_label_four.setText("SA");
        player_stat_label_five.setText("GA");
        player_stat_label_six.setText("GAA");
        player_stat_label_seven.setText("Saves");
        player_stat_label_eight.setText("S%");
        player_stat_label_nine.setText("SO");
        player_stat_label_ten.setText("MINS");
        player_stat_label_eleven.setText(" ");
    }

    private Stat_ getStat_(PlayerStats playerStatsResponse) {
        List<Stat> statsList = playerStatsResponse.getStats();
        List<Split> splits = statsList.get(0).getSplits();
        return splits.get(0).getStat();
    }
}
