package com.capps.imops;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;



public class Nfc extends Activity {
    TextView instructionText;
    Button yesBtn,noBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        instructionText = (TextView) findViewById(R.id.instructionTextView);
        Intent intent = getIntent();
        String json = intent.getStringExtra("UserData");
        Log.i("UserData:",json.toString());
        try {
           JSONObject obj = new JSONObject(json);
            String id = obj.getString("_id");
            Log.i("UserID:",id);
            AccountStorage.SetAccount(this, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}