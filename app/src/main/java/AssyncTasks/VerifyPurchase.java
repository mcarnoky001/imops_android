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

public class VerifyPurchase extends AsyncTask<String, Integer, Boolean> {

    String json;
    InputStream is = null;
    String result = null;
    String line = null;
    private Boolean back;

    private Context mContext;

    public VerifyPurchase(Context context) {
        mContext = context;
    }

    public void select() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("json",json));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://192.168.100.4:8080/php/verifyPurchase.php");
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
            if(result.equals("false"+"\n")) {
                back = false;
            }
            else{
                back = true;
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }

    }

    protected Boolean doInBackground(String... params) {
        json = params[0];
        select();
        return back;
    }

    protected void onPostExecute(Boolean result) {
        // use the result
        if (result == false) {
            Toast.makeText(mContext, "Bad login or password",
                    Toast.LENGTH_SHORT).show();
        }

    };
}
