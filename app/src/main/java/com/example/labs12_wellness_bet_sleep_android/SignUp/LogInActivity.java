package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.labs12_wellness_bet_sleep_android.Models.User;
import com.example.labs12_wellness_bet_sleep_android.Network.NetworkAdapter;
import com.example.labs12_wellness_bet_sleep_android.Network.UserDao;
import com.example.labs12_wellness_bet_sleep_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    public static final String TAG = "LoginTag";

    private  EditText usernameText, passwordText;
    private int counter = 5;
    private Context context;
    private RelativeLayout parentLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.name_text);
        passwordText=  findViewById(R.id.password_text);
        parentLayout = findViewById(R.id.parent_layout);
        CardView loginButton = findViewById(R.id.cardView);
        TextView registerText = findViewById(R.id.textView_register);

        context = this;

        final String username = usernameText.getText().toString();
        final String password = passwordText.getText().toString();


        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(LogInActivity.this, CreateAccount.class);
                startActivity(createAccountIntent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.matches("") && password.matches("")) {
                    Toast.makeText(getApplicationContext(),"Please enter User name and password.",Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.matches("")) {
                    Toast.makeText(getApplicationContext(),"Please enter password.",Toast.LENGTH_SHORT).show();
                    return;
                } else if (username.matches("")) {
                    Toast.makeText(getApplicationContext(),"Please enter user name.",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    UserDao.logIn(username, password);
                }
            }
        });
    }

}

//
//    private void validate(String username, String userPassword){
//        if((username.equals("Admin")) && (userPassword.equals("1234"))) {
//            Intent intent = new Intent(LogInActivity.this, BottomNavigationActivity.class);
//            startActivity(intent);
//        }else{
//            counter--;
//
//            if (counter == 0){
//                loginButton.setEnabled(false);
//            }
//
//        }
//
//    }
}
