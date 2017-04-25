package com.capps.imops;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import AssyncTasks.VerifyPurchaseStatus;


public class Nfc extends Activity {
    public TextView instructionText,confirmText;
    Button yesBtn,noBtn;
    public ImageView confirmImage;
    public String id;
    public static Handler UIHandler = new Handler(Looper.getMainLooper());




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        confirmImage = (ImageView) findViewById(R.id.imageView);
        confirmText = (TextView) findViewById(R.id.textView15);
        instructionText= (TextView) findViewById(R.id.instructionTextView);
        confirmImage.setVisibility(View.INVISIBLE);
        confirmText.setVisibility(View.VISIBLE);
        instructionText.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        String json = intent.getStringExtra("UserData");
        Log.i("UserData:",json.toString());
        try {
           JSONObject obj = new JSONObject(json);
            id = obj.getString("_id");
            Log.i("UserID:",id);
            AccountStorage.SetAccount(this, id);
            CardService.updateIntanceOfNfcClass(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void continueAfterTagSent(){
        Log.i("continueAfterTagSent","Method Called");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkPurchaseStatus();
                    }
                }, 3000);;
            }
        });
    }
    private void checkPurchaseStatus(){
        VerifyPurchaseStatus obj = new VerifyPurchaseStatus(this);
        try {
            Boolean result = obj.execute(id).get();
            if (result == true) {
                Log.i("Result of verify","true");
                confirmImage.setVisibility(View.VISIBLE);
                confirmText.setVisibility(View.INVISIBLE);
                instructionText.setVisibility(View.VISIBLE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                finish();
                            }
                        }, 2000);;
                    }
                });
            }
            else {
                Log.i("Result of verify", "false");
                showToast("Purchase failed, not enought credit or buying restricted item");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private void showToast(final String text){
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_LONG;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }


}