package com.timothy.optifind;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddBusiness extends AppCompatActivity{

    private TextInputEditText nameBiz;
    private TextInputEditText bizLoaction;
    private TextInputEditText bizLat;
    private TextInputEditText bizLong;

    private Button sbtnAdd;

    private Spinner spinner, spinnerSubcat;

    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterRetail;
    ArrayAdapter<CharSequence> adapterWholesale;
    ArrayAdapter<CharSequence> adapterServices;
    ArrayAdapter<CharSequence> adaptersubcat;

    private ProgressDialog mProgress;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Business_details");

        nameBiz = findViewById(R.id.txt_business_name);
        bizLoaction = findViewById(R.id.txt_location);
        bizLat = findViewById(R.id.txt_latitude);
        bizLong = findViewById(R.id.txt_longitude);
        mProgress = new ProgressDialog (this);

        sbtnAdd = findViewById(R.id.btnAddBiz);
        spinner = (Spinner) findViewById(R.id.spincategory);
        spinnerSubcat = (Spinner) findViewById(R.id.spinsubcat);

        adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        adaptersubcat = ArrayAdapter.createFromResource(this, R.array.subcategories, android.R.layout.simple_spinner_item);
        adaptersubcat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcat.setAdapter(adaptersubcat);

        adapterServices = ArrayAdapter.createFromResource(this, R.array.services, android.R.layout.simple_spinner_item);
        adapterServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterRetail = ArrayAdapter.createFromResource(this, R.array.retail, android.R.layout.simple_spinner_item);
        adapterRetail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterWholesale = ArrayAdapter.createFromResource(this, R.array.wholesale, android.R.layout.simple_spinner_item);
        adapterWholesale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Select category")){
                    spinnerSubcat.setAdapter(adaptersubcat);
                }
                if(adapterView.getItemAtPosition(i).equals("Services")){
                    spinnerSubcat.setAdapter(adapterServices);
                    Toast.makeText(AddBusiness.this, "Services selected", Toast.LENGTH_LONG).show();
                }
                else if(adapterView.getItemAtPosition(i).equals("Retail")){
                    spinnerSubcat.setAdapter(adapterRetail);
                    Toast.makeText(AddBusiness.this, "Retail selected", Toast.LENGTH_LONG).show();
                }
                else if(adapterView.getItemAtPosition(i).equals("Wholesale")){
                    spinnerSubcat.setAdapter(adapterWholesale);
                    Toast.makeText(AddBusiness.this, "Wholesale selected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postBusiness();
            }
        });
    }

    private void postBusiness() {

        mProgress.setMessage("Adding...");
        mProgress.show();
        String businessName = nameBiz.getText().toString().trim();
        String businessLocation = bizLoaction.getText().toString().trim();
        String businessLatitude = bizLat.getText().toString().trim();
        String businessLong = bizLong.getText().toString().trim();
        String mainCategory = spinner.getSelectedItem().toString();
        String subCategory = spinnerSubcat.getSelectedItem().toString();

        DatabaseReference newBusiness = mDatabase.push();
        newBusiness.child("Business name").setValue(businessName);
        newBusiness.child("Business location").setValue(businessLocation);
        newBusiness.child("Business latitude").setValue(businessLatitude);
        newBusiness.child("Business longitude").setValue(businessLong);
        newBusiness.child("Business category").setValue(mainCategory);
        newBusiness.child("Business subcategory").setValue(subCategory).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mProgress.dismiss();
            }
        });


    }
}
