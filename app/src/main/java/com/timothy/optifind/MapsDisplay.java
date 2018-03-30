package com.timothy.optifind;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsDisplay extends AppCompatActivity {

    private static final String TAG = "MapsDisplay";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_display);

        getSupportActionBar().setTitle("OptiFind");
        getSupportActionBar().setSubtitle("Home");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("OptiFindDb_users");
        mDatabase.keepSynced(true);

        //---------------onclick listener for settings button-----------------
        Button btnSetting = (Button) findViewById(R.id.btnSettings);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingact = new Intent(MapsDisplay.this, SettingsActivity.class);
                startActivity(settingact);
            }
        });


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null){
                    Intent signinActiv = new Intent(MapsDisplay.this, SignUp.class);
                    signinActiv.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(signinActiv);

                }
            }
        };

        //--------------onclick listener for logout button-------------------
        Button btnLogout = (Button) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MapsDisplay.this);
                builder1.setMessage("Sign out?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //code to be executed when okay is clicked
                                mAuth.signOut();
                            }
                        });
                builder1.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        //-------------onclick listener for category button----------------
        Button btnCat = (Button) findViewById(R.id.btn_category);
        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent catIntent = new Intent(MapsDisplay.this, CatActivity.class);
                startActivity(catIntent);
            }
        });

        checkingGpsEnabled();
    }//end of onCreate

    private void init() {
        Button btnMap = (Button)findViewById(R.id.buttonMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsDisplay.this, DisplayMaps.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking Google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsDisplay.this);
        if (available == ConnectionResult.SUCCESS) {
            //service is ok
            Log.d(TAG, "isServicesOK: Google services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occurred but can be fixed
            Log.d(TAG, "isServicesOK: an error occurred but can be fixed");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapsDisplay.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "we can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void checkingGpsEnabled() {

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        assert manager != null;
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog alertDialog = new AlertDialog.Builder(MapsDisplay.this).create();
            alertDialog.setTitle("Search nearby places inactive");
            alertDialog.setMessage("Please turn ON your GPS and restart OptiFind to use Maps");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            //Toast.makeText(this, "Please turn on your GPS", Toast.LENGTH_SHORT).show();
        } else if (isServicesOK()){
            init();
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


}

