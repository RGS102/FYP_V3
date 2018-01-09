package com.example.rossg_000.fyp_v3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Tasks extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
    }

    public void goToStretches(View view) {
        Intent intent = new Intent(this, Stretches.class);
        startActivity(intent);
    }


    public void goToExercise(View view) {
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);
    }

    public void goToMeditation(View view) {
        Intent intent = new Intent(this, Meditation.class);
        startActivity(intent);
    }


}
