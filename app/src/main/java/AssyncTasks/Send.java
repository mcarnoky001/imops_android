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
import android.widget.Toast;

public class Send extends AsyncTask<String, Integer, Boolean> {

	String meno;
	String heslo;
	InputStream is = null;
	String result = null;
	String line = null;
	Integer code;
	private String email;
	private boolean flag = false;

	private Context mContext;

	public Send(Context context) {
		mContext = context;
	}

	public void insert() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("meno", meno));
		nameValuePairs.add(new BasicNameValuePair("heslo", heslo));
		nameValuePairs.add(new BasicNameValuePair("email", email));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://147.175.145.190/insert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
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
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}
		Log.d("JSON Parser", result);
		try {
			JSONObject json_data = new JSONObject(result);
			code = (json_data.getInt("code"));
			if (code == 1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}

	}

	protected Boolean doInBackground(String... params) {
		meno = params[0];
		heslo = params[1];
		email = params[2];
		insert();
		publishProgress(code);
		return flag;
	}

	protected void OnProgressUpdate(Integer... p) {
		if (p[0] == 1) {
			if (code == 1) {
				Toast.makeText(mContext, "Inserted Successfully",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(mContext, "Sorry, Try Again", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	protected void onPostExecute(Boolean result) {
		// use the result
		// super.onPostExecute(result);
	};
}
