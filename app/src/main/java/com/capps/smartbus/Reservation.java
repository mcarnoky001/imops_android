package com.capps.smartbus;

import java.util.concurrent.ExecutionException;

import AssyncTasks.FindRoute;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Reservation extends Activity {

	EditText z;
	EditText ciel;
	ImageButton op;
	Button vymena;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservation);
		z = (EditText) findViewById(R.id.editText1);
		ciel = (EditText) findViewById(R.id.editText2);
		op = (ImageButton) findViewById(R.id.imageButton8);
		vymena = (Button) findViewById(R.id.button1);
		op.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SharedPreferences prefs = getSharedPreferences(
						"MojeNastavenia", Context.MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putString("z", z.getText().toString());
				editor.putString("do", ciel.getText().toString());
				editor.commit();
				spusti();

			}

		});
		vymena.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String tmp = z.getText().toString();
				String tmp2 = ciel.getText().toString();
				z.setText(tmp2);
				ciel.setText(tmp);
			}

		});
	}

	private void spusti() {
		try {
			if (new FindRoute(this).execute(z.getText().toString(),
					ciel.getText().toString()).get()) {

				Intent launchactivity = new Intent(Reservation.this,
						Rezervacia_cas.class);
				startActivity(launchactivity);
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
