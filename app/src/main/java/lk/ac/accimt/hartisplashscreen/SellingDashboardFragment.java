package lk.ac.accimt.hartisplashscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import lk.ac.accimt.hartisplashscreen.sellingvegitables.SellVegitablesListFragment;


public class SellingDashboardFragment extends Fragment {


    View view;

    CardView item_card_vegitable;

    TextView item_card_vegitable_name,
            item_card_fruit_name,
            item_card_rice_name,
            item_card_riceseed_name,
            item_card_coconet_name,
            item_card_potato_name,
            item_card_seeds_name,
            item_card_pala_name,
            item_card_greenchilli_name,
            item_card_meat_name,
            item_card_fish_name,
            item_card_eggs_name,
            item_card_milkproduts_name,
            item_card_herbs_name,
            item_card_flowers_name,
            item_card_oils_name,
            item_card_animalcontrol_name,
            item_card_salt_name,
            item_card_agryequipments_name,
            item_card_otherboga_name,
            item_card_transportlorry_name;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_selling_dashboard, container, false);

        /////Setting up a login fragment tittle to main activity
        ((MainScreenActivity) getActivity()).setActionBarTitle("විකුණුම්");

        item_card_vegitable = view.findViewById(R.id.item_card_vegitable);


        /// Declaring Textviews
        item_card_vegitable_name = view.findViewById(R.id.item_card_vegitable_name);
        item_card_fruit_name = view.findViewById(R.id.item_card_fruit_name);
        item_card_rice_name = view.findViewById(R.id.item_card_rice_name);
        item_card_riceseed_name = view.findViewById(R.id.item_card_riceseed_name);
        item_card_coconet_name = view.findViewById(R.id.item_card_coconet_name);
        item_card_potato_name = view.findViewById(R.id.item_card_potato_name);

        item_card_seeds_name = view.findViewById(R.id.item_card_seeds_name);
        item_card_pala_name = view.findViewById(R.id.item_card_pala_name);
        item_card_greenchilli_name = view.findViewById(R.id.item_card_greenchilli_name);
        item_card_meat_name = view.findViewById(R.id.item_card_meat_name);
        item_card_fish_name = view.findViewById(R.id.item_card_fish_name);

        item_card_eggs_name = view.findViewById(R.id.item_card_eggs_name);
        item_card_milkproduts_name = view.findViewById(R.id.item_card_milkproduts_name);
        item_card_herbs_name = view.findViewById(R.id.item_card_herbs_name);
        item_card_flowers_name = view.findViewById(R.id.item_card_flowers_name);
        item_card_oils_name = view.findViewById(R.id.item_card_oils_name);

        item_card_animalcontrol_name = view.findViewById(R.id.item_card_animalcontrol_name);
        item_card_salt_name = view.findViewById(R.id.item_card_salt_name);
        item_card_agryequipments_name = view.findViewById(R.id.item_card_agryequipments_name);
        item_card_otherboga_name = view.findViewById(R.id.item_card_otherboga_name);
        item_card_transportlorry_name = view.findViewById(R.id.item_card_transportlorry_name);
        /// Declaring Textviews



        //// Card View Clids

        item_card_vegitable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //// Vegitable ID = 100
                String vegi_cat_id="100";

                Fragment sellvegifragment = new SellVegitablesListFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("VEG_CAT_ID",vegi_cat_id);
                sellvegifragment.setArguments(bundle);

                ft.replace(R.id.nav_host_fragment,sellvegifragment);
                ft.addToBackStack(null);
                ft.commit();


            }
        });

        //// Card View Clids


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


                item_card_vegitable_name.setText(R.string.sinhala_mainpage_elawalu);
                item_card_fruit_name.setText(R.string.sinhala_mainpage_palathuru);
                item_card_rice_name.setText(R.string.sinhala_mainpage_sahal);
                item_card_riceseed_name.setText(R.string.sinhala_mainpage_vee);
                item_card_coconet_name.setText(R.string.sinhala_mainpage_pol);
                item_card_potato_name.setText(R.string.sinhala_mainpage_ala);
                item_card_seeds_name.setText(R.string.sinhala_mainpage_dhanya);
                item_card_pala_name.setText(R.string.sinhala_mainpage_palawarga);
                item_card_greenchilli_name.setText(R.string.sinhala_mainpage_miris);
                item_card_meat_name.setText(R.string.sinhala_mainpage_mas);
                item_card_fish_name.setText(R.string.sinhala_mainpage_maalu);
                item_card_eggs_name.setText(R.string.sinhala_mainpage_biththara);
                item_card_milkproduts_name.setText(R.string.sinhala_mainpage_kiri);
                item_card_herbs_name.setText(R.string.sinhala_mainpage_oushada);
                item_card_flowers_name.setText(R.string.sinhala_mainpage_mal);
                item_card_oils_name.setText(R.string.sinhala_mainpage_thel);
                item_card_animalcontrol_name.setText(R.string.sinhala_mainpage_sathwapalana);
                item_card_salt_name.setText(R.string.sinhala_mainpage_luunu);
                item_card_agryequipments_name.setText(R.string.sinhala_mainpage_goviupakarana);
                item_card_otherboga_name.setText(R.string.sinhala_mainpage_wenathbooga);
                item_card_transportlorry_name.setText(R.string.sinhala_mainpage_prawahana);

    }
}
