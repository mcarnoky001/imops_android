package com.capps.smartbus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import AssyncTasks.GetSedadla_driver;
import AssyncTasks.SaveTicket;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Sedadla64_driver extends Activity {
	List<Integer> cisla = new ArrayList<>();
	List<Integer> zvolene_sedadla = new ArrayList<>();
	List<Integer> UzObsadene = new ArrayList<>();
	String RetazecUzObsadene = "";
	List<Integer> listky = new ArrayList<>();
	String z, ciel;
	int id;
	boolean dokoncovanie_kontrola = false, tmp = false;
	int zaskrtnute = 0, dovolene = 0, ID, index, normal, student, ztp, dochod;
	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15,
			b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28,
			b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39, b40, b41,
			b42, b43, b44, b45, b46, b47, b48, b49, b50, b51, b52, b53, b54,
			b55, b56, b57, b58, b59, b60, b61, b62, b63, b64, basket;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sedadla64);
		index = Buy_driver.zoznam.get(0).getIndex();
		normal = Buy_driver.zoznam.get(0).getNormal();
		student = Buy_driver.zoznam.get(0).getStudent();
		ztp = Buy_driver.zoznam.get(0).getZtp();
		dochod = Buy_driver.zoznam.get(0).getDochod();
		dovolene = normal + student + ztp + dochod;
		z = VyberMestaDriver.start_c;
		ciel = VyberMestaDriver.ciel_c;
		new GetSedadla_driver(this, this).execute(Rides_driver.Jazda_ID,
				VyberMestaDriver.odjazd, VyberMestaDriver.prichod);
		basket = (Button) findViewById(R.id.button2);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		b3 = (Button) findViewById(R.id.Button03);
		b4 = (Button) findViewById(R.id.Button04);
		b5 = (Button) findViewById(R.id.Button05);
		b6 = (Button) findViewById(R.id.Button06);
		b7 = (Button) findViewById(R.id.Button07);
		b8 = (Button) findViewById(R.id.Button08);
		b9 = (Button) findViewById(R.id.Button09);
		b10 = (Button) findViewById(R.id.Button10);
		b11 = (Button) findViewById(R.id.Button11);
		b12 = (Button) findViewById(R.id.Button12);
		b13 = (Button) findViewById(R.id.Button13);
		b14 = (Button) findViewById(R.id.Button14);
		b15 = (Button) findViewById(R.id.Button15);
		b16 = (Button) findViewById(R.id.Button16);
		b17 = (Button) findViewById(R.id.Button17);
		b18 = (Button) findViewById(R.id.Button18);
		b19 = (Button) findViewById(R.id.Button19);
		b20 = (Button) findViewById(R.id.Button20);
		b21 = (Button) findViewById(R.id.Button21);
		b22 = (Button) findViewById(R.id.Button22);
		b23 = (Button) findViewById(R.id.Button23);
		b24 = (Button) findViewById(R.id.Button24);
		b25 = (Button) findViewById(R.id.Button25);
		b26 = (Button) findViewById(R.id.Button26);
		b27 = (Button) findViewById(R.id.Button27);
		b28 = (Button) findViewById(R.id.Button28);
		b29 = (Button) findViewById(R.id.Button29);
		b30 = (Button) findViewById(R.id.Button30);
		b31 = (Button) findViewById(R.id.Button31);
		b32 = (Button) findViewById(R.id.Button32);
		b33 = (Button) findViewById(R.id.Button33);
		b34 = (Button) findViewById(R.id.Button34);
		b35 = (Button) findViewById(R.id.Button35);
		b36 = (Button) findViewById(R.id.Button36);
		b37 = (Button) findViewById(R.id.Button37);
		b38 = (Button) findViewById(R.id.Button38);
		b39 = (Button) findViewById(R.id.Button39);
		b40 = (Button) findViewById(R.id.Button40);
		b41 = (Button) findViewById(R.id.Button41);
		b42 = (Button) findViewById(R.id.Button42);
		b43 = (Button) findViewById(R.id.Button43);
		b44 = (Button) findViewById(R.id.Button44);
		b45 = (Button) findViewById(R.id.Button45);
		b46 = (Button) findViewById(R.id.Button46);
		b47 = (Button) findViewById(R.id.Button47);
		b48 = (Button) findViewById(R.id.Button48);
		b49 = (Button) findViewById(R.id.Button49);
		b50 = (Button) findViewById(R.id.Button50);
		b51 = (Button) findViewById(R.id.Button51);
		b52 = (Button) findViewById(R.id.Button52);
		b53 = (Button) findViewById(R.id.Button53);
		b54 = (Button) findViewById(R.id.Button104);
		b55 = (Button) findViewById(R.id.Button55);
		b56 = (Button) findViewById(R.id.Button56);
		b57 = (Button) findViewById(R.id.Button57);
		b58 = (Button) findViewById(R.id.Button58);
		b59 = (Button) findViewById(R.id.Button59);
		b60 = (Button) findViewById(R.id.Button60);
		b61 = (Button) findViewById(R.id.Button61);
		b62 = (Button) findViewById(R.id.Button62);
		b63 = (Button) findViewById(R.id.Button63);
		basket.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (zaskrtnute != dovolene) {
					toaster();
				} else {
					dokoncovanie_kontrola = true;
					kontrola_rezervacii();
				}
			}
		});
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 01);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 02);
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 03);
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 04);
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 05);
		b6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 06);
		b7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 07);
		b8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 08);
		b9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 09);
		b10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 10);
		b11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 11);
		b12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 12);
		b13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 13);
		b14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 14);
		b15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 15);
		b16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 16);
		b17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 17);
		b18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 18);
		b19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 19);
		b20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 20);
		b21.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 21);
		b22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 22);
		b23.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 23);
		b24.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 24);
		b25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 25);
		b26.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 26);
		b27.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 27);
		b28.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 28);
		b29.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 29);
		b30.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 30);
		b31.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 31);
		b32.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 32);
		b33.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 33);
		b34.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 34);
		b35.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 35);
		b36.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 36);
		b37.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 37);
		b38.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 38);
		b39.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 39);
		b40.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 40);
		b41.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 41);
		b42.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 42);
		b43.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 43);
		b44.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 44);
		b45.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 45);
		b46.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 46);
		b47.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 47);
		b48.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 48);
		b49.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 49);
		b50.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 50);
		b51.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 51);
		b52.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 52);
		b53.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 53);
		b54.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 54);
		b55.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 55);
		b56.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 56);
		b57.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 57);
		b58.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 58);
		b59.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 59);
		b60.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 60);
		b61.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 61);
		b62.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 62);
		b63.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button b = (Button) v;
				String text = b.getText().toString();
				Drawable obj = b.getBackground();
				if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.green)
								.getConstantState())) {
					if (zaskrtnute < dovolene) {
						b.setBackgroundResource(R.drawable.yellow);
						zaskrtnute++;
						Log.e("zaskrtnute", Integer.toString(zaskrtnute));
						Log.e("farba tlacidla bola", "zelena");
						zvolene_sedadla.add(Integer.parseInt(text));
						Log.e("cislo sedadla :", text);
					}
				} else if (obj.getConstantState().equals(
						getResources().getDrawable(R.drawable.yellow)
								.getConstantState())) {
					b.setBackgroundResource(R.drawable.green);
					zaskrtnute--;
					Log.e("farba tlacidla bola", "zlta");
					Log.e("cislo sedadla :", text);
					for (int i = 0; i < zvolene_sedadla.size(); i++) {
						if (zvolene_sedadla.get(i) == Integer.parseInt(text)) {
							Log.e("cislo sedadla odstranene :", zvolene_sedadla
									.get(i).toString());
							zvolene_sedadla.remove(i);
						}
					}
				}
			}
		});// 63);

	}

	public void setList(List<Integer> result) {
		cisla = result;
		if ((cisla.size() + dovolene) <= Integer.parseInt(Rides_driver.obj
				.getPocet_miest())) {
			for (int i = 0; i < cisla.size(); i++) {
				int cislo = cisla.get(i);
				switch (cislo) {
				case 1:
					b1.setBackgroundResource(R.drawable.red);
					break;
				case 2:
					b2.setBackgroundResource(R.drawable.red);
					break;
				case 3:
					b3.setBackgroundResource(R.drawable.red);
					break;
				case 4:
					b4.setBackgroundResource(R.drawable.red);
					break;
				case 5:
					b5.setBackgroundResource(R.drawable.red);
					break;
				case 6:
					b6.setBackgroundResource(R.drawable.red);
					break;
				case 7:
					b7.setBackgroundResource(R.drawable.red);
					break;
				case 8:
					b8.setBackgroundResource(R.drawable.red);
					break;
				case 9:
					b9.setBackgroundResource(R.drawable.red);
					break;
				case 10:
					b10.setBackgroundResource(R.drawable.red);
					break;
				case 11:
					b11.setBackgroundResource(R.drawable.red);
					break;
				case 12:
					b12.setBackgroundResource(R.drawable.red);
					break;
				case 13:
					b13.setBackgroundResource(R.drawable.red);
					break;
				case 14:
					b14.setBackgroundResource(R.drawable.red);
					break;
				case 15:
					b15.setBackgroundResource(R.drawable.red);
					break;
				case 16:
					b16.setBackgroundResource(R.drawable.red);
					break;
				case 17:
					b17.setBackgroundResource(R.drawable.red);
					break;
				case 18:
					b18.setBackgroundResource(R.drawable.red);
					break;
				case 19:
					b19.setBackgroundResource(R.drawable.red);
					break;
				case 20:
					b20.setBackgroundResource(R.drawable.red);
					break;
				case 21:
					b21.setBackgroundResource(R.drawable.red);
					break;
				case 22:
					b22.setBackgroundResource(R.drawable.red);
					break;
				case 23:
					b23.setBackgroundResource(R.drawable.red);
					break;
				case 24:
					b24.setBackgroundResource(R.drawable.red);
					break;
				case 25:
					b25.setBackgroundResource(R.drawable.red);
					break;
				case 26:
					b26.setBackgroundResource(R.drawable.red);
					break;
				case 27:
					b27.setBackgroundResource(R.drawable.red);
					break;
				case 28:
					b28.setBackgroundResource(R.drawable.red);
					break;
				case 29:
					b29.setBackgroundResource(R.drawable.red);
					break;
				case 30:
					b30.setBackgroundResource(R.drawable.red);
					break;
				case 31:
					b31.setBackgroundResource(R.drawable.red);
					break;
				case 32:
					b32.setBackgroundResource(R.drawable.red);
					break;
				case 33:
					b33.setBackgroundResource(R.drawable.red);
					break;
				case 34:
					b34.setBackgroundResource(R.drawable.red);
					break;
				case 35:
					b35.setBackgroundResource(R.drawable.red);
					break;
				case 36:
					b36.setBackgroundResource(R.drawable.red);
					break;
				case 37:
					b37.setBackgroundResource(R.drawable.red);
					break;
				case 38:
					b38.setBackgroundResource(R.drawable.red);
					break;
				case 39:
					b39.setBackgroundResource(R.drawable.red);
					break;
				case 40:
					b40.setBackgroundResource(R.drawable.red);
					break;
				case 41:
					b41.setBackgroundResource(R.drawable.red);
					break;
				case 42:
					b42.setBackgroundResource(R.drawable.red);
					break;
				case 43:
					b43.setBackgroundResource(R.drawable.red);
					break;
				case 44:
					b44.setBackgroundResource(R.drawable.red);
					break;
				case 45:
					b45.setBackgroundResource(R.drawable.red);
					break;
				case 46:
					b46.setBackgroundResource(R.drawable.red);
					break;
				case 47:
					b47.setBackgroundResource(R.drawable.red);
					break;
				case 48:
					b48.setBackgroundResource(R.drawable.red);
					break;
				case 49:
					b49.setBackgroundResource(R.drawable.red);
					break;
				case 50:
					b50.setBackgroundResource(R.drawable.red);
					break;
				case 51:
					b51.setBackgroundResource(R.drawable.red);
					break;
				case 52:
					b52.setBackgroundResource(R.drawable.red);
					break;
				case 53:
					b53.setBackgroundResource(R.drawable.red);
					break;
				case 54:
					b54.setBackgroundResource(R.drawable.red);
					break;
				case 55:
					b55.setBackgroundResource(R.drawable.red);
					break;
				case 56:
					b56.setBackgroundResource(R.drawable.red);
					break;
				case 57:
					b57.setBackgroundResource(R.drawable.red);
					break;
				case 58:
					b58.setBackgroundResource(R.drawable.red);
					break;
				case 59:
					b59.setBackgroundResource(R.drawable.red);
					break;
				case 60:
					b60.setBackgroundResource(R.drawable.red);
					break;
				case 61:
					b61.setBackgroundResource(R.drawable.red);
					break;
				case 62:
					b62.setBackgroundResource(R.drawable.red);
					break;
				case 63:
					b63.setBackgroundResource(R.drawable.red);
					break;

				default:
					break;
				}
				if (dokoncovanie_kontrola) {
					for (int k = 0; k < zvolene_sedadla.size(); k++) {
						if (zvolene_sedadla.get(k) == cislo) {
							tmp = true;
							zvolene_sedadla.remove(k);
							zaskrtnute--;
							UzObsadene.add(cislo);
							if (RetazecUzObsadene.equals("")) {
								RetazecUzObsadene = Integer.toString(cislo);
							} else {
								RetazecUzObsadene = RetazecUzObsadene + ","
										+ Integer.toString(cislo);
							}
						}
					}
				}
			}
			if (tmp == true && dokoncovanie_kontrola == true) {
				toaster_obsadene();
			} else if (tmp == false && dokoncovanie_kontrola == true) {
				NaplnenieListka();
				// volat vytvorenie listka
				toaster_finish();
				finish();
			}
		} else if (cisla.size() == 63) {
			Toast.makeText(this,
					"Bus ride is fully reserved, please choose another ride",
					Toast.LENGTH_LONG).show();
			onBackPressed();
		} else if (cisla.size() + dovolene > 63) {
			Toast.makeText(
					this,
					"Few seats was reserved while you were picking. You're ticket count is bigger then free seats, please pick less tickets or change ride.",
					Toast.LENGTH_LONG).show();
			onBackPressed();
		}
	}

	private void toaster() {
		if (dovolene > 1) {
			Toast.makeText(
					this,
					"You need to choose " + Integer.toString(dovolene)
							+ " seats", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(
					this,
					"You need to choose " + Integer.toString(dovolene)
							+ " seat", Toast.LENGTH_SHORT).show();
		}
	}

	private void toaster_finish() {
		Toast.makeText(this, "Booking accomplished successfully",
				Toast.LENGTH_SHORT).show();
	}

	private void toaster_obsadene() {
		Toast.makeText(
				this,
				"While you were picking, these seats was reserved: "
						+ RetazecUzObsadene + ". Please pick diffrent ones.",
				Toast.LENGTH_SHORT).show();
	}

	private void NaplnenieListka() {
		DecimalFormat df = new DecimalFormat("#.##");
		String jazda = Rides_driver.Jazda_ID;
		// tu ma byt for a vklada sa typ listka zvlast a miesto
		int a = 0, b = 0, c = 0, d = 0, dokopy = 0;
		for (a = 0; a < normal; a++) {
			// ukladaj normalne
			String miesto = Integer.toString(zvolene_sedadla.get(dokopy));
			new SaveTicket(this).execute(Integer.toString(100), jazda, "1", z,
					ciel, miesto, VyberMestaDriver.odjazd,
					df.format(Buy_driver.c_normal), Integer.toString(0),
					VyberMestaDriver.prichod);
			dokopy++;
		}
		for (b = 0; b < student; b++) {
			// ukladaj student
			String miesto = Integer.toString(zvolene_sedadla.get(dokopy));
			new SaveTicket(this).execute(Integer.toString(100), jazda, "2", z,
					ciel, miesto, VyberMestaDriver.odjazd,
					df.format(Buy_driver.c_student), Integer.toString(0),
					VyberMestaDriver.prichod);
			dokopy++;
		}
		for (c = 0; c < dochod; c++) {
			// ukladaj dochodkove
			String miesto = Integer.toString(zvolene_sedadla.get(dokopy));
			new SaveTicket(this).execute(Integer.toString(100), jazda, "3", z,
					ciel, miesto, VyberMestaDriver.odjazd,
					df.format(Buy_driver.c_dochodca), Integer.toString(0),
					VyberMestaDriver.prichod);
			dokopy++;
		}
		for (d = 0; d < ztp; d++) {
			// ukladaj ztp
			String miesto = Integer.toString(zvolene_sedadla.get(dokopy));
			new SaveTicket(this).execute(Integer.toString(100), jazda, "4", z,
					ciel, miesto, VyberMestaDriver.odjazd,
					df.format(Buy_driver.c_ztp), Integer.toString(0),
					VyberMestaDriver.prichod);
			dokopy++;
		}
	}

	public void kontrola_rezervacii() {
		new GetSedadla_driver(this, this).execute(Rides_driver.Jazda_ID,
				VyberMestaDriver.odjazd, VyberMestaDriver.prichod);
		dokoncovanie_kontrola = true;
	}
}
