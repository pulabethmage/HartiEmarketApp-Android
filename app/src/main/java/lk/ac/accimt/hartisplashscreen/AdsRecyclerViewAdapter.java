package lk.ac.accimt.hartisplashscreen;


import android.content.Context;
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

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import lk.ac.accimt.hartisplashscreen.buyingItems.BuyingItemFragment;

import static android.content.Context.MODE_PRIVATE;

public class AdsRecyclerViewAdapter extends RecyclerView.Adapter<AdsRecyclerViewAdapter.ViewHolder>{

    //Arrays Lists
    private ArrayList<String> adsImages = new ArrayList<>();
    private ArrayList<String> adsTittles= new ArrayList<>();
    private ArrayList<String> adsUnitPrice= new ArrayList<>();
    private ArrayList<String> adsSellerName= new ArrayList<>();
    private ArrayList<String> adsFroms= new ArrayList<>();
    private ArrayList<String> adsGoviCenter= new ArrayList<>();
    private ArrayList<String> adsDatePlaced= new ArrayList<>();
    private ArrayList<String> adsID= new ArrayList<>();
    private Context adsContext;

    String selected_language;

    public AdsRecyclerViewAdapter( Context adsContext,
                                   ArrayList<String> adsImages,
                                   ArrayList<String> adsTittles,
                                   ArrayList<String> adsUnitPrice,
                                   ArrayList<String> adsSellerName,
                                   ArrayList<String> adsFroms,
                                   ArrayList<String> adsGoviCenter,
                                   ArrayList<String> adsDatePlaced,
                                   ArrayList<String> adsID) {

        this.adsImages = adsImages;
        this.adsTittles = adsTittles;
        this.adsUnitPrice = adsUnitPrice;
        this.adsSellerName = adsSellerName;
        this.adsFroms = adsFroms;
        this.adsGoviCenter = adsGoviCenter;
        this.adsDatePlaced = adsDatePlaced;
        this.adsID = adsID;
        this.adsContext = adsContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_custom_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        /////// Custom ad layout for tamil language
        SharedPreferences prefs = adsContext.getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Tamil"))
        {

            ////For lanngeage change
            holder.ad_unitprice_amount_text.setText(R.string.s_ad_applicationform_unit_price_tamil);
            holder.ad_sellername_text.setText(R.string.s_register_name_tamil);
            holder.ad_from_text.setText(R.string.s_register_address_tamil);
            holder.ad_govicenter_text.setText(R.string.s_register_govicenter_tamil);
            holder.ad_dateadplaced_text.setText(R.string.tamil_ad_posted_date);
            holder.ad_order.setText(R.string.s_ad_applicationform_submit_btn_tamil);
            ////

        }
        else if(selected_language.equals("English"))
        {

            ////For lanngeage change
            holder.ad_unitprice_amount_text.setText(R.string.s_ad_applicationform_unit_price_english);
            holder.ad_sellername_text.setText(R.string.s_register_name_english);
            holder.ad_from_text.setText(R.string.s_register_address_english);
            holder.ad_govicenter_text.setText(R.string.s_register_govicenter_english);
            holder.ad_dateadplaced_text.setText(R.string.english_ad_posted_date);
            holder.ad_order.setText("Order Now");
            ////

        }
        /////// End Custom ad layout for tamil language

        Glide.with(adsContext)
                .asBitmap()
                .load(adsImages.get(position))
                .into(holder.ad_image);

        holder.ad_tittle.setText(adsTittles.get(position));
        holder.ad_unitprice.setText(adsUnitPrice.get(position));
        holder.ad_sellername.setText(adsSellerName.get(position));
        holder.ad_from.setText(adsFroms.get(position));
        holder.ad_govicenter.setText(adsGoviCenter.get(position));

        //////////// showing ad date
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
//        cal.setTime(date);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//
//        int ddfromDM = Integer.parseInt(adsDatePlaced.get(position).substring(8,10));

//        if(day == ddfromDM)
//        {
//            holder.ad_dateplaced.setText("Today");
//        }
//        else if(ddfromDM>day)
//        {
//            holder.ad_dateplaced.setText(day+30-ddfromDM+" days ago.");
//        }
//        else
//        {
//            holder.ad_dateplaced.setText(day-ddfromDM+" days ago.");
//        }
        holder.ad_dateplaced.setText(adsDatePlaced.get(position));
        //////////// showing ad date




        holder.ad_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(adsContext,adsFroms.get(position),Toast.LENGTH_LONG).show();

                MainScreenActivity activity = (MainScreenActivity)view.getContext();
                Fragment myFragment = new BuyingItemFragment();

                Bundle bundle = new Bundle();
                bundle.putString("ItemName",adsTittles.get(position));
                bundle.putString("AdId",adsID.get(position));
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return adsImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        /// For language change
        TextView ad_unitprice_amount_text,ad_sellername_text,ad_from_text,ad_govicenter_text,ad_dateadplaced_text;
        //////



        ImageView ad_image;
        TextView ad_tittle;
        TextView ad_unitprice;
        TextView ad_sellername;
        TextView ad_from;
        TextView ad_govicenter;
        TextView ad_dateplaced;
        Button ad_order;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ad_image = itemView.findViewById(R.id.ad_image);
            ad_tittle = itemView.findViewById(R.id.ad_tittle);
            ad_unitprice = itemView.findViewById(R.id.ad_unitprice_amount);
            ad_sellername = itemView.findViewById(R.id.ad_sellername);
            ad_from = itemView.findViewById(R.id.ad_from);
            ad_govicenter = itemView.findViewById(R.id.ad_govicenter);
            ad_dateplaced =  itemView.findViewById(R.id.ad_dateadplaced);
            ad_order = itemView.findViewById(R.id.ad_order_btn);

            ////For lanngeage change
            ad_unitprice_amount_text=itemView.findViewById(R.id.ad_unitprice);
            ad_sellername_text=itemView.findViewById(R.id.ad_sellername_text);
            ad_from_text=itemView.findViewById(R.id.ad_from_text);
            ad_govicenter_text=itemView.findViewById(R.id.ad_govicenter_text);
            ad_dateadplaced_text=itemView.findViewById(R.id.ad_dateadplaced_text);

            ////

        }
    }

}
