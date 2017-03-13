package com.capps.imops;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Adapters.CustomListAdapterHistory;
import AssyncTasks.Get;
import AssyncTasks.GetByID;
import Objects.Historia;
import Objects.Mesto;

public class History extends Activity {
	CustomListAdapterHistory listAdapter;
	private ListView lv;
	String pouz;
	public JSONArray spoje;
	String time;
	String benefit;
	List<Mesto> mesta;
	ArrayList<String> zoznam;
	String idcko;
	private Context context = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		zoznam = new ArrayList<String>();
		mesta = new ArrayList<Mesto>();
		lv = (ListView) findViewById(R.id.listView1);
		View footerView = ((LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.footer_layout, null, false);
		lv.addFooterView(footerView);
		Intent intent = getIntent();
		String json = intent.getStringExtra("UserData");
		Log.i("UserData:",json.toString());
		String id =null;
		try {
			JSONObject obj = new JSONObject(json);
			id = obj.getString("_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		GetByID obj = new GetByID(this);
		try {
			JSONObject result = obj.execute(id).get();
			if (result !=null) {
				JSONArray purchases = result.getJSONArray("purchases");
				Log.i("Purchases:",purchases.toString());
				setList(purchases);
				//call list append function
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void setList(JSONArray result) {
		spoje = result;
		for (int i = 0; i < spoje.length(); i++) {
			Object obj = null;
			try {
				obj = spoje.get(i);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			String text = obj.toString();
			zoznam.add(text);
		}
		adapter();

	}



	private void adapter() {
		Collections.sort(zoznam);
		listAdapter = new CustomListAdapterHistory(this, R.layout.custom_list,
				zoznam);
		lv.setAdapter(listAdapter);

	}

}
