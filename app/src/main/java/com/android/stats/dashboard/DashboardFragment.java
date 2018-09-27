package com.android.stats.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.stats.ApiService;
import com.android.stats.ApiUtils;
import com.android.stats.R;
import com.android.stats.dashboard.standings.Record;
import com.android.stats.dashboard.standings.Standings;
import com.android.stats.dashboard.standings.TeamRecord;
import com.android.stats.dashboard.team.Team;
import com.android.stats.dashboard.team.Team_;
import com.android.stats.roster.RosterActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements TeamAdapter.TeamAdapterOnClickHandler {

    private static final String TAG = DashboardFragment.class.getSimpleName();

    private RecyclerView metroDivRecyclerView;
    private RecyclerView atlanticDivRecyclerView;
    private RecyclerView centralDivRecyclerView;
    private RecyclerView pacDivRecyclerView;
    private RecyclerView teamsRecyclerView;

    private ApiService apiService;

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

        apiService = ApiUtils.getApiService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        teamsRecyclerView = v.findViewById(R.id.teams_recycler_view);

        RecyclerView.LayoutManager teamsLayoutManager
                = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        teamsRecyclerView.setLayoutManager(teamsLayoutManager);
        teamsRecyclerView.setHasFixedSize(true);

        loadTeams();

        metroDivRecyclerView = v.findViewById(R.id.metropolitan_standings);
        atlanticDivRecyclerView = v.findViewById(R.id.atlantic_standings);
        centralDivRecyclerView = v.findViewById(R.id.central_standings);
        pacDivRecyclerView = v.findViewById(R.id.pacific_standings);

        RecyclerView.LayoutManager metroLayoutManager = new LinearLayoutManager(getContext());
        metroDivRecyclerView.setLayoutManager(metroLayoutManager);
        metroDivRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager atlanticLayoutManager = new LinearLayoutManager(getContext());
        atlanticDivRecyclerView.setLayoutManager(atlanticLayoutManager);
        atlanticDivRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager centralLayoutManager = new LinearLayoutManager(getContext());
        centralDivRecyclerView.setLayoutManager(centralLayoutManager);
        centralDivRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager pacificLayoutManager = new LinearLayoutManager(getContext());
        pacDivRecyclerView.setLayoutManager(pacificLayoutManager);
        pacDivRecyclerView.setHasFixedSize(true);


        loadStandings();

        return v;
    }

    private void loadTeams() {
        apiService.getTeams().enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                Log.d(TAG, "Teams Successful");
                bindTeams(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {

            }
        });
    }

    private void bindTeams(Team teams) {
        List<Team_> teamList = teams.getTeams();
        sortTeamList(teamList);
        setUpTeamAdapter(teamList);
    }

    private List<Team_> sortTeamList(List<Team_> teamList) {

        Collections.sort(teamList, new Comparator<Team_>() {
            @Override
            public int compare(Team_ team_1, Team_ team_2) {
                return team_1.getLocationName().compareToIgnoreCase(team_2.getLocationName());
            }
        });

        for (int i = 0; i < teamList.size(); i++) {
            Log.d(TAG, teamList.get(i).getTeamName());
        }

        return teamList;
    }

    private void loadStandings() {
        apiService.getStandings().enqueue(new Callback<Standings>() {
            @Override
            public void onResponse(Call<Standings> call, Response<Standings> response) {
                Log.d(TAG, "Standings Successful");
                bindStandings(Objects.requireNonNull(response.body()));
            }

            @Override
            public void onFailure(Call<Standings> call, Throwable t) {

            }
        });
    }

    private void bindStandings(Standings standings) {
        List<Record> records = standings.getRecords();

        for (int i = 0; i < records.size(); i++) {
            int divisionId = records.get(i).getDivision().getId();
            postStandings(divisionId, records.get(i));
        }

    }

    private void postStandings(int divisionId, Record record) {
        List<TeamRecord> teamRecordList = record.getTeamRecords();
        setUpStandingsAdapter(teamRecordList, divisionId);
    }

    private void setUpTeamAdapter(List<Team_> teams) {
        if (isAdded()) {
            teamsRecyclerView.setAdapter(new com.android.stats.dashboard.TeamAdapter(teams, this));
        }
    }

    private void setUpStandingsAdapter(List<TeamRecord> teamRecords, int divisionId) {
        if (isAdded()) {
            if (divisionId == 18) {
                // Division is Metro with id 18
                metroDivRecyclerView.setAdapter(new StandingsAdapter(teamRecords));
            } else if (divisionId == 17) {
                // Division is Atlantic with id 17
                atlanticDivRecyclerView.setAdapter(new StandingsAdapter(teamRecords));
            } else if (divisionId == 16) {
                // Division is Central with id 16
                centralDivRecyclerView.setAdapter(new StandingsAdapter(teamRecords));
            } else if (divisionId == 15) {
                // Division is Pacific with id 15
                pacDivRecyclerView.setAdapter(new StandingsAdapter(teamRecords));
            }
        }
    }

    @Override
    public void onClick(Team_ team) {
        Intent newIntent = RosterActivity.newRosterIntent(getContext(), team.getId());
        startActivity(newIntent);
    }
}

