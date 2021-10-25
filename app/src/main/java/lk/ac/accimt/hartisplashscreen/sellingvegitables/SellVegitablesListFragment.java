package lk.ac.accimt.hartisplashscreen.sellingvegitables;

import android.content.Context;
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

import lk.ac.accimt.hartisplashscreen.AddAverticementFragment;
import lk.ac.accimt.hartisplashscreen.R;


public class SellVegitablesListFragment extends Fragment {

    View view;

    CardView veg_item_card_01,veg_item_card_02;

    TextView veg_item_01,veg_item_02,veg_item_03,veg_item_04,veg_item_05,veg_item_06,veg_item_07,veg_item_08,veg_item_09,veg_item_10,
            veg_item_11,veg_item_12,veg_item_13,veg_item_14,veg_item_15,veg_item_16,veg_item_17,veg_item_18,veg_item_19,veg_item_20,
            veg_item_21,veg_item_22,veg_item_23,veg_item_24,veg_item_25,veg_item_26,veg_item_27;

    String VEG_CAT_ID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =  inflater.inflate(R.layout.fragment_sell_vegitables_list, container, false);

        veg_item_card_01 = view.findViewById(R.id.vegi_item_card_01);
        veg_item_card_02 = view.findViewById(R.id.vegi_item_card_02);

        veg_item_01 = view.findViewById(R.id.vegi_item_01);
        veg_item_02 = view.findViewById(R.id.vegi_item_02);
        veg_item_03 = view.findViewById(R.id.vegi_item_03);

        veg_item_04 = view.findViewById(R.id.vegi_item_04);
        veg_item_05 = view.findViewById(R.id.vegi_item_05);
        veg_item_06 = view.findViewById(R.id.vegi_item_06);

        veg_item_07 = view.findViewById(R.id.vegi_item_07);
        veg_item_08 = view.findViewById(R.id.vegi_item_08);
        veg_item_09 = view.findViewById(R.id.vegi_item_09);

        veg_item_10 = view.findViewById(R.id.vegi_item_10);
        veg_item_11 = view.findViewById(R.id.vegi_item_11);
        veg_item_12 = view.findViewById(R.id.vegi_item_12);

        veg_item_13 = view.findViewById(R.id.vegi_item_13);
        veg_item_14 = view.findViewById(R.id.vegi_item_14);
        veg_item_15 = view.findViewById(R.id.vegi_item_15);

        veg_item_16 = view.findViewById(R.id.vegi_item_16);
        veg_item_17 = view.findViewById(R.id.vegi_item_17);
        veg_item_18 = view.findViewById(R.id.vegi_item_18);

        veg_item_19 = view.findViewById(R.id.vegi_item_19);
        veg_item_20 = view.findViewById(R.id.vegi_item_20);
        veg_item_21 = view.findViewById(R.id.vegi_item_21);

        veg_item_22 = view.findViewById(R.id.vegi_item_22);
        veg_item_23 = view.findViewById(R.id.vegi_item_23);
        veg_item_24 = view.findViewById(R.id.vegi_item_24);
        veg_item_25 = view.findViewById(R.id.vegi_item_25);
        veg_item_26 = view.findViewById(R.id.vegi_item_26);
        veg_item_27 = view.findViewById(R.id.vegi_item_27);


        //// Card View Clicks

        veg_item_card_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //// Vegitable sub cat ID = 101
                String vegi_sub_cat_id="101";

                Fragment adaddfragment = new AddAverticementFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putString("VegName",veg_item_01.getText().toString());
                bundle.putString("VEG_CAT_ID",VEG_CAT_ID);
                bundle.putString("VEG_SUB_CAT_ID",vegi_sub_cat_id);
                adaddfragment.setArguments(bundle);


                ft.replace(R.id.nav_host_fragment,adaddfragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        veg_item_card_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //// Vegitable sub cat ID = 101
                String vegi_sub_cat_id="102";

                Fragment adaddfragment = new AddAverticementFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("VegName",veg_item_02.getText().toString());
                bundle.putString("VEG_CAT_ID",VEG_CAT_ID);
                bundle.putString("VEG_SUB_CAT_ID",vegi_sub_cat_id);
                adaddfragment.setArguments(bundle);
                ft.replace(R.id.nav_host_fragment,adaddfragment);
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

         VEG_CAT_ID = getArguments().getString("VEG_CAT_ID");

        veg_item_01.setText(R.string.s_selling_vegi_item_01);
        veg_item_02.setText(R.string.s_selling_vegi_item_02);
        veg_item_03.setText(R.string.s_selling_vegi_item_03);
        veg_item_04.setText(R.string.s_selling_vegi_item_04);
        veg_item_05.setText(R.string.s_selling_vegi_item_05);
        veg_item_06.setText(R.string.s_selling_vegi_item_06);
        veg_item_07.setText(R.string.s_selling_vegi_item_07);
        veg_item_08.setText(R.string.s_selling_vegi_item_08);
        veg_item_09.setText(R.string.s_selling_vegi_item_09);
        veg_item_10.setText(R.string.s_selling_vegi_item_10);
        veg_item_11.setText(R.string.s_selling_vegi_item_11);
        veg_item_12.setText(R.string.s_selling_vegi_item_12);
        veg_item_13.setText(R.string.s_selling_vegi_item_13);
        veg_item_14.setText(R.string.s_selling_vegi_item_14);
        veg_item_15.setText(R.string.s_selling_vegi_item_15);
        veg_item_16.setText(R.string.s_selling_vegi_item_16);
        veg_item_17.setText(R.string.s_selling_vegi_item_17);
        veg_item_18.setText(R.string.s_selling_vegi_item_18);
        veg_item_19.setText(R.string.s_selling_vegi_item_19);
        veg_item_20.setText(R.string.s_selling_vegi_item_20);
        veg_item_21.setText(R.string.s_selling_vegi_item_21);
        veg_item_22.setText(R.string.s_selling_vegi_item_22);
        veg_item_23.setText(R.string.s_selling_vegi_item_23);
        veg_item_24.setText(R.string.s_selling_vegi_item_24);
        veg_item_25.setText(R.string.s_selling_vegi_item_25);
        veg_item_26.setText(R.string.s_selling_vegi_item_26);
        veg_item_27.setText(R.string.s_selling_vegi_item_27);


    }

}
