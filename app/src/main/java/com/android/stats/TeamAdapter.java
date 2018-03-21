package com.android.stats;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created 3/19/2018.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {

    private List<Team> teamList;
    private Context mContext;

    public TeamAdapter(Context context, List<Team> teams) {
        mContext = context;
        teamList = teams;
    }

    @Override
    public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_view_team, parent, false);
        return new TeamAdapter.TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamHolder holder, int position) {
        Team team = teamList.get(position);
        String teamLocation = team.getTeamLocation();
        String teamName = team.getTeamName();
        String teamConference = "Conference"; // just a placeholder for the time being
        holder.bindView(teamLocation, teamName, teamConference);
    }

    @Override
    public int getItemCount() {
        if (teamList == null) {
            return 0;
        }
        return teamList.size();
    }

    public class TeamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView teamCityTextView;
        private TextView teamNameTextView;
        private TextView teamConferenceTextView;

        public TeamHolder(View itemView) {
            super(itemView);

            teamCityTextView = itemView.findViewById(R.id.teamCity);
            teamNameTextView = itemView.findViewById(R.id.teamName);
            teamConferenceTextView = itemView.findViewById(R.id.teamConference);
            itemView.setOnClickListener(this);
        }

        public void bindView(String teamLocation, String teamName, String conference) {
            teamCityTextView.setText(teamLocation);
            teamNameTextView.setText(teamName);
            teamConferenceTextView.setText(conference);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Team selectedTeam = teamList.get(adapterPosition);
            Toast.makeText(mContext, selectedTeam.getTeamWebSite(), Toast.LENGTH_SHORT);
        }
    }
}
