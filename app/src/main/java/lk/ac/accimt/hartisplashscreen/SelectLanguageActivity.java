package lk.ac.accimt.hartisplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class SelectLanguageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);

        fullscreen();
    }

    public void btnEnglish(View v)
    {

        SharedPreferences.Editor editor = getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE).edit();
        editor.putString("language","English");
        editor.apply();

        Intent intent = new Intent(SelectLanguageActivity.this,MainScreenActivity.class);
        startActivity(intent);

    }

    public void btnSinhala(View v)
    {

        SharedPreferences.Editor editor = getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE).edit();
        editor.putString("language","Sinhala");
        editor.apply();

        Intent intent = new Intent(SelectLanguageActivity.this,MainScreenActivity.class);
        startActivity(intent);

    }

    public void btnTamil(View v)
    {

        SharedPreferences.Editor editor = getSharedPreferences("LANGUAGESELECTION", MODE_PRIVATE).edit();
        editor.putString("language","Tamil");
        editor.apply();

        Intent intent = new Intent(SelectLanguageActivity.this,MainScreenActivity.class);
        startActivity(intent);

    }

    public  void fullscreen()
    {
        this.getWindow().getDecorView().setSystemUiVisibility(

                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }

}
