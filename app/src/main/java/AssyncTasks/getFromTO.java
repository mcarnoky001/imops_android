package AssyncTasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.Sedadla64;

public class getFromTO extends AsyncTask<String, Integer, Boolean> {

	String z;
	String ciel;
	InputStream is = null;
	String result = null;
	String line = null;
	String start, finish;
	private boolean back = false;
	Sedadla64 obj;

	private Context mContext;

	public getFromTO(Context context, Sedadla64 obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("z", z));
		nameValuePairs.add(new BasicNameValuePair("ciel", ciel));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://147.175.145.190/GetFromTO.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("getfromto 1", "connection success ");
		} catch (Exception e) {
			Log.e("getfromto", e.toString());
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
			Log.e("getfromto", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2 getfromto", e.toString());
		}
		Log.d("JSON Parser", result);
		try {
			JSONObject json_data = new JSONObject(result);
			start = (json_data.getString("start"));
			finish = (json_data.getString("ciel"));
		} catch (Exception e) {
			Log.e("Fail 3 getfromto", e.toString());
		}

	}

	protected Boolean doInBackground(String... params) {
		z = params[0];
		ciel = params[1];
		Log.d("getfromto Z", z);
		Log.d("getfromto ciel", ciel);
		select();
		return back;
	}

	protected void onPostExecute(Boolean result) {
		// use the result
		obj.putTowns(start, finish);
		Log.d("getfromto mesta", z + ciel);

	};
}
