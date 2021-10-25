package lk.ac.accimt.hartisplashscreen.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import lk.ac.accimt.hartisplashscreen.LoginFragment;
import lk.ac.accimt.hartisplashscreen.R;
import lk.ac.accimt.hartisplashscreen.ui.home.HomeFragment;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Fragment loginfragment = new LoginFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,loginfragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}