package com.example.labs12_wellness_bet_sleep_android.fragmentsNav.innerActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.ManageGroups;

public class CreateGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        CardView btnCreate = (CardView) findViewById(R.id.cardView_creat_group);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent creatGroupIntent = new Intent(CreateGroup.this, ManageGroups.class);
                startActivity(creatGroupIntent);
            }
        });
    }
}
