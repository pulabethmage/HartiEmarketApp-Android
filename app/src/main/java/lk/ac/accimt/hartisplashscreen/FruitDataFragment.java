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
import android.widget.Toast;

import java.util.ArrayList;


public class FruitDataFragment extends Fragment {


    String fruitNewName,fruitNewImage;

    //Arrays Lists
    private ArrayList<String> adsImages = new ArrayList<>();
    private ArrayList<String> adsTittles= new ArrayList<>();
    private ArrayList<String> adsUnitPrice= new ArrayList<>();
    private ArrayList<String> adsSellerName= new ArrayList<>();
    private ArrayList<String> adsFroms= new ArrayList<>();
    private ArrayList<String> adsGoviCenter= new ArrayList<>();
    private ArrayList<String> adsDatePlaced= new ArrayList<>();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_fruit_data, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            fruitNewName=bundle.getString("FruitName");
            fruitNewImage=bundle.getString("FruitImage");
            getdata(view,fruitNewName,fruitNewImage);
        }
        else
        loadalldata(view);

    }



    public void initRecyclerSingleAdapter(View view)
    {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = view.findViewById(R.id.fruit_list_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        //AdsRecyclerViewAdapter adapter = new AdsRecyclerViewAdapter(getContext(),adsImages,adsTittles,adsUnitPrice,adsSellerName,adsFroms,adsGoviCenter,adsDatePlaced);
        //recyclerView.setAdapter(adapter);
    }



    public void getdata(View view,String Fruitname,String Fruitimage) {

        ////Get the selected data

        adsImages.add(Fruitimage);
        adsTittles.add(Fruitname);
        adsUnitPrice.add("120/kg");
        adsSellerName.add("විපුලසේන කොතලවල");
        adsFroms.add("නුවරඑළිය");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");


        adsImages.add(Fruitimage);
        adsTittles.add(Fruitname);
        adsUnitPrice.add("90/kg");
        adsSellerName.add("මල්ෂාන් ගුණවර්ධන");
        adsFroms.add("මොණරාගල");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add(Fruitimage);
        adsTittles.add(Fruitname);
        adsUnitPrice.add("75/kg");
        adsSellerName.add("සුනේෂ්කා දේවසිරි");
        adsFroms.add("මාතලේ");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add(Fruitimage);
        adsTittles.add(Fruitname);
        adsUnitPrice.add("125/kg");
        adsSellerName.add("රාමනායක විජරත්න");
        adsFroms.add("මන්නාරම");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add(Fruitimage);
        adsTittles.add(Fruitname);
        adsUnitPrice.add("78/kg");
        adsSellerName.add("යේෂාන් නන්දසිරි");
        adsFroms.add("කිලිනොච්චි");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add(Fruitimage);
        adsTittles.add(Fruitname);
        adsUnitPrice.add("65/kg");
        adsSellerName.add("රූපරත්න ගුණවර්ධන");
        adsFroms.add("කෑගල්ල");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        initRecyclerSingleAdapter(view);

    }

    public void loadalldata(View view)
    {
        /// Getting the all the data

        adsImages.add("http://192.248.85.22/vegilkimages/Fruits/Mango/Mango-PNG.png");
        adsTittles.add("අඹ");
        adsUnitPrice.add("85/kg");
        adsSellerName.add("තිලකරත්න ජයසේන\n");
        adsFroms.add("කළුතර");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add("http://192.248.85.22/vegilkimages/Fruits/Guava/guava_PNG63.png");
        adsTittles.add("පේර");
        adsUnitPrice.add("63/kg");
        adsSellerName.add("නයනන්ද ලංගේ");
        adsFroms.add("හම්බන්තොට");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add("http://192.248.85.22/vegilkimages/Fruits/Pineapple/Pineapple-PNG-Background.png");
        adsTittles.add("අන්නාසි");
        adsUnitPrice.add("98/kg");
        adsSellerName.add("උපුල් අබේනායක");
        adsFroms.add("කොළඹ");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");

        adsImages.add("http://192.248.85.22/vegilkimages/Fruits/Papaya/Papaya-PNG-File.png");
        adsTittles.add("පැපොල්");
        adsUnitPrice.add("21/kg");
        adsSellerName.add("ගීත් කළුපාහාන");
        adsFroms.add("මඩකලපුව");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");


        adsImages.add("http://192.248.85.22/vegilkimages/Fruits/Banana/Banana-PNG.png");
        adsTittles.add("කෙසෙල්");
        adsUnitPrice.add("53/kg");
        adsSellerName.add("මොහින්ද්රා ගබදමුදලිගේ");
        adsFroms.add("බදුල්ල");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");


        adsImages.add("http://192.248.85.22/vegilkimages/Fruits/Orange/Half-Orange-PNG-HD.png");
        adsTittles.add("දොඩම්");
        adsUnitPrice.add("41/kg");
        adsSellerName.add("ලොකුනරංගොඩ බුද්ධිකා");
        adsFroms.add("අම්පාර");
        adsGoviCenter.add("‍ගොවි මධ්යස්ථානය 1234");
        adsDatePlaced.add("‍2020/01/01");



        initRecyclerSingleAdapter(view);
    }






    }
