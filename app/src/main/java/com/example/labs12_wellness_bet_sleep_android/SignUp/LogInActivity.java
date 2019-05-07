
package com.example.labs12_wellness_bet_sleep_android.SignUp;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labs12_wellness_bet_sleep_android.Models.User;
import com.example.labs12_wellness_bet_sleep_android.Network.UserDao;
import com.example.labs12_wellness_bet_sleep_android.R;

import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.ManageGroups;
import com.example.labs12_wellness_bet_sleep_android.innerActivity.GroupRegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

public class LogInActivity extends AppCompatActivity {

    public static final String TAG = "LoginTag";

    private  EditText usernameText, passwordText;
    private int counter = 5;
    private String username, password;
    private TextView forgotPassword;
  
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {

        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        usernameText = findViewById(R.id.name_text);
        passwordText=  findViewById(R.id.password_text);
        ConstraintLayout parentLayout = findViewById(R.id.parent_layout);
        final CardView loginButton = findViewById(R.id.cardView);
        TextView registerText = findViewById(R.id.textView_register);
        forgotPassword = findViewById(R.id.textView_forgot);
      
        Context context = this;


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
                Intent createAccountIntent = new Intent(LogInActivity.this, GroupRegistrationActivity.class);
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
                    LoginUser();
                }
            }
        });

    }

    private void LoginUser() {
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String idToken = task.getResult().getToken();
                                Log.w(TAG, idToken);
                                Intent groupIntent = new Intent(LogInActivity.this, GroupRegistrationActivity.class);
                                groupIntent.putExtra("TOKEN_ID", idToken);
                                startActivity(groupIntent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}