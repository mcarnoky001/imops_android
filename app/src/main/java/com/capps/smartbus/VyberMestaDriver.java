package com.capps.smartbus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import AssyncTasks.mestecka;
import Objects.DriverInsertSpoj;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class VyberMestaDriver extends Activity {
	Spinner spinner1, spinner2;
	String start_por = "", ciel_por = "";
	String start, ciel;
	static String start_c;
	static String ciel_c;
	public static int kilometre = 0;
	public static String odjazd, prichod;
	ImageButton tlacidlo;
	List<DriverInsertSpoj> zoznam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vyber_mesta_driver);
		getMesta();
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		tlacidlo = (ImageButton) findViewById(R.id.imageButton8);
		tlacidlo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("Tlacidlo", "spustilo sa");
				pocitaj();
				if (Integer.parseInt(start_por) >= Integer.parseInt(ciel_por)) {
					toast();
				} else {
					Intent launchactivity = new Intent(VyberMestaDriver.this,
							Buy_driver.class);
					startActivity(launchactivity);
				}
			}
		});
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onNothingSelected(AdapterView<?> arg0) {
				Log.e("klkl", "klkl");

			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView textview = (TextView) findViewById(R.id.textView22);
				String item = (String) spinner1.getAdapter().getItem(arg2);
				start = item;
				Log.d("spinner", item);

			}
		});
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onNothingSelected(AdapterView<?> arg0) {
				Log.e("klkl", "klkl");

			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView textview = (TextView) findViewById(R.id.textView22);
				String item = (String) spinner2.getAdapter().getItem(arg2);
				ciel = item;
				Log.d("spinner", item);

			}
		});
	}

	protected void toast() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Wrong stop order", Toast.LENGTH_SHORT).show();

	}

	protected void pocitaj() {
		for (int x = 0; x < Rides_driver.mesta.size(); x++) {
			if (start.equals(Rides_driver.mesta.get(x).getMesto().toString())) {
				start_c = Rides_driver.mesta.get(x).getId();
				Log.e("start c", start_c);
			}
			if (ciel.equals(Rides_driver.mesta.get(x).getMesto().toString())) {
				ciel_c = Rides_driver.mesta.get(x).getId();
				Log.e("start c", ciel_c);
			}
		}
		for (int x = 0; x < zoznam.size(); x++) {
			if (zoznam.get(x).getMesto().equals(start_c)) {
				start_por = zoznam.get(x).getPoradie().toString();
				Log.e("start por", ciel_por);
			}
			if (zoznam.get(x).getMesto().equals(ciel_c)) {
				ciel_por = zoznam.get(x).getPoradie().toString();
				Log.e("start por", ciel_por);
			}
		}
		for (int x = 0; x < zoznam.size(); x++) {
			if (Integer.parseInt(zoznam.get(x).getPoradie()) > (Integer
					.parseInt(start_por))
					&& Integer.parseInt(zoznam.get(x).getPoradie()) <= (Integer
							.parseInt(ciel_por))) {
				kilometre += Integer.parseInt(zoznam.get(x).getKilometre());
				Log.e("Kilometre", Integer.toString(kilometre));
			}
		}
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat srcDf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date convertedDate = null;
		try {
			convertedDate = parser.parse(Rides_driver.cas);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String output = srcDf.format(convertedDate);
		try {
			convertedDate = srcDf.parse(output);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.e("output cas", output);
		Calendar cl = Calendar.getInstance();
		cl.setTime(convertedDate);
		for (int x = 0; x < zoznam.size(); x++) {
			if (Integer.parseInt(zoznam.get(x).getPoradie()) <= (Integer
					.parseInt(start_por))) {
				String dateStr = zoznam.get(x).getStart();

				java.sql.Time timeValue = null;
				try {
					timeValue = new java.sql.Time(formatter.parse(dateStr)
							.getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cl.add(Calendar.HOUR, timeValue.getHours());
				cl.add(Calendar.MINUTE, timeValue.getMinutes());
				Log.e("vysledny cas startu", srcDf.format(cl.getTime()));
				odjazd = srcDf.format(cl.getTime());
			}
		}
		cl = Calendar.getInstance();
		cl.setTime(convertedDate);
		for (int x = 0; x < zoznam.size(); x++) {
			if (Integer.parseInt(zoznam.get(x).getPoradie()) <= (Integer
					.parseInt(ciel_por))) {
				String dateStr = zoznam.get(x).getStart();

				java.sql.Time timeValue = null;
				try {
					timeValue = new java.sql.Time(formatter.parse(dateStr)
							.getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cl.add(Calendar.HOUR, timeValue.getHours());
				cl.add(Calendar.MINUTE, timeValue.getMinutes());
				Log.e("vysledny cas ciela", srcDf.format(cl.getTime()));
				prichod = srcDf.format(cl.getTime());
			}
		}
	}

	private void getMesta() {
		new mestecka(this, this).execute(Rides_driver.Jazda_ID);

	}

	public void setList(List<DriverInsertSpoj> result) {
		zoznam = result;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < result.size(); i++) {
			for (int x = 0; x < Rides_driver.mesta.size(); x++) {
				if (result.get(i).getMesto().toString()
						.equals(Rides_driver.mesta.get(x).getId().toString()))
					list.add(Rides_driver.mesta.get(x).getMesto().toString());
			}
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(dataAdapter);
		spinner2.setAdapter(dataAdapter);

	}
}
