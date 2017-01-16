package AssyncTasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import Objects.Mesto;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.capps.smartbus.Rides_driver;

public class getPlacesDriver extends AsyncTask<String, Integer, List<Mesto>> {

	String name;
	String pwd;
	InputStream is = null;
	String result = null;
	String line = null;
	String heslo;
	private boolean back = false;
	Rides_driver obj;
	List<Mesto> list;

	private Context mContext;

	public getPlacesDriver(Context context, Rides_driver obj) {
		mContext = context;
		this.obj = obj;
	}

	public void select() {

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/GetPlaces.php");
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1 places", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1 places", e.toString());
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
			JSONArray arr = new JSONArray(result);
			list = new ArrayList<Mesto>();
			for (int i = 0; i < arr.length(); i++) {
				Mesto obj = new Mesto(arr.getJSONObject(i)
						.getString("idZoznam_miest").toString(), arr
						.getJSONObject(i).getString("Mesto").toString());
				list.add(obj);
			}
			Log.e("pass 2 places", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2 places", e.toString());
		}
		Log.d("JSON Parser", result);

	}

	protected List<Mesto> doInBackground(String... params) {
		select();
		return list;
	}

	protected void onPostExecute(List<Mesto> kk) {
		// use the result
		try {
			super.onPostExecute(kk);
			obj.setTown(kk);
		} catch (Exception x) {
			Log.e("chyba getPlaces", x.toString());
		}
	};
}
