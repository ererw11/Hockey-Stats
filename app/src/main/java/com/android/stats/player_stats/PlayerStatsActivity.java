//package com.android.stats.player_stats;
//
//import android.content.Context;
//import android.content.Intent;
//import androidx.fragment.app.Fragment;
//
//import com.android.stats.SingleFragmentActivity;
//
//public class PlayerStatsActivity extends SingleFragmentActivity {
//
//    private final static String EXTRA_PLAYER_STATS_ID =
//            "com.android.stats.player_stats.id";
//
//    public static Intent newPlayerStatsIntent(Context packageContext, String id) {
//        Intent newIntent = new Intent(packageContext, PlayerStatsActivity.class);
//        newIntent.putExtra(EXTRA_PLAYER_STATS_ID, id);
//        return newIntent;
//    }
//
//    @Override
//    protected Fragment createFragment() {
//        String playerStatsId = (String) getIntent()
//                .getSerializableExtra(EXTRA_PLAYER_STATS_ID);
//        return PlayerStatsFragment.newInstance(playerStatsId);
//    }
//}
