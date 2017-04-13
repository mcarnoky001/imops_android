package com.capps.imops;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import AssyncTasks.EmailVerify;
import AssyncTasks.ForgotPass;
import AssyncTasks.Get;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity  {

	String heslo;
	String meno;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	EditText email;
	Button email_b;
	Integer typ = 0;
	EditText edit_name;
	EditText edit_pass;
	OnClickListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edit_name = (EditText) findViewById(R.id.meno);
		edit_pass = (EditText) findViewById(R.id.hesielko);
		email = (EditText) findViewById(R.id.editText1);
		email_b = (Button) findViewById(R.id.button3);
		Button login = (Button) findViewById(R.id.button1);
		edit_name.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (edit_name.getText().toString().equals("Login name")) {
					edit_name.setText("");
				}
				return false;
			}
		});
		edit_pass.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (edit_pass.getText().toString().equals("Password")) {
					edit_pass.setText("");
				}
				return false;
			}
		});

		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				meno = edit_name.getText().toString();
				heslo = edit_pass.getText().toString();

				verify();
			}
		});
	}

	public void verify() {
		Get obj = new Get(this);
		try {
			JSONObject result = obj.execute(meno, heslo).get();
			if (result !=null) {
				if(result.getString("accountType").equals("employee")){
					Intent launchactivity = new Intent(MainActivity.this, Home.class);
					launchactivity.putExtra("UserData", result.toString());
					edit_pass.setText("");
					startActivity(launchactivity);
				}
				else if(result.getString("accountType").equals("cashier")){
					Intent launchactivity = new Intent(MainActivity.this, Home_cashier.class);
					launchactivity.putExtra("UserData", result.toString());
					edit_pass.setText("");
					startActivity(launchactivity);
				}
				else{
					Context context = getApplicationContext();
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, "not supported account type", duration);
					toast.show();
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void forgottenPass(View view){
		if(edit_name.getText().toString().equals("")){
			Toast toast = Toast.makeText(getApplicationContext(), "fill your username please", Toast.LENGTH_SHORT);
			toast.show();
		}
		else{
			ForgotPass obj = new ForgotPass(this);
			try {
				Boolean result = obj.execute(edit_name.getText().toString()).get();
				if (result == true) {
					Toast toast = Toast.makeText(getApplicationContext(), "Email with new password succesfully sent.", Toast.LENGTH_SHORT);
					toast.show();
				}
				else{
					Toast toast = Toast.makeText(getApplicationContext(), "Username not found, cannot restore password.", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void error1() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Email not found", Toast.LENGTH_SHORT).show();
	}


}