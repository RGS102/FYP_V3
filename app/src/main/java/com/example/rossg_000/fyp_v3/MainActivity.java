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

        //TextView completedTasks = (TextView)findViewById(R.id.CompletedTasks);
        //TextView completedExercises = (TextView)findViewById(R.id.ExercisesCompleted);
        //TextView completedMeditations = (TextView)findViewById(R.id.MeditationsCompleted);
        TextView userTitle = (TextView)findViewById(R.id.UserTitle);
        ProgressBar exerciseProgressBar = (ProgressBar) findViewById(R.id.ExerciseProgressBar);
        int i = Exercise.getTaskCompleted();
        exerciseProgressBar.setProgress(i);


        //int i = Exercise.getTaskCompleted();
        //int j = Meditation.getTaskCompleted();
        //int k = i + j;
        String title = Rewards.getUserTitle();

        //completedExercises.setText(String.valueOf(i));
        //completedMeditations.setText(String.valueOf(j));
        //completedTasks.setText(String.valueOf(k));
        userTitle.setText(title);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //TextView completedTasks = (TextView)findViewById(R.id.CompletedTasks);
        //TextView completedExercises = (TextView)findViewById(R.id.ExercisesCompleted);
        //TextView completedMeditations = (TextView)findViewById(R.id.MeditationsCompleted);
        TextView userTitle = (TextView)findViewById(R.id.UserTitle);

        ProgressBar exerciseProgressBar = (ProgressBar) findViewById(R.id.ExerciseProgressBar);
        int i = Exercise.getTaskCompleted();
        int test = i*10;
        int test2 = test/100;
        exerciseProgressBar.setProgress(30);





        //int i = Exercise.getTaskCompleted();
        //int j = Meditation.getTaskCompleted();
        //int k = i + j;
        String title = Rewards.getUserTitle();

        //completedExercises.setText(String.valueOf(i));
        //completedMeditations.setText(String.valueOf(j));
        //completedTasks.setText(String.valueOf(k));
        userTitle.setText(title);
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
