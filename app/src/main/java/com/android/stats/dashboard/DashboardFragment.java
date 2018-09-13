package com.android.stats.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.stats.R;
import com.android.stats.roster.RosterActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardFragment extends Fragment implements TeamAdapter.TeamAdapterOnClickHandler {

    private RecyclerView teamsRecyclerView;

    private List<Team> teamList = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchTeamTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        teamsRecyclerView = v.findViewById(R.id.teams_recycler_view);

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        teamsRecyclerView.setLayoutManager(layoutManager);
        teamsRecyclerView.setHasFixedSize(true);

        setUpAdapter();

        return v;
    }

    private void setUpAdapter() {
        if (isAdded()) {
            teamsRecyclerView.setAdapter(new TeamAdapter(teamList, this));
        }
    }

    @Override
    public void onClick(Team team) {
        Intent newIntent = RosterActivity.newRosterIntent(getContext(), team.getTeamId());
        startActivity(newIntent);
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchTeamTask extends AsyncTask<Void, Void, List<Team>> {

        @Override
        protected List<Team> doInBackground(Void... voids) {
            return new TeamFetcher().fetchTeams();
        }

        @Override
        protected void onPostExecute(List<Team> teams) {
            teamList = teams;
            setUpAdapter();
        }
    }
}

