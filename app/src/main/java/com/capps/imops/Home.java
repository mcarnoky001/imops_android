package com.capps.imops;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Home extends Activity {
	ImageButton tickets, history, star, review, user_setup, settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		history = (ImageButton) findViewById(R.id.imageButton3);
		review = (ImageButton) findViewById(R.id.imageButton4);
		settings = (ImageButton) findViewById(R.id.imageButton8);
		user_setup = (ImageButton) findViewById(R.id.imageButton5);


		review.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				btnRateAppOnClick();
			}
		});
		settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		user_setup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent launchactivity = new Intent(Home.this, UserSetup.class);
				startActivity(launchactivity);

			}
		});
	}

	protected void error1() {
		// TODO Auto-generated method stub
		Toast.makeText(
				this,
				"You cannot enter because you have not filled out your PERSONAL INFORMATION",
				Toast.LENGTH_SHORT).show();

	}

	public void startNFC(View view){
		Intent intent = getIntent();
		String json = intent.getStringExtra("UserData");
		Intent launchactivity = new Intent(Home.this, Nfc.class);
		launchactivity.putExtra("UserData", json);
		startActivity(launchactivity);
	}

	public void btnRateAppOnClick() {
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
