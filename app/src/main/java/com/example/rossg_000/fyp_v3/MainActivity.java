package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView userTitle = (TextView)findViewById(R.id.UserTitle);
        String title = Rewards.getUserTitle();
        userTitle.setText(title);

        //ProgressBar exerciseProgressBar = (ProgressBar) findViewById(R.id.ExerciseProgressBar);
        //int i = Exercise.getTaskCompleted();
        //int test = i*10;
        //exerciseProgressBar.setProgress(test);


        //int i = Exercise.getTaskCompleted();
        //int j = Meditation.getTaskCompleted();
        //int k = i + j;

    }

    @Override
    protected void onResume(){
        super.onResume();

        TextView userTitle = (TextView)findViewById(R.id.UserTitle);
        String title = Rewards.getUserTitle();
        userTitle.setText(title);

        ProgressBar taskProgressBar = (ProgressBar) findViewById(R.id.TaskProgressBar);
        ProgressBar exerciseProgressBar = (ProgressBar) findViewById(R.id.ExerciseProgressBar);
        ProgressBar meditationProgressBar = (ProgressBar) findViewById(R.id.MeditationProgressBar);


        int i = Exercise.getTaskCompleted();
        int j = Meditation.getTaskCompleted();
        int k = i + j;

        if(k % 20 == 0){taskProgressBar.setProgress(0);}
        if(k % 20 == 1){taskProgressBar.setProgress(5);}
        if(k % 20 == 2){taskProgressBar.setProgress(10);}
        if(k % 20 == 3){taskProgressBar.setProgress(15);}
        if(k % 20 == 4){taskProgressBar.setProgress(20);}
        if(k % 20 == 5){taskProgressBar.setProgress(25);}
        if(k % 20 == 6){taskProgressBar.setProgress(30);}
        if(k % 20 == 7){taskProgressBar.setProgress(35);}
        if(k % 20 == 8){taskProgressBar.setProgress(40);}
        if(k % 20 == 9){taskProgressBar.setProgress(45);}
        if(k % 20 == 10){taskProgressBar.setProgress(50);}
        if(k % 20 == 11){taskProgressBar.setProgress(55);}
        if(k % 20 == 12){taskProgressBar.setProgress(60);}
        if(k % 20 == 13){taskProgressBar.setProgress(65);}
        if(k % 20 == 14){taskProgressBar.setProgress(70);}
        if(k % 20 == 15){taskProgressBar.setProgress(75);}
        if(k % 20 == 16){taskProgressBar.setProgress(80);}
        if(k % 20 == 17){taskProgressBar.setProgress(85);}
        if(k % 20 == 18){taskProgressBar.setProgress(90);}
        if(k % 20 == 19){taskProgressBar.setProgress(95);}

        if(i % 10 == 0){exerciseProgressBar.setProgress(0);}
        if(i % 10 == 1){exerciseProgressBar.setProgress(10);}
        if(i % 10 == 2){exerciseProgressBar.setProgress(20);}
        if(i % 10 == 3){exerciseProgressBar.setProgress(30);}
        if(i % 10 == 4){exerciseProgressBar.setProgress(40);}
        if(i % 10 == 5){exerciseProgressBar.setProgress(50);}
        if(i % 10 == 6){exerciseProgressBar.setProgress(60);}
        if(i % 10 == 7){exerciseProgressBar.setProgress(70);}
        if(i % 10 == 8){exerciseProgressBar.setProgress(80);}
        if(i % 10 == 9){exerciseProgressBar.setProgress(90);}

        if(j % 10 == 0){meditationProgressBar.setProgress(0);}
        if(j % 10 == 1){meditationProgressBar.setProgress(10);}
        if(j % 10 == 2){meditationProgressBar.setProgress(20);}
        if(j % 10 == 3){meditationProgressBar.setProgress(30);}
        if(j % 10 == 4){meditationProgressBar.setProgress(40);}
        if(j % 10 == 5){meditationProgressBar.setProgress(50);}
        if(j % 10 == 6){meditationProgressBar.setProgress(60);}
        if(j % 10 == 7){meditationProgressBar.setProgress(70);}
        if(j % 10 == 8){meditationProgressBar.setProgress(80);}
        if(j % 10 == 9){meditationProgressBar.setProgress(90);}
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

    public void goToRewards(View view){
        Intent intent = new Intent(this, Rewards.class);
        startActivity(intent);
    }
}
