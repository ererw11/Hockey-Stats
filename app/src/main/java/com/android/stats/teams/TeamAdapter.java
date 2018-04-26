package com.android.stats.teams;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.stats.R;

import java.util.List;

/**
 * Created 3/19/2018.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {

    private final TeamAdapterOnClickHandler mClickHandler;
    private final List<Team> teamList;

    TeamAdapter(List<Team> teams, TeamAdapterOnClickHandler clickHandler) {
        teamList = teams;
        mClickHandler = clickHandler;
    }


    @NonNull
    @Override
    public TeamAdapter.TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view_team, parent, false);
        return new TeamAdapter.TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.TeamHolder holder, int position) {
        Team team = teamList.get(position);
        String teamLocation = team.getTeamLocation();
        String teamName = team.getTeamName();
        String teamDivision = team.getTeamDivision();
        holder.bindView(teamLocation, teamName, teamDivision);
    }

    @Override
    public int getItemCount() {
        if (teamList == null) {
            return 0;
        }
        return teamList.size();
    }

    public interface TeamAdapterOnClickHandler {
        void onClick(Team team);
    }

    public class TeamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView teamCityTextView;
        private final TextView teamNameTextView;
        private final TextView teamConferenceTextView;

        TeamHolder(View itemView) {
            super(itemView);
            teamCityTextView = itemView.findViewById(R.id.teamCity);
            teamNameTextView = itemView.findViewById(R.id.teamName);
            teamConferenceTextView = itemView.findViewById(R.id.teamDivision);
            itemView.setOnClickListener(this);
        }

        void bindView(String teamLocation, String teamName, String conference) {
            teamCityTextView.setText(teamLocation);
            teamNameTextView.setText(teamName);
            teamConferenceTextView.setText(conference);
//            teamColorChanger(teamName);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Team selectedTeam = teamList.get(adapterPosition);
            mClickHandler.onClick(selectedTeam);
        }
    }
}
