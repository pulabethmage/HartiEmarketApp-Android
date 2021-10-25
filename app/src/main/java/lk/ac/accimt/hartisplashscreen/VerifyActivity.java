package lk.ac.accimt.hartisplashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VerifyActivity extends Activity implements Response.Listener<String>,Response.ErrorListener {

    EditText etOtpNumber;
    String sRegPhone,sRegName,sRegNic,sRegGender,sRegAddress,sRegPassword,distr_id,govicenter_id;


    ProgressBar progressBar;

    String VerificationId;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));

        mAuth= FirebaseAuth.getInstance();

        etOtpNumber = findViewById(R.id.etOtpCode);
        progressBar = findViewById(R.id.progressBar);


        Bundle extras = getIntent().getExtras();
        sRegPhone = extras.getString("sRegPhone");

        sRegName = extras.getString("sRegName");
        sRegNic = extras.getString("sRegNic");
        sRegGender = extras.getString("sRegGender");
        sRegAddress = extras.getString("sRegAddress");
        sRegPassword = extras.getString("sRegPassword");
        distr_id = extras.getString("distr_id");
        govicenter_id = extras.getString("govicenter_id");


        sendVerificationCode("+94"+sRegPhone);

    }


    public  void btnVerify (View v){


        String code = etOtpNumber.getText().toString();
        if(code.isEmpty() || code.length()<6)
        {

            etOtpNumber.setError("Please Enter Code");
            etOtpNumber.requestFocus();
            return;
        }
        else {

            veryfyCode(code);

        }


    }

    ///////
    public  void sendVerificationCode(String number)
    {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack

        );

    }

    ////////

    //////////////
    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            VerificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String codesms = phoneAuthCredential.getSmsCode();

            if(codesms !=null)
            {
                etOtpNumber.setText(codesms);
                veryfyCode(codesms);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Toast.makeText(VerifyActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

    /////////////


    /////////////
    public  void veryfyCode(String code)
    {
        PhoneAuthCredential credentials = PhoneAuthProvider.getCredential(VerificationId,code);

        singInwithCredentials(credentials);
    }
    ////////////

    /////////////
    private void singInwithCredentials(PhoneAuthCredential credentials) {

        mAuth.signInWithCredential(credentials)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            confirmAdd(sRegName,sRegNic,sRegGender,sRegAddress,sRegPhone,sRegPassword,distr_id,govicenter_id);
                            //Intent intent = new Intent(VerifyActivity.this,MainScreenActivity.class);
                           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           // startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(VerifyActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    /////////////









    public void confirmAdd(String sRegName,String sRegNic,String sRegGender,String sRegAddress,String sRegPhone,String sRegPassword,String sRegDistrict,String sRegGovicenter)
    {
        final String sRegNameE=sRegName;
        final String sRegNicE=sRegNic;
        final String sRegGenderE=sRegGender;
        final String sRegAddressE=sRegAddress;
        final String sRegPhoneE=sRegPhone;
        final String sRegPasswordE=sRegPassword;
        final String sRegDistrictE=sRegDistrict.toString();
        final String sRegGovicenterE=sRegGovicenter.toString();



        RequestQueue queue = Volley.newRequestQueue(VerifyActivity.this);
        int type = Request.Method.POST;
        String url ="http://vegi.lk/admin/mobileappfiles/register_suplier/add.php";

        //String url = dburls.VerifyActivityRegister;

        StringRequest request = new StringRequest(
                type,
                url,
                this,
                this
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String,String> params = new HashMap<>();
                params.put("name",sRegNameE);
                params.put("nic",sRegNicE);
                params.put("gender",sRegGenderE);
                params.put("address",sRegAddressE);
                params.put("mphone",sRegPhoneE);
                params.put("suppassword",sRegPasswordE);
                params.put("district",sRegDistrictE);
                params.put("govicenter",sRegGovicenterE);

                return params;
            }
        };

        queue.add(request);

    }



    @Override
    public void onErrorResponse(VolleyError error) {
        // Toast.makeText(getContext(),"Error!"+error.getMessage(), Toast.LENGTH_LONG).show();
        Toast.makeText(VerifyActivity.this,R.string.s_register_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(VerifyActivity.this);
        builder.setTitle(R.string.s_register_msg_title);
        builder.setMessage(R.string.s_register_msg);
        builder.setPositiveButton(R.string.s_register_loginpage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fini();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void fini()
    {
        Intent intent = new Intent(VerifyActivity.this,MainScreenActivity.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
         startActivity(intent);
       this.finish();

    }










}
