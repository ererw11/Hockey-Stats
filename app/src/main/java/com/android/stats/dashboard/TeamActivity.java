package com.android.stats.dashboard;

import androidx.fragment.app.Fragment;

import com.android.stats.SingleFragmentActivity;

public class TeamActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return TeamsFragment.newInstance();
    }

}
