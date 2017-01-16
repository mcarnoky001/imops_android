package com.capps.smartbus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Adapters.CustomListAdapterRidesDriver;
import AssyncTasks.getPlacesDriver;
import AssyncTasks.getRides;
import Objects.Mesto;
import Objects.Rides;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Rides_driver extends Activity {
	ListView lv;
	ArrayList<String> myList;
	CustomListAdapterRidesDriver listAdapter;
	public static List<Rides> zoznam;
	String meno, item;
	public static Rides obj;
	public static String Jazda_ID, cas;
	public static List<Mesto> mesta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rides_driver);
		lv = (ListView) findViewById(R.id.listView1);
		myList = new ArrayList<String>();
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		meno = sharedpreferences.getString("meno", null);
		new getPlacesDriver(this, this).execute();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView textview = (TextView) findViewById(R.id.textView83);
				item = (String) lv.getAdapter().getItem(position);
				for (int i = 0; i < zoznam.size(); i++) {
					obj = zoznam.get(i);
					cas = obj.getCas();
					String text = obj.getText();
					if (text.equals(item)) {
						Jazda_ID = obj.getId();
						Intent x = new Intent(Rides_driver.this,
								Listky_driver.class);
						startActivity(x);
					}
				}

			}

		});
	}

	private void getData() {
		new getRides(this, this).execute(meno);
	}

	public void setList(List<Rides> result) {
		// TODO Auto-generated method stub
		zoznam = result;
		for (int i = 0; i < zoznam.size(); i++) {
			Rides obj = zoznam.get(i);
			String start = najdimesto(obj.getStart());
			String ciel = najdimesto(obj.getCiel());
			String text = " Cas:" + obj.getCas().toString() + " ID jazdy:"
					+ obj.getId().toString() + " Z:" + start + " Do:" + ciel
					+ " Pocet Miest:" + obj.getPocet_miest();
			obj.setText(text);
			myList.add(text);
		}
		Collections.sort(myList);
		listAdapter = new CustomListAdapterRidesDriver(this,
				R.layout.custom_list, myList);
		lv.setAdapter(listAdapter);
	}

	private String najdimesto(String z) {
		String vysledok = "";
		for (int i = 0; i < mesta.size(); i++) {
			Mesto obj = mesta.get(i);
			if (obj.getId().equals(z)) {
				vysledok = obj.getMesto();
				break;
			}
		}
		return vysledok;
	}

	public void setTown(List<Mesto> kk) {
		mesta = kk;
		getData();

	}
}
