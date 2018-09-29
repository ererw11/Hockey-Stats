package com.android.stats.roster;

import android.annotation.SuppressLint;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.stats.ApiService;
import com.android.stats.ApiUtils;
import com.android.stats.R;
import com.android.stats.roster.teamStats.Split;
import com.android.stats.roster.teamStats.Stat;
import com.android.stats.roster.teamStats.Stat_;
import com.android.stats.roster.teamStats.Team;
import com.android.stats.roster.teamStats.TeamStats;
//import com.android.stats.player_stats.PlayerStatsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RosterFragment extends Fragment implements RosterAdapter.RosterAdapterOnClickHandler {

    private static final String TAG = RosterFragment.class.getCanonicalName();

    private static final String ARG_TEAM_ID =
            "com.android.stats.team_id";

    private TextView teamNameTextView;
    private TextView teamRecordTextView;
    private RecyclerView rosterRecyclerView;

    private String teamId;
    private List<Player> roster = new ArrayList<>();

    private ApiService apiService;

    public RosterFragment() {
        // Required empty public constructor
    }

    public static RosterFragment newInstance(String teamId) {
        Bundle arg = new Bundle();
        arg.putSerializable(ARG_TEAM_ID, teamId);

        RosterFragment fragment = new RosterFragment();
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        apiService = ApiUtils.getApiService();

        teamId = Objects.requireNonNull(getArguments()).getString(ARG_TEAM_ID);
        new FetchRosterTask().execute();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_roster, container, false);
        rosterRecyclerView = v.findViewById(R.id.roster_recycler_view);

        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().smallestScreenWidthDp >= 600 || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
        }
        rosterRecyclerView.setLayoutManager(layoutManager);
        rosterRecyclerView.setHasFixedSize(true);

        teamNameTextView = v.findViewById(R.id.team_name);
        teamRecordTextView = v.findViewById(R.id.team_record);

        setUpAdapter();

        loadTeamStandings(teamId);

        return v;
    }

    private void setUpAdapter() {
        if (isAdded()) {
            rosterRecyclerView.setAdapter(new RosterAdapter(roster, this));
        }
    }

    private void loadTeamStandings(String  teamId) {
        apiService.getTeamStats(teamId).enqueue(new Callback<TeamStats>() {
            @Override
            public void onResponse(Call<TeamStats> call, Response<TeamStats> response) {
                Log.d(TAG, "Team Stats Successful");
                bindTeamStats(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(Call<TeamStats> call, Throwable t) {

            }
        });
    }

    private void bindTeamStats(TeamStats teamStatsResponse) {
        List<Stat> stats = teamStatsResponse.getStats();
        Stat stat = stats.get(0);
        List<Split> splits = stat.getSplits();
        Split split = splits.get(0);
        Team team = split.getTeam();
        teamNameTextView.setText(team.getName());
        Stat_ stat_ = split.getStat();
        String wins = stat_.getWins();
        String losses = stat_.getLosses();
        String oTLosses = stat_.getOt();
        teamRecordTextView.setText(wins + " - " + losses + " - " + oTLosses);
    }

    @Override
    public void onClick(Player player) {
//        Intent newIntent = PlayerStatsActivity.newPlayerStatsIntent(
//                getContext(),
//                Integer.toString(player.getPlayerId()));
//        startActivity(newIntent);
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchRosterTask extends AsyncTask<Void, Void, List<Player>> {

        @Override
        protected List<Player> doInBackground(Void... voids) {
            return new RosterFetcher().fetchPlayers(teamId);
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            roster = players;
            setUpAdapter();
        }
    }
}

