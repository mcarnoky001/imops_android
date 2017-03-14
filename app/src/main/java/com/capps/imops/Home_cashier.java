package com.capps.imops;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home_cashier extends Activity {
	ImageButton exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_cashier);
		exit = (ImageButton) findViewById(R.id.imageButton8);

		exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}
	public void startNFC(View view){
		Intent intent = getIntent();
		String json = intent.getStringExtra("UserData");
		Intent launchactivity = new Intent(Home_cashier.this, Nfc_cashier.class);
		launchactivity.putExtra("UserData", json);
		startActivity(launchactivity);
	}
}
