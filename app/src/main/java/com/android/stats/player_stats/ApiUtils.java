package com.android.stats.player_stats;

/**
 * Created 4/4/2018.
 */

class ApiUtils {

    public static ApiService getApiService() {
        return RetroClient.getRetrofitInstance().create(ApiService.class);
    }

}
