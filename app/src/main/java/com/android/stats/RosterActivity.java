package com.android.stats;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class RosterActivity extends SingleFragmentActivity {

    private final static String EXTRA_TEAM_ID =
            "com.android.stats.team_id";

    public static Intent newRosterIntent(Context packageContext, int Id) {
        Intent newIntent = new Intent(packageContext, RosterActivity.class);
        newIntent.putExtra(EXTRA_TEAM_ID, Id);
        return newIntent;
    }

    @Override
    protected Fragment createFragment() {
        int teamId = (int) getIntent()
                .getSerializableExtra(EXTRA_TEAM_ID);
        return RosterFragment.newInstance(Integer.toString(teamId));
    }
}
