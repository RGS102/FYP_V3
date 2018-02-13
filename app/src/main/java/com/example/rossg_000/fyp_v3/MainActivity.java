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


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    //Run in background permission required?
    //Get rid of "implements SensorEventListner" above if you remove the accelerometer from this class


    public static boolean taskAlmostComplete = false;
    public static boolean exeAlmostComplete = false;
    public static boolean medAlmostComplete = false;

    public static int tasksCompleted = 0;
    public static int exercisesCompleted = 0;
    public static int meditationsCompleted = 0;

    public static int checkedTasks = 0;
    public static int checkedExercises = 0;
    public static int checkedMeditations = 0;

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



    private TextView Xtest, Ytest, Ztest;
    private SensorManager sensorManager;
    private Sensor sensor;

    //private final int LOCATION_REQUEST_COARSE_CODE = 123; //WORKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sensor test

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);

        Xtest = (TextView) findViewById(R.id.XCo);
        Ytest = (TextView) findViewById(R.id.YCo);
        Ztest = (TextView) findViewById(R.id.ZCo);



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

        updateValues();
        updateTitle();
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateTitle();
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
            //Toast.makeText(getApplicationContext(), "Clicked ?", Toast.LENGTH_LONG).show();
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

                handler.postDelayed(this, 5000);
            }
        });
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
        if(roll > 50.0){
            Ztest.setText("50");
        }
        else{
            Ztest.setText("0");
        }
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
