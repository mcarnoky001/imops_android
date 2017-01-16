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
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class FindRoute extends AsyncTask<String, Integer, Boolean> {

	String start;
	String ciel;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	private boolean back = false;

	private Context mContext;

	public FindRoute(Context context) {
		mContext = context;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Log.e("Mesto", ciel);
		nameValuePairs.add(new BasicNameValuePair("start", start));
		nameValuePairs.add(new BasicNameValuePair("ciel", ciel));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/trasa.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e(" trasa pass 1", "connection success ");
		} catch (Exception e) {
			Log.e(" trasa Fail 1", e.toString());
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
			if (result != null) {
				back = true;
			}
			Log.e(" trasa pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" trasa Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);
		try {
			JSONTokener tokener = new JSONTokener(result);
			JSONArray finalResult = new JSONArray(tokener);
			JSONObject json_data = new JSONObject(result);
			heslo = (json_data.getString("trasa"));
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}

	}

	protected Boolean doInBackground(String... params) {
		start = params[0];
		ciel = params[1];
		select();
		return back;
	}

	protected void onPostExecute(Boolean result) {
		// use the result
		if (result == false) {
			Toast.makeText(mContext,
					"There is no connection between the points",
					Toast.LENGTH_SHORT).show();
		} else {
			super.onPostExecute(result);
		}

	};
}
