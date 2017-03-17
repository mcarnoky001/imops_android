package com.capps.imops;

import java.util.concurrent.ExecutionException;

import AssyncTasks.GetByID;
import AssyncTasks.GetInfo;
import AssyncTasks.UpdateAll;
import AssyncTasks.UpdateOnlyUserInfo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserSetup extends Activity {
	String menoo, priezv, schranka, addresa,id;
	EditText pass1, pass2, name, last;
	Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setup);

		pass1 = (EditText) findViewById(R.id.editText1);
		pass2 = (EditText) findViewById(R.id.editText2);
		name = (EditText) findViewById(R.id.editText3);
		last = (EditText) findViewById(R.id.editText4);
		button1 = (Button) findViewById(R.id.button1);
		Intent intent = getIntent();
		String json = intent.getStringExtra("UserData");
		JSONObject obj = null;
		try {
			obj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			id = obj.getString("_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			JSONObject result = new GetByID(this).execute(id).get();
			if (result !=null) {
				String userName = result.getString("name");
				String userSurName = result.getString("surname");
				name.setText(userName);
				last.setText(userSurName);
			}

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!name.getText().toString().matches("")
						&& !last.getText().toString().matches("")
						) {
					if (pass1.getText().toString().matches("")
							&& pass2.getText().toString().matches("")) {
						update1();
					}
					if ((pass1.getText().toString().matches("") && !pass2
							.getText().toString().matches(""))
							|| (!pass1.getText().toString().matches("") && pass2
									.getText().toString().matches(""))) {
						chyba1();
					}
					if (!pass1.getText().toString().matches("")
							&& !pass2.getText().toString().matches("")) {
						if (pass1.getText().toString()
								.equals(pass2.getText().toString())) {
							update2();
						} else {
							chyba2();
						}
					}

				} else {
					chyba3();
				}
			}
		});

	}

	protected void chyba3() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Fill all user information fields, please",
				Toast.LENGTH_SHORT).show();

	}

	private void chyba1() {
		Toast.makeText(this, "Fill both pass fields, please",
				Toast.LENGTH_SHORT).show();

	}

	private void chyba2() {
		Toast.makeText(this, "Passwords are not same", Toast.LENGTH_SHORT)
				.show();

	}

	private void update1() {
		new UpdateOnlyUserInfo(this).execute(id,name.getText().toString(),
				last.getText().toString());
	}

	private void update2() {
		//
		new UpdateAll(this).execute(id,name.getText().toString(), last
				.getText().toString(), pass1.getText().toString());

	}

	public void Info(String meno, String priezvisko, String email, String adresa) {
		if (meno.equals("null")) {
			menoo = "";
		} else {
			menoo = meno;
		}
		if (priezvisko.equals("null")) {
			priezv = "";
		} else {
			priezv = priezvisko;
		}
		if (email.equals("null")) {
			schranka = "";
		} else {
			schranka = email;
		}
		if (adresa.equals("null")) {
			addresa = "";
		} else {
			addresa = adresa;
		}
		nastavenie();
	}

	private void nastavenie() {
		name.setText(menoo);
		last.setText(priezv);

	}
}
