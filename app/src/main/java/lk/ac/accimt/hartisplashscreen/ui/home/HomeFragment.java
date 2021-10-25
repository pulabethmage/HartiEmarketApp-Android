package lk.ac.accimt.hartisplashscreen.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import lk.ac.accimt.hartisplashscreen.LoginFragment;
import lk.ac.accimt.hartisplashscreen.MainScreenActivity;
import lk.ac.accimt.hartisplashscreen.R;
import lk.ac.accimt.hartisplashscreen.SFruitsListFragment;
import lk.ac.accimt.hartisplashscreen.SvegitableListFragment;
import lk.ac.accimt.hartisplashscreen.todaysprice.TodaysPrice;
import lk.ac.accimt.hartisplashscreen.ui.gallery.GalleryFragment;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {


    LinearLayout s_cat_vegitable, s_cat_fruits, s_cat_sahal,
            s_cat_vee, s_cat_daanya, s_cat_pol,
            s_cat_mas,s_cat_maalu,s_cat_karawala,
            s_cat_biththara, s_cat_kiri, s_cat_pani,
            s_cat_kulubadu, s_cat_palaawarga, s_cat_miris,
            s_cat_pela, s_cat_beeja,s_cat_bimmal,
            s_cat_thel, s_cat_ala,s_cat_maanshaboga,
            s_cat_mal,s_cat_apanayanaboga,s_cat_aayurwedashaka,
            s_cat_helabojun,s_cat_athkam,s_cat_gap,
            s_cat_krushiupakarana,s_cat_prawahana,s_cat_krushishramikain,
            s_cat_sathwapaalana,s_cat_pohora;

    TextView tv_vegitable, tv_fruits, tv_vee, tv_sahal, tv_daanya,tv_pol,tv_mas,tv_maalu,tv_karawala,tv_kiri,tv_biththara,tv_peni,tv_kulubadu,tv_palaawarga,tv_miris,
            tv_pela,tv_beeja,tv_bimmal,tv_thel,tv_ala,tv_maanshaboga,tv_mal,tv_apanayanaboga,tv_ayurwedashaka,tv_helabojun,tv_athkam,tv_gap,tv_krushiupakarana,tv_prawahana,
            tv_krushishramikain,tv_sathwapaalana,tv_pohora;


    Button s_btn_sepayuma,s_btn_thogamila;

    String language;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inisializing the buttiongs and layouts
        s_btn_sepayuma = view.findViewById(R.id.s_btn_sepayuma);
        s_btn_thogamila = view.findViewById(R.id.s_btn_thogamila);

        s_cat_vegitable = view.findViewById(R.id.s_cat_vegitable);
        s_cat_fruits = view.findViewById(R.id.s_cat_fruits);
        s_cat_sahal = view.findViewById(R.id.s_cat_sahal);

        s_cat_vee = view.findViewById(R.id.s_cat_vee);
        s_cat_daanya = view.findViewById(R.id.s_cat_daanya);
        s_cat_pol = view.findViewById(R.id.s_cat_pol);


        s_cat_mas = view.findViewById(R.id.s_cat_mas);
        s_cat_maalu = view.findViewById(R.id.s_cat_maalu);
        s_cat_karawala= view.findViewById(R.id.s_cat_karawala);

        s_cat_kiri = view.findViewById(R.id.s_cat_kiri);
        s_cat_biththara = view.findViewById(R.id.s_cat_biththara);
        s_cat_pani = view.findViewById(R.id.s_cat_pani);

        s_cat_kulubadu = view.findViewById(R.id.s_cat_kulubadu);
        s_cat_palaawarga = view.findViewById(R.id.s_cat_palaawarga);
        s_cat_miris = view.findViewById(R.id.s_cat_miris);

        s_cat_pela = view.findViewById(R.id.s_cat_pela);
        s_cat_beeja = view.findViewById(R.id.s_cat_beeja);
        s_cat_bimmal = view.findViewById(R.id.s_cat_bimmal);

        s_cat_thel = view.findViewById(R.id.s_cat_thel);
        s_cat_ala = view.findViewById(R.id.s_cat_ala);
        s_cat_maanshaboga = view.findViewById(R.id.s_cat_maanshaboga);

        s_cat_mal = view.findViewById(R.id.s_cat_mal);
        s_cat_apanayanaboga = view.findViewById(R.id.s_cat_apanayanaboga);
        s_cat_aayurwedashaka = view.findViewById(R.id.s_cat_aayurwedashaka);

        s_cat_helabojun = view.findViewById(R.id.s_cat_helabojun);
        s_cat_athkam = view.findViewById(R.id.s_cat_athkam);
        s_cat_gap = view.findViewById(R.id.s_cat_gap);


        s_cat_krushiupakarana = view.findViewById(R.id.s_cat_krushiupakarana);
        s_cat_prawahana = view.findViewById(R.id.s_cat_prawahana);
        s_cat_krushishramikain = view.findViewById(R.id.s_cat_krushishramikain);

        s_cat_sathwapaalana = view.findViewById(R.id.s_cat_sathwapaalana);
        s_cat_pohora = view.findViewById(R.id.s_cat_pohora);



        ///Inisializing the textviews
        tv_vegitable= view.findViewById(R.id.main_cat_textview_elawalu);
        tv_fruits= view.findViewById(R.id.main_cat_textview_palathuru);
        tv_vee= view.findViewById(R.id.main_cat_textview_vee);
        tv_sahal= view.findViewById(R.id.main_cat_textview_sahal);
        tv_daanya= view.findViewById(R.id.main_cat_textview_daanya);
        tv_pol= view.findViewById(R.id.main_cat_textview_pol);
        tv_mas= view.findViewById(R.id.main_cat_textview_mas);
        tv_maalu= view.findViewById(R.id.main_cat_textview_maalu);
        tv_karawala= view.findViewById(R.id.main_cat_textview_karawala);
        tv_kiri= view.findViewById(R.id.main_cat_textview_kiri);

        tv_biththara= view.findViewById(R.id.main_cat_textview_biththara);
        tv_peni= view.findViewById(R.id.main_cat_textview_pani);
        tv_kulubadu= view.findViewById(R.id.main_cat_textview_kulubadu);
        tv_palaawarga= view.findViewById(R.id.main_cat_textview_palawarga);
        tv_miris= view.findViewById(R.id.main_cat_textview_miris);
        tv_pela= view.findViewById(R.id.main_cat_textview_pela);
        tv_beeja= view.findViewById(R.id.main_cat_textview_beeja);
        tv_bimmal= view.findViewById(R.id.main_cat_textview_bimmal);
        tv_thel= view.findViewById(R.id.main_cat_textview_thel);
        tv_ala= view.findViewById(R.id.main_cat_textview_ala);

        tv_maanshaboga= view.findViewById(R.id.main_cat_textview_maanshaboga);
        tv_mal= view.findViewById(R.id.main_cat_textview_mal);
        tv_apanayanaboga= view.findViewById(R.id.main_cat_textview_apanayanaboga);
        tv_ayurwedashaka= view.findViewById(R.id.main_cat_textview_aayurwedashaka);
        tv_helabojun= view.findViewById(R.id.main_cat_textview_helabojun);
        tv_athkam= view.findViewById(R.id.main_cat_textview_athkam);
        tv_gap= view.findViewById(R.id.main_cat_textview_gap);
        tv_krushiupakarana= view.findViewById(R.id.main_cat_textview_goviupakarana);
        tv_prawahana= view.findViewById(R.id.main_cat_textview_prawahana);
        tv_krushishramikain= view.findViewById(R.id.main_cat_textview_krushishramikayin);

        tv_sathwapaalana= view.findViewById(R.id.main_cat_textview_sathwapalana);
        tv_pohora= view.findViewById(R.id.main_cat_textview_pohora);

        SharedPreferences prefs = this.getActivity().getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
         language = prefs.getString("language", "notselected");

        if(language.equals("Sinhala"))
        {
            /////Setting up a login fragment tittle to main activity
            ((MainScreenActivity) getActivity()).setActionBarTitle("මුල් පිට");
        }
        else if(language.equals("Tamil")) {

            ((MainScreenActivity) getActivity()).setActionBarTitle("வீடு");

            s_btn_sepayuma.setText(R.string.tamil_mainpage_sapayuma_tamil);
            s_btn_thogamila.setText(R.string.tamil_mainpage_todayprice_tamil);

            tv_vegitable.setText(R.string.tamil_mainpage_elawalu_tamil);
            tv_fruits.setText(R.string.tamil_mainpage_palathuru_tamil);
            tv_vee.setText(R.string.tamil_mainpage_vee_tamil);
            tv_sahal.setText(R.string.tamil_mainpage_sahal_tamil);
            tv_daanya.setText(R.string.tamil_mainpage_dhanya_tamil);
            tv_pol.setText(R.string.tamil_mainpage_pol_tamil);
            tv_mas.setText(R.string.tamil_mainpage_mas_tamil);
            tv_maalu.setText(R.string.tamil_mainpage_maalu_tamil);
            tv_karawala.setText(R.string.tamil_mainpage_karawala_tamil);
            tv_kiri.setText(R.string.tamil_mainpage_kiri_tamil);

            tv_biththara.setText(R.string.tamil_mainpage_biththara_tamil);
            tv_peni.setText(R.string.tamil_mainpage_pani_tamil);
            tv_kulubadu.setText(R.string.tamil_mainpage_kulubadu_tamil);
            tv_palaawarga.setText(R.string.tamil_mainpage_palawarga_tamil);
            tv_miris.setText(R.string.tamil_mainpage_miris_tamil);
            tv_pela.setText(R.string.tamil_mainpage_pela_tamil);
            tv_beeja.setText(R.string.tamil_mainpage_beeja_tamil);
            tv_bimmal.setText(R.string.tamil_mainpage_bimmal_tamil);
            tv_thel.setText(R.string.tamil_mainpage_thel_tamil);
            tv_ala.setText(R.string.tamil_mainpage_ala_tamil);

            tv_maanshaboga.setText(R.string.tamil_mainpage_maanshaboga_tamil);
            tv_mal.setText(R.string.tamil_mainpage_mal_tamil);
            tv_apanayanaboga.setText(R.string.tamil_mainpage_apanayanaboga_tamil);
            tv_ayurwedashaka.setText(R.string.tamil_mainpage_aayurwedashaka_tamil);
            tv_helabojun.setText(R.string.tamil_mainpage_helabojun_tamil);
            tv_athkam.setText(R.string.tamil_mainpage_athkam_tamil);
            tv_gap.setText(R.string.tamil_mainpage_gap_tamil);
            tv_krushiupakarana.setText(R.string.tamil_mainpage_goviupakarana_tamil);
            tv_prawahana.setText(R.string.tamil_mainpage_prawahana_tamil);
            tv_krushishramikain.setText(R.string.tamil_mainpage_krushishramikayin_tamil);

            tv_sathwapaalana.setText(R.string.tamil_mainpage_sathwapalana_tamil);
            tv_pohora.setText(R.string.tamil_mainpage_pohora_tamil);

        }
        else if(language.equals("English")) {

            ((MainScreenActivity) getActivity()).setActionBarTitle("Home");

            s_btn_sepayuma.setText(R.string.tamil_mainpage_sapayuma_english);
            s_btn_thogamila.setText(R.string.tamil_mainpage_todayprice_english);

            tv_vegitable.setText(R.string.tamil_mainpage_elawalu_english);
            tv_fruits.setText(R.string.tamil_mainpage_palathuru_english);
            tv_vee.setText(R.string.tamil_mainpage_vee_english);
            tv_sahal.setText(R.string.tamil_mainpage_sahal_english);
            tv_daanya.setText(R.string.tamil_mainpage_dhanya_english);
            tv_pol.setText(R.string.tamil_mainpage_pol_english);
            tv_mas.setText(R.string.tamil_mainpage_mas_english);
            tv_maalu.setText(R.string.tamil_mainpage_maalu_english);
            tv_karawala.setText(R.string.tamil_mainpage_karawala_english);
            tv_kiri.setText(R.string.tamil_mainpage_kiri_english);

            tv_biththara.setText(R.string.tamil_mainpage_biththara_english);
            tv_peni.setText(R.string.tamil_mainpage_pani_english);
            tv_kulubadu.setText(R.string.tamil_mainpage_kulubadu_english);
            tv_palaawarga.setText(R.string.tamil_mainpage_palawarga_english);
            tv_miris.setText(R.string.tamil_mainpage_miris_english);
            tv_pela.setText(R.string.tamil_mainpage_pela_english);
            tv_beeja.setText(R.string.tamil_mainpage_beeja_english);
            tv_bimmal.setText(R.string.tamil_mainpage_bimmal_english);
            tv_thel.setText(R.string.tamil_mainpage_thel_english);
            tv_ala.setText(R.string.tamil_mainpage_ala_english);

            tv_maanshaboga.setText(R.string.tamil_mainpage_maanshaboga_english);
            tv_mal.setText(R.string.tamil_mainpage_mal_english);
            tv_apanayanaboga.setText(R.string.tamil_mainpage_apanayanaboga_english);
            tv_ayurwedashaka.setText(R.string.tamil_mainpage_aayurwedashaka_english);
            tv_helabojun.setText(R.string.tamil_mainpage_helabojun_english);
            tv_athkam.setText(R.string.tamil_mainpage_athkam_english);
            tv_gap.setText(R.string.tamil_mainpage_gap_english);
            tv_krushiupakarana.setText(R.string.tamil_mainpage_goviupakarana_english);
            tv_prawahana.setText(R.string.tamil_mainpage_prawahana_english);
            tv_krushishramikain.setText(R.string.tamil_mainpage_krushishramikayin_english);

            tv_sathwapaalana.setText(R.string.tamil_mainpage_sathwapalana_english);
            tv_pohora.setText(R.string.tamil_mainpage_pohora_english);

        }




        ////// Buttons
        s_btn_sepayuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Fragment homefragment = new HomeFragment();

                Fragment loginFragment = new LoginFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                ft.remove(homefragment);
                ft.add(R.id.nav_host_fragment,loginFragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        s_btn_thogamila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment homefragment = new HomeFragment();

                Fragment todaysPriceFragment = new TodaysPrice();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                ft.remove(homefragment);
                ft.add(R.id.nav_host_fragment,todaysPriceFragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        ///// Buttons

        s_cat_vegitable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_elawalu);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_elawalu_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_elawalu_english);
                }
                commonFunction("1",maincatname);

            }
        });

        s_cat_fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_palathuru);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_palathuru_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_palathuru_english);
                }
                commonFunction("2",maincatname);

            }
        });

        s_cat_vee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_vee);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_vee_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_vee_english);
                }
                commonFunction("3",maincatname);

            }
        });

        s_cat_sahal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_sahal);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_sahal_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_sahal_english);
                }
                commonFunction("4",maincatname);

            }
        });


        s_cat_daanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_dhanya);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_dhanya_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_dhanya_english);
                }
                commonFunction("5",maincatname);

            }
        });

        s_cat_pol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_pol);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pol_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pol_english);
                }
                commonFunction("6",maincatname);

            }
        });

        s_cat_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_mas);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_mas_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_mas_english);
                }
                commonFunction("10",maincatname);

            }
        });

        s_cat_maalu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_maalu);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_maalu_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_maalu_english);
                }
                commonFunction("11",maincatname);

            }
        });

        s_cat_karawala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_karawala);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_karawala_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_karawala_english);
                }
                commonFunction("26",maincatname);

            }
        });

        s_cat_kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_kiri);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_kiri_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_kiri_english);
                }
                commonFunction("13",maincatname);

            }
        });

        s_cat_biththara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_biththara);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_biththara_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_biththara_english);
                }
                commonFunction("12",maincatname);

            }
        });

        s_cat_pani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_pani);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pani_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pani_english);
                }
                commonFunction("14",maincatname);

            }
        });

        s_cat_kulubadu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_kulubadu);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_kulubadu_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_kulubadu_english);
                }
                commonFunction("7",maincatname);

            }
        });

        s_cat_palaawarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_palawarga);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_palawarga_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_palawarga_english);
                }
                commonFunction("9",maincatname);

            }
        });

        s_cat_miris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_miris);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_miris_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_miris_english);
                }
                commonFunction("29",maincatname);

            }
        });

        s_cat_pela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_pela);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pela_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pela_english);
                }
                commonFunction("17",maincatname);

            }
        });

        s_cat_beeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_beeja);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_beeja_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_beeja_english);
                }
                commonFunction("18",maincatname);

            }
        });

        s_cat_bimmal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_bimmal);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_bimmal_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_bimmal_english);
                }
                commonFunction("19",maincatname);

            }
        });

        s_cat_thel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_ala);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_ala_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_thel_english);
                }
                commonFunction("33",maincatname);

            }
        });

        s_cat_ala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_ala);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_ala_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_ala_english);
                }
                commonFunction("27",maincatname);

            }
        });

        s_cat_maanshaboga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_maanshaboga);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_maanshaboga_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_maanshaboga_english);
                }
                commonFunction("30",maincatname);

            }
        });

        s_cat_mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_mal);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_mal_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_mal_english);
                }
                commonFunction("21",maincatname);

            }
        });

        s_cat_apanayanaboga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_apanayanaboga);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_apanayanaboga_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_apanayanaboga_english);
                }
                commonFunction("15",maincatname);

            }
        });

        s_cat_aayurwedashaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_aayurwedashaka);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_aayurwedashaka_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_aayurwedashaka_english);
                }
                commonFunction("28",maincatname);

            }
        });


        s_cat_helabojun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_helabojun);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_helabojun_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_helabojun_english);
                }
                commonFunction("25",maincatname);

            }
        });

        s_cat_athkam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                   // maincatname = "අත්කම්";
                    maincatname = getResources().getString(R.string.sinhala_mainpage_athkam);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_athkam_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_athkam_english);
                }
                commonFunction("34",maincatname);

            }
        });

        s_cat_gap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_gap);
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_gap_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_gap_english);
                }
                commonFunction("31",maincatname);

            }
        });


        s_cat_krushiupakarana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    //maincatname = "කෘෂි උපකරණ";
                    maincatname = getResources().getString(R.string.sinhala_mainpage_goviupakarana);
                }
                else if(language.equals("Tamil")){
                    //maincatname = "விவசாய உபகரணங்கள்";
                    maincatname = getResources().getString(R.string.tamil_mainpage_goviupakarana_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_goviupakarana_english);
                }
                commonFunction("22",maincatname);

            }
        });

        s_cat_prawahana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    //maincatname = "ප්රවාහන";
                    maincatname = getResources().getString(R.string.sinhala_mainpage_prawahana);
                }
                else if(language.equals("Tamil")){
                    //maincatname = "போக்குவரத்து";
                    maincatname = getResources().getString(R.string.tamil_mainpage_prawahana_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_prawahana_english);
                }
                commonFunction("23",maincatname);

            }
        });

        s_cat_krushishramikain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                   // maincatname = "කෘෂි ශ්රමිකයන්";
                    maincatname = getResources().getString(R.string.sinhala_mainpage_krushishramikayin);
                }
                else if(language.equals("Tamil")){
                    //maincatname = "விவசாய கூலி";
                    maincatname = getResources().getString(R.string.tamil_mainpage_krushishramikayin_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_krushishramikayin_english);
                }
                commonFunction("24",maincatname);

            }
        });


        s_cat_sathwapaalana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    //maincatname = "සත්ව පාලන";
                    maincatname = getResources().getString(R.string.sinhala_mainpage_sathwapalana);
                }
                else if(language.equals("Tamil")){
                   // maincatname = "கால்நடை வளர்ப்பு";
                    maincatname = getResources().getString(R.string.tamil_mainpage_sathwapalana_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_sathwapalana_english);
                }
                commonFunction("16",maincatname);

            }
        });

        s_cat_pohora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maincatname="";
                if(language.equals("Sinhala")) {
                    maincatname = getResources().getString(R.string.sinhala_mainpage_pohora);;
                }
                else if(language.equals("Tamil")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pohora_tamil);
                }
                else if(language.equals("English")){
                    maincatname = getResources().getString(R.string.tamil_mainpage_pohora_english);
                }
                commonFunction("20",maincatname);

            }
        });






        return  view;

    }


    public void commonFunction(String maincatID , String maincatname)
    {
        Fragment sfragment = new SvegitableListFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("FROM_MAIN_CAT_ID",maincatID);
        bundle.putString("FROM_MAIN_CAT_NAME",maincatname);
        sfragment.setArguments(bundle);

        ft.replace(R.id.nav_host_fragment,sfragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}