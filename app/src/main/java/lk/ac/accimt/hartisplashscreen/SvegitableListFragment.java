package lk.ac.accimt.hartisplashscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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


public class SvegitableListFragment extends Fragment {


    //Arrays Lists
    private ArrayList<String> sImages = new ArrayList<>();
    private ArrayList<String> sImageNames = new ArrayList<>();
    private ArrayList<String> sImageId = new ArrayList<>();
    private ArrayList<String> sImageMainCatID = new ArrayList<>();

    String FROM_MAIN_CAT_ID,FROM_MAIN_CAT_NAME;

    View view;

    String selected_language;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_svegitable_list, container, false);

        Bundle bundle = getArguments();
         FROM_MAIN_CAT_ID=bundle.getString("FROM_MAIN_CAT_ID");
         FROM_MAIN_CAT_NAME=bundle.getString("FROM_MAIN_CAT_NAME");

        /////Setting up a login fragment tittle to main activity
       ((MainScreenActivity) getActivity()).setActionBarTitle(FROM_MAIN_CAT_NAME);


       /////////////////


        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getdata(FROM_MAIN_CAT_ID);


    }

    public void getdata(String FROM_MAIN_CAT_ID)
    {
        String url ="http://vegi.lk/admin/mobileappfiles/alladsdata/selected_list_main_cats.php?&maincatid="+FROM_MAIN_CAT_ID;
        final RequestQueue queue = Volley.newRequestQueue(getContext());

        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Sinhala"))
        {
            url ="http://vegi.lk/admin/mobileappfiles/alladsdata/selected_list_main_cats.php?&maincatid="+FROM_MAIN_CAT_ID;
        }
        else if(selected_language.equals("Tamil")){
             url ="http://vegi.lk/admin/mobileappfiles/alladsdata/selected_list_main_cats_tamil.php?&maincatid="+FROM_MAIN_CAT_ID;
        }
        else if(selected_language.equals("English")){
            url ="http://vegi.lk/admin/mobileappfiles/alladsdata/selected_list_main_cats_english.php?&maincatid="+FROM_MAIN_CAT_ID;
        }





        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setallselecteddata(response);
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

    public void setallselecteddata(JSONArray response) {


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                sImages.add(obj.getString("sub_cat_imageurl"));
                sImageNames.add(obj.getString("sub_catname"));
                sImageMainCatID.add(obj.getString("main_catid"));
                sImageId.add(obj.getString("sub_catid"));



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ////Default data
        MainScreenActivity activity = (MainScreenActivity) getView().getContext();
        Fragment myFragment = new VegitableDataFragment();
        Bundle bundle = new Bundle();
        bundle.putString("SUB_CAT_ID", sImageId.get(0));
        bundle.putString("MAIN_ID", sImageMainCatID.get(0));
        myFragment.setArguments(bundle);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.dataFragment, myFragment).addToBackStack(null).commit();
        ////Default data

        initRecyclerSingleAdapter(view);


    }


    public void initRecyclerSingleAdapter(View view)
    {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.single_items_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        SingleItemRecyclerViewAdapter adapter = new SingleItemRecyclerViewAdapter(getContext(),sImages,sImageNames,sImageId,sImageMainCatID);
        recyclerView.setAdapter(adapter);
    }


}
