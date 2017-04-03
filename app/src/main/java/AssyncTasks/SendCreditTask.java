package AssyncTasks;

import java.io.BufferedReader;
import java.io.IOException;
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

public class SendCreditTask extends AsyncTask<String, Integer, Boolean> {

    String name, meno, priezvisko, adresa, email, id,recepientid,amount;
    String pwd;
    InputStream is = null;
    String result = null;
    String line = null;
    String heslo;
    private boolean back = false;

    private Context mContext;

    public SendCreditTask(Context context) {
        mContext = context;
    }

    public void select() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", id));
        nameValuePairs.add(new BasicNameValuePair("recepientid", recepientid));
        nameValuePairs.add(new BasicNameValuePair("amount", amount));
        String ip = null;
        String port = null;
        try {
            ip = Util.Util.getProperty("ip",mContext);
            port = Util.Util.getProperty("port",mContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+ip+":"+port+"/php/SendCredit.php");
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
        try {
            if(!result.equals("false"+"\n")) {
                back = true;
            }
            else{
                back = false;
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }

    }

    protected Boolean doInBackground(String... params) {
        id = params[0];
        recepientid = params[1];
        amount = params[2];
        select();
        return back;
    }

    protected void onPostExecute(Boolean result) {
        // use the result

    };
}
