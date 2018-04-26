package com.android.stats;

/**
 * Created 4/4/2018.
 */

public class ApiUtils {

    public static ApiService getApiService() {
        return RetroClient.getRetrofitInstance().create(ApiService.class);
    }

}
