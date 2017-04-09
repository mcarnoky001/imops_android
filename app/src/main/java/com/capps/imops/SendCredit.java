package com.capps.imops;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import AssyncTasks.GetByID;
import AssyncTasks.SendCreditTask;

public class SendCredit extends Activity {
    EditText recepientID,amount;
    String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_credit);
        recepientID = (EditText) findViewById(R.id.editText5);
        amount = (EditText) findViewById(R.id.editText7);
        Intent intent = getIntent();
        String json = intent.getStringExtra("UserData");
        Log.i("UserData:",json.toString());
        try {
            JSONObject obj = new JSONObject(json);
            id = obj.getString("_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void error1() {
        // TODO Auto-generated method stub
        Toast.makeText(
                this,
                "You cannot enter because you have not filled out your PERSONAL INFORMATION",
                Toast.LENGTH_SHORT).show();

    }

    public void sendCredit(View view){
        SendCreditTask obj = new SendCreditTask(this);
        try {
            Boolean result= obj.execute(id,recepientID.getText().toString(),amount.getText().toString()).get();
            if (result ==true) {
                Toast.makeText(this, "Credit successfully sent",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Credit not sent, error occured",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void btnRateAppOnClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Try Google play
        intent.setData(Uri.parse("market://details?id=com.capps.speedgeekfree"));
        if (!MyStartActivity(intent)) {
            // Market (Google play) app seems not installed, let's try to open a
            // webbrowser
            intent.setData(Uri
                    .parse("https://play.google.com/store/apps/details?id=com.capps.speedgeekfree"));
            if (!MyStartActivity(intent)) {
                // Well if this also fails, we have run out of options, inform
                // the user.
                Toast.makeText(this, "Could not open Google play.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
}
