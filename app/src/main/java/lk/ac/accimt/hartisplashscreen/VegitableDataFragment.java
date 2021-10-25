package lk.ac.accimt.hartisplashscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

import static android.content.Context.MODE_PRIVATE;


public class VegitableDataFragment extends Fragment {


    String fruitNewName,fruitNewImage,fruitNewImageId;

    String SUP_ID,MAIN_CAT_ID,SUB_CAT_ID;

    //Arrays Lists
    private ArrayList<String> adsImages = new ArrayList<>();
    private ArrayList<String> adsTittles= new ArrayList<>();
    private ArrayList<String> adsUnitPrice= new ArrayList<>();
    private ArrayList<String> adsSellerName= new ArrayList<>();
    private ArrayList<String> adsFroms= new ArrayList<>();
    private ArrayList<String> adsGoviCenter= new ArrayList<>();
    private ArrayList<String> adsDatePlaced= new ArrayList<>();
    private ArrayList<String> adsID= new ArrayList<>();
    View view;

    String selected_language;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_vegitable_data, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            MAIN_CAT_ID=bundle.getString("MAIN_ID");
            SUB_CAT_ID=bundle.getString("SUB_CAT_ID");

            loadalldata(MAIN_CAT_ID,SUB_CAT_ID);
        }

    }

    public void initRecyclerSingleAdapter(View view)
    {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.vegi_list_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        AdsRecyclerViewAdapter adapter = new AdsRecyclerViewAdapter(getContext(),adsImages,adsTittles,adsUnitPrice,adsSellerName,adsFroms,adsGoviCenter,adsDatePlaced,adsID);
        recyclerView.setAdapter(adapter);
    }


    public void loadalldata(String main_cat_id, String sub_cat_id)
    {

        String url = null;
        final RequestQueue queue = Volley.newRequestQueue(getContext());

        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Sinhala"))
        {

            url ="http://vegi.lk/admin/mobileappfiles/alladsdata/all_list.php?&maincatid="+main_cat_id+"&subcatid="+sub_cat_id;
        }
        else if(selected_language.equals("Tamil")) {
            url ="http://vegi.lk/admin/mobileappfiles/alladsdata/all_list_tamil.php?&maincatid="+main_cat_id+"&subcatid="+sub_cat_id;
        }
        else if(selected_language.equals("English")) {
            url ="http://vegi.lk/admin/mobileappfiles/alladsdata/all_list_english.php?&maincatid="+main_cat_id+"&subcatid="+sub_cat_id;
        }




        //String url =  dburls.vegitableDataFragmentLoadAllData+main_cat_id+"&subcatid="+sub_cat_id;

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

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();

            }
        });


    }

    public void setalldata(JSONArray response) {


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                adsImages.add(obj.getString("Image_Url"));
                adsTittles.add(obj.getString("sub_catname"));
                adsUnitPrice.add(obj.getString("Unit_Price"));
                adsSellerName.add(obj.getString("Name"));
                adsFroms.add(obj.getString("DIS_Name"));
                adsGoviCenter.add(obj.getString("AGRI_Name"));
                adsDatePlaced.add(obj.getString("AD_Date"));
                adsID.add(obj.getString("Ad_Id"));



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        initRecyclerSingleAdapter(view);



    }




}
