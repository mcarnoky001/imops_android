package AssyncTasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import com.capps.smartbus.Buy_driver;

import Objects.Ceny;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class CenyListkovDriver extends AsyncTask<String, Integer, List<Ceny>> {

	String cas;
	String z;
	Buy_driver obj;
	String ciel;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	ArrayList<Ceny> ceny;

	private Context mContext;

	public CenyListkovDriver(Context context, Buy_driver obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/listky.php");
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e(" cas pass 1", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 1", e.toString());
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);//
			// //
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			ceny = new ArrayList<Ceny>();
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				Ceny obj = new Ceny(jsonArray.getJSONObject(i).get("nazov")
						.toString(), Float.parseFloat(jsonArray
						.getJSONObject(i).get("cena").toString()));
				ceny.add(obj);
			}

			Log.e(" cas pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<Ceny> doInBackground(String... params) {

		select();
		return ceny;
	}

	protected void onPostExecute(List<Ceny> result) {
		super.onPostExecute(result);
		obj.setList(result);
	};

}
