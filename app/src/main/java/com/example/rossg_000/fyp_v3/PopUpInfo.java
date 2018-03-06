package com.example.rossg_000.fyp_v3;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class PopUpInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_info);

        Intent intent = getIntent();
        String value = intent.getStringExtra("details");

        TextView PopUp = (TextView) findViewById(R.id.TaskPopUpInfo);
        PopUp.setText(value);
    }
}
