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


import com.example.labs12_wellness_bet_sleep_android.Models.User;
import com.example.labs12_wellness_bet_sleep_android.Network.NetworkAdapter;
import com.example.labs12_wellness_bet_sleep_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    public static final String BASE_URL = "whateveverurl";
    public static final String USER_URL = BASE_URL + "/product";
    public static final String TAG = "LoginTag";

    private  EditText name, password;
    private int counter = 5;
    private Context context;
    private RelativeLayout parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.name_text);
        password=  findViewById(R.id.password_text);
        CardView loginButton = findViewById(R.id.cardView);
        TextView registerText = findViewById(R.id.textView_register);

        context = this;

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(LogInActivity.this, CreateAccount.class);
                startActivity(createAccountIntent);

            }
        });

        final ArrayList<User> data = new ArrayList<>();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

                        Map<String, String> headerProperties = new HashMap<>();
                        headerProperties.put("Authorization", "Basic " + auth);

                        String tokenUrl= BASE_URL + "/oauth/token?grant_type=password&username="
                                +name.getText().toString()+"&password="
                                +password.getText().toString()+"&scope=";

                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    tokenUrl, "POST", null, headerProperties);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.i(TAG, tokenRequest);
                        try {
                            String token = new JSONObject(tokenRequest).getString("access_token");

                            headerProperties.clear();
                            headerProperties.put("Authorization", "Bearer " + token);
                            try {
                                String result = null;
                                try {
                                    result = NetworkAdapter.httpRequest(USER_URL, "GET", null, headerProperties);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                JSONArray dataJsonArray = new JSONArray(result);

                                for (int i = 0; i < dataJsonArray.length(); ++i) {
                                    User user = new User(dataJsonArray.getJSONObject(i));
                                    data.add(user);
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        for(int i = 0; i < data.size(); i++) {
                                            TextView textView = new TextView(context);
                                            final User getUsers = data.get(i);


                                            textView.setText(getUsers.getUsername());
                                            textView.setTextSize(20);
                                            parentLayout.addView(textView);
                                        }
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
//
//    private void validate(String username, String userPassword){
//        if((username.equals("Admin")) && (userPassword.equals("1234"))) {
//            Intent intent = new Intent(LogInActivity.this, bottomNavigatoinActivity.class);
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
