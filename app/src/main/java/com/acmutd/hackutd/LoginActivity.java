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

    public void login(View V) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        boolean emailError = false;
        boolean passError = false;

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            findViewById(R.id.emailError).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.emailError).setVisibility(View.VISIBLE);
            emailError = true;
        }

        if (password.length() > 0) {
            findViewById(R.id.passwordError).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.passwordError).setVisibility(View.VISIBLE);
            passError = true;
        }

        if (emailError || passError) return;

        Map<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);

        MyJSONObjectRequest request = new MyJSONObjectRequest(Request.Method.POST, Helpers.host + "/api/users/login", params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("error") && !response.getString("error").equals("null")) {
                        final AlertDialog dialog = new AlertDialog.Builder(superThis)
                                .setTitle("Error")
                                .setMessage(response.getString("error"))
                                .create();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.show();
                            }
                        });
                    } else {
                        JSONObject user = response.getJSONObject("data");

                        SharedPreferences preferences = getSharedPreferences(getString(R.string.prefs_name), 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("user_email", user.getString("email"));
                        editor.putString("user_first_name", user.getString("first_name"));
                        editor.putString("user_last_name", user.getString("last_name"));
                        editor.commit();

                        Intent intent = new Intent(superThis, MainActivity.class);
                        intent.putExtra("user_first_name", user.getString("first_name"));
                        startActivity(intent);
                        superThis.finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("HackUTD Error", error.getMessage());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    public void question(View v) {
        AlertDialog dialog = new AlertDialog.Builder(superThis)
                .setTitle("Where do I get these?")
                .setMessage("You will receive a login once you register on the day of HackUTD")
                .setPositiveButton("Aye Aye, Capitan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
