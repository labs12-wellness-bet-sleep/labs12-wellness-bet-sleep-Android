package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.labs12_wellness_bet_sleep_android.R;

public class CreateAccount extends AppCompatActivity {

    TextView login, termOfS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        login = (TextView) findViewById(R.id.textView_logIn);
        termOfS = (TextView) findViewById(R.id.textView_terms);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(CreateAccount.this, LogInActivity.class);
                startActivity(MainActivityIntent);

            }
        });

    }
}
