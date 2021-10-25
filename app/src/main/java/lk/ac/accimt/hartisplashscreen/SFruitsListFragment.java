package lk.ac.accimt.hartisplashscreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class SFruitsListFragment extends Fragment {

    //Arrays Lists
    private ArrayList<String> sImages = new ArrayList<>();
    private ArrayList<String> sImageNames = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sfruits_list, container, false);

        /////Setting up a login fragment tittle to main activity
        ((MainScreenActivity) getActivity()).setActionBarTitle("පලතුරු");

        /// for horizontal recycler view
        getsingledata(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        ////Default data
        MainScreenActivity activity = (MainScreenActivity)getView().getContext();
        Fragment myFragment = new FruitDataFragment();

        Bundle bundle = new Bundle();
        bundle.putString("FruitName",sImageNames.get(0));
        bundle.putString("FruitImage",sImages.get(0));
        myFragment.setArguments(bundle);
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.dataFruitFragment, myFragment).addToBackStack(null).commit();
        ////Default data

    }

    public void getsingledata(View view)
    {

        sImages.add("http://192.248.85.22/vegilkimages/Fruits/Mango/Mango-PNG.png");
        sImageNames.add("අඹ");

        sImages.add("http://192.248.85.22/vegilkimages/Fruits/Guava/guava_PNG63.png");
        sImageNames.add("පේර");

        sImages.add("http://192.248.85.22/vegilkimages/Fruits/Pineapple/Pineapple-PNG-Background.png");
        sImageNames.add("අන්නාසි");

        sImages.add("http://192.248.85.22/vegilkimages/Fruits/Papaya/Papaya-PNG-File.png");
        sImageNames.add("පැපොල්");

        sImages.add("http://192.248.85.22/vegilkimages/Fruits/Banana/Banana-PNG.png");
        sImageNames.add("කෙසෙල්");

        sImages.add("http://192.248.85.22/vegilkimages/Fruits/Orange/Half-Orange-PNG-HD.png");
        sImageNames.add("දොඩම්");



        initRecyclerSingleAdapter(view);
    }

    public void initRecyclerSingleAdapter(View view)
    {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.single_fitems_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        SingleFruitItemRecyclerViewAdapter adapter = new SingleFruitItemRecyclerViewAdapter(getContext(),sImages,sImageNames);
        recyclerView.setAdapter(adapter);
    }









}
