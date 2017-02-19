package com.capps.imops;

import java.util.concurrent.ExecutionException;

import AssyncTasks.GetInfo;
import AssyncTasks.UpdateAll;
import AssyncTasks.UpdateOnlyUserInfo;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserSetup extends Activity {
	String menoo, priezv, schranka, addresa;
	EditText pass1, pass2, name, last, addr, email;
	Button button1;
	String meno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setup);
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		meno = sharedpreferences.getString("meno", null);
		pass1 = (EditText) findViewById(R.id.editText1);
		pass2 = (EditText) findViewById(R.id.editText2);
		name = (EditText) findViewById(R.id.editText3);
		last = (EditText) findViewById(R.id.editText4);
		addr = (EditText) findViewById(R.id.editText5);
		email = (EditText) findViewById(R.id.textView22);
		button1 = (Button) findViewById(R.id.button1);

		try {
			if (new GetInfo(this, this).execute(meno).get()) {
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!name.getText().toString().matches("")
						&& !last.getText().toString().matches("")
						&& !addr.getText().toString().matches("")
						&& !email.getText().toString().matches("")) {
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
		new UpdateOnlyUserInfo(this).execute(meno, name.getText().toString(),
				last.getText().toString(), addr.getText().toString(), email
						.getText().toString());
		SharedPreferences prefs = getSharedPreferences("MojeNastavenia",
				MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putBoolean("nastavene", true);
		editor.commit();
	}

	private void update2() {
		new UpdateAll(this).execute(meno, name.getText().toString(), last
				.getText().toString(), addr.getText().toString(), email
				.getText().toString(), pass1.getText().toString());
		SharedPreferences prefs = getSharedPreferences("MojeNastavenia",
				MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putBoolean("nastavene", true);
		editor.commit();

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
		addr.setText(addresa);
		email.setText(schranka);

	}
}
