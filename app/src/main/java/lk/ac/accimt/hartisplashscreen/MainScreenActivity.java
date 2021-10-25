package lk.ac.accimt.hartisplashscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import lk.ac.accimt.hartisplashscreen.ui.home.HomeFragment;
import lk.ac.accimt.hartisplashscreen.ui.register.RegisterFragment;

public class MainScreenActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    VegitableDataFragment vegitableDataFragment;
    SvegitableListFragment svegitableListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ////

        ///Change the Text to seleted language
        Menu menu = navigationView.getMenu();

        MenuItem nav_home = menu.findItem(R.id.nav_home);
        MenuItem nav_gallery = menu.findItem(R.id.nav_gallery);
        MenuItem nav_tools = menu.findItem(R.id.nav_tools);
        MenuItem nav_share = menu.findItem(R.id.nav_share);
        MenuItem nav_send = menu.findItem(R.id.nav_send);
        MenuItem nav_register = menu.findItem(R.id.nav_register);

        SharedPreferences prefs = getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE);
        String language = prefs.getString("language", "notselected");
        if(language.equals("Tamil"))
        {
            nav_home.setTitle(R.string.menu_home_tamil);
            nav_gallery.setTitle(R.string.menu_gallery_tamil);

            nav_tools.setTitle(R.string.tamil_menu_tools_tamil);
            nav_share.setTitle(R.string.tamil_menu_share_tamil);

            nav_send.setTitle(R.string.tamil_menu_send_tamil);
            nav_register.setTitle(R.string.tamil_menu_register_tamil);

        }
        else if(language.equals("English"))
        {
            nav_home.setTitle(R.string.menu_home_english);
            nav_gallery.setTitle(R.string.menu_gallery_english);

            nav_tools.setTitle(R.string.tamil_menu_tools_english);
            nav_share.setTitle(R.string.tamil_menu_share_english);

            nav_send.setTitle(R.string.tamil_menu_send_english);
            nav_register.setTitle(R.string.tamil_menu_register_english);

        }
        //////////



        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send,R.id.nav_register)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Hard Code

        vegitableDataFragment = new VegitableDataFragment();
        svegitableListFragment = new SvegitableListFragment();


    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);

        menu.getItem(0).setTitle("Logout");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
                case R.id.action_settings:
                    logout();
                    break;
                case R.id.action_changelanguage:
                    languagechange();
                    break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void logout(){

        /// Saving data on device
        SharedPreferences.Editor editor = this.getSharedPreferences("LOGGEDDETAILS", MODE_PRIVATE).edit();
        editor.putString("SUP_LOGED","0");
        editor.apply();

        Fragment homefragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,homefragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    public void languagechange()
    {
        Intent languageIntent = new Intent(this,SelectLanguageActivity.class);
        startActivity(languageIntent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    ////Login Fragment
    public void linkregisternow(View v)
    {
       /* running a funcltion in login fragment
        loginFrag = new LoginFragment();
        loginFrag.linkregisternow(v);*/

        Fragment regfragment = new RegisterFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,regfragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void loginsubmitbtn(View v)
    {
       /* running a funcltion in login fragment
        loginFrag = new LoginFragment();
        loginFrag.linkregisternow(v);*/

        Fragment selligfragment = new SellingDashboardFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.nav_host_fragment,selligfragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    ////Login Fragment

    ///setting tittle of each fragment
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }






}
