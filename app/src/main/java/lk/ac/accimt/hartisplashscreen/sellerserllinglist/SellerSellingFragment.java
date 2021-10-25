package lk.ac.accimt.hartisplashscreen.sellerserllinglist;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import lk.ac.accimt.hartisplashscreen.AddAverticementFragment;
import lk.ac.accimt.hartisplashscreen.AdsRecyclerViewAdapter;
import lk.ac.accimt.hartisplashscreen.AdsSellerRecyclerViewAdapter;
import lk.ac.accimt.hartisplashscreen.MainScreenActivity;
import lk.ac.accimt.hartisplashscreen.R;
import lk.ac.accimt.hartisplashscreen.SellingDashboardFragment;

import static android.content.Context.MODE_PRIVATE;


public class SellerSellingFragment extends Fragment {

    View view;

    Button selling_adnewbtn;

    String SUP_ID;

    //Arrays Lists
    private ArrayList<String> adsDate = new ArrayList<>();
    private ArrayList<String> adsMainCat= new ArrayList<>();
    private ArrayList<String> adsSubCat= new ArrayList<>();
    private ArrayList<String> adsAdId= new ArrayList<>();
    private ArrayList<String> adsAdStat= new ArrayList<>();
    private ArrayList<String> adsMainCatID= new ArrayList<>();
    private ArrayList<String> adsSubCatID= new ArrayList<>();

    private ArrayList<String> adsType= new ArrayList<>();
    private ArrayList<String> adsUnitPrice= new ArrayList<>();
    private ArrayList<String> adsQuantity= new ArrayList<>();
    private ArrayList<String> adsUnits = new ArrayList<>();
    private ArrayList<String> adsSellingPlace= new ArrayList<>();
    private ArrayList<String> adsExpitariondate= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_seller_selling, container, false);

        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        String language = prefs.getString("language", "notselected");

        /////Setting up a login fragment tittle to main activity
        if(language.equals("Sinhala"))
        {
            ((MainScreenActivity) getActivity()).setActionBarTitle("ඔබගේ දැන්වීම්");
        }
        else if(language.equals("Tamil")) {
            ((MainScreenActivity) getActivity()).setActionBarTitle("உங்கள் விளம்பரங்கள்");
        }
        else if(language.equals("English")) {
            ((MainScreenActivity) getActivity()).setActionBarTitle("Your Ads");
        }
        /////Setting up a login fragment tittle to main activity


         selling_adnewbtn = view.findViewById(R.id.sellerselling_list_addnewbtn);


         selling_adnewbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Bundle bundle = new Bundle();
                 bundle.putString("fromlist", "yes");
                 Fragment sfragment = new AddAverticementFragment();
                 sfragment.setArguments(bundle);
                 FragmentManager fm = getFragmentManager();
                 FragmentTransaction ft= fm.beginTransaction();
                 ft.replace(R.id.nav_host_fragment,sfragment);
                 ft.addToBackStack(null);
                 ft.commit();

             }
         });





         return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        /// Getting Logged Details
        SharedPreferences prefs = getContext().getSharedPreferences("LOGGEDDETAILS",Context.MODE_PRIVATE);
        SUP_ID = prefs.getString("SUP_ID", "0");

        adsDate.clear();
        adsMainCat.clear();
        adsSubCat.clear();
        adsAdId.clear();
        adsAdStat.clear();
        adsMainCatID.clear();
        adsSubCatID.clear();

        adsType.clear();
        adsUnitPrice.clear();
        adsQuantity.clear();
        adsUnits.clear();
        adsSellingPlace.clear();
        adsExpitariondate.clear();


        loadalldata(SUP_ID);

    }


    public void initRecyclerSingleAdapter(View view)
    {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.sellerselling_list_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        AdsSellerRecyclerViewAdapter adapter = new AdsSellerRecyclerViewAdapter(getContext(),adsDate,adsMainCat,adsSubCat,adsAdId,adsAdStat,adsMainCatID,adsSubCatID,adsType,adsUnitPrice,
                adsQuantity,adsUnits,adsSellingPlace,adsExpitariondate);
        recyclerView.setAdapter(adapter);
    }


    public void loadalldata(String suplier_id)
    {
        final RequestQueue queue = Volley.newRequestQueue(getContext());

        String url ="http://vegi.lk/admin/mobileappfiles/supliersads/all_list.php?suplierid="+suplier_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setalltheads(response);
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


    public void setalltheads(JSONArray response) {


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                adsDate.add(obj.getString("Ad_Date"));
                adsMainCat.add(obj.getString("main_cat_name"));
                adsMainCatID.add(obj.getString("main_cat_id"));
                adsSubCat.add(obj.getString("sub_catname"));
                adsSubCatID.add(obj.getString("sub_catid"));
                adsAdId.add(obj.getString("Ad_Id"));
                adsAdStat.add(obj.getString("Adver_Status"));

                adsType.add(obj.getString("Type"));
                adsUnitPrice.add(obj.getString("Unit_Price"));
                adsQuantity.add(obj.getString("Quantity"));
                adsUnits.add(obj.getString("Units"));
                adsSellingPlace.add(obj.getString("Selling_place"));
                adsExpitariondate.add(obj.getString("Expiration_date"));



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        initRecyclerSingleAdapter(view);



    }







}
