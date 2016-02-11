package com.acmutd.hackutd;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.Window;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Intent intent = new Intent(this, LoginActivity.class);
        final View background = findViewById(R.id.background);
        final View hack_utd_text = findViewById(R.id.hack_utd_text);
        final View hack_utd_logo = findViewById(R.id.hack_utd_logo);
        final SplashScreen superThis = this;
        final Context context = this;

        if (!getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE).getString("userType", "").equals("")) {
            Intent skipIntent = new Intent(this, MainActivity.class);
            startActivity(skipIntent);
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // CHECK IF THE STATUS BAR AND NAVIGATION BAR IS NULL //
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(superThis,
                                Pair.create(findViewById(android.R.id.statusBarBackground), Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME),
                                Pair.create(findViewById(R.id.splashLoginHeader), "splashLoginHeader"));
                        startActivity(intent, options.toBundle());
                        finish();
                    } else {
                        startActivity(intent);
                        finish();
                    }
                    superThis.finish();
                }
            }, 1500);
        }
    }
}
