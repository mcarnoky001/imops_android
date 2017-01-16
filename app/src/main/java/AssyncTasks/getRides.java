package AssyncTasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import Objects.Rides;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.Rides_driver;

public class getRides extends AsyncTask<String, Integer, List<Rides>> {

	String meno;
	String ciel;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	List<Rides> cisla;
	Rides_driver obj;

	private Context mContext;

	public getRides(Context context, Rides_driver obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("meno", meno));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/getRides.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e(" cas pass 1", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 1", e.toString());
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);//
					//
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			cisla = new ArrayList<Rides>();
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				String text = " Cas:"
						+ jsonArray.getJSONObject(i).get("cas").toString()
						+ "ID jazdy:"
						+ jsonArray.getJSONObject(i).get("idjazda").toString()
						+ "Z:"
						+ jsonArray.getJSONObject(i).get("start").toString()
						+ " Do:"
						+ jsonArray.getJSONObject(i).get("ciel").toString()
						+ "Pocet Miest:"
						+ jsonArray.getJSONObject(i).get("pocet_miest")
								.toString();
				Rides obj = new Rides(jsonArray.getJSONObject(i).get("idjazda")
						.toString(), jsonArray.getJSONObject(i).get("trasa")
						.toString(), jsonArray.getJSONObject(i).get("start")
						.toString(), jsonArray.getJSONObject(i).get("ciel")
						.toString(), jsonArray.getJSONObject(i).get("cas")
						.toString(), jsonArray.getJSONObject(i)
						.get("pocet_miest").toString(), text);
				cisla.add(obj);
			}

			Log.e(" cas hist 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas hist 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<Rides> doInBackground(String... params) {
		meno = params[0];
		select();
		return cisla;
	}

	protected void onPostExecute(List<Rides> result) {
		super.onPostExecute(result);
		obj.setList(result);

	};

}
