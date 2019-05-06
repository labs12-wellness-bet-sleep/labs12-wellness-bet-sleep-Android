package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.google.android.material.tabs.TabLayout;

public class WinnerResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_results);

        TabLayout tabLayout = findViewById(R.id.tablayout_winner_results);
        tabLayout.addTab(tabLayout.newTab().setText("Winner"));
        tabLayout.addTab(tabLayout.newTab().setText("Results"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager_winner_results);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}

