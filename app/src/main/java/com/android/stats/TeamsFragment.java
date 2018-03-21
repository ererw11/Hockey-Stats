package com.android.stats;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TeamsFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teams, container, false);

        teamRecyclerView = v.findViewById(R.id.team_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        teamRecyclerView.setLayoutManager(layoutManager);
        teamRecyclerView.setHasFixedSize(true);

        setUpAdapter();

        return v;
    }

    private void setUpAdapter() {
        if (isAdded()) {
            teamRecyclerView.setAdapter(new TeamAdapter(getContext(), teamList));
        }
    }

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
