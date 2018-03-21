package com.android.stats;

import android.support.v4.app.Fragment;

/**
 * Created 3/21/2018.
 */

public class TeamActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return TeamsFragment.newInstance();
    }
}
