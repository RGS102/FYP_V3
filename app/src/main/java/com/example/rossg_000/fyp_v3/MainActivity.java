package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Color;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    public static String titleDisplay = "A Rookie";
    public static boolean taskAlmostComplete = false;
    public static boolean exeAlmostComplete = false;
    public static boolean medAlmostComplete = false;
    public static boolean taskRewardGiven = false;
    public static boolean exeRewardGiven = false;
    public static boolean medRewardGiven = false;
    public static boolean rewardClicked = false;
    public static int taskRewardsEarned = 0;
    public static int exerciseRewardsEarned = 0;
    public static int meditationRewardsEarned = 0;
    public static int tasksCompleted = 0;
    public static int exercisesCompleted = 0;
    public static int meditationsCompleted = 0;
    public static int checkedTasks = 0;
    public static int checkedExercises = 0;
    public static int checkedMeditations = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //Works!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            askPermission(Manifest.permission.ACCESS_COARSE_LOCATION, LOCATION_REQUEST_COARSE_CODE);
        }
        */

        SharedPreferences sharedPreferences = getSharedPreferences("SharedPreference One", MODE_PRIVATE);
        tasksCompleted = sharedPreferences.getInt("Task Completed", tasksCompleted);
        setTasksCompleted(tasksCompleted);

        SharedPreferences sharedPreferences2 = getSharedPreferences("SharedPreference Two", MODE_PRIVATE);
        exercisesCompleted = sharedPreferences2.getInt("Exercise Completed", exercisesCompleted);
        setExercisesCompleted(exercisesCompleted);

        SharedPreferences sharedPreferences3 = getSharedPreferences("SharedPreference Three", MODE_PRIVATE);
        meditationsCompleted = sharedPreferences3.getInt("Meditation Completed", meditationsCompleted);
        setMeditationsCompleted(meditationsCompleted);

        SharedPreferences sharedPreferences4 = getSharedPreferences("SharedPreference Four", MODE_PRIVATE);
        checkedTasks = sharedPreferences4.getInt("Checked Tasks", checkedTasks);
        setCheckedTasks(checkedTasks);

        SharedPreferences sharedPreferences5 = getSharedPreferences("SharedPreference Five", MODE_PRIVATE);
        checkedExercises = sharedPreferences5.getInt("Checked Exercises", checkedExercises);
        setCheckedExercises(checkedExercises);

        SharedPreferences sharedPreferences6 = getSharedPreferences("SharedPreference Six", MODE_PRIVATE);
        checkedMeditations = sharedPreferences6.getInt("Checked Meditations", checkedMeditations);
        setCheckedMeditations(checkedMeditations);

        SharedPreferences sharedPreferences7 = getSharedPreferences("SharedPreference Seven", MODE_PRIVATE);
        taskAlmostComplete = sharedPreferences7.getBoolean("Task Almost", taskAlmostComplete);
        setTaskAlmostComplete(taskAlmostComplete);

        SharedPreferences sharedPreferences8 = getSharedPreferences("SharedPreference Eight", MODE_PRIVATE);
        exeAlmostComplete = sharedPreferences8.getBoolean("Exe Almost", exeAlmostComplete);
        setExeAlmostComplete(exeAlmostComplete);

        SharedPreferences sharedPreferences9 = getSharedPreferences("SharedPreference Nine", MODE_PRIVATE);
        medAlmostComplete = sharedPreferences9.getBoolean("Med Almost", medAlmostComplete);
        setMedAlmostComplete(medAlmostComplete);

        SharedPreferences sharedPreferences10 = getSharedPreferences("SharedPreference Ten", MODE_PRIVATE);
        titleDisplay = sharedPreferences10.getString("Title Display", titleDisplay);
        setTitleDisplay(titleDisplay);

        SharedPreferences sharedPreferences11 = getSharedPreferences("SharedPreference Eleven", MODE_PRIVATE);
        taskRewardsEarned = sharedPreferences11.getInt("Task Earned", taskRewardsEarned);
        setTaskRewardsEarned(taskRewardsEarned);

        SharedPreferences sharedPreferences12 = getSharedPreferences("SharedPreference Twelve", MODE_PRIVATE);
        exerciseRewardsEarned = sharedPreferences12.getInt("Exe Earned", exerciseRewardsEarned);
        setExerciseRewardsEarned(exerciseRewardsEarned);

        SharedPreferences sharedPreferences13 = getSharedPreferences("SharedPreference Thirteen", MODE_PRIVATE);
        meditationRewardsEarned = sharedPreferences13.getInt("Med Earned", meditationRewardsEarned);
        setMeditationRewardsEarned(meditationRewardsEarned);

        SharedPreferences sharedPreferences14 = getSharedPreferences("SharedPreference Fourteen", MODE_PRIVATE);
        taskRewardGiven = sharedPreferences14.getBoolean("Task Given", taskRewardGiven);
        setTaskRewardGiven(taskRewardGiven);

        SharedPreferences sharedPreferences15 = getSharedPreferences("SharedPreference Fifteen", MODE_PRIVATE);
        exeRewardGiven = sharedPreferences15.getBoolean("Exe Given", exeRewardGiven);
        setExeRewardGiven(exeRewardGiven);

        SharedPreferences sharedPreferences16 = getSharedPreferences("SharedPreference Sixteen", MODE_PRIVATE);
        medRewardGiven = sharedPreferences16.getBoolean("Med Given", medRewardGiven);
        setMedRewardGiven(medRewardGiven);

        updateValues();
        updateTitle();
        isRewardEarned();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(rewardClicked == true){
            titleAnimation();
            setRewardClicked(false);
        }
        else {updateTitle();}
        isRewardEarned();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        Drawable drawable = menu.findItem(R.id.questionMark).getIcon();
        if(drawable!=null){
            drawable.mutate();
            drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == R.id.questionMark){
            Intent intent = new Intent(this, HowTo.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void titleAnimation(){
        TextView userTitle = (TextView)findViewById(R.id.UserTitle);
        Animation in = new AlphaAnimation(0.0f,1.0f);
        in.setDuration(3000);
        Animation out = new AlphaAnimation(1.0f,0.0f);
        out.setDuration(3000);

        String title = getTitleDisplay();
        userTitle.startAnimation(out);
        userTitle.setText(title);
        userTitle.startAnimation(in);
    }



    public void updateTitle(){
        TextView userTitle = (TextView)findViewById(R.id.UserTitle);
        //Animation in = new AlphaAnimation(0.0f,1.0f);
        //in.setDuration(3000);
        //Animation out = new AlphaAnimation(1.0f,0.0f);
        //out.setDuration(3000);

        String title = getTitleDisplay();
        //userTitle.startAnimation(out);
        userTitle.setText(title);
        //userTitle.startAnimation(in);
    }

    public void updateValues(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = getSharedPreferences("SharedPreference One", MODE_PRIVATE).edit();
                editor.putInt("Task Completed", tasksCompleted);
                editor.commit();

                SharedPreferences.Editor editor2 = getSharedPreferences("SharedPreference Two", MODE_PRIVATE).edit();
                editor2.putInt("Exercise Completed", exercisesCompleted);
                editor2.commit();

                SharedPreferences.Editor editor3 = getSharedPreferences("SharedPreference Three", MODE_PRIVATE).edit();
                editor3.putInt("Meditation Completed", meditationsCompleted);
                editor3.commit();

                SharedPreferences.Editor editor4 = getSharedPreferences("SharedPreference Four", MODE_PRIVATE).edit();
                editor4.putInt("Checked Tasks", checkedTasks);
                editor4.commit();

                SharedPreferences.Editor editor5 = getSharedPreferences("SharedPreference Five", MODE_PRIVATE).edit();
                editor5.putInt("Checked Exercises", checkedExercises);
                editor5.commit();

                SharedPreferences.Editor editor6 = getSharedPreferences("SharedPreference Six", MODE_PRIVATE).edit();
                editor6.putInt("Checked Meditations", checkedMeditations);
                editor6.commit();

                SharedPreferences.Editor editor7 = getSharedPreferences("SharedPreference Seven", MODE_PRIVATE).edit();
                editor7.putBoolean("Task Almost", taskAlmostComplete);
                editor7.commit();

                SharedPreferences.Editor editor8 = getSharedPreferences("SharedPreference Eight", MODE_PRIVATE).edit();
                editor8.putBoolean("Exe Almost", exeAlmostComplete);
                editor8.commit();

                SharedPreferences.Editor editor9 = getSharedPreferences("SharedPreference Nine", MODE_PRIVATE).edit();
                editor9.putBoolean("Med Almost", medAlmostComplete);
                editor9.commit();

                SharedPreferences.Editor editor10 = getSharedPreferences("SharedPreference Ten", MODE_PRIVATE).edit();
                editor10.putString("Title Display", titleDisplay);
                editor10.commit();

                SharedPreferences.Editor editor11 = getSharedPreferences("SharedPreference Eleven", MODE_PRIVATE).edit();
                editor11.putInt("Task Earned", taskRewardsEarned);
                editor11.commit();

                SharedPreferences.Editor editor12 = getSharedPreferences("SharedPreference Twelve", MODE_PRIVATE).edit();
                editor12.putInt("Exe Earned", exerciseRewardsEarned);
                editor12.commit();

                SharedPreferences.Editor editor13 = getSharedPreferences("SharedPreference Thirteen", MODE_PRIVATE).edit();
                editor13.putInt("Med Earned", meditationRewardsEarned);
                editor13.commit();

                SharedPreferences.Editor editor14 = getSharedPreferences("SharedPreference Fourteen", MODE_PRIVATE).edit();
                editor14.putBoolean("Task Given", taskRewardGiven);
                editor14.commit();

                SharedPreferences.Editor editor15 = getSharedPreferences("SharedPreference Fifteen", MODE_PRIVATE).edit();
                editor15.putBoolean("Exe Given", exeRewardGiven);
                editor15.commit();

                SharedPreferences.Editor editor16 = getSharedPreferences("SharedPreference Sixteen", MODE_PRIVATE).edit();
                editor16.putBoolean("Med Given", medRewardGiven);
                editor16.commit();

                handler.postDelayed(this, 5000);
            }
        });
    }

    public void isRewardEarned(){
        int i = MainActivity.getExercisesCompleted();
        int j = MainActivity.getMeditationsCompleted();
        int k = MainActivity.getTasksCompleted();

        int exeRewards = MainActivity.getExerciseRewardsEarned();
        int medRewards = MainActivity.getMeditationRewardsEarned();
        int taskRewards = MainActivity.getTaskRewardsEarned();

        boolean exeBool = MainActivity.isExeRewardGiven();
        boolean medBool = MainActivity.isMedRewardGiven();
        boolean taskBool = MainActivity.isTaskRewardGiven();

        if(i%10==0 && i!=0 && exeBool == false) {
            exeRewards+=1;
            setExerciseRewardsEarned(exeRewards);
            setExeRewardGiven(true);
            setExeAlmostComplete(true);
        }
        else if(i%10!=0){
            setExeRewardGiven(false);
            setExeAlmostComplete(false);
        }

        if(j%10==0 && j!=0 && medBool == false) {
            medRewards+=1;
            setMeditationRewardsEarned(medRewards);
            setMedRewardGiven(true);
            setMedAlmostComplete(true);
        }
        else if(j%10!=0){
            setMedRewardGiven(false);
            setMedAlmostComplete(false);
        }

        if(k%20==0 && k!=0 && taskBool == false) {
            taskRewards+=1;
            setTaskRewardsEarned(taskRewards);
            setTaskRewardGiven(true);
            setTaskAlmostComplete(true);
        }
        else if(k%20!=0){
            setTaskRewardGiven(false);
            setTaskAlmostComplete(false);
        }
    }

    public void goToTask(View view) {
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

    public void goToExercise(View view) {
        Intent intent = new Intent(this, Exercise.class);
        startActivity(intent);
    }

    public void goToMeditation(View view) {
        Intent intent = new Intent(this, Meditation.class);
        startActivity(intent);
    }

    public void goToStretches(View view){
        Intent intent = new Intent(this, Stretches.class);
        startActivity(intent);
    }

    public static int getTasksCompleted() {
        return tasksCompleted;
    }

    public static void setTasksCompleted(int tasksCompleted) {
        MainActivity.tasksCompleted = tasksCompleted;
    }

    public static int getExercisesCompleted() {
        return exercisesCompleted;
    }

    public static void setExercisesCompleted(int exercisesCompleted) {
        MainActivity.exercisesCompleted = exercisesCompleted;
    }

    public static int getMeditationsCompleted() {
        return meditationsCompleted;
    }

    public static void setMeditationsCompleted(int meditationsCompleted) {
        MainActivity.meditationsCompleted = meditationsCompleted;
    }

    public static boolean isTaskAlmostComplete() {
        return taskAlmostComplete;
    }

    public static void setTaskAlmostComplete(boolean taskAlmostComplete) {
        MainActivity.taskAlmostComplete = taskAlmostComplete;
    }

    public static boolean isExeAlmostComplete() {
        return exeAlmostComplete;
    }

    public static void setExeAlmostComplete(boolean exeAlmostComplete) {
        MainActivity.exeAlmostComplete = exeAlmostComplete;
    }

    public static boolean isMedAlmostComplete() {
        return medAlmostComplete;
    }

    public static void setMedAlmostComplete(boolean medAlmostComplete) {
        MainActivity.medAlmostComplete = medAlmostComplete;
    }

    public static int getCheckedTasks() {
        return checkedTasks;
    }

    public static void setCheckedTasks(int checkedTasks) {
        MainActivity.checkedTasks = checkedTasks;
    }

    public static int getCheckedExercises() {
        return checkedExercises;
    }

    public static void setCheckedExercises(int checkedExercises) {
        MainActivity.checkedExercises = checkedExercises;
    }

    public static int getCheckedMeditations() {
        return checkedMeditations;
    }

    public static void setCheckedMeditations(int checkedMeditations) {
        MainActivity.checkedMeditations = checkedMeditations;
    }

    public static String getTitleDisplay() {
        return titleDisplay;
    }

    public static void setTitleDisplay(String titleDisplay) {
        MainActivity.titleDisplay = titleDisplay;
    }

    public static int getTaskRewardsEarned() {
        return taskRewardsEarned;
    }

    public static void setTaskRewardsEarned(int taskRewardsEarned) {
        MainActivity.taskRewardsEarned = taskRewardsEarned;
    }

    public static int getExerciseRewardsEarned() {
        return exerciseRewardsEarned;
    }

    public static void setExerciseRewardsEarned(int exerciseRewardsEarned) {
        MainActivity.exerciseRewardsEarned = exerciseRewardsEarned;
    }

    public static int getMeditationRewardsEarned() {
        return meditationRewardsEarned;
    }

    public static void setMeditationRewardsEarned(int meditationRewardsEarned) {
        MainActivity.meditationRewardsEarned = meditationRewardsEarned;
    }

    public static boolean isTaskRewardGiven() {
        return taskRewardGiven;
    }

    public static void setTaskRewardGiven(boolean taskRewardGiven) {
        MainActivity.taskRewardGiven = taskRewardGiven;
    }

    public static boolean isExeRewardGiven() {
        return exeRewardGiven;
    }

    public static void setExeRewardGiven(boolean exeRewardGiven) {
        MainActivity.exeRewardGiven = exeRewardGiven;
    }

    public static boolean isMedRewardGiven() {
        return medRewardGiven;
    }

    public static void setMedRewardGiven(boolean medRewardGiven) {
        MainActivity.medRewardGiven = medRewardGiven;
    }

    public static boolean isRewardClicked() {
        return rewardClicked;
    }

    public static void setRewardClicked(boolean rewardClicked) {
        MainActivity.rewardClicked = rewardClicked;
    }

    /*
    //WORKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private void askPermission(String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }else{
            //Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case LOCATION_REQUEST_COARSE_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();
                }
                else{
                    //Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }

        }
    }
    */
}
