package com.timothy.optifind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EditBizDetails extends AppCompatActivity {

    private Spinner spinnerEdit, spinnerSubcatEdit;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterRetail;
    ArrayAdapter<CharSequence> adapterWholesale;
    ArrayAdapter<CharSequence> adapterServices;
    ArrayAdapter<CharSequence> adaptersubcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_biz_details);

        spinnerEdit = (Spinner) findViewById(R.id.spincategoryEdit);
        spinnerSubcatEdit = (Spinner) findViewById(R.id.spinsubcatEdit);

        adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdit.setAdapter(adapter);

        adaptersubcat = ArrayAdapter.createFromResource(this, R.array.subcategories, android.R.layout.simple_spinner_item);
        adaptersubcat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubcatEdit.setAdapter(adaptersubcat);

        adapterServices = ArrayAdapter.createFromResource(this, R.array.services, android.R.layout.simple_spinner_item);
        adapterServices.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterRetail = ArrayAdapter.createFromResource(this, R.array.retail, android.R.layout.simple_spinner_item);
        adapterRetail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterWholesale = ArrayAdapter.createFromResource(this, R.array.wholesale, android.R.layout.simple_spinner_item);
        adapterWholesale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Select category")){
                    spinnerSubcatEdit.setAdapter(adaptersubcat);
                }
                if(adapterView.getItemAtPosition(i).equals("Services")){
                    spinnerSubcatEdit.setAdapter(adapterServices);
                    Toast.makeText(EditBizDetails.this, "Services selected", Toast.LENGTH_LONG).show();
                }
                else if(adapterView.getItemAtPosition(i).equals("Retail")){
                    spinnerSubcatEdit.setAdapter(adapterRetail);
                    Toast.makeText(EditBizDetails.this, "Retail selected", Toast.LENGTH_LONG).show();
                }
                else if(adapterView.getItemAtPosition(i).equals("Wholesale")){
                    spinnerSubcatEdit.setAdapter(adapterWholesale);
                    Toast.makeText(EditBizDetails.this, "Wholesale selected", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
