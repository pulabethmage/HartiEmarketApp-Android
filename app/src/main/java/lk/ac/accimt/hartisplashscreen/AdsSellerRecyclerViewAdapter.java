package lk.ac.accimt.hartisplashscreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

import lk.ac.accimt.hartisplashscreen.buyingItems.BuyingItemFragment;
import lk.ac.accimt.hartisplashscreen.editadsbyseller.EditSellerAds;
import lk.ac.accimt.hartisplashscreen.sellerserllinglist.SellerSellingFragment;

import static android.content.Context.MODE_PRIVATE;

public class AdsSellerRecyclerViewAdapter extends RecyclerView.Adapter<AdsSellerRecyclerViewAdapter.ViewHolder> {

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

    String language;

    private Context adsContext;

    public AdsSellerRecyclerViewAdapter( Context adsContext,
                                   ArrayList<String> adsDate,
                                   ArrayList<String> adsMainCat,
                                   ArrayList<String> adsSubCat,
                                   ArrayList<String> adsAdId,
                                   ArrayList<String> adsAdStat,
                                   ArrayList<String> adsMainCatID,
                                   ArrayList<String> adsSubCatID,
                                         ArrayList<String> adsType,
                                         ArrayList<String> adsUnitPrice,
                                         ArrayList<String> adsQuantity,
                                         ArrayList<String> adsUnits,
                                         ArrayList<String> adsSellingPlace,
                                         ArrayList<String> adsExpitariondate)
    {

        this.adsDate = adsDate;
        this.adsMainCat = adsMainCat;
        this.adsSubCat = adsSubCat;
        this.adsAdId = adsAdId;
        this.adsAdStat = adsAdStat;
        this.adsContext = adsContext;
        this.adsMainCatID = adsMainCatID;
        this.adsSubCatID = adsSubCatID;

        this.adsType = adsType;
        this.adsUnitPrice = adsUnitPrice;
        this.adsQuantity = adsQuantity;
        this.adsUnits = adsUnits;
        this.adsSellingPlace = adsSellingPlace;
        this.adsExpitariondate = adsExpitariondate;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sellersinglelistitem,parent,false);

        SharedPreferences prefs = view.getContext().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        language = prefs.getString("language", "notselected");


        return new AdsSellerRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {



        if(language.equals("Sinhala"))
        {
            //දැන්වීම පලකල දිනය
            holder.ads_DateTitle.setText(R.string.s_ad_posted_date);
        }
        else if(language.equals("Tamil")) {
            //දැන්වීම පලකල දිනය tamil
            holder.ads_DateTitle.setText(R.string.tamil_ad_posted_date);
        }
        else if(language.equals("English")) {
            //දැන්වීම පලකල දිනය tamil
            holder.ads_DateTitle.setText(R.string.english_ad_posted_date);
        }


        holder.ads_Date.setText(adsDate.get(position));
        holder.ads_MainCat.setText(adsMainCat.get(position));
        holder.ads_SubCat.setText(adsSubCat.get(position));
        holder.ads_AdId.setText(adsAdId.get(position));

        if(adsAdStat.get(position).equals("1")){

            holder.ads_Status.setBackgroundResource(R.drawable.callgreenroundedback);

        }



        holder.ads_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(adsContext,"Edit : "+adsAdId.get(position)+" Main Cat Id :"+adsMainCatID.get(position)+" Sub Cat ID : "+adsSubCatID.get(position),Toast.LENGTH_LONG).show();

                MainScreenActivity activity = (MainScreenActivity)view.getContext();
                Fragment myFragment = new EditSellerAds();

                Bundle bundle = new Bundle();
                bundle.putString("AD_ID",adsAdId.get(position));

                bundle.putString("AD_MAIN_CAT_NAME",adsMainCat.get(position));
                bundle.putString("AD_SUB_CAT_NAME",adsSubCat.get(position));
                bundle.putString("AD_TYPE",adsType.get(position));
                bundle.putString("AD_UNITP",adsUnitPrice.get(position));
                bundle.putString("AD_QTY",adsQuantity.get(position));
                bundle.putString("AD_UNITS",adsUnits.get(position));
                bundle.putString("AD_SELLING_P",adsSellingPlace.get(position));
                bundle.putString("AD_EX_DATE",adsExpitariondate.get(position));

               // Toast.makeText(adsContext,"MAIN ID : "+adsMainCatID.get(position),Toast.LENGTH_LONG).show();


                myFragment.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();


            }
        });

        holder.ads_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

               // Toast.makeText(adsContext,"Delete : "+adsAdId.get(position),Toast.LENGTH_LONG).show();




                AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getContext());
                builder1.setMessage("ඹබට මෙම දැන්වීම Delete කිරීමට අවශ්‍යද?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "ඹව්",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                delteadd(view,adsAdId.get(position));
                            }
                        });

                builder1.setNegativeButton(
                        "නැත",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();



            }
        });

    }

    @Override
    public int getItemCount() {
        return adsDate.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ads_DateTitle;
        TextView ads_Date;
        TextView ads_MainCat;
        TextView ads_SubCat;
        TextView ads_AdId;
        Button ads_Edit;
        Button ads_Delete;
        TextView ads_Status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ads_DateTitle = itemView.findViewById(R.id.sellerlist_datetitle);
             ads_Date = itemView.findViewById(R.id.sellerlist_addate);
             ads_MainCat = itemView.findViewById(R.id.sellerlist_maincat);
             ads_SubCat = itemView.findViewById(R.id.sellerlist_subcat);
             ads_AdId = itemView.findViewById(R.id.sellerlist_adid);
            ads_Edit = itemView.findViewById(R.id.sellerlist_editbtn);
            ads_Delete = itemView.findViewById(R.id.sellerlist_deletebtn);
            ads_Status = itemView.findViewById(R.id.sellerlist_ad_status);

        }
    }


    public  void  delteadd(final View view, String ad_id){

        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/adingAdverticment/delete.php?ad_id="+ad_id;

        //String url = dburls.adsSellerRecyclerViewAdaperDeleteAdd+ad_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();

                    }
                });

        Toast.makeText(view.getContext(),"සාර්ථකයි!",Toast.LENGTH_LONG).show();

        MainScreenActivity activity = (MainScreenActivity)view.getContext();
        Fragment myFragment = new SellerSellingFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();

        queue.add(request);

    }
}
