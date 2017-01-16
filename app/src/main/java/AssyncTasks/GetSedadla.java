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

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.Sedadla64;

public class GetSedadla extends AsyncTask<String, Integer, List<Integer>> {

	String jazda;
	String ciel;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo, cas, prichod;
	boolean stav = false;
	List<Integer> cisla;
	Sedadla64 obj;

	private Context mContext;

	public GetSedadla(Context context, Sedadla64 obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("jazda", jazda));
		nameValuePairs.add(new BasicNameValuePair("cas", cas));
		nameValuePairs.add(new BasicNameValuePair("prichod", prichod));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://147.175.145.190/miesta.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e(" get Sedadla pass 1", "connection success ");
		} catch (Exception e) {
			Log.e(" get Sedadla  FAIL 1", e.toString());
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
			cisla = new ArrayList<>();
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				int cislo = Integer.parseInt(jsonArray.getJSONObject(i)
						.get("miesto").toString());
				cisla.add(cislo);
			}

			Log.e(" get sedadla pass 2", "connection success ");
		} catch (Exception e) {
			Log.e(" get sedadla Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<Integer> doInBackground(String... params) {
		jazda = params[0];
		cas = params[1];
		prichod = params[2];
		select();
		return cisla;
	}

	protected void onPostExecute(List<Integer> result) {
		super.onPostExecute(result);
		Log.e(" get Sedadla", "finished ");
		obj.setList(result);
	};

}
