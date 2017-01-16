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

import com.capps.smartbus.UserSetup;

public class GetInfo extends AsyncTask<String, Integer, Boolean> {

	String meno;
	String heslo;
	InputStream is = null;
	String result = null;
	String line = null;
	UserSetup obj;
	String email, menoo, priezvisko, adresa;
	private boolean flag = false;

	private Context mContext;

	public GetInfo(Context context, UserSetup obj) {
		mContext = context;
		this.obj = obj;
	}

	public void insert() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("meno", meno));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/GetInfo.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
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
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);
		try {
			JSONObject json_data = new JSONObject(result);
			email = (json_data.getString("email"));
			menoo = (json_data.getString("meno"));
			priezvisko = (json_data.getString("priezvisko"));
			adresa = (json_data.getString("adress"));
			flag = true;
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
			flag = false;
		}

	}

	protected Boolean doInBackground(String... params) {
		meno = params[0];
		insert();
		return flag;
	}

	protected void OnProgressUpdate(Integer... p) {

	}

	protected void onPostExecute(Boolean result) {
		// use the result
		obj.Info(menoo, priezvisko, email, adresa);
		super.onPostExecute(result);
	};
}
