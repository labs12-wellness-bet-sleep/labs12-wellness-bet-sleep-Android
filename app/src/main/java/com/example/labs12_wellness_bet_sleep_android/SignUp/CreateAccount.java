package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Intent;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;

import com.example.labs12_wellness_bet_sleep_android.Network.NetworkAdapter;
import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.innerActivity.GroupRegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class
CreateAccount extends AppCompatActivity {

    public static final String TAG = "CreateAccountTag";

    private EditText usernameText, emailText, passwordText, fullnameText;
    private String username, password, email, fullname, idToken;
    public static Map<String, String> headerProperties;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();

        TextView login = findViewById(R.id.textView_logIn);
        TextView termOfS = findViewById(R.id.textView_terms);

        CardView signupButton = findViewById(R.id.cardView_signup);

        usernameText = findViewById(R.id.name_text_ca);
        fullnameText = findViewById(R.id.fullname_text_ca);
        passwordText = findViewById(R.id.password_text_ca);
        emailText = findViewById(R.id.email_text_ca);


        headerProperties = new HashMap<>();
        headerProperties.put("Authorization", "Basic " + idToken);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivityIntent = new Intent
                        (CreateAccount.this, LogInActivity.class);
                startActivity(MainActivityIntent);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                email = emailText.getText().toString();
                fullname = fullnameText.getText().toString();


                CreateUser(email, password);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject userdata = new JSONObject();
                        try {
                            userdata.put("username",
                                    username + ",");
                            userdata.put("fullName",
                                    fullname + ",");
                            userdata.put("password",
                                    password + ",");
                            userdata.put("email",
                                          email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    "https://sleep-bet.herokuapp.com/api/users/register/",
                                    "POST", userdata, headerProperties);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                    }
                }).start();

                Intent groupIntent = new Intent(CreateAccount.this, LogInActivity.class);
                startActivity(groupIntent);
            }
        });

    }

    private void CreateUser(String emailFb, String passwordFb) {
        mAuth.createUserWithEmailAndPassword(emailFb, passwordFb).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.getIdToken(true)
                            .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    if (task.isSuccessful()) {
                                        idToken = task.getResult().getToken();
                                        Log.w(TAG, idToken);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Log.w(TAG, "createUser: failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
