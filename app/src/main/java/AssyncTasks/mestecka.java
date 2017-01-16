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

import Objects.DriverInsertSpoj;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.VyberMestaDriver;

public class mestecka extends
		AsyncTask<String, Integer, List<DriverInsertSpoj>> {

	String trasa;
	VyberMestaDriver obj;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	ArrayList<DriverInsertSpoj> spoje;

	private Context mContext;

	public mestecka(Context context, VyberMestaDriver obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("trasa", trasa));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/mestecka.php");
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
			spoje = new ArrayList<DriverInsertSpoj>();
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				DriverInsertSpoj obj = new DriverInsertSpoj("", jsonArray
						.getJSONObject(i).get("poradie").toString(), jsonArray
						.getJSONObject(i).get("mesto").toString(), jsonArray
						.getJSONObject(i).get("start").toString(), jsonArray
						.getJSONObject(i).get("kilometre").toString());
				spoje.add(obj);
			}

			Log.e(" cas pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<DriverInsertSpoj> doInBackground(String... params) {
		trasa = params[0];
		select();
		return spoje;
	}

	protected void onPostExecute(List<DriverInsertSpoj> result) {
		super.onPostExecute(result);
		obj.setList(result);
	};

}
