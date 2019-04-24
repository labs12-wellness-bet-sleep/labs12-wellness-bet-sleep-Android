package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.dashBoardFragment;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.homeFragment;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.roomsFragment;

public class bottomNavigatoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigatoin);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new homeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                   Fragment selectedFragment = null;

                   switch (Item.getItemId()) {
                       case R.id.nav_home:
                           selectedFragment = new homeFragment();
                           break;
                       case R.id.nav_dashBoard:
                           selectedFragment = new dashBoardFragment();
                           break;
                       case R.id.nav_Rooms:
                           selectedFragment = new roomsFragment();
                           break;
                   }

                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                           selectedFragment).commit();
                   return true;
                }
            };
}
