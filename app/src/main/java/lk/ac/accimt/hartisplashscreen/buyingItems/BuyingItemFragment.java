package lk.ac.accimt.hartisplashscreen.buyingItems;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import lk.ac.accimt.hartisplashscreen.R;

import static android.content.Context.MODE_PRIVATE;


public class BuyingItemFragment extends Fragment {

    View view;

    TextView buy_title;

    //for language
    TextView buy_order_type_text,buy_order_unitprice_text,buy_application_total_qty_text,buy_order_district_text,buy_order_govicenter_text,buy_order_sellingplace_text,
            buy_order_sellername_text,buy_order_phonenumber_text,buy_order_expirationdate_text;

    EditText buy_order_type,buy_order_unitprice,buy_application_total_qty,buy_order_units,buy_order_district,buy_order_govicenter,buy_order_sellingplace,
            buy_order_sellername,buy_order_phonenumber,buy_order_expirationdate;

    ImageView image01,image02,image03,buy_phonebutton;

    String selected_language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_buying_item, container, false);
        buy_title = view.findViewById(R.id.buy_order_title);

        buy_order_type = view.findViewById(R.id.buy_order_type);
        buy_order_unitprice = view.findViewById(R.id.buy_order_unitprice);
        buy_application_total_qty = view.findViewById(R.id.buy_application_total_qty);
        buy_order_units = view.findViewById(R.id.buy_order_units);
        buy_order_district = view.findViewById(R.id.buy_order_district);
        buy_order_govicenter = view.findViewById(R.id.buy_order_govicenter);
        buy_order_sellingplace = view.findViewById(R.id.buy_order_sellingplace);
        buy_order_sellername = view.findViewById(R.id.buy_order_sellername);
        buy_order_phonenumber = view.findViewById(R.id.buy_order_phonenumber);
        buy_order_expirationdate = view.findViewById(R.id.buy_order_expirationdate);

        /// For Language
        buy_order_type_text= view.findViewById(R.id.buy_order_type_text);
        buy_order_unitprice_text= view.findViewById(R.id.buy_order_unitprice_text);
        buy_application_total_qty_text= view.findViewById(R.id.buy_application_total_qty_text);
        buy_order_district_text= view.findViewById(R.id.buy_order_district_text);
        buy_order_govicenter_text= view.findViewById(R.id.buy_order_govicenter_text);
        buy_order_sellingplace_text= view.findViewById(R.id.buy_order_sellingplace_text);
        buy_order_sellername_text= view.findViewById(R.id.buy_order_sellername_text);
        buy_order_phonenumber_text= view.findViewById(R.id.buy_order_phonenumber_text);
        buy_order_expirationdate_text= view.findViewById(R.id.buy_order_expirationdate_text);
        ////

        image01 = view.findViewById(R.id.buy_order_image01);
        image02 = view.findViewById(R.id.buy_order_image02);
        image03 = view.findViewById(R.id.buy_order_image03);
        buy_phonebutton=view.findViewById(R.id.buy_phonebutton);


        buy_phonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", buy_order_phonenumber.getText().toString(), null)));

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments() != null) {

            String value = getArguments().getString("ItemName");
            String adId = getArguments().getString("AdId");

            buy_title.setText(value);

            loadalldata(adId);
            loadalldataImages(adId);

        }


        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Tamil"))
        {

            buy_order_type_text.setText(R.string.s_ad_applicationform_type_tamil);
            buy_order_unitprice_text.setText(R.string.s_ad_applicationform_unit_price_tamil);
            buy_application_total_qty_text.setText(R.string.s_ad_applicationform_total_qty_tamil);
            buy_order_district_text.setText(R.string.s_ad_applicationform_selling_district_tamil);
            buy_order_govicenter_text.setText(R.string.s_ad_applicationform_selling_govicenter_tamil);
            buy_order_sellingplace_text.setText(R.string.s_ad_applicationform_selling_place_tamil);
            buy_order_sellername_text.setText(R.string.s_ad_applicationform_seller_name_tamil);
            buy_order_phonenumber_text.setText(R.string.s_ad_applicationform_selling_phoneno_tamil);
            buy_order_expirationdate_text.setText(R.string.s_ad_applicationform_expirationdate_tamil);

        }
        else if(selected_language.equals("English"))
        {

            buy_order_type_text.setText(R.string.s_ad_applicationform_type_english);
            buy_order_unitprice_text.setText(R.string.s_ad_applicationform_unit_price_english);
            buy_application_total_qty_text.setText(R.string.s_ad_applicationform_total_qty_english);
            buy_order_district_text.setText(R.string.s_ad_applicationform_selling_district_english);
            buy_order_govicenter_text.setText(R.string.s_ad_applicationform_selling_govicenter_english);
            buy_order_sellingplace_text.setText(R.string.s_ad_applicationform_selling_place_english);
            buy_order_sellername_text.setText(R.string.s_ad_applicationform_seller_name_english);
            buy_order_phonenumber_text.setText(R.string.s_ad_applicationform_selling_phoneno_english);
            buy_order_expirationdate_text.setText(R.string.s_ad_applicationform_expirationdate_english);

        }





    }


    public void loadalldata(String adi_id)
    {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/alladsdata/all_list_selected_one_item.php?&adid="+adi_id;


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setalldata(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        queue.add(request);


    }

    public void loadalldataImages(String adi_id)
    {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url ="http://vegi.lk/admin/mobileappfiles/alladsdata/all_list_selected_allimages.php?&adid="+adi_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setalldataImages(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        queue.add(request);


    }

    public void setalldata(JSONArray response) {


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                buy_order_type.setText(obj.getString("Type"));
                buy_order_unitprice.setText(obj.getString("Unit_Price"));
                buy_application_total_qty.setText(obj.getString("Quantity"));
                buy_order_units.setText(obj.getString("Units"));
                buy_order_district.setText(obj.getString("DIS_Name"));
                buy_order_govicenter.setText(obj.getString("AGRI_Name"));
                buy_order_sellingplace.setText(obj.getString("Selling_place"));
                buy_order_sellername.setText(obj.getString("Name"));
                buy_order_phonenumber.setText(obj.getString("Mobile_No"));
                buy_order_expirationdate.setText(obj.getString("Expiration_date"));



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }



    }

    public void setalldataImages(JSONArray response) {

         ArrayList<String> adImages = new ArrayList<>();

        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                adImages.add(obj.getString("Image_Url"));


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        if(adImages.size() == 3)
        {
            Glide.with(this).load(adImages.get(0)).into(image01);
            Glide.with(this).load(adImages.get(1)).into(image02);
            Glide.with(this).load(adImages.get(2)).into(image03);

            image01.setBackgroundResource(android.R.color.transparent);
            image02.setBackgroundResource(android.R.color.transparent);
            image03.setBackgroundResource(android.R.color.transparent);

        }
        else if(adImages.size() == 2)
        {
            Glide.with(this).load(adImages.get(0)).into(image01);
            Glide.with(this).load(adImages.get(1)).into(image02);

            image01.setBackgroundResource(android.R.color.transparent);
            image02.setBackgroundResource(android.R.color.transparent);



        }
        else if(adImages.size() == 1)
        {
            Glide.with(this).load(adImages.get(0)).into(image01);
            image01.setBackgroundResource(android.R.color.transparent);

        }




    }


}
