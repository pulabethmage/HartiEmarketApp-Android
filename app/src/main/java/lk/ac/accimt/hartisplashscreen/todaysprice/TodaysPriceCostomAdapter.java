package lk.ac.accimt.hartisplashscreen.todaysprice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lk.ac.accimt.hartisplashscreen.R;

public class TodaysPriceCostomAdapter extends SimpleAdapter {

    List<String> subcat_name_array;
    List<String> subcat_stockprice_array;
    List<String> subcat_retailprice_array;
    private  Context context;


    public TodaysPriceCostomAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // here you let SimpleAdapter built the view normally.
        View v = super.getView(position, convertView, parent);


        final TextView subcatName = v.findViewById(R.id.custom_todaysprice_subcat_name);
        final TextView subcat_stock_price = v.findViewById(R.id.todays_price_stock_price);
        final TextView subcat_retail_price = v.findViewById(R.id.todays_price_retail_price);


        return v;
    }


}
