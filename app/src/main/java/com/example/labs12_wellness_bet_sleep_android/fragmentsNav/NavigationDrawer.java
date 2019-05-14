package com.example.labs12_wellness_bet_sleep_android.fragmentsNav;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;



import com.example.labs12_wellness_bet_sleep_android.R;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar navDrawToolBar = findViewById(R.id.nav_draw_tool_bar);
        setSupportActionBar(navDrawToolBar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, navDrawToolBar, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_draw_view);
        navigationView.setNavigationItemSelectedListener(this);


        // load Fragment in to Framelayout by default
        replaceCreateJoinGroupFragment();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if(id == R.id.create_join_group) {
            replaceCreateJoinGroupFragment();
        } else if(id == R.id.groups) {
            GroupsFragment fragment = new GroupsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_draw_frame_layout, fragment, "Groups");
            fragmentTransaction.commit();
        } else if(id == R.id.settings) {
            SettingsFragment fragment = new SettingsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_draw_frame_layout, fragment, "Settings");
            fragmentTransaction.commit();
        } else if(id == R.id.logout) {
            LogOutFragment fragment = new LogOutFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_draw_frame_layout, fragment, "Log Out");
            fragmentTransaction.commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceCreateJoinGroupFragment() {
        CreateJoinFragment fragment = new CreateJoinFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_draw_frame_layout, fragment, "Create / Join Group");
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
