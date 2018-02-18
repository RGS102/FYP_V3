package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Tasks extends AppCompatActivity {
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        gestureDetectorCompat = new GestureDetectorCompat(this, new Tasks.Gesture());

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



}
