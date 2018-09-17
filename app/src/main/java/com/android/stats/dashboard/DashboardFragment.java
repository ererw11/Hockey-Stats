package com.android.stats.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.stats.ApiService;
import com.android.stats.ApiUtils;
import com.android.stats.R;
import com.android.stats.dashboard.team.Team;
import com.android.stats.dashboard.team.Team_;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private static final String TAG = DashboardFragment.class.getSimpleName();
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

        return v;
    }

    private void loadTeams() {
        apiService.getTeams().enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {

            }
        });
    }

    private void bindTeams(List<Team_> teams) {
        setUpAdapter(teams);

    }

    private void setUpAdapter(List<Team_> teams) {
        if (isAdded()) {
            teamsRecyclerView.setAdapter(new com.android.stats.dashboard.TeamAdapter(teams, null));
        }
    }
}

