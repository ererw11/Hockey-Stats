package com.android.stats.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.stats.ApiService;
import com.android.stats.ApiUtils;
import com.android.stats.R;
import com.android.stats.dashboard.standings.Record;
import com.android.stats.dashboard.standings.Standings;
import com.android.stats.dashboard.standings.TeamRecord;
import com.android.stats.dashboard.team.Team;
import com.android.stats.dashboard.team.Team_;
import com.android.stats.roster.RosterActivity;

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

    private TextView metroDivTextView;
    private TextView atlanticDivTextView;
    private TextView centralDivTextView;
    private TextView pacDivTextView;

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

        RecyclerView.LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        teamsRecyclerView.setLayoutManager(layoutManager);
        teamsRecyclerView.setHasFixedSize(true);

        loadTeams();

        metroDivTextView = v.findViewById(R.id.metropolitan_standings);
        atlanticDivTextView = v.findViewById(R.id.atlantic_standings);
        centralDivTextView = v.findViewById(R.id.central_standings);
        pacDivTextView = v.findViewById(R.id.pacific_standings);

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
        setUpAdapter(teamList);
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
        if (divisionId == 18) {
            // Division is Metro with id 18
            String metroStandings = parseDivision(record);
            metroDivTextView.setText(metroStandings);
            Log.d(TAG + "Metro_Standings", metroStandings);
        } else if (divisionId == 17) {
            // Division is Atlantic with id 17
            String atlanticStandings = parseDivision(record);
            atlanticDivTextView.setText(atlanticStandings);
            Log.d(TAG + "Atlantic_Standings", atlanticStandings);
        } else if (divisionId == 16) {
            // Division is Central with id 16
            String centralStandings = parseDivision(record);
            centralDivTextView.setText(centralStandings);
            Log.d(TAG + "Central_Standings", centralStandings);
        } else if (divisionId == 15) {
            // Division is Pacific with id 15
            String pacificStandings = parseDivision(record);
            pacDivTextView.setText(pacificStandings);
            Log.d(TAG + "Pacific_Standings", pacificStandings);
        }
    }

    private String parseDivision(Record record) {
        List<TeamRecord> teams = record.getTeamRecords();
        String standings = "";
        for (int i = 0; i < teams.size(); i++) {
            com.android.stats.dashboard.standings.Team team =
                    teams.get(i).getTeam();
            Log.d(TAG + " Parse_Division", team.getName());
            standings = standings + team.getName() + "\n";
        }
        return standings;
    }

    private void setUpAdapter(List<Team_> teams) {
        if (isAdded()) {
            teamsRecyclerView.setAdapter(new com.android.stats.dashboard.TeamAdapter(teams, this));
        }
    }

    @Override
    public void onClick(Team_ team) {
        Intent newIntent = RosterActivity.newRosterIntent(getContext(), team.getId());
        startActivity(newIntent);
    }
}

