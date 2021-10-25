package lk.ac.accimt.hartisplashscreen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SingleFruitItemRecyclerViewAdapter extends RecyclerView.Adapter<SingleFruitItemRecyclerViewAdapter.ViewHolder> {


    //Arrays Lists
    private ArrayList<String> sImages = new ArrayList<>();
    private ArrayList<String> sImageNames = new ArrayList<>();
    private Context sContext;

    public SingleFruitItemRecyclerViewAdapter(Context sContext, ArrayList<String> sImages, ArrayList<String> sImageNames) {
        this.sImages = sImages;
        this.sImageNames = sImageNames;
        this.sContext = sContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_single_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(sContext)
                .asBitmap()
                .load(sImages.get(position))
                .into(holder.single_image);

        holder.single_image_name.setText(sImageNames.get(position));

        holder.single_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(sContext,sImageNames.get(position),Toast.LENGTH_LONG).show();


                MainScreenActivity activity = (MainScreenActivity)view.getContext();
                Fragment myFragment = new FruitDataFragment();

                Bundle bundle = new Bundle();
                bundle.putString("FruitName",sImageNames.get(position));
                bundle.putString("FruitImage",sImages.get(position));
                myFragment.setArguments(bundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.dataFruitFragment, myFragment).addToBackStack(null).commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return sImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView single_image;
        TextView single_image_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            single_image = itemView.findViewById(R.id.single_image);
            single_image_name = itemView.findViewById(R.id.single_image_name);


        }
    }


}
