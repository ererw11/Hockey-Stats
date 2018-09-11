package com.android.stats.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.stats.R;
import com.android.stats.roster.RosterActivity;
import com.android.stats.dashboard.teams.TeamAdapter;

import com.android.stats.dashboard.teams.Team;
import com.android.stats.dashboard.teams.TeamFetcher;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardFragment extends Fragment {

    private RecyclerView teamsRecyclerView;

    private List<Team> teamList = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        teamsRecyclerView = v.findViewById(R.id.teams_recycler_view);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

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

