package com.timothy.optifind;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class ActivityCategory extends AppCompatActivity {

    private TabLayout tablalayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tablalayout = (TabLayout) findViewById(R.id.tab_host);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new WholeSale(), "Wholesale");
        adapter.AddFragment(new RetailFragment(), "Retail");
        adapter.AddFragment(new ServicesFragment(), "Services");

        viewPager.setAdapter(adapter);
        tablalayout.setupWithViewPager(viewPager);
    }
}
