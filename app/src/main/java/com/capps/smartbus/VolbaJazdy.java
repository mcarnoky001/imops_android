package com.capps.smartbus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Adapters.CustomListAdapter;
import AssyncTasks.getSedadla_volbaJazdy;
import Objects.Spoj;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VolbaJazdy extends Activity {
	private ListView lv;
	DatePicker datum;
	String Timestamp;
	private static List<String> list = new ArrayList<String>();
	ArrayList<String> myList;
	CustomListAdapter listAdapter;
	int id, limit, rezervovane_pocet = 999;
	public static int ostatok;
	public static String prichod;
	String item;
	int farba = 0;
	Spoj obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volba_jazdy);
		lv = (ListView) findViewById(R.id.listView1);
		myList = (ArrayList<String>) getIntent().getSerializableExtra("list");
		Log.e("myList", Integer.toString(myList.size()));
		Collections.sort(myList);
		listAdapter = new CustomListAdapter(this, R.layout.custom_list, myList);
		lv.setAdapter(listAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView textview = (TextView) findViewById(R.id.textView83);
				item = (String) lv.getAdapter().getItem(position);
				for (int i = 0; i < Rezervacia_cas.spoje.size(); i++) {
					obj = Rezervacia_cas.spoje.get(i);
					String text = obj.getText();
					if (text.equals(item)) {
						skontroluj();
						if (farba == 1) {
							id = obj.getId();

							prichod = obj.getPrichod();
							limit = obj.getPocet();
							otestuj((int) id);
						} else {
							toaster2();
						}

					}
				}

			}

		});

	}

	protected void toaster2() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Ride cannot be accessed anymore",
				Toast.LENGTH_SHORT).show();

	}

	protected void skontroluj() {
		// TODO Auto-generated method stub
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		final Calendar c = Calendar.getInstance();
		Timestamp = f.format(c.getTime());
		Date teraz = null;
		try {
			teraz = f.parse(Timestamp);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.e("cas", teraz.toString());
		String dateStr = obj.getOdchod();
		DateFormat srcDf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date date = null;
		try {
			date = srcDf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("cas", date.toString());
		if (date.before(teraz)) {
			farba = 0;
			Log.e("farba", "0");
		} else {
			farba = 1;
			Log.e("farba", "1");
		}

	}

	protected void toast() {
		Toast.makeText(this, "Sorry,Ride is fully booked. Choose another.",
				Toast.LENGTH_SHORT).show();

	}

	private void otestuj(int id) {
		new getSedadla_volbaJazdy(this, this).execute(Integer.toString(id));
	}

	public void setList(List<Integer> result) {
		rezervovane_pocet = result.size();
		Log.d("rezerv pocet", Integer.toString(rezervovane_pocet));
		Log.d("limit", Integer.toString(limit));
		if (rezervovane_pocet < limit) {
			ostatok = limit - rezervovane_pocet;
			Log.d("ostatok", Integer.toString(ostatok));
			Intent x = new Intent(VolbaJazdy.this, Listok.class);
			x.putExtra("vyber", item);
			startActivity(x);
		} else {
			toast();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myList = myList;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
