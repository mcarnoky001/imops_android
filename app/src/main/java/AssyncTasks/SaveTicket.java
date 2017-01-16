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

public class SaveTicket extends AsyncTask<String, Integer, Boolean> {

	String z, cas, cena, benefit;
	String ciel, pouz, jazda, typ, miesto;
	boolean vysledok = false;
	InputStream is = null;
	String result = null, prichod;
	String line = null;
	String heslo;
	boolean stav = false;
	ArrayList<Spoj> spoje;

	private Context mContext;

	public SaveTicket(Context context) {
		mContext = context;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("z", z));
		nameValuePairs.add(new BasicNameValuePair("ciel", ciel));
		nameValuePairs.add(new BasicNameValuePair("pouz", pouz));
		nameValuePairs.add(new BasicNameValuePair("jazda", jazda));
		nameValuePairs.add(new BasicNameValuePair("typ", typ));
		nameValuePairs.add(new BasicNameValuePair("miesto", miesto));
		nameValuePairs.add(new BasicNameValuePair("cas", cas));
		nameValuePairs.add(new BasicNameValuePair("cena", cena));
		nameValuePairs.add(new BasicNameValuePair("benefit", benefit));
		nameValuePairs.add(new BasicNameValuePair("prichod", prichod));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://147.175.145.190/SaveTicket.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e(" SaveTicket 1", "connection success ");
		} catch (Exception e) {
			Log.e(" SaveTicket 1", e.toString());
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);//
			//
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.e(" SaveTicket", "connection success ");
		} catch (Exception e) {
			Log.e(" SaveTicket Fail 2", e.toString());
		}
		Log.e("JSON Parser save ticket", result);

	}

	protected Boolean doInBackground(String... params) {
		pouz = params[0];
		jazda = params[1];
		typ = params[2];
		z = params[3];
		ciel = params[4];
		miesto = params[5];
		cas = params[6];
		cena = params[7];
		benefit = params[8];
		prichod = params[9];
		select();
		return stav;
	}

	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		Log.e(" SaveTicket", "finished ");
	};

}
