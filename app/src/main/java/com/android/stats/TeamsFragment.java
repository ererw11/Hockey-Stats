package com.android.stats;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TeamsFragment extends Fragment implements TeamAdapter.TeamAdapterOnClickHandler {

    RecyclerView teamRecyclerView;
    private List<Team> teamList = new ArrayList<>();

    public TeamsFragment() {
        // Required empty public constructor
    }

    public static TeamsFragment newInstance() {
        return new TeamsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchTeamTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teams, container, false);

        teamRecyclerView = v.findViewById(R.id.team_recycler_view);

        RecyclerView.LayoutManager layoutManager;
        if (getResources().getConfiguration().smallestScreenWidthDp >= 600 || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getContext());
        }
        teamRecyclerView.setLayoutManager(layoutManager);
        teamRecyclerView.setHasFixedSize(true);

        setUpAdapter();

        return v;
    }

    private void setUpAdapter() {
        if (isAdded()) {
            teamRecyclerView.setAdapter(new TeamAdapter(getContext(), teamList, this));
        }
    }

    @Override
    public void onClick(Team team) {
        Intent newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(team.getTeamWebSite()));
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
