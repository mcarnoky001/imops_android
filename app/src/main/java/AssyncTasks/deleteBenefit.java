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

import Objects.Spoj;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class deleteBenefit extends AsyncTask<String, Integer, Boolean> {

	String z, cas, cena, benefit, meno, id;
	String ciel, pouz, jazda, typ, miesto;
	boolean vysledok = false;
	Float cislo;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	ArrayList<Spoj> spoje;

	private Context mContext;

	public deleteBenefit(Context context) {
		mContext = context;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("id", id));
		nameValuePairs.add(new BasicNameValuePair("benefit", benefit));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://147.175.145.190/deleteBenefit.php");
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
			Log.e(" cas pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected Boolean doInBackground(String... params) {
		id = params[0];
		benefit = params[1];
		select();
		return stav;
	}

	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
	};

}
