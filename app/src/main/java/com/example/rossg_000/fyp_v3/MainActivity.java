package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the button */
    public void goToPage(View view) {
        Intent intent = new Intent(this, Tasks.class);
        startActivity(intent);
    }

    public void goToJournal(View view) {
        Intent intent = new Intent(this, Journal.class);
        startActivity(intent);
    }
}
