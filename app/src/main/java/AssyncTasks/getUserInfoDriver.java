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

import com.capps.smartbus.Listky_driver;

import Objects.Info;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class getUserInfoDriver extends AsyncTask<String, Integer, List<Info>> {

	String jazda;
	String pwd;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	private boolean back = false;
	Listky_driver obj;
	List<Info> list;

	private Context mContext;

	public getUserInfoDriver(Context context, Listky_driver obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("id", jazda));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/getUserInfo.php");
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
			// //
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.d("JSON Parser", result);
			JSONArray arr = new JSONArray(result);
			list = new ArrayList<Info>();
			for (int i = 0; i < arr.length(); i++) {
				Info obj = new Info(arr.getJSONObject(i).getString("idaccount")
						.toString(), arr.getJSONObject(i).getString("meno")
						.toString(), arr.getJSONObject(i)
						.getString("priezvisko").toString());
				list.add(obj);
			}
			Log.e("pass 2 info", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2 info", e.toString());
		}
	}

	protected List<Info> doInBackground(String... params) {
		jazda = params[0];
		select();
		return list;
	}

	protected void onPostExecute(List<Info> kk) {
		// use the result
		try {
			super.onPostExecute(kk);
			obj.setInfo(kk);
		} catch (Exception x) {
			Log.e("chyba getPlaces", x.toString());
		}
	};
}
