package lk.ac.accimt.hartisplashscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import lk.ac.accimt.hartisplashscreen.sellerserllinglist.SellerSellingFragment;
import lk.ac.accimt.hartisplashscreen.ui.home.HomeFragment;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment implements Response.Listener<String>,Response.ErrorListener {

    Button login_submit;

    View view;

    EditText login_nic_number,login_nic_password;

    TextView subtitle,forgotpassword,donthaveaccount;

    ///
    String SUP_ID,SUP_PASS,SUP_NAME,SUP_GENDER,SUP_NIC,SUP_ADDRESS,SUP_MOBILE,SUP_DISTRICT_ID,SUP_GOVICENTER_ID;
    ///



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
         view = inflater.inflate(R.layout.fragment_login, container, false);

        subtitle = view.findViewById(R.id.subtitle);
        forgotpassword = view.findViewById(R.id.forgotpassword);
        donthaveaccount = view.findViewById(R.id.donthaveaccouint);

        login_nic_number = view.findViewById(R.id.log_username);
        login_nic_password = view.findViewById(R.id.log_password);

        login_submit = view.findViewById(R.id.login_submitbtn);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        String language = prefs.getString("language", "notselected");
        if(language.equals("Tamil"))
        {

            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("உங்கள் கணக்கில் உள்நுழைக");

            subtitle.setText(R.string.Login_subtittle_tamil);
            forgotpassword.setText(R.string.Login_subtittle_forgotpass_tamil);
            donthaveaccount.setText(R.string.Login_subtittle_ginumaknathida_tamil);
            login_submit.setText(R.string.Login_subtittle_athulwanna_tamil);

        }
        else if(language.equals("English"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("Please Login");

            subtitle.setText(R.string.Login_subtittle_english);
            forgotpassword.setText(R.string.Login_subtittle_forgotpass_english);
            donthaveaccount.setText(R.string.Login_subtittle_ginumaknathida_english);
            login_submit.setText(R.string.Login_subtittle_athulwanna_english);
        }
        else {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("ඇතුල් වන්න");
        }



        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(login_nic_number.getText().toString());


            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        /// Getting Logged Details
        SharedPreferences prefs = getContext().getSharedPreferences("LOGGEDDETAILS",Context.MODE_PRIVATE);
        String logstatus = prefs.getString("SUP_LOGED", "0");

        if(logstatus.equals("YES"))
        {
           // Fragment sfragment = new SellingDashboardFragment();
            Fragment sfragment = new SellerSellingFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment,sfragment);
            ft.addToBackStack(null);
            ft.commit();
        }


    }


    public void login(String nicnumber)
    {
        String nic=nicnumber;
        //Toast.makeText(view.getContext(),nic,Toast.LENGTH_LONG).show();

        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url ="http://vegi.lk/admin/mobileappfiles/login/login.php?nic_number="+nic;


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setLogin(response);
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

    public void setLogin(JSONArray response) {



        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                SUP_ID = obj.getString("Supplier_ID");
                SUP_PASS = obj.getString("sup_password");

                SUP_NAME = obj.getString("Name");
                SUP_GENDER = obj.getString("Gender");
                SUP_NIC= obj.getString("Nic");
                SUP_ADDRESS = obj.getString("Address");
                SUP_MOBILE = obj.getString("Mobile_No");
                SUP_DISTRICT_ID = obj.getString("District_name");
                SUP_GOVICENTER_ID = obj.getString("GoviCenter");


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        //Toast.makeText(view.getContext(),SUP_PASS,Toast.LENGTH_LONG).show();
        if(login_nic_password.getText().toString().equals(SUP_PASS)){

            /// Saving data on device
            SharedPreferences.Editor editor = getContext().getSharedPreferences("LOGGEDDETAILS", MODE_PRIVATE).edit();
            editor.putString("SUP_LOGED","YES");
            editor.putString("SUP_ID",SUP_ID);
            editor.putString("SUP_NAME",SUP_NAME);
            editor.putString("SUP_GENDER",SUP_GENDER);
            editor.putString("SUP_NIC",SUP_NIC);
            editor.putString("SUP_MOBILE",SUP_MOBILE);
            editor.putString("SUP_DISTRICT_ID",SUP_DISTRICT_ID);
            editor.putString("SUP_GOVICENTER_ID",SUP_GOVICENTER_ID);
            editor.putString("SUP_ADDRESS",SUP_ADDRESS);
            editor.apply();
            /// Saving data on device

            SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
            String language = prefs.getString("language", "notselected");

            if(language.equals("Tamil"))
            {
                Toast.makeText(view.getContext(),"உங்களை வரவேற்கிறோம்!",Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(view.getContext(),"ඔබව සාදරයෙන් පිළිගන්නවා!",Toast.LENGTH_LONG).show();

            Fragment sfragment = new SellerSellingFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment,sfragment);
            ft.addToBackStack(null);
            ft.commit();

        }
        else
            Toast.makeText(getContext(),"Please Check your Username and Password!!", Toast.LENGTH_LONG).show();



    }


    @Override
    public void onErrorResponse(VolleyError error) {
 Toast.makeText(getContext(),"Error!"+error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {



    }
}
