package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

public class Tasks extends AppCompatActivity {
    //private GestureDetectorCompat gestureDetectorCompat;
    //private ImageButton getNewTasks = (ImageButton) findViewById(R.id.GetNewTasks);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        //gestureDetectorCompat = new GestureDetectorCompat(this, new Tasks.Gesture());


        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences sharedPreferences = getSharedPreferences("Days", 0);
        int yesterday = sharedPreferences.getInt("Days", 0);

        //SharedPreferences sharedPreferences3 = getSharedPreferences("Button Clicked", 0);
        //Boolean buttonClicked = sharedPreferences2.getBoolean("Button Clicked", true);

        if(yesterday != today){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("Days", today);
            editor.commit();

            //SharedPreferences.Editor editor2 = sharedPreferences3.edit();
            //editor2.putBoolean("Button Clicked", false);
            //editor2.commit();
            //getNewTasks.setEnabled(true);
            dailyTasks();
        }

        //Boolean buttonClicked = sharedPreferences3.getBoolean("Button Clicked", true);

        //if(buttonClicked == false){
        //    getNewTasks.setEnabled(true);
       // }






        SharedPreferences sharedPreferences1 = getSharedPreferences("DT1", 0);
        String taskOne = sharedPreferences1.getString("DT1", "Walk and Jog");

        SharedPreferences sharedPreferences2 = getSharedPreferences("DT1", 0);
        String taskTwo = sharedPreferences2.getString("DT2", "Deep Breathing and Body Scan");

        TextView dailyTaskOne = (TextView)findViewById(R.id.DailyTaskOne);
        TextView dailyTaskTwo = (TextView)findViewById(R.id.DailyTaskTwo);
        dailyTaskOne.setText(taskOne);
        dailyTaskTwo.setText(taskTwo);


        TextView completedTasks = (TextView)findViewById(R.id.CompletedTasks);
        TextView completedExercises = (TextView)findViewById(R.id.ExercisesCompleted);
        TextView completedMeditations = (TextView)findViewById(R.id.MeditationsCompleted);

        int i = MainActivity.getExercisesCompleted();
        int j = MainActivity.getMeditationsCompleted();
        int k = MainActivity.getTasksCompleted();

        completedExercises.setText(String.valueOf(i));
        completedMeditations.setText(String.valueOf(j));
        completedTasks.setText(String.valueOf(k));
    }

    @Override
    protected void onResume(){
        super.onResume();
        TextView completedTasks = (TextView)findViewById(R.id.CompletedTasks);
        TextView completedExercises = (TextView)findViewById(R.id.ExercisesCompleted);
        TextView completedMeditations = (TextView)findViewById(R.id.MeditationsCompleted);

        int i = MainActivity.getExercisesCompleted();
        int j = MainActivity.getMeditationsCompleted();
        int k = i + j;

        completedExercises.setText(String.valueOf(i));
        completedMeditations.setText(String.valueOf(j));
        completedTasks.setText(String.valueOf(k));
    }

    /*
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
    */

    public void dailyTasks(){
        String[] allExercises = new String[]{"Walk","Run","Jog","Swim","Sit Ups","Push Ups","Cycle","Crunches",
                "Squats","Superman","Tuck Jump","Prone Walkout","Burpees","Plank","Wall Sit","Lunge",
                "Clock Lunge","Single Leg Deadlift","Step-Up","Calf Raise","Tricep Dip","Boxer","Flutter Kick","Shoulder Bridge","Sprinter Sit Up"};

        String[] allMeditations = new String[]{"Deep Breathing","Power Nap","Muscle Relaxation","Body Scan",
                "Open Monitoring","Focused Attention","Walking Meditation","Yoga","Tai Chi","Sleep",
                "Mindful Eating","Visualisation","Mantra Meditation","Metta Meditation","Self Enquiry",
                "Scalp Soother","Easy on the Eyes","Sinus Pressure Relief","Shoulder Tension Relief",
                "Listen to Music","Reading","Effortless Presence","Zen Meditation"};

        int exeLength = allExercises.length-1;
        int medLength = allMeditations.length-1;

        Random random = new Random();
        int r1 = random.nextInt(exeLength-0)+0;
        int r2 = random.nextInt(exeLength-0)+0;
        int r3 = random.nextInt(medLength-0)+0;
        int r4 = random.nextInt(medLength-0)+0;

        String dailyExercises = "Walk and Jog";
        String dailyMeditations = "Deep Breathing and Body Scan";


        if(r1 == r2){dailyExercises = "Double " + allExercises[r2];}
        else{dailyExercises = allExercises[r1] + " and " + allExercises[r2];}

        if(r3 == r4){dailyMeditations = "Double " + allMeditations[r2];}
        else{dailyMeditations = allMeditations[r1] + " and " + allMeditations[r2];}

        SharedPreferences sharedPreferences1 = getSharedPreferences("DT1", 0);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        editor.putString("DT1", dailyExercises);
        editor.commit();

        SharedPreferences sharedPreferences2 = getSharedPreferences("DT2", 0);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.putString("DT2", dailyMeditations);
        editor2.commit();
    }

    /*
    public void onClickGetNewTasks(View view) {
        getNewTasks.setEnabled(true);
        SharedPreferences sharedPreferences3 = getSharedPreferences("Button Clicked", 0);
        SharedPreferences.Editor editor2 = sharedPreferences3.edit();
        editor2.putBoolean("Button Clicked", true);
        editor2.commit();

        dailyTasks();
    }*/




    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class Gesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //if(e2.getX() > e1.getX()){
            //    Intent intent = new Intent(Tasks.this, Stretches.class);
            //    startActivity(intent);
            //}
            if(e2.getX() < e1.getX()){
                Intent intent = new Intent(Tasks.this, MainActivity.class);
                startActivity(intent);

            }

            return true;
            //return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    */




}
