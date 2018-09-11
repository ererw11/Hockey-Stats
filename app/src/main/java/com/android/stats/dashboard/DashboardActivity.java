package com.android.stats.dashboard;

import com.android.stats.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class DashboardActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return DashboardFragment.newInstance();
    }
}
