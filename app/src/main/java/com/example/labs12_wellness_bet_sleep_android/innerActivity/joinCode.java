package com.example.labs12_wellness_bet_sleep_android.innerActivity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.example.labs12_wellness_bet_sleep_android.R;
import com.example.labs12_wellness_bet_sleep_android.fragmentsNav.ManageGroups;

public class joinCode extends AppCompatActivity {

    private EditText codeInput;
    private CardView submitButton;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_code);

        submitButton = (CardView) findViewById(R.id.cardView_joinNow);
        codeInput = (EditText) findViewById(R.id.joinCode_input);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate(codeInput.getText().toString());


            }
        });
    }

    private void validate(String toString) {

        if((codeInput.equals("1234"))) {
            Intent joinGroupIntent = new Intent(joinCode.this, ManageGroups.class);
            startActivity(joinGroupIntent);
        }else {
            counter --;
            if(counter == 0 ){
                submitButton.setEnabled(false);
            }
        }
    }
}
