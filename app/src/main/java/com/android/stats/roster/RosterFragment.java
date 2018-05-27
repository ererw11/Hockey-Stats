package com.android.stats.roster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.stats.R;
import com.android.stats.player_stats.PlayerStatsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RosterFragment extends Fragment implements RosterAdapter.RosterAdapterOnClickHandler {

    private static final String ARG_TEAM_ID =
            "com.android.stats.team_id";
    private RecyclerView rosterRecyclerView;

    private String teamId;
    private List<Player> roster = new ArrayList<>();

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
        teamId = Objects.requireNonNull(getArguments()).getString(ARG_TEAM_ID);
        new FetchRosterTask().execute();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_roster, container, false);
        rosterRecyclerView = v.findViewById(R.id.team_recycler_view);

        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().smallestScreenWidthDp >= 600 || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
        }
        rosterRecyclerView.setLayoutManager(layoutManager);
        rosterRecyclerView.setHasFixedSize(true);

        setUpAdapter();

        return v;
    }

    private void setUpAdapter() {
        if (isAdded()) {
            rosterRecyclerView.setAdapter(new RosterAdapter(roster, this));
        }
    }

    @Override
    public void onClick(Player player) {
        Intent newIntent = PlayerStatsActivity.newPlayerStatsIntent(
                getContext(),
                Integer.toString(player.getPlayerId()));
        startActivity(newIntent);
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

