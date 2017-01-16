package com.capps.smartbus;

import AssyncTasks.getBenefitMenu;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;

public class Star extends Activity {
	TextView cislo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star);
		cislo = (TextView) findViewById(R.id.textView2);
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		String meno = sharedpreferences.getString("meno", null);
		new getBenefitMenu(this, this).execute(meno);

	}

	public void setBenefit(Float cislo2) {
		cislo.setText(Float.toString(cislo2));

	}
}
