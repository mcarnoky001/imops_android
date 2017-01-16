package com.capps.smartbus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import AssyncTasks.CasPhp;
import Objects.Spoj;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class Rezervacia_cas extends Activity {
	DatePicker datum;
	TimePicker cas;
	String Timestamp;
	String z, ciel;
	public static List<Spoj> spoje = new ArrayList<Spoj>();
	private List<String> zoznam = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rezervacia_cas);
		cas = (TimePicker) findViewById(R.id.timePicker2);
		cas.setIs24HourView(true);
		datum = (DatePicker) findViewById(R.id.datePicker22);
		ImageButton tlacidlo = (ImageButton) findViewById(R.id.imageButton23);
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		z = sharedpreferences.getString("z", null);
		ciel = sharedpreferences.getString("do", null);
		tlacidlo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				setInfo();
				sendIt();
			}

		});
	}

	private void setInfo() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int Year = datum.getYear();
		int Month = datum.getMonth();
		int Day = datum.getDayOfMonth();
		cas.clearFocus();
		int Hour = cas.getCurrentHour();
		int Minute = cas.getCurrentMinute();

		final Calendar c = Calendar.getInstance();
		c.set(Year, Month, Day, Hour, Minute, 0);

		Timestamp = f.format(c.getTime());

	}

	private void sendIt() {
		new CasPhp(this, this).execute(Timestamp, z, ciel);
	}

	public void setList(List<Spoj> list) {
		spoje = list;
		for (int i = 0; i < spoje.size(); i++) {
			Spoj obj = spoje.get(i);
			String text = obj.getText();
			zoznam.add(text);
		}
		prepni();
	}

	private void prepni() {
		Intent i = new Intent(getApplicationContext(), VolbaJazdy.class);
		i.putStringArrayListExtra("list", (ArrayList<String>) zoznam);
		startActivity(i);
	}

	public List<String> getList() {
		return zoznam;
	}
}
