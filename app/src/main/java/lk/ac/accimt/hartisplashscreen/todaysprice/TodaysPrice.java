package lk.ac.accimt.hartisplashscreen.todaysprice;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lk.ac.accimt.hartisplashscreen.MainScreenActivity;
import lk.ac.accimt.hartisplashscreen.R;

import static android.content.Context.MODE_PRIVATE;


public class TodaysPrice extends Fragment implements Response.Listener<String>,Response.ErrorListener{

    View view;

    TextView todayPrice_eco_center_id_title,todayPrice_maincat_id_title;

    Spinner economic_centers, main_cats;
    List<String> economiclist_id;
    List<String> economicCenterst;

    List<String> maincat_id;
    List<String> mainCategories;

    List<String> subcat_name_array;
    List<String> subcat_stockprice_array;
    List<String> subcat_retailprice_array;

    ListView subcatListView;

    String selected_language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_todays_price, container, false);

        todayPrice_eco_center_id_title = view.findViewById(R.id.todayPrice_eco_center_id_title);
        todayPrice_maincat_id_title= view.findViewById(R.id.todayPrice_maincat_id_title);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Sinhala"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("අද මිල");

        }
        else if(selected_language.equals("Tamil")) {
            ((MainScreenActivity) getActivity()).setActionBarTitle("Today Price");
            todayPrice_eco_center_id_title.setText(R.string.s_todays_price_economic_center_tamil);
            todayPrice_maincat_id_title.setText(R.string.s_todays_price_maincat_tamil);

        }
        else if(selected_language.equals("English")) {
            ((MainScreenActivity) getActivity()).setActionBarTitle("Today Price");
            todayPrice_eco_center_id_title.setText(R.string.s_todays_price_economic_center_english);
            todayPrice_maincat_id_title.setText(R.string.s_todays_price_maincat_english);

        }


        ///Declaring Spinners
        economic_centers = view.findViewById(R.id.todayPrice_eco_center_id);
        main_cats = view.findViewById(R.id.todayPrice_maincat_id);

        // Declaring List View
        subcatListView = view.findViewById(R.id.todayPrice_alldata);


        /// Spinner Arrays
        economiclist_id = new ArrayList<String>();
        economicCenterst = new ArrayList<String>();

        maincat_id = new ArrayList<String>();
        mainCategories = new ArrayList<String>();
        /// Spinner Arrays

        //// Price Datea Array
        subcat_name_array = new ArrayList<String>();
        subcat_stockprice_array = new ArrayList<String>();
        subcat_retailprice_array = new ArrayList<String>();

        ////


        main_cats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Integer ecoCenterPosition = economic_centers.getSelectedItemPosition();
                Integer maincatPosition = main_cats.getSelectedItemPosition();

                String ecoCenterRealID = economiclist_id.get(ecoCenterPosition);
                String mainCatRealId = maincat_id.get(maincatPosition);


                if(selected_language.equals("Sinhala"))
                {
                    loadAllSubCats("sub_cats_using_eco_n_maincat.php",ecoCenterRealID,mainCatRealId);
                }
                else if(selected_language.equals("Tamil")) {

                    loadAllSubCats("sub_cats_using_eco_n_maincat_tamil.php",ecoCenterRealID,mainCatRealId);
                }
                else if(selected_language.equals("English")) {
                    loadAllSubCats("sub_cats_using_eco_n_maincat_english.php",ecoCenterRealID,mainCatRealId);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(selected_language.equals("Sinhala"))
        {
            loadEconomicCenters("all_list.php");
            loadMainCats("all_list_main_cats.php");

        }
        else if(selected_language.equals("Tamil")) {

            loadEconomicCenters("all_list_tamil.php");
            loadMainCats("all_list_main_cats_tamil.php");

        }
        else if(selected_language.equals("English")) {
            loadEconomicCenters("all_list_english.php");
            loadMainCats("all_list_main_cats_english.php");

        }


    }

    public void loadEconomicCenters(String filename)
    {
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/alleconomiccenters/"+filename;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setEconomicCenters(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        queue.add(request);

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();

            }
        });



    }

    public void loadMainCats(String filename)
    {
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/alleconomiccenters/"+filename;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setMainCats(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        queue.add(request);

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();

            }
        });



    }

    public void loadAllSubCats(String filename,String ecoId,String mainId)
    {
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/alleconomiccenters/"+filename+"?ecocenter="+ecoId+"&maincatid="+mainId;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setSubCatsPrices(response);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        queue.add(request);

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();

            }
        });



    }



    //////////// SETTING ////////////////////
    public void setEconomicCenters(JSONArray response) {

        //add ids in this list


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                String  economic_center_id = obj.getString("economic_center_id");
                String economic_center_name = obj.getString("economic_center_name");
                economiclist_id.add(economic_center_id);
                economicCenterst.add(economic_center_name);



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, economicCenterst);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        economic_centers.setAdapter(dataAdapter);

    }

    public void setMainCats(JSONArray response) {


        //add ids in this list


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                String  main_cat_id = obj.getString("main_cat_id");
                String main_cat_name = obj.getString("main_cat_name");
                maincat_id.add(main_cat_id);
                mainCategories.add(main_cat_name);



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, mainCategories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        main_cats.setAdapter(dataAdapter);

    }

    public void setSubCatsPrices(JSONArray response) {

        //add ids in this list
        List<HashMap<String,String>> list = new ArrayList<>();

        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                HashMap<String,String> map = new HashMap<>();

                map.put("sub_catname",obj.getString("sub_catname"));
                map.put("stock_price",obj.getString("stock_price"));
                map.put("retail_price",obj.getString("retail_price"));

                list.add(map);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        //1. Custom layout
        int layout = R.layout.custom_todays_price_list;
        //2. View array
        int[] views = {R.id.custom_todaysprice_subcat_name,R.id.todays_price_stock_price,R.id.todays_price_retail_price};
        //3. Columns Strings
        String[] cols = {"sub_catname","stock_price","retail_price"};

        ListAdapter adapter = new TodaysPriceCostomAdapter(view.getContext(),list,layout,cols,views);

        subcatListView.setAdapter(adapter);



    }




    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }
}
