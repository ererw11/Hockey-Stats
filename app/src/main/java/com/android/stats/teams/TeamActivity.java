package com.android.stats.teams;

import android.support.v4.app.Fragment;

import com.android.stats.SingleFragmentActivity;

public class TeamActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return TeamsFragment.newInstance();
    }

}
