package com.acmutd.hackutd;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    LoginActivity superThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        superThis = this;
    }

    public void participantPress(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE).edit();
        editor.putString("userType", "participant").apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void sponsorPress(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
