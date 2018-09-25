package com.android.stats.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.stats.R;
import com.android.stats.dashboard.standings.TeamRecord;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsHolder> {

    private final List<TeamRecord> recordList;

    public StandingsAdapter(List<TeamRecord> records) {
        recordList = records;
    }

    @NonNull
    @Override
    public StandingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_view_standings, parent, false);
        return new StandingsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsHolder holder, int position) {
        TeamRecord teamRecord = recordList.get(position);
        String teamName = teamRecord.getTeam().getName();
        int wins = teamRecord.getLeagueRecord().getWins();
        int otl = teamRecord.getLeagueRecord().getOt();
        int points = (wins * 2) + otl;
        holder.bindView(teamName, Integer.toString(points));
    }

    @Override
    public int getItemCount() {
        if (recordList == null) {
            return 0;
        } else {
            return recordList.size();
        }
    }

    public class StandingsHolder extends RecyclerView.ViewHolder {

        private final TextView standingsTeamName;
        private final TextView standingsRecord;

        StandingsHolder(View itemView) {
            super(itemView);

            standingsTeamName = itemView.findViewById(R.id.standing_name);
            standingsRecord = itemView.findViewById(R.id.standing_points);
        }

        void bindView(String teamName, String teamRecord) {
            standingsTeamName.setText(teamName);
            standingsRecord.setText(teamRecord);
        }
    }
}
