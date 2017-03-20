package com.capps.imops;

import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import AssyncTasks.Get;
import AssyncTasks.VerifyPurchase;


public class Nfc_cashier extends Activity implements LoyaltyCardReader.AccountCallback{
    public static int READER_FLAGS =
            NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;
    public LoyaltyCardReader mLoyaltyCardReader;
    public TextView mAccountField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_cashier);
        mLoyaltyCardReader = new LoyaltyCardReader(this);
        mAccountField = (TextView) findViewById(R.id.instructionTextView);
        enableReaderMode();
    }
    @Override
    public void onPause() {
        super.onPause();
        disableReaderMode();
    }

    @Override
    public void onResume() {
        super.onResume();
        enableReaderMode();
    }

    private void enableReaderMode() {
        /*Activity activity = getActivity();*/
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.enableReaderMode(this, mLoyaltyCardReader, READER_FLAGS, null);
        }
    }

    private void disableReaderMode() {
        /*Activity activity = getActivity();*/
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.disableReaderMode(this);
        }
    }
    private void verify(String ID){
        VerifyPurchase obj = new VerifyPurchase(this);
        Intent intent = getIntent();
        String json1 = intent.getStringExtra("UserData");
        JSONObject obj1 = null;
        try {
            obj1 = new JSONObject(json1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String id = null;
        try {
            id = obj1.getString("_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = "{\"customerID\":\""+ID+"\",\"vendorID\":\""+id+"\",\"purchaseID\":\"12312\",\"cost\":\"25\",\"date\":\""+new Date().toString()+"\",\"place\":\"Tesco BA\",\"list\":[{\"name\":\"chicken breasts\",\"price\":\"12.2\",\"category\":\"meat\"},{\"name\":\"Marlboro\",\"price\":\"10.2\",\"category\":\"Tabacoo\"},{\"name\":\"Vodka\",\"price\":\"2.6\",\"category\":\"Alcohol\"}]}";
        Log.i("jsonResult",json);
        try {
            Boolean result = obj.execute(json).get();
            if (result == true) {
                Log.i("Result of verify","true");
                showToast("Purchase successful");
            }
            else {
                Log.i("Result of verify", "false");
                showToast("Purchase failed");
            }
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onAccountReceived(final String account) {
        verify(account);
    }
    private void showToast(final String text){
        final Context context = getApplicationContext();
        final int duration = Toast.LENGTH_SHORT;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }

}
