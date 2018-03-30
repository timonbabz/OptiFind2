package com.timothy.optifind;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private Button btnEditDetails, addBusiness, btnResetLocation;
    LinearLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnEditDetails = (Button) findViewById(R.id.btn_edit_business_details);
        addBusiness = (Button) findViewById(R.id.btn_businesslist);
        btnResetLocation = (Button) findViewById(R.id.reset_locationbtn);

        getSupportActionBar().setTitle("OptiFind");
        getSupportActionBar().setSubtitle("Settings");

        addBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, AddBusiness.class));
            }
        });

        btnResetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

                if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                    Toast.makeText(SettingsActivity.this, "GPS is disabled", Toast.LENGTH_LONG).show();
                }else{
                    Intent intentReset = new Intent(SettingsActivity.this, DisplayMaps.class);
                    startActivity(intentReset);
                }
            }
        });

        btnEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, EditBizDetails.class));
            }
        });
    }
}
