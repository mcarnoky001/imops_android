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
import org.json.JSONArray;

import com.capps.smartbus.Sedadla64;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class getPouzID extends AsyncTask<String, Integer, Integer> {

	String meno;
	int cislo;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	Sedadla64 obj;

	private Context mContext;

	public getPouzID(Context context, Sedadla64 obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("meno", meno));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/getUserID.php");
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
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				cislo = Integer.parseInt(jsonArray.getJSONObject(i).get("id")
						.toString());
			}

			Log.e(" cas pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected Integer doInBackground(String... params) {
		meno = params[0];
		select();
		return cislo;
	}

	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		obj.putID(result);
	};

}
