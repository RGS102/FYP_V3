package com.example.rossg_000.fyp_v3;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Tasks extends AppCompatActivity {
    Handler handler = new Handler();
    Button start;
    Button stop;
    TextView timeDisplay;
    boolean pause = false;
    long timeInMilli = 0;
    long temp = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        start = (Button) findViewById(R.id.Start);
        stop = (Button) findViewById(R.id.Stop);
        timeDisplay = (TextView) findViewById(R.id.TimeDisplay);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeInMilli = 0;
                pause = false;
                temp = 0;

                handler.removeCallbacks(updateTimer);
                handler.postDelayed(updateTimer, 0);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() { //NO WAY TO UN PAUSE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            @Override
            public void onClick(View view) {
                if (pause == false) {
                    pause = true;
                } else if (pause == true) {
                    pause = false;
                    timeInMilli = temp;
                    handler.postDelayed(updateTimer, 0);
                }
            }
        });
    }

    Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if (pause == false) {
                timeInMilli += 1000;

                //WEIRD GLITCH AFTER COMING BACK FROM STANDBY WHILE CLOCK IS RUNNING
                long seconds = timeInMilli/1000;
                long minutes = seconds/60;
                long hours = minutes/60;
                seconds = seconds%60;
                minutes = minutes%60;

                //ALSO PROBABLY NEEDS TO SAVE AND LOAD THE LAST VALUE OF MILLISECONDS

                String secString = String.valueOf(seconds);
                String minString = String.valueOf(minutes);
                String hourString = String.valueOf(hours);

                if(seconds<10){secString = "0"+ String.valueOf(seconds);}
                if(minutes<10){minString = "0"+ String.valueOf(minutes);}
                if(hours<10){hourString = "0"+ String.valueOf(hours);}

                String time = hourString + ":" + minString +":"+ secString;


                temp = timeInMilli;
                timeDisplay.setText(time);
                handler.postDelayed(this, 0);
            }
        }
    };


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
