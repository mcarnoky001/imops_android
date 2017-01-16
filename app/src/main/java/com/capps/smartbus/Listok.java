package com.capps.smartbus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import AssyncTasks.CenyListkov;
import AssyncTasks.GetSedadlaListok;
import AssyncTasks.getBenefit;
import Objects.Ceny;
import Objects.Spoj;
import Objects.zaznam_po_listkoch;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class Listok extends Activity {
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
	String meno;
	int pocet_miest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listok);
		cena = (TextView) findViewById(R.id.textView5);
		miesta = (TextView) findViewById(R.id.textView8);
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		meno = sharedpreferences.getString("meno", null);
		tlacidlo = (ImageButton) findViewById(R.id.imageButton1);
		zl = (Button) findViewById(R.id.button1);
		new CenyListkov(this, this).execute();
		addItemsOnSpinner();
		String vyber = (String) getIntent().getStringExtra("vyber");
		for (int i = 0; i < Rezervacia_cas.spoje.size(); i++) {
			Spoj obj = Rezervacia_cas.spoje.get(i);
			String text = obj.getText();
			if (text.equals(vyber)) {
				index = i;
				kilometre = obj.getKilometre();
				pocet = obj.getPocet();
			}
		}
		new GetSedadlaListok(this, this).execute(
				Integer.toString(Rezervacia_cas.spoje.get(index).getId()),
				Rezervacia_cas.spoje.get(index).getOdchod(),
				Rezervacia_cas.spoje.get(index).getPrichod());
		new getBenefit(this).execute(meno);
		zl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (stlacene == false) {
					if (vysledok > benefit_suma) {
						toast_zlava();
						benefit_old = benefit_suma;
						vysledok = vysledok - benefit_suma;
						benefit_suma = 0;
						RefreshPrice();
						RefreshBenefit();
						stlacene = true;
					} else if (vysledok <= benefit_suma) {
						benefit_old = benefit_suma;
						benefit_suma -= vysledok;
						vysledok = 0;
						RefreshPrice();
						RefreshBenefit();
						stlacene = true;

					}
				} else {
					toast_zlava2();
					vysledok = vysledok + benefit_suma;
					benefit_suma = benefit_old;
					RefreshBenefit();
					RefreshPrice();

					stlacene = false;
				}

			}
		});
		tlacidlo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("Tlacidlo", "spustilo sa");
				if (jeden + dva + tri + styri <= VolbaJazdy.ostatok) {
					Log.d("Tlacidlo", "nepresiahol limit ok");
					naplnlist();
					if (pocet == 63) {
						Log.d("Tlacidlo",
								"ide o spoj 63 spustam dalsiu aktivitu");
						Intent intent = new Intent(Listok.this, Sedadla64.class);
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

	protected void toast_zlava() {
		Toast.makeText(this, "Discount applied", Toast.LENGTH_SHORT).show();

	}

	protected void toast_zlava2() {
		Toast.makeText(this, "Discount removed", Toast.LENGTH_SHORT).show();

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
		if (stlacene == true) {
			vysledok = vysledok - benefit_suma;
		}
		Log.e("vysledok suma", Float.toString(vysledok));
		benefit = vysledok / 20;
		RefreshPrice();
		pocitaj_benefity();
	}

	private void pocitaj_benefity() {
		b_dochodca = (dochodca * (float) kilometre) / 20;
		b_normal = (normal * (float) kilometre) / 20;
		b_student = (student * (float) kilometre) / 20;
		b_ztp = (ztp * (float) kilometre) / 20;
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
		new GetSedadlaListok(this, this).execute(
				Integer.toString(Rezervacia_cas.spoje.get(index).getId()),
				Rezervacia_cas.spoje.get(index).getOdchod(),
				Rezervacia_cas.spoje.get(index).getPrichod());
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
		new getBenefit(this).execute(meno);

	}

	public void setListok(List<Integer> result) {
		nieco = result;
		miesta.setText(Integer.toString(Rezervacia_cas.spoje.get(index)
				.getPocet() - nieco.size()));
		if ((Rezervacia_cas.spoje.get(index).getPocet() - nieco.size()) == 0) {
			onBackPressed();
		}
	}

	public void setMiesta(List<Integer> result) {
		nieco = result;
		Log.e("nieco size", Integer.toString(nieco.size()));
		Log.e("pocet miest spoja",
				Integer.toString(Rezervacia_cas.spoje.get(index).getPocet()));
		miesta.setText(Integer.toString(Rezervacia_cas.spoje.get(index)
				.getPocet() - nieco.size()));
		if ((Rezervacia_cas.spoje.get(index).getPocet() - nieco.size()) == 0) {
			onBackPressed();
		}

	}
}
