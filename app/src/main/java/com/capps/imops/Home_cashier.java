package com.capps.imops;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home_cashier extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_cashier);

	}
	public void startNFC(View view){
		Intent launchactivity = new Intent(Home_cashier.this, Nfc_cashier.class);
		startActivity(launchactivity);
	}
}
