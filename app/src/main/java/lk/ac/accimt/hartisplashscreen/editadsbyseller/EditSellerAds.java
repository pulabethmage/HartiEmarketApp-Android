package lk.ac.accimt.hartisplashscreen.editadsbyseller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.accimt.hartisplashscreen.MainScreenActivity;
import lk.ac.accimt.hartisplashscreen.R;
import lk.ac.accimt.hartisplashscreen.sellerserllinglist.SellerSellingFragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static lk.ac.accimt.hartisplashscreen.AddAverticementFragment.decodeBitmap;


public class EditSellerAds extends Fragment implements Response.Listener<String>,Response.ErrorListener {


    View view;
    String AD_ID,AD_TYPE,AD_UNITP,AD_QTY,AD_UNITS,AD_SELLING_P,AD_EX_DATE,AD_MAIN_CAT_NAME,AD_SUB_CAT_NAME;
 //   Spinner uitsspinner,maincatspinner, mainsumcatspinner;

    //// Main cats Spinner ///
    List<String> listmains;
    List<String> sIdsMainCats;
    String maincats_id;

    //// Sub cats Spinner ///
    List<String> listsubcat;
    List<String> sIdsSubCat;
    String subcats_id;

    TextView ad_title,edit_application_type_title,edit_application_unitprice_title,edit_application_total_qty_title,edit_application_sellingplace_title,edit_application_expiridate_title;


    EditText edit_order_type,edit_order_unitprice,edit_application_total_qty,edit_order_sellingplace,
            edit_order_sellername,maincatspinner,mainsumcatspinner,uitsspinner;

    DatePicker edit_order_expirationdate;

    Button edit_btn;

    String selected_language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_edit_seller_ads, container, false);

        sIdsMainCats = new ArrayList<String>();
        sIdsSubCat = new ArrayList<String>();

        ad_title = view.findViewById(R.id.edit_application_title);

        maincatspinner = view.findViewById(R.id.edit_application_maincat);
        mainsumcatspinner = view.findViewById(R.id.edit_application_subcat);
        uitsspinner = view.findViewById(R.id.edit_application_units);

        edit_order_type = view.findViewById(R.id.edit_application_type);
        edit_order_unitprice = view.findViewById(R.id.edit_application_unitprice);
        edit_application_total_qty = view.findViewById(R.id.edit_application_total_qty);
        edit_order_sellingplace = view.findViewById(R.id.edit_application_sellingplace);
        edit_order_expirationdate = view.findViewById(R.id.edit_application_expiridate);

        ///Textview titles
        edit_application_type_title = view.findViewById(R.id.edit_application_type_title);
        edit_application_unitprice_title = view.findViewById(R.id.edit_application_unitprice_title);
        edit_application_total_qty_title = view.findViewById(R.id.edit_application_total_qty_title);
        edit_application_sellingplace_title = view.findViewById(R.id.edit_application_sellingplace_title);
        edit_application_expiridate_title = view.findViewById(R.id.edit_application_expiridate_title);

        edit_btn =  view.findViewById(R.id.edit_application_submit);

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                updatedate();

            }
        });

        return  view;
    }


    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Sinhala"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("Edit Your Ad");

        }
        else if(selected_language.equals("Tamil")) {
            ((MainScreenActivity) getActivity()).setActionBarTitle("Edit Your Ad");

            edit_application_type_title.setText(R.string.s_ad_applicationform_expirationdate_tamil);
            edit_application_unitprice_title.setText(R.string.s_ad_applicationform_unit_price_tamil);
            edit_application_total_qty_title.setText(R.string.s_ad_applicationform_total_qty_tamil);
            edit_application_sellingplace_title.setText(R.string.s_ad_applicationform_selling_place_tamil);
            edit_application_expiridate_title.setText(R.string.s_ad_applicationform_expirationdate_tamil);
            edit_btn.setText(R.string.s_ad_applicationform_edit_btn_tamil);

        }
        else if(selected_language.equals("English")) {
            ((MainScreenActivity) getActivity()).setActionBarTitle("Edit Your Ad");
            edit_application_type_title.setText(R.string.s_ad_applicationform_type_english);
            edit_application_unitprice_title.setText(R.string.s_ad_applicationform_unit_price_english);
            edit_application_total_qty_title.setText(R.string.s_ad_applicationform_total_qty_english);
            edit_application_sellingplace_title.setText(R.string.s_ad_applicationform_selling_place_english);
            edit_application_expiridate_title.setText(R.string.s_ad_applicationform_expirationdate_english);
            edit_btn.setText(R.string.s_ad_applicationform_edit_btn_english);
        }


        Bundle bundle = getArguments();
        if(bundle != null)
        {


            AD_ID=bundle.getString("AD_ID");

            AD_MAIN_CAT_NAME=bundle.getString("AD_MAIN_CAT_NAME");
            AD_SUB_CAT_NAME=bundle.getString("AD_SUB_CAT_NAME");

            AD_TYPE=bundle.getString("AD_TYPE");
            AD_UNITP=bundle.getString("AD_UNITP");
            AD_QTY=bundle.getString("AD_QTY");
            AD_UNITS=bundle.getString("AD_UNITS");
            AD_SELLING_P=bundle.getString("AD_SELLING_P");
            AD_EX_DATE=bundle.getString("AD_EX_DATE");

            int yyyy = Integer.parseInt(AD_EX_DATE.substring(0,4));
            int mm = Integer.parseInt(AD_EX_DATE.substring(5,7));
            int dd = Integer.parseInt(AD_EX_DATE.substring(8,10)); //2019-11-13

       //    Toast.makeText(getContext(),AD_EX_DATE+" :    "+yyyy+"-"+mm+"-"+dd,Toast.LENGTH_LONG).show();

            ad_title.setText(AD_SUB_CAT_NAME);

            maincatspinner.setText(AD_MAIN_CAT_NAME);
            mainsumcatspinner.setText(AD_SUB_CAT_NAME);
            uitsspinner.setText(AD_UNITS);

            edit_order_type.setText(AD_TYPE);
            edit_order_unitprice.setText(AD_UNITP);
            edit_application_total_qty.setText(AD_QTY);
            edit_order_sellingplace.setText(AD_SELLING_P);

            edit_order_expirationdate.updateDate(yyyy,mm-1,dd);





        }



    }





    public void  updatedate(){


        String up_type = edit_order_type.getText().toString();
        String up_unitprice = edit_order_unitprice.getText().toString();
        String up_totalqty = edit_application_total_qty.getText().toString();
        String up_sellingplace = edit_order_sellingplace.getText().toString();
       // String up_expier_date = edit_order_sellingplace.getText().toString();

        /// Ecpiration datetime Date Picker
        int   day  = edit_order_expirationdate.getDayOfMonth();
        int   month= edit_order_expirationdate.getMonth();
        int   year = edit_order_expirationdate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String up_expier_date = sdf.format(calendar.getTime());
        ///Date Picker


        confirmupdate(AD_ID,up_type,up_unitprice,up_totalqty,up_sellingplace,up_expier_date);





    }

    public void  confirmupdate(String update_ad_id,String update_type,String update_unitprice,String update_totalqty,String update_sellplace,String update_exdate) {

        final String upda_ad_id=update_ad_id;
        final String upda_type=update_type;
        final  String upda_unitprice=update_unitprice;
        final String upda_totalqty=update_totalqty;
        final String upda_sellplace=update_sellplace;
        final String upda_exdate=update_exdate;

        RequestQueue queue = Volley.newRequestQueue(getContext());
        int type = Request.Method.POST;
        String url ="http://vegi.lk/admin/mobileappfiles/adingAdverticment/update.php";

        StringRequest request = new StringRequest(
                type,
                url,
                this,
                this
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String,String> params = new HashMap<>();
                params.put("ad_id",upda_ad_id);
                params.put("ad_type",upda_type);
                params.put("ad_unitprice",upda_unitprice);
                params.put("ad_totalqty",upda_totalqty);
                params.put("ad_sellingplace",upda_sellplace);
                params.put("ad_exdate",upda_exdate);


                return params;
            }
        };

        queue.add(request);


    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {

        Toast.makeText(getContext(),"සාර්ථකයි!",Toast.LENGTH_LONG).show();
        Fragment Sellfragment = new SellerSellingFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,Sellfragment);
        ft.addToBackStack(null);
        ft.commit();

    }




}
