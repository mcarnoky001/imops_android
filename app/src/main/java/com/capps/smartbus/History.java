package com.capps.smartbus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Adapters.CustomListAdapterHistory;
import AssyncTasks.AddBenefit;
import AssyncTasks.DeleteTicket;
import AssyncTasks.getHistory;
import AssyncTasks.getPlaces;
import Objects.Historia;
import Objects.Mesto;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
		new getPlaces(this, this).execute();
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView textview = (TextView) findViewById(R.id.textView83);
				String item = (String) lv.getAdapter().getItem(position);
				for (int i = 0; i < spoje.size(); i++) {
					Historia obj = spoje.get(i);
					String text = obj.getText();
					if (text.equals(item)) {
						pouz = obj.getPouz();
						benefit = obj.getBenefit();
						idcko = obj.getId();
						String time = obj.getCas();
						DateFormat dateFormat = new SimpleDateFormat(
								"dd.MM.yyyy HH:mm");
						Date date = new Date();
						Calendar cal = Calendar.getInstance();
						String strDate = dateFormat.format(cal.getTime());
						try {
							if (dateFormat.parse(strDate).before(
									dateFormat.parse(time))) {
								Log.e("porovnanie casov", "je mensie,cize ok");
								Log.e("current time", dateFormat.parse(strDate)
										.toString());
								Log.e("ticket time", dateFormat.parse(time)
										.toString());
								dialog();
							} else {
								toaster();
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				return true;
			}

		});

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
								vymaz();
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

	protected void vymaz() {
		// TODO Auto-generated method stub
		new DeleteTicket(this).execute(idcko);
		String ben = "-" + benefit;
		new AddBenefit(this).execute(ben, pouz);

	}

	private void getData() {
		SharedPreferences sharedpreferences = getSharedPreferences(
				"MojeNastavenia", Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		String meno = sharedpreferences.getString("meno", null);
		new getHistory(this, this).execute(meno);
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
			String start = najdimesto(obj.getZ());
			String ciel = najdimesto(obj.getCiel());
			String text = " Čas:" + obj.getCas().toString() + "Z:" + start
					+ " Do:" + ciel + " Miesto:" + obj.getMiesto() + " Typ:"
					+ typ + " Cena:" + obj.getCena().toString() + "€";
			zoznam.add(text);
			spoje.get(i).setText(text);
		}
		adapter();

	}

	private String najdimesto(String z) {
		String vysledok = "";
		for (int i = 0; i < mesta.size(); i++) {
			Mesto obj = mesta.get(i);
			if (obj.getId().equals(z)) {
				vysledok = obj.getMesto();
				break;
			}
		}
		return vysledok;
	}

	private void adapter() {
		Collections.sort(zoznam);
		listAdapter = new CustomListAdapterHistory(this, R.layout.custom_list,
				zoznam);
		lv.setAdapter(listAdapter);

	}

	public void setTown(List<Mesto> result) {
		mesta = result;
		getData();
	}
}
