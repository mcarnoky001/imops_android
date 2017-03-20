package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.capps.imops.History;
import com.capps.imops.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Objects.Historia;

public class CustomListAdapterHistory extends ArrayAdapter {

    private Context mContext;
    private int id;
    private List<String> items;
    String cas, Timestamp;
    int farba = 0;

    public CustomListAdapterHistory(Context context, int textViewResourceId,
                                    List<String> list) {
        super(context, textViewResourceId, list);
        mContext = context;
        id = textViewResourceId;
        items = list;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View mView = v;
        if (mView == null) {
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(id, null);
        }
        Log.e("getView", "zaciatok");
        TextView text = (TextView) mView.findViewById(R.id.textView83);
        String tex = items.get(position);
        Log.e("listview tex", tex);

        text.setTextColor(Color.WHITE);
        text.setText(items.get(position));
        int color = Color.argb(200, 0, 0, 0);
        text.setBackgroundColor(color);


        return mView;
    }

    protected String formatTime(String cas) {
        ;
        String dateStr = cas;
        DateFormat srcDf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;
        try {
            date = srcDf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.toString();

    }

}
