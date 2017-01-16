package com.capps.smartbus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Adapters.CustomListAdapterListokDriver;
import AssyncTasks.SetNastupil;
import AssyncTasks.getTicketsDriver;
import AssyncTasks.getUserInfoDriver;
import Objects.Historia;
import Objects.Info;
import Objects.Mesto;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class Listky_driver extends Activity {
	ListView lv;
	public static ArrayList<String> myList;
	CustomListAdapterListokDriver listAdapter;
	public static List<Historia> zoznam;
	ImageButton listok;
	List<Info> info;
	String meno;
	String old;
	String item;
	String texto;
	public static int zostatok;
	public static String jazda;
	public static Historia obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listky_driver);
		TextView nadpis = (TextView) findViewById(R.id.textView1);

		nadpis.setText(Rides_driver.obj.getText());
		lv = (ListView) findViewById(R.id.listView1);
		listok = (ImageButton) findViewById(R.id.imageButton1);
		listok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent launchactivity = new Intent(Listky_driver.this,
						VyberMestaDriver.class);
				startActivity(launchactivity);

			}
		});
		View footerView = ((LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.header, null, false);
		lv.addHeaderView(footerView);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView textview = (TextView) findViewById(R.id.textView83);
				item = (String) lv.getAdapter().getItem(position);
				for (int i = 0; i < zoznam.size(); i++) {
					obj = zoznam.get(i);
					String text = obj.getText();
					if (text.equals(item)) {
						if (obj.getNastupil().equals("nie")) {
							texto = "ano";
						} else {
							texto = "nie";
						}
						nastupil();
						refresh();
					}
				}
				return true;
			}

		});
		myList = new ArrayList<String>();
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		meno = sharedpreferences.getString("meno", null);
		new getUserInfoDriver(this, this).execute(Rides_driver.Jazda_ID);
	}

	protected void nastupil() {
		// TODO Auto-generated method stub
		new SetNastupil(this).execute(obj.getMiesto().toString(), obj
				.getJazda().toString(), obj.getId(), texto);
	}

	public void setList(List<Historia> result) {
		// TODO Auto-generated method stub
		zoznam = result;
		zostatok = Integer.parseInt(Rides_driver.obj.getPocet_miest())
				- zoznam.size();
		for (int i = 0; i < zoznam.size(); i++) {
			Historia obj = zoznam.get(i);
			String meno = najdimeno(obj.getPouz());
			String priezv = najdpriezv(obj.getPouz());
			Log.d("pouz cislo listok", obj.getPouz());
			String z = najdimesto(obj.getZ());
			String ciel = najdimesto(obj.getCiel());
			String text = priezv + " " + meno + " Cas:" + obj.getCas() + " Z:"
					+ z + " Do:" + ciel + " Typ:" + obj.getText() + " Miesto:"
					+ obj.getMiesto() + " Nastúpil:" + obj.getNastupil();
			obj.setText(text);
			myList.add(text);
		}
		Collections.sort(myList);
		listAdapter = new CustomListAdapterListokDriver(this,
				R.layout.custom_list, myList);
		lv.setAdapter(listAdapter);
	}

	private String najdimesto(String z) {
		String vysledok = "";
		for (int i = 0; i < Rides_driver.mesta.size(); i++) {
			Mesto obj = Rides_driver.mesta.get(i);
			if (obj.getId().equals(z)) {
				vysledok = obj.getMesto();
				break;
			}
		}
		return vysledok;
	}

	private String najdpriezv(String z) {
		// TODO Auto-generated method stub
		String vysledok = "";
		for (int i = 0; i < info.size(); i++) {
			Info obj = info.get(i);
			Log.d("najdi priezv info get id", obj.getId().toString());
			if (obj.getId().toString().equals(z)) {
				vysledok = obj.getPriezvisko();
				break;
			}
		}
		return vysledok;
	}

	private String najdimeno(String z) {
		String vysledok = "";
		for (int i = 0; i < info.size(); i++) {
			Info obj = info.get(i);
			if (obj.getId().equals(z)) {
				vysledok = obj.getMeno();
				break;
			}
		}
		return vysledok;
	}

	public void setInfo(List<Info> kk) {
		info = kk;
		new getTicketsDriver(this, this).execute(Rides_driver.Jazda_ID);

	}

	public void refresh() {
		myList = new ArrayList<String>();
		new getTicketsDriver(this, this).execute(Rides_driver.Jazda_ID);
	}
}
