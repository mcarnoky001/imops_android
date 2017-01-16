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

import Objects.Spoj;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.Rezervacia_cas;

public class CasPhp extends AsyncTask<String, Integer, List<Spoj>> {

	String cas;
	String z;
	Rezervacia_cas obj;
	String ciel;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	ArrayList<Spoj> spoje;

	private Context mContext;

	public CasPhp(Context context, Rezervacia_cas obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("cas", cas));
		nameValuePairs.add(new BasicNameValuePair("z", z));
		nameValuePairs.add(new BasicNameValuePair("ciel", ciel));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/cas.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
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
			// is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			spoje = new ArrayList<Spoj>();
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				String text = "Odkiaľ/Kam: " + z + "-" + ciel + "\nOdchod:"
						+ jsonArray.getJSONObject(i).get("odchod")
						+ "\nPrichod:"
						+ jsonArray.getJSONObject(i).get("prichod")
						+ "\nDĺžka cesty: "
						+ jsonArray.getJSONObject(i).get("trvanie")
						+ "\nVzdialenosť: "
						+ jsonArray.getJSONObject(i).get("kilometre") + "km";
				Log.e("text", text);
				Spoj obj = new Spoj(jsonArray.getJSONObject(i).get("id")
						.toString(), jsonArray.getJSONObject(i)
						.get("kilometre").toString(), jsonArray
						.getJSONObject(i).get("odchod").toString(), jsonArray
						.getJSONObject(i).get("prichod").toString(), jsonArray
						.getJSONObject(i).get("trvanie").toString(), text,
						jsonArray.getJSONObject(i).get("pocet").toString(), z,
						ciel);
				spoje.add(obj);
			}

			Log.e(" cas pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<Spoj> doInBackground(String... params) {
		cas = params[0];
		z = params[1];
		ciel = params[2];
		select();
		return spoje;
	}

	protected void onPostExecute(List<Spoj> result) {
		super.onPostExecute(result);
		obj.setList(result);
	};

}
