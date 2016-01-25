package com.acmutd.hackutd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

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

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Helpers.host + "/api/users", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("error") && !response.getString("error").equals("null")) {
                        final AlertDialog dialog = new AlertDialog.Builder(superThis)
                                .setTitle("User Error")
                                .setMessage(response.getString("error"))
                                .create();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.show();
                            }
                        });
                    } else {
                        JSONArray users = response.getJSONArray("data");

                        final AlertDialog dialog = new AlertDialog.Builder(superThis)
                                .setTitle(users.getJSONObject(0).getString("first_name") + "'s Email")
                                .setMessage(users.getJSONObject(0).getString("email"))
                                .create();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.show();
                            }
                        });
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

        MySingleton.getInstance(this).addToRequestQueue(request);
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
