package com.example.rossg_000.fyp_v3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.atan2;


public class MainActivity extends AppCompatActivity{

    //Run in background permission required?
    //Get rid of "implements SensorEventListner" above if you remove the accelerometer from this class


    public static boolean taskAlmostComplete = false;
    public static boolean exeAlmostComplete = false;
    public static boolean medAlmostComplete = false;

    public static boolean taskRewardGiven = false;
    public static boolean exeRewardGiven = false;
    public static boolean medRewardGiven = false;

    public static int tasksCompleted = 0;
    public static int exercisesCompleted = 0;
    public static int meditationsCompleted = 0;

    public static int checkedTasks = 0;
    public static int checkedExercises = 0;
    public static int checkedMeditations = 0;

    public static int taskRewardsEarned = 0;
    public static int exerciseRewardsEarned = 0;
    public static int meditationRewardsEarned = 0;

    public static String titleDisplay = "A Rookie";

    /*
    taskAlmostComplete
    exeAlmostComplete
    medAlmostComplete
    tasksCompleted
    exercisesCompleted
    meditationsCompleted
    checkedTasks
    checkedExercises
    checkedMeditations
    titleDisplay
     */



    //private TextView Xtest, Ytest, Ztest;
    //private SensorManager sensorManager;
    //private Sensor sensor;

    //private boolean notePlayed = false;

    //private final int LOCATION_REQUEST_COARSE_CODE = 123; //WORKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sensor test

        /*
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);

        Xtest = (TextView) findViewById(R.id.XCo);
        Ytest = (TextView) findViewById(R.id.YCo);
        Ztest = (TextView) findViewById(R.id.ZCo);
        */


        /*
        //Works!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            askPermission(Manifest.permission.ACCESS_COARSE_LOCATION, LOCATION_REQUEST_COARSE_CODE);
        }
        */

        /*
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10,0,this);
        this.onLocationChanged(null);
        */

        //Sensor test


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

        //load rewards earned

        updateValues();
        updateTitle();
        isRewardEarned();
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateTitle();
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


    public void updateTitle(){
        TextView userTitle = (TextView)findViewById(R.id.UserTitle);
        String title = getTitleDisplay();
        userTitle.setText(title);
    }

    public void updateValues(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                /*
                Toast.makeText(MainActivity.this, "Tasks Completed = " + tasksCompleted +
                        "\nExercises Completed = " + exercisesCompleted +
                        "\nMeditation Completed = " + meditationsCompleted +
                        "\nCheckedExe = " + checkedExercises +
                        "\nCheckedMed = " + checkedMeditations +
                        "\nCheckedTas = " + checkedTasks +
                        "\nTitle = " + titleDisplay +
                        "\nAlmost T = " + taskAlmostComplete +
                        "\nAlmost E = " + exeAlmostComplete +
                        "\nAlmost M = " + medAlmostComplete

                        ,Toast.LENGTH_LONG).show();
                    */

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

                //save rewards earned

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

        if(i%10==0 && i!=0 && exeBool == false)
        {
            exeRewards+=1;
            setExerciseRewardsEarned(exeRewards);
            setExeRewardGiven(true);
        }
        else if(i%10!=0){
            setExeRewardGiven(false);
        }

        if(j%10==0 && j!=0 && medBool == false)
        {
            medRewards+=1;
            setMeditationRewardsEarned(medRewards);
            setMedRewardGiven(true);
        }
        else if(j%10!=0){
            setMedRewardGiven(false);
        }

        if(k%20==0 && k!=0 && taskBool == false)
        {
            taskRewards+=1;
            setTaskRewardsEarned(taskRewards);
            setTaskRewardGiven(true);
        }
        else if(k%20!=0){
            setTaskRewardGiven(false);
        }
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

    /*
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //Xtest.setText("X: " + sensorEvent.values[0]);
        //Ytest.setText("Y: " + sensorEvent.values[1]);
        //Ztest.setText("Z: " + sensorEvent.values[2]);
        double roll = 0.00;
        double pitch = 0.00;

        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        double i = Math.sqrt(x*x + y*y + z*z);

        roll = atan2(y,z)*57.3;
        pitch = atan2((-x), Math.sqrt(y*y + z*z))*5.73;

        Xtest.setText("Roll: " + roll);
        Ytest.setText("Pitch: " + pitch);

        */
        //if(roll >= 0 && roll <= 9){Ztest.setText("0"); /*notePlayed = false;*/}
        //if(roll >= 10 && roll <= 19){Ztest.setText("10");/*notePlayed = false;*/}
        //if(roll >= 20 && roll <= 29){Ztest.setText("20");/*notePlayed = false;*/}
        /*if(roll >= 30 && roll <= 39){Ztest.setText("20");}
        if(roll >= 40 && roll <= 49){Ztest.setText("40");}
        if(roll >= 50 && roll <= 59){Ztest.setText("50");}
        if(roll >= 60 && roll <= 69){Ztest.setText("60");}
        if(roll >= 70 && roll <= 79){Ztest.setText("70");}
        if(roll >= 80 && roll <= 89){Ztest.setText("80");}
        if(roll >= 90 && roll <= 99 /*&& notePlayed == false*//*)
        {
            Ztest.setText("90");
            //Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            //ringtone.play();
            //notePlayed = true;
        }
        if(roll >= 100 && roll <= 109){Ztest.setText("100");}
        if(roll >= 110 && roll <= 119){Ztest.setText("110");}
        if(roll >= 120 && roll <= 129){Ztest.setText("120");}
        if(roll >= 130 && roll <= 139){Ztest.setText("130");}
        if(roll >= 140 && roll <= 149){Ztest.setText("140");}
        if(roll >= 150 && roll <= 159){Ztest.setText("150");}
        if(roll >= 160 && roll <= 169){Ztest.setText("160");}
        if(roll >= 170 && roll <= 179){Ztest.setText("170");}
        if(roll >= 180 && roll <= 189){Ztest.setText("180");}
        //else{Ztest.setText("0");}

        //if(roll > 50.0){
        //    Ztest.setText("50");
        //}
        //else{Ztest.setText("0");}

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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

    /*
    @Override
    public void onLocationChanged(Location location) {

        TextView xText = (TextView)findViewById(R.id.XCo);
        if(location==null){
            xText.setText("-.- m/s");
        }
        else{
            float currentSpeed = location.getSpeed();
            xText.setText(currentSpeed + " m/s");
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    */
}
