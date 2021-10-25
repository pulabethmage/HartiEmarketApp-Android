package lk.ac.accimt.hartisplashscreen.ui.register;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.accimt.hartisplashscreen.LoginFragment;
import lk.ac.accimt.hartisplashscreen.MainScreenActivity;
import lk.ac.accimt.hartisplashscreen.R;
import lk.ac.accimt.hartisplashscreen.SellingDashboardFragment;
import lk.ac.accimt.hartisplashscreen.VerifyActivity;

import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment implements Response.Listener<String>,Response.ErrorListener{

    Spinner district_spinner, govname_spinner;

    EditText s_edt_reg_name,s_edt_reg_nic,s_edt_reg_address,s_edt_reg_phone,s_edt_reg_password,s_edt_reg_comfpassword;

    RadioButton s_reg_male,s_reg_femal;

    Button reg_submit;

    String sRegName,sRegNic,sRegGender,sRegAddress,sRegPhone,sRegPassword,sRegConfPassword;

    Integer sRegDistrict,sRegGovicenter;

    Context context;
    View view1;

    TextView mainTitle,genderTitle,districtTitle,govicenterTitle;


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

    String selected_language;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        context = getContext();


        /// Spinner Arrays
        sIdsDistrict = new ArrayList<String>();
        sIdsGovicenter = new ArrayList<String>();
        /// Spinner Arrays


        View view = inflater.inflate(R.layout.register_fragment, container, false);

        view1 = view;

        district_spinner = view.findViewById(R.id.s_edt_reg_district);
        govname_spinner = view.findViewById(R.id.s_edt_reg_govicenter);

        reg_submit = view.findViewById(R.id.s_edt_reg_submit_btn);

        s_edt_reg_name  = view.findViewById(R.id.s_edt_reg_name);
        s_edt_reg_nic  = view.findViewById(R.id.s_edt_reg_nic);

        s_reg_male  = view.findViewById(R.id.regGenMale);
        s_reg_femal  = view.findViewById(R.id.regGenFemale);

        s_edt_reg_address  = view.findViewById(R.id.s_edt_reg_address);
        s_edt_reg_phone  = view.findViewById(R.id.s_edt_reg_phone);
        s_edt_reg_password  = view.findViewById(R.id.s_edt_reg_password);
        s_edt_reg_comfpassword  = view.findViewById(R.id.s_edt_reg_comfpassword);

        mainTitle = view.findViewById(R.id.s_edt_reg_mainTitle);
        genderTitle = view.findViewById(R.id.s_edt_reg_genderTitle);
        districtTitle = view.findViewById(R.id.s_edt_reg_districtTitle);
        govicenterTitle = view.findViewById(R.id.s_edt_reg_govicenterTitle);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs.getString("language", "notselected");

        if(selected_language.equals("Sinhala"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("ලියාපදිංචිය");

        }
        else if(selected_language.equals("Tamil")){
            ((MainScreenActivity) getActivity()).setActionBarTitle("பதிவு");

            mainTitle.setText(R.string.s_register_tittle_tamil);
            genderTitle.setText(R.string.s_register_gender_tamil);
            districtTitle.setText(R.string.s_register_district_tamil);
            govicenterTitle.setText(R.string.s_register_govicenter_tamil);


            s_edt_reg_name.setHint(R.string.s_register_name_tamil);
            s_edt_reg_nic.setHint(R.string.s_register_nic_tamil);

            s_reg_male.setText(R.string.s_register_gender_male_tamil);
            s_reg_femal.setText(R.string.s_register_gender_female_tamil);

            s_edt_reg_address.setHint(R.string.s_register_address_tamil);
            s_edt_reg_phone.setHint(R.string.s_register_phone_tamil);
            s_edt_reg_password.setHint(R.string.s_register_pass_tamil);
            s_edt_reg_comfpassword.setHint(R.string.s_register_comfpass_tamil);

            reg_submit.setText(R.string.s_register_submit_tamil);

        }
        else if(selected_language.equals("English")){
            ((MainScreenActivity) getActivity()).setActionBarTitle("Register");

            mainTitle.setText(R.string.s_register_tittle_english);
            genderTitle.setText(R.string.s_register_gender_english);
            districtTitle.setText(R.string.s_register_district_english);
            govicenterTitle.setText(R.string.s_register_govicenter_english);


            s_edt_reg_name.setHint(R.string.s_register_name_english);
            s_edt_reg_nic.setHint(R.string.s_register_nic_english);

            s_reg_male.setText(R.string.s_register_gender_male_english);
            s_reg_femal.setText(R.string.s_register_gender_female_english);

            s_edt_reg_address.setHint(R.string.s_register_address_english);
            s_edt_reg_phone.setHint(R.string.s_register_phone_english);
            s_edt_reg_password.setHint(R.string.s_register_pass_english);
            s_edt_reg_comfpassword.setHint(R.string.s_register_comfpass_english);

            reg_submit.setText(R.string.s_register_submit_english);

        }


        ////// Submit Button////

        reg_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitdata();

            }
        });

        /////// Submit Button /////



        /// Disttict Spinner on Click////

        district_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Integer SelectedItem = adapterView.getSelectedItemPosition();
                distr_id = sIdsDistrict.get(SelectedItem);

                //Toast.makeText(getContext(),": District id :"+distr_id,Toast.LENGTH_LONG).show();
                if(selected_language.equals("Sinhala"))
                {
                    loadgovcenters(distr_id,"all_list.php");
                }
                else if(selected_language.equals("Tamil")){
                    loadgovcenters(distr_id,"all_list_tamil.php");
                }
                else if(selected_language.equals("English")){
                    loadgovcenters(distr_id,"all_list_english.php");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        govname_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Integer SelectedItem = adapterView.getSelectedItemPosition();
               // govicenter_id = sIdsGovicenter.get(SelectedItem);

               // Toast.makeText(getContext()," : gov center id : "+govicenter_id,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /// Disttict Spinner on Click////


        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(selected_language.equals("Sinhala"))
        {
            loaddistricts("all_list.php");
        }
        else if(selected_language.equals("Tamil")){
            loaddistricts("all_list_tamil.php");
        }
        else if(selected_language.equals("English")){
            loaddistricts("all_list_english.php");
        }




    }

    public  void submitdata()
    {


        ////
        if(s_edt_reg_name.getText().toString().isEmpty()
                || s_edt_reg_nic.getText().toString().isEmpty()
                || s_edt_reg_address.getText().toString().isEmpty()
                || s_edt_reg_phone.getText().toString().isEmpty()
                || s_edt_reg_password.getText().toString().isEmpty()
                || s_edt_reg_comfpassword.getText().toString().isEmpty())
        {
            s_edt_reg_name.setError("* Required");
            s_edt_reg_nic.setError("* Required");
            s_edt_reg_address.setError("* Required");
            s_edt_reg_phone.setError("* Required");
            s_edt_reg_password.setError("* Required");
            s_edt_reg_comfpassword.setError("* Required");
        }
        else {
            sRegName = s_edt_reg_name.getText().toString();
            sRegNic = s_edt_reg_nic.getText().toString();
            sRegAddress = s_edt_reg_address.getText().toString();
            sRegPhone = s_edt_reg_phone.getText().toString();
            sRegPassword = s_edt_reg_password.getText().toString();
            sRegConfPassword = s_edt_reg_comfpassword.getText().toString();


            if(s_reg_male.isChecked())
            { sRegGender = s_reg_male.getText().toString();
            }
            else if(s_reg_femal.isChecked())
            { sRegGender = s_reg_femal.getText().toString();
            }

            // gistrict and govi center ids
            distr_id = sIdsDistrict.get(district_spinner.getSelectedItemPosition());
            govicenter_id = sIdsGovicenter.get(govname_spinner.getSelectedItemPosition());


            if(sRegPassword.equals(sRegConfPassword))
            {

                //// This opens the verification Intent, when the verification is done data will added to DB
                Intent intent = new Intent(getContext(),VerifyActivity.class);

                intent.putExtra("sRegName",sRegName);
                intent.putExtra("sRegNic",sRegNic);
                intent.putExtra("sRegGender",sRegGender);
                intent.putExtra("sRegAddress",sRegAddress);
                intent.putExtra("sRegPhone",sRegPhone);
                intent.putExtra("sRegPassword",sRegPassword);
                intent.putExtra("distr_id",distr_id);
                intent.putExtra("govicenter_id",govicenter_id);

                startActivity(intent);

                //Toast.makeText(getContext(),distr_id+" : gov center id : "+govicenter_id,Toast.LENGTH_LONG).show();
                //confirmAdd(sRegName,sRegNic,sRegGender,sRegAddress,sRegPhone,sRegPassword,distr_id,govicenter_id);
            }
            else
                Toast.makeText(getContext(),"Passwords don't match!!",Toast.LENGTH_LONG).show();


        }
        // End




    }


    public void confirmAdd(String sRegName,String sRegNic,String sRegGender,String sRegAddress,String sRegPhone,String sRegPassword,String sRegDistrict,String sRegGovicenter)
    {
        final String sRegNameE=sRegName;
        final String sRegNicE=sRegNic;
        final String sRegGenderE=sRegGender;
        final String sRegAddressE=sRegAddress;
        final String sRegPhoneE=sRegPhone;
        final String sRegPasswordE=sRegPassword;
        final String sRegDistrictE=sRegDistrict.toString();
        final String sRegGovicenterE=sRegGovicenter.toString();



        RequestQueue queue = Volley.newRequestQueue(getContext());
        int type = Request.Method.POST;
        String url ="http://vegi.lk/admin/mobileappfiles/register_suplier/add.php";

        StringRequest request = new StringRequest(
                type,
                url,
                this,
                this
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> params = new HashMap<>();
                params.put("name",sRegNameE);
                params.put("nic",sRegNicE);
                params.put("gender",sRegGenderE);
                params.put("address",sRegAddressE);
                params.put("mphone",sRegPhoneE);
                params.put("suppassword",sRegPasswordE);
                params.put("district",sRegDistrictE);
                params.put("govicenter",sRegGovicenterE);

                return params;
            }
        };

        queue.add(request);

    }


    @Override
    public void onErrorResponse(VolleyError error) {
       // Toast.makeText(getContext(),"Error!"+error.getMessage(), Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(),R.string.s_register_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.s_register_msg_title);
        builder.setMessage(R.string.s_register_msg);
        builder.setPositiveButton(R.string.s_register_loginpage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               fini();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void fini()
    {
        Fragment logfragment = new LoginFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,logfragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void loaddistricts(String filename)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/alldistricts/"+filename;

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

    public void loadgovcenters(String distr_id,String districtfile)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url ="http://vegi.lk/admin/mobileappfiles/govicenter/"+districtfile+"?disid="+distr_id;

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




    }





}
