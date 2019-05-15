package com.example.labs12_wellness_bet_sleep_android.TempPackage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.ManageGroups;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.NavigationDrawer;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.WinnerResults;

public class TempNavigationActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_navigation);

        listView = findViewById(R.id.list);
        String[] values = new String[]{"Navigation Drawer Activity", "Manage Groups Activity",
                "Winner Results Activity",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), NavigationDrawer.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), ManageGroups.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), WinnerResults.class);
                    startActivityForResult(myIntent, 0);
                }

            }
        });
    }
}
