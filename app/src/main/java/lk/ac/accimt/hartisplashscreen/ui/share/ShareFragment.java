package lk.ac.accimt.hartisplashscreen.ui.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lk.ac.accimt.hartisplashscreen.MainScreenActivity;
import lk.ac.accimt.hartisplashscreen.R;

import static android.content.Context.MODE_PRIVATE;

public class ShareFragment extends Fragment implements Response.Listener<String>,Response.ErrorListener{

    EditText name,gender,address,phone;
    Spinner district_spinner, govname_spinner;

    TextView profilename,profilegender,profileaddress,profilemobile,profiledistrict,profilegovicenter;

    ///////
    List<String> list;
    List<String> listGov;

    //// District Spinner ///
    List<String> sIdsDistrict;
    String distr_id;


    //// Govicenter Spinner ///
    List<String> sIdsGovicenter;
    String govicenter_id;
    String onlyGoviId;

    String districtcomparename;
    String govicomparename;
    int  districtposition ;
    int  goviposition ;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_share, container, false);

        district_spinner = view.findViewById(R.id.s_profile_reg_district);
        govname_spinner = view.findViewById(R.id.s_profile_reg_govicenter);
        /// Spinner Arrays
        sIdsDistrict = new ArrayList<String>();
        sIdsGovicenter = new ArrayList<String>();
        /// Spinner Arrays

        district_spinner.setEnabled(false);
        govname_spinner.setEnabled(false);

        name = view.findViewById(R.id.profile_name);
        gender = view.findViewById(R.id.profile_gender);
        address = view.findViewById(R.id.profile_address);
        phone = view.findViewById(R.id.profile_phone);


        profilename = view.findViewById(R.id.yourprofile_name);
        profilegender = view.findViewById(R.id.yourprofile_gender);
        profileaddress = view.findViewById(R.id.yourprofile_address);
        profilemobile = view.findViewById(R.id.yourprofile_mobile);
        profiledistrict = view.findViewById(R.id.yourprofile_district);
        profilegovicenter = view.findViewById(R.id.yourprofile_govicenter);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        String language = prefs.getString("language", "notselected");

        if(language.equals("Sinhala"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("ඔබේ ගිණුම");
        }
        else if(language.equals("Tamil")){
            ((MainScreenActivity) getActivity()).setActionBarTitle("உங்கள் கணக்க");

            name.setHint(R.string.s_register_tittle_tamil);
            gender.setHint(R.string.s_register_gender_tamil);
            address.setHint(R.string.s_register_address_tamil);
            phone.setHint(R.string.s_register_phone_tamil);


            profilename.setText(R.string.s_register_tittle_tamil);
            profilegender.setText(R.string.s_register_gender_tamil);
            profileaddress.setText(R.string.s_register_address_tamil);
            profilemobile.setText(R.string.s_register_phone_tamil);
            profiledistrict.setText(R.string.s_register_district_tamil);
            profilegovicenter.setText(R.string.s_register_govicenter_tamil);

        }
        else if(language.equals("English")){
            ((MainScreenActivity) getActivity()).setActionBarTitle("Your Account");

            name.setHint(R.string.s_register_tittle_english);
            gender.setHint(R.string.s_register_gender_english);
            address.setHint(R.string.s_register_address_english);
            phone.setHint(R.string.s_register_phone_english);


            profilename.setText(R.string.s_register_tittle_english);
            profilegender.setText(R.string.s_register_gender_english);
            profileaddress.setText(R.string.s_register_address_english);
            profilemobile.setText(R.string.s_register_phone_english);
            profiledistrict.setText(R.string.s_register_district_english);
            profilegovicenter.setText(R.string.s_register_govicenter_english);

        }


        /// Disttict Spinner on Click////

        district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Integer SelectedItem = adapterView.getSelectedItemPosition();
                distr_id = sIdsDistrict.get(SelectedItem);

                //Toast.makeText(getContext(),": District id :"+distr_id,Toast.LENGTH_LONG).show();
                loadgovcenters(distr_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        /// Getting Logged Details
        SharedPreferences prefs = getContext().getSharedPreferences("LOGGEDDETAILS", Context.MODE_PRIVATE);
        String db_logged = prefs.getString("SUP_LOGED", "0");
        String db_name = prefs.getString("SUP_NAME", "0");
        String db_gender = prefs.getString("SUP_GENDER", "0");
        String db_address = prefs.getString("SUP_ADDRESS", "0");
        String db_mobile = prefs.getString("SUP_MOBILE", "0");
        String db_district = prefs.getString("SUP_DISTRICT_ID", "0");
        String db_govicenter = prefs.getString("SUP_GOVICENTER_ID", "0");

        if(!db_logged.equals("0"))
        {
            name.setText(db_name);
            gender.setText(db_gender);
            address.setText(db_address);
            phone.setText(db_mobile);

            districtcomparename = db_district;
            govicomparename = db_govicenter;

            loaddistricts();
        }


    }


    public void loaddistricts()
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/alldistricts/all_list.php";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setDistricts(response);
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

    public void setDistricts(JSONArray response) {

        list = new ArrayList<String>();
        //add ids in this list


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                String  district_id = obj.getString("dis_auto_id");
                String district = obj.getString("District_name");
                sIdsDistrict.add(district_id);
                list.add(district);



            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district_spinner.setAdapter(dataAdapter);

        districtposition = dataAdapter.getPosition(districtcomparename);
        district_spinner.setSelection(districtposition);

    }


    public void loadgovcenters(String distr_id)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/govicenter/all_list.php?disid="+distr_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setgovicenters(response);
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

    public void setgovicenters(JSONArray response) {


        listGov = new ArrayList<String>();
        sIdsGovicenter.clear();
        //add ids in this list


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                String govi_id = obj.getString("Center_ID");
                String govi_name = obj.getString("Name");
                sIdsGovicenter.add(govi_id);
                listGov.add(govi_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, listGov);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        govname_spinner.setAdapter(dataAdapter);

        goviposition = dataAdapter.getPosition(govicomparename);
        govname_spinner.setSelection(goviposition);



    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),R.string.s_register_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(getContext(),"Success!", Toast.LENGTH_LONG).show();
    }
}