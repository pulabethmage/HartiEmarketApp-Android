package lk.ac.accimt.hartisplashscreen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.accimt.hartisplashscreen.sellerserllinglist.SellerSellingFragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class AddAverticementFragment extends Fragment implements Response.Listener<String>,Response.ErrorListener{

Spinner uitsspinner,maincatspinner, mainsumcatspinner;
TextView ad_title,expirydate_title,sellingimages_title;

EditText add_application_type,add_application_unitprice,add_application_total_qty,add_application_district,add_application_govicenter,
        add_application_sellingplace,add_application_phonenumber;

DatePicker add_application_expiridate;

ImageView add_application_image01,add_application_image02,add_application_image03;

Button add_application_submit;

    ProgressDialog progress_dialog;

///// image contained linear layouts

    LinearLayout linearLayout_image01,linearLayout_image02,linearLayout_image03;
    private final int IMG_03_REQUEST = 1;
    private Bitmap bitMap,bm;

    int IMGCLICKED = 0;

    ///////

    String CAT_ID ;
    String SUB_CAT_ID ;

    String Image_string_01,Image_string_02,Image_string_03;


    //// Main cats Spinner ///
    List<String> listmains;
    List<String> sIdsMainCats;
    String maincats_id;

    //// Sub cats Spinner ///
    List<String> listsubcat;
    List<String> sIdsSubCat;
    String subcats_id;

    ///
    String SUP_ID,SUP_PASS,SUP_NAME,SUP_GENDER,SUP_NIC,SUP_ADDRESS,SUP_MOBILE,SUP_DISTRICT_ID,SUP_GOVICENTER_ID;
    ///
    View view;

    String selected_language;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_add_averticement, container, false);



        sIdsMainCats = new ArrayList<String>();
        sIdsSubCat = new ArrayList<String>();

        add_application_type = view.findViewById(R.id.add_application_type);
        add_application_unitprice = view.findViewById(R.id.add_application_unitprice);
        add_application_total_qty = view.findViewById(R.id.add_application_total_qty);
        add_application_district = view.findViewById(R.id.add_application_district);
        add_application_govicenter = view.findViewById(R.id.add_application_govicenter);
        add_application_sellingplace = view.findViewById(R.id.add_application_sellingplace);
        add_application_phonenumber = view.findViewById(R.id.add_application_phonenumber);

        expirydate_title = view.findViewById(R.id.add_application_expiridate_title);
        sellingimages_title = view.findViewById(R.id.add_application_images_title);

        add_application_expiridate = view.findViewById(R.id.add_application_expiridate);

        add_application_image01 = view.findViewById(R.id.add_application_image01);
        add_application_image02 = view.findViewById(R.id.add_application_image02);
        add_application_image03 = view.findViewById(R.id.add_application_image03);

        add_application_submit= view.findViewById(R.id.add_application_submit);

        ad_title = view.findViewById(R.id.add_application_title);

        maincatspinner = view.findViewById(R.id.add_application_maincat);
        mainsumcatspinner = view.findViewById(R.id.add_application_subcat);
        uitsspinner = view.findViewById(R.id.add_application_units);

        linearLayout_image01 = view.findViewById(R.id.add_application_image01_linear01);
        linearLayout_image02 = view.findViewById(R.id.add_application_image02_linear02);
        linearLayout_image03 = view.findViewById(R.id.add_application_image03_linear03);


        SharedPreferences prefs_language = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        selected_language = prefs_language.getString("language", "notselected");

        if(selected_language.equals("Sinhala"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("දැන්වීම් පලකිරීම");
        }
        else if(selected_language.equals("Tamil")) {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("விளம்பரங்கள்");

            add_application_type.setHint(R.string.s_ad_applicationform_type_tamil);
            add_application_unitprice.setHint(R.string.s_ad_applicationform_unit_price_tamil);
            add_application_total_qty.setHint(R.string.s_ad_applicationform_total_qty_tamil);
            add_application_sellingplace.setHint(R.string.s_ad_applicationform_selling_place_tamil);
            expirydate_title.setText(R.string.s_ad_applicationform_expirationdate_tamil);
            sellingimages_title.setText(R.string.s_ad_applicationform_if_photos_available_tamil);
            add_application_submit.setText(R.string.s_ad_applicationform_submit_btn_tamil);
        }
        else if(selected_language.equals("English")) {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("Post your ads");

            add_application_type.setHint(R.string.s_ad_applicationform_type_english);
            add_application_unitprice.setHint(R.string.s_ad_applicationform_unit_price_english);
            add_application_total_qty.setHint(R.string.s_ad_applicationform_total_qty_english);
            add_application_sellingplace.setHint(R.string.s_ad_applicationform_selling_place_english);
            expirydate_title.setText(R.string.s_ad_applicationform_expirationdate_english);
            sellingimages_title.setText(R.string.s_ad_applicationform_if_photos_available_english);
            add_application_submit.setText(R.string.s_ad_applicationform_submit_btn_english);
        }



        ///////Click event of lenear layout////


        linearLayout_image01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /// Saving data on device
                SharedPreferences.Editor editor = getContext().getSharedPreferences("CATIDS", MODE_PRIVATE).edit();
                editor.putInt("main_cat_id",maincatspinner.getSelectedItemPosition());
                editor.putInt("sub_cat_id",mainsumcatspinner.getSelectedItemPosition());
                editor.apply();
                /// Saving data on device

                IMGCLICKED = 1;
                //Toast.makeText(getContext(),"Image 3 Clicked!",Toast.LENGTH_LONG).show();
                selectImage();

            }
        });

        linearLayout_image02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(add_application_image01.getDrawable() == null){
                    Toast.makeText(getContext(),"කරුනාකර මුල් ඡායාරූපය තෝරන්න!ස්තුතියි",Toast.LENGTH_LONG).show();
                }
                else
                {
                    /// Saving data on device
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("CATIDS", MODE_PRIVATE).edit();
                    editor.putInt("main_cat_id",maincatspinner.getSelectedItemPosition());
                    editor.putInt("sub_cat_id",mainsumcatspinner.getSelectedItemPosition());
                    editor.apply();
                    /// Saving data on device

                    IMGCLICKED = 2;
                    selectImage();
                }


            }
        });

        linearLayout_image03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(add_application_image01.getDrawable() == null || add_application_image02.getDrawable() == null){
                    Toast.makeText(getContext(),"කරුනාකර මුල් ඡායාරූපය තෝරන්න!ස්තුතියි",Toast.LENGTH_LONG).show();
                }
                else
                {
                    /// Saving data on device
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("CATIDS", MODE_PRIVATE).edit();
                    editor.putInt("main_cat_id",maincatspinner.getSelectedItemPosition());
                    editor.putInt("sub_cat_id",mainsumcatspinner.getSelectedItemPosition());
                    editor.apply();
                    /// Saving data on device

                    IMGCLICKED = 3;
                    selectImage();
                }

            }
        });
        ////////////



        maincatspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Integer SelectedItem = adapterView.getSelectedItemPosition();
                maincats_id = sIdsMainCats.get(SelectedItem);

                String value = listmains.get(maincatspinner.getSelectedItemPosition());
                ad_title.setText(value);
                //Toast.makeText(getContext(),": Main id :"+maincats_id,Toast.LENGTH_LONG).show();
                if(selected_language.equals("Sinhala"))
                {
                    loadsubcat(maincats_id,"all_list.php");

                }
                else if(selected_language.equals("Tamil")) {
                    loadsubcat(maincats_id,"all_list_tamil.php");
                }
                else if(selected_language.equals("English")) {
                    loadsubcat(maincats_id,"all_list_english.php");
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        add_application_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitdata();
            }
        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        if(getArguments() != null)
        {

            if(selected_language.equals("Sinhala"))
            {
                loadunits();
                loadmaincategories("all_list.php");

            }
            else if(selected_language.equals("Tamil")){
                loadunits_tamil();
                loadmaincategories("all_list_tamil.php");
            }
            else if(selected_language.equals("English")){
                loadunits_english();
                loadmaincategories("all_list_english.php");
            }


           // Toast.makeText(getContext(),"On Resume with args!",Toast.LENGTH_LONG).show();

            /// Getting Logged Details
            SharedPreferences prefs = getContext().getSharedPreferences("LOGGEDDETAILS",Context.MODE_PRIVATE);
            SUP_ID = prefs.getString("SUP_ID", "0");
            SUP_NAME = prefs.getString("SUP_NAME", "0");
            SUP_GENDER = prefs.getString("SUP_GENDER", "0");
            SUP_NIC = prefs.getString("SUP_NIC", "0");
            SUP_MOBILE = prefs.getString("SUP_MOBILE", "0");
            SUP_DISTRICT_ID = prefs.getString("SUP_DISTRICT_ID", "0");
            SUP_GOVICENTER_ID = prefs.getString("SUP_GOVICENTER_ID", "0");
            /// Getting Logged Details



            add_application_district.setText(SUP_DISTRICT_ID);
            add_application_govicenter.setText(SUP_GOVICENTER_ID);
            add_application_phonenumber.setText(SUP_MOBILE);

            setArguments(null);

        }
        else {
           // Toast.makeText(getContext(),"On Resume with-out args!",Toast.LENGTH_LONG).show();
            /// Getting Logged Details
            SharedPreferences prefs1 = getContext().getSharedPreferences("CATIDS",Context.MODE_PRIVATE);
            Integer ad_Cat_id = prefs1.getInt("main_cat_id", 0);
            Integer ad_Cat_Sub_id = prefs1.getInt("sub_cat_id", 0);

           // Toast.makeText(getContext(),ad_Cat_id+" "+ad_Cat_Sub_id,Toast.LENGTH_LONG).show();

            maincatspinner.setSelection(ad_Cat_id);
            mainsumcatspinner.setSelection(ad_Cat_Sub_id);

        }


    }


    ////////// Image selecting ////////

    private void selectImage() {


        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_03_REQUEST);

    }

    ////////// Image selecting ////////

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(IMGCLICKED == 1)
        {
            if (requestCode == IMG_03_REQUEST && resultCode == RESULT_OK && data != null) {

                Uri path = data.getData();
                try {
                    bitMap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path);

                    bm = decodeBitmap(path,getContext());

                    add_application_image01.setImageBitmap(bm);
                    add_application_image01.setBackgroundResource(android.R.color.transparent);

                   Image_string_01 = imageToString(bm);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        else if(IMGCLICKED == 2)
        {
            if (requestCode == IMG_03_REQUEST && resultCode == RESULT_OK && data != null) {

                Uri path = data.getData();
                try {
                    bitMap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path);

                    bm = decodeBitmap(path,getContext());

                    add_application_image02.setImageBitmap(bm);
                    add_application_image02.setBackgroundResource(android.R.color.transparent);

                    Image_string_02 = imageToString(bm);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        else if(IMGCLICKED == 3)
        {
            if (requestCode == IMG_03_REQUEST && resultCode == RESULT_OK && data != null) {

                Uri path = data.getData();
                try {
                    bitMap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), path);

                    bm = decodeBitmap(path,getContext());

                    add_application_image03.setImageBitmap(bm);
                    add_application_image03.setBackgroundResource(android.R.color.transparent);

                    Image_string_03 = imageToString(bm);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }


    public static Bitmap decodeBitmap(Uri selectedImage, Context context)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(selectedImage), null, o);

        final int REQUIRED_SIZE = 100;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver()
                .openInputStream(selectedImage), null, o2);
    }


    ///// BIT MAP TO STRING CONVERTION

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        String imageEncoded = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return imageEncoded;

    }



    public void loadunits()
    {

        List<String> list = new ArrayList<String>();
        list.add("Kg");
        list.add("g");
        list.add("Liters");
        list.add("වෙනත්");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uitsspinner.setAdapter(dataAdapter);


    }

    public void loadunits_tamil()
    {

        List<String> list = new ArrayList<String>();
        list.add("Kg");
        list.add("g");
        list.add("Liters");
        list.add("மற்ற");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uitsspinner.setAdapter(dataAdapter);


    }

    public void loadunits_english()
    {

        List<String> list = new ArrayList<String>();
        list.add("Kg");
        list.add("g");
        list.add("Liters");
        list.add("Other");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        uitsspinner.setAdapter(dataAdapter);


    }

    public void loadmaincategories(String laguageSelctedFile)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/allmaincats/"+laguageSelctedFile;

        //String url = dburls.addAdvertisementAllMainCatogories;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setmaincats(response);

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

    public void loadsubcat(String maincat_id, String language_selected_file)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // String url ="http://rudhiraya.tk/register/donees_list.php";
        String url ="http://vegi.lk/admin/mobileappfiles/subcats/"+language_selected_file+"?subcatid="+maincat_id;

        //String url = dburls.addAdvertisementAllSubCatogories+maincat_id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setsubcat(response);
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


    public void setmaincats(JSONArray response) {

        listmains = new ArrayList<String>();
        //add ids in this list
        listmains.clear();
        sIdsMainCats.clear();

        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                String  main_id = obj.getString("main_cat_id");
                String main_name = obj.getString("main_cat_name");
                sIdsMainCats.add(main_id);
                listmains.add(main_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, listmains);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maincatspinner.setAdapter(dataAdapter);



    }


    public void setsubcat(JSONArray response) {


        listsubcat = new ArrayList<String>();
        sIdsSubCat.clear();
        listsubcat.clear();
        //add ids in this list


        for (int i = 0; i<response.length();i++)
        {
            try {
                JSONObject obj = response.getJSONObject(i);

                String subcatid = obj.getString("sub_catid");
                String subcat_name = obj.getString("sub_catname");
                sIdsSubCat.add(subcatid);
                listsubcat.add(subcat_name);


            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, listsubcat);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainsumcatspinner.setAdapter(dataAdapter);




    }






    public void submitdata(){


       if(add_application_type.getText().toString().isEmpty()
               || add_application_unitprice.getText().toString().isEmpty()
               || add_application_total_qty.getText().toString().isEmpty()
                || add_application_sellingplace.getText().toString().isEmpty())
       {

           add_application_type.setError("* Required");
           add_application_unitprice.setError("* Required");
           add_application_total_qty.setError("* Required");
           add_application_sellingplace.setError("* Required");
       }
       else {



           String ad_sup_id = SUP_ID;

           String ad_Cat_id = sIdsMainCats.get(maincatspinner.getSelectedItemPosition());
           String ad_Cat_Sub_id = sIdsSubCat.get(mainsumcatspinner.getSelectedItemPosition());

           String ad_type = add_application_type.getText().toString();
           String ad_unitprice = add_application_unitprice.getText().toString();
           String ad_units = uitsspinner.getSelectedItem().toString();
           String ad_total_qty = add_application_total_qty.getText().toString();
           String ad_sellingplace = add_application_sellingplace.getText().toString();

           /// Ecpiration datetime Date Picker
           int   day  = add_application_expiridate.getDayOfMonth();
           int   month= add_application_expiridate.getMonth();
           int   year = add_application_expiridate.getYear();
           Calendar calendar = Calendar.getInstance();
           calendar.set(year, month, day);

           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String ad_expirationdate = sdf.format(calendar.getTime());


           ///Date Picker

           //// Adverticement date
           Calendar calendarr = Calendar.getInstance();
           SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String ad_posteddate = sdff.format(calendarr.getTime());

           //// Adverticement date

           String as_status = "0";


           //  Toast.makeText(getContext(),ad_posteddate,Toast.LENGTH_LONG).show();
           confirmAdd(ad_sup_id,ad_Cat_id,ad_Cat_Sub_id,ad_type,ad_unitprice,ad_units,ad_total_qty,ad_sellingplace,ad_expirationdate,ad_posteddate,as_status);


       }



    }

    public void confirmAdd(String ad_sup_id,String ad_Cat_id,String ad_Cat_Sub_id,String ad_type,String ad_unitprice,String ad_units,String ad_total_qty,String ad_sellingplace
            ,String ad_expirationdate,String ad_posteddate, String as_status )
    {
        final String Ad_sup_id=ad_sup_id;
        final String Ad_Cat_id=ad_Cat_id;
        final String Ad_Cat_Sub_id=ad_Cat_Sub_id;
        final String Ad_type=ad_type;
        final String Ad_unitprice=ad_unitprice;
        final String Ad_units=ad_units;
        final String Ad_total_qty=ad_total_qty;
        final String Ad_sellingplace=ad_sellingplace;
        final String Ad_expirationdate=ad_expirationdate;
        final String Ad_posteddate=ad_posteddate;
        final String As_status=as_status;


        final RequestQueue queue = Volley.newRequestQueue(getContext());

        int type = Request.Method.POST;
        String url ="http://vegi.lk/admin/mobileappfiles/adingAdverticment/add.php";



        StringRequest request = new StringRequest(
                type,
                url,
                this,
                this
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                HashMap<String,String> params = new HashMap<>();
                params.put("ad_sup_id",Ad_sup_id);
                params.put("ad_Cat_id",Ad_Cat_id);
                params.put("ad_Cat_Sub_id",Ad_Cat_Sub_id);
                params.put("ad_type",Ad_type);
                params.put("ad_unitprice",Ad_unitprice);
                params.put("ad_units",Ad_units);
                params.put("ad_total_qty",Ad_total_qty);
                params.put("ad_sellingplace",Ad_sellingplace);
                params.put("ad_expirationdate",Ad_expirationdate);
                params.put("ad_posteddate",Ad_posteddate);
                params.put("as_status",As_status);

                if(Image_string_01 != null && Image_string_02 != null && Image_string_03 != null )
                {
                    params.put("image_string_01",Image_string_01);
                    params.put("image_string_02",Image_string_02);
                    params.put("image_string_03",Image_string_03);

                }
               else if(Image_string_01 != null && Image_string_02 != null && Image_string_03 == null )
                {
                    params.put("image_string_01",Image_string_01);
                    params.put("image_string_02",Image_string_02);
                    params.put("image_string_03","Empty");

                }
                else if(Image_string_01 != null && Image_string_02 == null && Image_string_03 == null )
                {
                    params.put("image_string_01",Image_string_01);
                    params.put("image_string_02","Empty");
                    params.put("image_string_03","Empty");

                }
                else if(Image_string_01 == null && Image_string_02 == null && Image_string_03 == null )
                {
                    params.put("image_string_01","Empty");
                    params.put("image_string_02","Empty");
                    params.put("image_string_03","Empty");

                }


                return params;
            }
        };




        final int socketTimeout = 10000;
        request.setRetryPolicy(new DefaultRetryPolicy(
                socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        progress_dialog = new ProgressDialog(getContext());
        progress_dialog.setMessage("Inserting data, please wait a moment...");
        progress_dialog.show();


        queue.add(request);

        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                queue.getCache().clear();

            }
        });

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        progress_dialog.dismiss();
        //Toast.makeText(getContext(),R.string.s_register_error, Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(),error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        progress_dialog.dismiss();
        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
        //Toast.makeText(getContext(),"සාර්ථකයි! නැවත එන්න..",Toast.LENGTH_LONG).show();
        fini();

    }

    public void fini()
    {
        Fragment Sellfragment = new SellerSellingFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,Sellfragment);
        ft.addToBackStack(null);
        ft.commit();

    }
}
