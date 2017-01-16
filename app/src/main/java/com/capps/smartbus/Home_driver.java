package com.capps.smartbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home_driver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_driver);
		ImageButton settings = (ImageButton) findViewById(R.id.imageButton8);
		settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		ImageButton start = (ImageButton) findViewById(R.id.imageButton3);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent launchactivity = new Intent(Home_driver.this,
						Rides_driver.class);
				startActivity(launchactivity);

			}
		});
	}
}
