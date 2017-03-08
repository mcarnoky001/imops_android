package com.capps.imops;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Adapters.CustomListAdapterHistory;
import Objects.Historia;
import Objects.Mesto;

public class History extends Activity {
	CustomListAdapterHistory listAdapter;
	private ListView lv;
	String pouz;
	public static List<Historia> spoje;
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
		spoje = new ArrayList<Historia>();
		mesta = new ArrayList<Mesto>();
		lv = (ListView) findViewById(R.id.listView1);
		View footerView = ((LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
				R.layout.footer_layout, null, false);
		lv.addFooterView(footerView);

	}

	protected void toaster() {
		// TODO Auto-generated method stub
		Toast.makeText(this,
				"ticket could not be deleted because already expired.",
				Toast.LENGTH_LONG).show();
	}

	protected void dialog() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(this)
				.setTitle("Delete entry")
				.setMessage("Are you sure you want to delete this entry?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								zoznam = new ArrayList<String>();
								spoje = new ArrayList<Historia>();
								getData();
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// do nothing
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();

	}

	private void getData() {

	}

	public void setList(List<Historia> result) {
		spoje = result;
		for (int i = 0; i < spoje.size(); i++) {
			Historia obj = spoje.get(i);
			String typ = "";
			switch (obj.getTyp_listka().toString()) {
			case "1":
				typ = "Normalny";
				break;
			case "2":
				typ = "Študentský";
				break;
			case "3":
				typ = "Dôchodcovský";
				break;
			case "4":
				typ = "ZŤP";
				break;

			default:
				break;
			}
			String text = " Čas:" + obj.getCas().toString() + "Z:"
					+ " Do:"  + " Miesto:" + obj.getMiesto() + " Typ:"
					+ typ + " Cena:" + obj.getCena().toString() + "€";
			zoznam.add(text);
			spoje.get(i).setText(text);
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
