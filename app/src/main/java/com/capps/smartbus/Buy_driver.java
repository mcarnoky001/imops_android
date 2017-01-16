package com.capps.smartbus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import AssyncTasks.CenyListkovDriver;
import AssyncTasks.GetSedadlaDriver;
import Objects.Ceny;
import Objects.zaznam_po_listkoch;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Buy_driver extends Activity {
	Spinner spinner, spinner1, spinner2, spinner3;
	TextView cena, miesta;
	static TextView suma;
	ImageButton tlacidlo;
	public static List<zaznam_po_listkoch> zoznam = new ArrayList<zaznam_po_listkoch>();
	int kilometre, jeden = 0, dva = 0, tri = 0, styri = 0, pocet = 0;
	static Button zl;
	public static int index;
	public static float b_ztp, b_dochodca, b_normal, b_student;
	public static float c_ztp, c_dochodca, c_normal, c_student, benefit,
			benefit_old = 0;
	float ztp, dochodca, normal, student, vysledok;
	public static float benefit_suma;
	public static boolean stlacene = false;
	List<Ceny> ceny = new ArrayList<Ceny>();
	List<Integer> nieco = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buy_driver);
		cena = (TextView) findViewById(R.id.textView5);
		miesta = (TextView) findViewById(R.id.textView8);
		miesta.setText(Integer.toString(VolbaJazdy.ostatok));
		tlacidlo = (ImageButton) findViewById(R.id.imageButton1);
		new CenyListkovDriver(this, this).execute();
		addItemsOnSpinner();
		kilometre = VyberMestaDriver.kilometre;
		pocet = Integer.parseInt(Rides_driver.obj.getPocet_miest());
		new GetSedadlaDriver(this, this).execute(Rides_driver.Jazda_ID,
				VyberMestaDriver.odjazd, VyberMestaDriver.prichod);
		tlacidlo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("Tlacidlo", "spustilo sa");
				if (jeden + dva + tri + styri <= Listky_driver.zostatok) {
					Log.d("Tlacidlo", "nepresiahol limit ok");
					naplnlist();
					if (pocet == 63) {
						Log.d("Tlacidlo",
								"ide o spoj 63 spustam dalsiu aktivitu");
						Intent intent = new Intent(Buy_driver.this,
								Sedadla64_driver.class);
						startActivity(intent);
					}
				} else {
					toast3();
				}
			}
		});
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onNothingSelected(AdapterView<?> arg0) {
				Log.e("klkl", "klkl");

			}

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView textview = (TextView) findViewById(R.id.textView22);
				String item = (String) spinner.getAdapter().getItem(arg2);
				jeden = Integer.parseInt(item);
				Log.d("spinner", item);
				pocitaj();

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
				String item = (String) spinner.getAdapter().getItem(arg2);
				dva = Integer.parseInt(item);
				Log.d("spinner1", item);
				pocitaj();

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
				String item = (String) spinner.getAdapter().getItem(arg2);
				tri = Integer.parseInt(item);
				Log.d("spinner2", item);
				pocitaj();

			}
		});
		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView textview = (TextView) findViewById(R.id.textView22);
				String item = (String) spinner.getAdapter().getItem(arg2);
				styri = Integer.parseInt(item);
				Log.d("spinner3", item);
				pocitaj();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected void toast3() {
		Toast.makeText(this, "Cannot buy more tickets than the remainig",
				Toast.LENGTH_SHORT).show();

	}

	private void addItemsOnSpinner() {

		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner1 = (Spinner) findViewById(R.id.Spinner01);
		spinner2 = (Spinner) findViewById(R.id.Spinner02);
		spinner3 = (Spinner) findViewById(R.id.Spinner03);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= 64; i++) {
			list.add(Integer.toString(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		spinner1.setAdapter(dataAdapter);
		spinner2.setAdapter(dataAdapter);
		spinner3.setAdapter(dataAdapter);
	}

	private void RefreshPrice() {

		String vysl = String.format("%.02f", vysledok);
		cena.setText(vysl);
	}

	private void pocitaj() {
		vysledok = (float) jeden * (normal * (float) kilometre) + (float) dva
				* (student * (float) kilometre) + (float) tri
				* (dochodca * (float) kilometre) + (float) styri
				* (ztp * (float) kilometre);
		Log.e("vysledok suma", Float.toString(vysledok));
		RefreshPrice();
		pocitaj_benefity();
	}

	private void pocitaj_benefity() {
		c_dochodca = (dochodca * (float) kilometre);
		c_normal = (normal * (float) kilometre);
		c_student = (student * (float) kilometre);
		c_ztp = (ztp * (float) kilometre);

	}

	public void setList(List<Ceny> result) {
		ceny = result;
		for (int i = 0; i < ceny.size(); i++) {
			Ceny obj = ceny.get(i);
			String text = obj.getMeno();
			Log.e("cena", text);
			switch (text) {
			case "normalny":
				normal = obj.getHodnota();
				break;
			case "dochodcovsky":
				dochodca = obj.getHodnota();
				break;
			case "studentsky":
				student = obj.getHodnota();
				break;
			case "ztp":
				ztp = obj.getHodnota();
				break;
			default:
				break;
			}
		}

	}

	public void naplnlist() {
		zaznam_po_listkoch obj = new zaznam_po_listkoch(styri, dva, tri, jeden,
				index, vysledok);
		zoznam.add(obj);
	}

	public static void setBenefit(Float Benefit) {
		benefit_suma = Benefit;
		DecimalFormat df = new DecimalFormat("#.##");
		zl.setText(df.format(benefit_suma) + "€");
		zl.setTextColor(Color.RED);

	}

	public void RefreshBenefit() {

		DecimalFormat df = new DecimalFormat("#.##");
		zl.setText(df.format(benefit_suma) + "€");
		zl.setTextColor(Color.RED);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new GetSedadlaDriver(this, this).execute(Rides_driver.Jazda_ID,
				VyberMestaDriver.odjazd, VyberMestaDriver.prichod);
		if (zoznam.size() == 1) {
			zoznam.remove(0);
		}
		spinner.setSelection(0);
		spinner1.setSelection(0);
		spinner2.setSelection(0);
		spinner3.setSelection(0);
		benefit_old = 0;
		benefit_suma = 0;
		benefit = 0;
		vysledok = 0;

	}

	public void setMiesta(List<Integer> result) {
		nieco = result;
		miesta.setText(Integer.toString(Integer.parseInt(Rides_driver.obj
				.getPocet_miest()) - nieco.size()));
		if ((Integer.parseInt(Rides_driver.obj.getPocet_miest()) - nieco.size()) == 0) {
			onBackPressed();
		}
	}

	public void setListok(List<Integer> result) {
		// TODO Auto-generated method stub

	}
}
