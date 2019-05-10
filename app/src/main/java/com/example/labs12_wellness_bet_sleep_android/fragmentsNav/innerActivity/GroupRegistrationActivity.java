package com.example.labs12_wellness_bet_sleep_android.fragmentsNav.innerActivity;




import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.labs12_wellness_bet_sleep_android.Network.NetworkAdapter;
import com.example.labs12_wellness_bet_sleep_android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GroupRegistrationActivity extends AppCompatActivity {
    public static final String TAG = "GroupRegTag";

    private EditText groupName, buyAmount, startDate, endDate, groupMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_registration);

        CardView createGroupB = findViewById(R.id.CreateGroup_button);

        groupName = findViewById(R.id.text_groupName);
        buyAmount = findViewById(R.id.text_buyAmount);
        startDate = findViewById(R.id.text_dateStart);
        endDate = findViewById(R.id.text_dateEnd);
        groupMessage = findViewById(R.id.text_messageToG);

        createGroupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject userdata = new JSONObject();
                        try {
                            userdata.put("groupName",
                                    groupName.getText().toString() + ",");
                            userdata.put("buyInAmt",
                                    buyAmount.getText().toString() + ",");
                            userdata.put("startDate",
                                    startDate.getText().toString() + ",");
                            userdata.put("endDate",
                                    endDate.getText().toString() + ",");
                            userdata.put("groupMessage",
                                    groupMessage.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String tokenRequest = null;
                        try {
                            tokenRequest = NetworkAdapter.httpRequest(
                                    "https://sleep-bet.herokuapp.com/api/groups/create",
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
