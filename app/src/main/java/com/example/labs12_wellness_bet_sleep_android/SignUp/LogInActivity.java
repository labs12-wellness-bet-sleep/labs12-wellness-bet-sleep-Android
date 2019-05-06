package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.labs12_wellness_bet_sleep_android.Network.UserDao;
import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.ManageGroups;

public class LogInActivity extends AppCompatActivity {

    public static final String TAG = "LoginTag";

    private  EditText usernameText, passwordText;
    private int counter = 5;
    private Context context;
    private String username, password;
    private RelativeLayout parentLayout;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.name_text);
        passwordText=  findViewById(R.id.password_text);
        parentLayout = findViewById(R.id.parent_layout);
        final CardView loginButton = findViewById(R.id.cardView);
        TextView registerText = findViewById(R.id.textView_register);
        forgotPassword = findViewById(R.id.textView_forgot);
        context = this;


        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(LogInActivity.this, CreateAccount.class);
                startActivity(createAccountIntent);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(LogInActivity.this, ManageGroups.class);
                startActivity(createAccountIntent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameText.getText().toString();
                password = passwordText.getText().toString();

                if (username.matches("") && password.matches("")) {
                    Toast.makeText(getApplicationContext(),"Please enter User name and password.",Toast.LENGTH_SHORT).show();
                } else if (password.matches("")) {
                    Toast.makeText(getApplicationContext(),"Please enter password.",Toast.LENGTH_SHORT).show();
                } else if (username.matches("")) {
                    Toast.makeText(getApplicationContext(),"Please enter user name.",Toast.LENGTH_SHORT).show();
                } else {
                    UserDao.logIn(username, password);
                }
            }
        });
    }

}