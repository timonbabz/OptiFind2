package com.timothy.optifind;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> categories;
    Map<String, List<String>> subCategories;
    private int lastPosition = -1;

    ExpandableListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        final Drawable img = getResources().getDrawable(R.drawable.ic_action_down);
        final Drawable img2 = getResources().getDrawable(R.drawable.ic_list_view);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list_view);
        fillData();
        listAdapter = new MyExandableListAdpater(this, categories, subCategories);
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            TextView parentText = (TextView) findViewById(R.id.text_parent);
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastPosition != -1 && groupPosition != lastPosition)
                {
                    expandableListView.collapseGroup(lastPosition);
                }
                lastPosition = groupPosition;
            }
        });

    }//end of onCreate

    public void fillData(){

        categories = new ArrayList<>();
        subCategories = new HashMap<>();

        categories.add("Services");
        categories.add("Retail");
        categories.add("Wholesale");


        List<String> services = new ArrayList<>();

        services.add("Agencies");
        services.add("Financial");
        services.add("Computer/ICT");
        services.add("Hospital/heath");
        services.add("Miscellaneous");
        services.add("Agencies");

        List<String> retail = new ArrayList<>();

        retail.add("Department Stores");
        retail.add("Discount Stores");
        retail.add("Supermarket");
        retail.add("Warehouse Stores");
        retail.add("Specialty Stores");
        retail.add("Malls");
        retail.add("E-tailers");
        retail.add("Mom and Pop shop");

        List<String> wholesale = new ArrayList<>();

        wholesale.add("Merchant");
        wholesale.add("General");
        wholesale.add("Specialty");
        wholesale.add("Specific Product");
        wholesale.add("Discount");
        wholesale.add("Drop Ship");
        wholesale.add("On-line");

        subCategories.put(categories.get(0), services);
        subCategories.put(categories.get(1), retail);
        subCategories.put(categories.get(2), wholesale);
    }

}
