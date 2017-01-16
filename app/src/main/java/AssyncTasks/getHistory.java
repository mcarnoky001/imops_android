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

import Objects.Historia;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.History;

public class getHistory extends AsyncTask<String, Integer, List<Historia>> {

	String meno;
	String ciel;
	boolean vysledok = false;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	boolean stav = false;
	List<Historia> cisla;
	History obj;

	private Context mContext;

	public getHistory(Context context, History obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("meno", meno));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/getHistory.php");
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
			cisla = new ArrayList<Historia>();
			JSONArray jsonArray = new JSONArray(result);

			for (int i = 0; i < jsonArray.length(); i++) {
				String typ = "";
				switch (jsonArray.getJSONObject(i).get("typ_listka").toString()) {
				case "1":
					typ = "Normalny";
					break;
				case "2":
					typ = "Študentský";
					break;
				case "3":
					typ = "Dôchodcovský";
					break;
				case "4":
					typ = "ZŤP";
					break;

				default:
					break;
				}
				String text = " Cas:"
						+ jsonArray.getJSONObject(i).get("cas").toString()
						+ "Z:" + jsonArray.getJSONObject(i).get("z").toString()
						+ " Do:"
						+ jsonArray.getJSONObject(i).get("do").toString()
						+ " Typ:" + typ + " Cena:"
						+ jsonArray.getJSONObject(i).get("cena").toString();
				Historia obj = new Historia(jsonArray.getJSONObject(i)
						.get("idlistok").toString(), jsonArray.getJSONObject(i)
						.get("pouz").toString(), jsonArray.getJSONObject(i)
						.get("jazda").toString(), jsonArray.getJSONObject(i)
						.get("typ_listka").toString(), jsonArray
						.getJSONObject(i).get("miesto").toString(), jsonArray
						.getJSONObject(i).get("z").toString(), jsonArray
						.getJSONObject(i).get("do").toString(), jsonArray
						.getJSONObject(i).get("cas").toString(), jsonArray
						.getJSONObject(i).get("cena").toString(), jsonArray
						.getJSONObject(i).get("nastupil").toString(), jsonArray
						.getJSONObject(i).get("benefit").toString(), text);
				cisla.add(obj);
			}

			Log.e(" cas hist 2", "connection success ");
		} catch (Exception e) {
			Log.e(" cas hist 2", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<Historia> doInBackground(String... params) {
		meno = params[0];
		select();
		return cisla;
	}

	protected void onPostExecute(List<Historia> result) {
		try {
			super.onPostExecute(result);
			obj.setList(result);
		} catch (Exception x) {
			Log.e("chyba getHistory", x.toString());
		}
	};

}
