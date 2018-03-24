package com.android.stats;

import android.support.v4.app.Fragment;

public class TeamActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return TeamsFragment.newInstance();
    }
}
