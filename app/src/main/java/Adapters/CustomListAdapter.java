package Adapters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Objects.Spoj;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capps.smartbus.R;
import com.capps.smartbus.Rezervacia_cas;

public class CustomListAdapter extends ArrayAdapter {

	private Context mContext;
	private int id;
	private List<String> items;
	String cas, Timestamp;
	int farba = 0;

	public CustomListAdapter(Context context, int textViewResourceId,
			List<String> list) {
		super(context, textViewResourceId, list);
		mContext = context;
		id = textViewResourceId;
		items = list;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View mView = v;
		if (mView == null) {
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mView = vi.inflate(id, null);
		}
		Log.e("getView", "zaciatok");
		TextView text = (TextView) mView.findViewById(R.id.textView83);
		String tex = items.get(position);
		Log.e("listview tex", tex);
		for (int i = 0; i < Rezervacia_cas.spoje.size(); i++) {
			Spoj obj = Rezervacia_cas.spoje.get(i);
			String tes = obj.getText();
			Log.e("z array tes", tes);
			if (tes.equals(tex)) {
				Log.e("for", "zhoda textov");
				cas = obj.getOdchod();
				skontroluj();
				Log.e("for", "skontrolovalo");
				if (farba == 1) {
					Log.e("farba", "jedna");
					if (items.get(position) != null) {
						Log.e("menim na zelene", tes);
						text.setTextColor(Color.WHITE);
						text.setText(items.get(position));
						mView.setBackgroundColor(Color.GREEN);
						int color = Color.argb(200, 0, 204, 0);
						text.setBackgroundColor(color);
						break;
					}
				} else {
					if (items.get(position) != null) {
						Log.e("menim na cervene", tes);
						text.setTextColor(Color.WHITE);
						text.setText(items.get(position));
						mView.setBackgroundColor(Color.RED);
						int color = Color.argb(200, 255, 64, 64);
						text.setBackgroundColor(color);
						break;
					}
				}
			}
		}

		return mView;
	}

	protected void skontroluj() {
		// TODO Auto-generated method stub
		SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		final Calendar c = Calendar.getInstance();
		Timestamp = f.format(c.getTime());
		Date teraz = null;
		try {
			teraz = f.parse(Timestamp);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Log.e("cas", teraz.toString());
		String dateStr = cas;
		DateFormat srcDf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date date = null;
		try {
			date = srcDf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.e("cas", date.toString());
		if (date.before(teraz)) {
			farba = 0;
			Log.e("farba", "0");
		} else {
			farba = 1;
			Log.e("farba", "1");
		}

	}

}
