package com.android.stats.roster;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import com.android.stats.SingleFragmentActivity;

public class RosterActivity extends SingleFragmentActivity {

    private final static String EXTRA_TEAM_ID =
            "com.android.stats.team_id";

    public static Intent newRosterIntent(Context packageContext, int id) {
        Intent newIntent = new Intent(packageContext, RosterActivity.class);
        newIntent.putExtra(EXTRA_TEAM_ID, id);
        return newIntent;
    }

    @Override
    protected Fragment createFragment() {
        int teamId = (int) getIntent()
                .getSerializableExtra(EXTRA_TEAM_ID);
        return RosterFragment.newInstance(Integer.toString(teamId));
    }
}
