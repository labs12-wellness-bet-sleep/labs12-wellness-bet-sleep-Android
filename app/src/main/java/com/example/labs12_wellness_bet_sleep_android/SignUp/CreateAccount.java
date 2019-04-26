package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.labs12_wellness_bet_sleep_android.Network.NetworkAdapter;
import com.example.labs12_wellness_bet_sleep_android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class CreateAccount extends AppCompatActivity {
    
    public static final String TAG = "CreateAccountTag";

    TextView login, termOfS;
    EditText nameText, emailText, passwordText;
    CardView signupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        login = findViewById(R.id.textView_logIn);
        termOfS = findViewById(R.id.textView_terms);

        nameText = findViewById(R.id.name_text_ca);
        passwordText = findViewById(R.id.password_text_ca);
        emailText = findViewById(R.id.email_text_ca);

        signupButton = findViewById(R.id.cardView_signup);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent(CreateAccount.this, LogInActivity.class);
                startActivity(MainActivityIntent);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject userdata = new JSONObject();
                        try {
                            userdata.put("username", nameText.getText().toString());
                            userdata.put("password", passwordText.getText().toString());
                            userdata.put("email", emailText.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    "https://sleep-bet.herokuapp.com/auth/register",
                                    "POST", userdata, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                    }
                }).start();
            }
        });

    }
}
