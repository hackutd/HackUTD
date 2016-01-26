package com.acmutd.hackutd;

import android.app.Application;

import com.urbanairship.UAirship;

public class HackUTDApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UAirship.takeOff(this, new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship airship) {
                airship.getPushManager().setUserNotificationsEnabled(true);
            }
        });
    }
}
