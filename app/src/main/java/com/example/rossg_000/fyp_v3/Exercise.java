package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Exercise extends AppCompatActivity implements SensorEventListener {
    public static final int REQUEST_CODE_COMPLETE_OR_FAIL = 101;
    private ListView taskDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> taskDetailsListTest;
    private int dataTest = 0;
    SensorManager sensorManager;    //To do with the step count sensor, might change later
    boolean running = false;    //To do with the step count sensor, might change later
    private static int taskCompleted = 0;
    //private int[] excessArray = new int[]{0,0,0,0,0};


    private List<Integer> excessList;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        final String [] taskInfo = popUpInfo();
        taskDetails = (ListView) findViewById(R.id.taskListView);
        loadData();
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);

        //stepCounter = (TextView) findViewById(R.id.ExerciseTextView);   //TESTING PURPOSE!!!!!!!!!!!!!!!!!!!!!!!!
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);   //To do with the step count sensor, might change later

        taskDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", taskInfo[i]);
                startActivity(intent);
                //loadExcess();
                //Toast.makeText(Exercise.this, "Excess: " +excessList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        taskDetails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    dataTest = i;
                    final Intent intent2 = new Intent(getApplicationContext(), CompleteOrFail.class);
                    startActivityForResult(intent2, REQUEST_CODE_COMPLETE_OR_FAIL);
                }
                    return true;

            }
        });
        saveData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_CODE_COMPLETE_OR_FAIL:
                if(resultCode== Activity.RESULT_OK) {
                    int CompleteOrFail = com.example.rossg_000.fyp_v3.CompleteOrFail.getResult(data);
                    TaskDetails clickedList = taskDetailsListTest.get(dataTest);

                    int cLevelInteger = clickedList.getTaskLevelInteger();
                    int cAttempts = clickedList.getAttempts();
                    int progressMade = data.getIntExtra("Progress", 0);
                    int duration = data.getIntExtra("Duration", 0);

                    if(CompleteOrFail == +1)
                    {
                        if(progressMade > 0) {progressUpdate(clickedList, dataTest, progressMade, duration);}
                    }
                }
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskDetailsListTest);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<TaskDetails>>() {}.getType();
        taskDetailsListTest = gson.fromJson(json, type);

        if(taskDetailsListTest == null){
            taskDetailsListTest = new ArrayList<>();
            taskDetailsListTest.add(new TaskDetails(1, "Walk" , 500, "step(s)"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(2, "Run"  , 11, "mile(s)"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(3, "Jog"  , 21, "mile(s)"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(4, "Swim" , 31, "length(s)",  1, 1));
            taskDetailsListTest.add(new TaskDetails(5, "Cycle", 41, "mile(s)"  ,  1, 1));
        }}


    private void difficultyLevels(TaskDetails clickedList, int i, int cLevelInteger, int levelUpOrDown, int progress, int duration){
        int cId = clickedList.getId();
        String cTaskName = clickedList.getTaskName();
        int cRequirmentInteger = clickedList.getTaskRequirementInteger();
        String cRequirmentString = clickedList.getTaskRequirementString();
        int cAttempts = clickedList.getAttempts();

        passToJournal(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts, levelUpOrDown, progress, duration);
        cLevelInteger = cLevelInteger + levelUpOrDown;

        if(cLevelInteger<1){cLevelInteger = 1;}
        //loadExcessArray();
        loadExcess();
        int e = excessList.get(i);

        if(cId == 1){
            if(cLevelInteger == 1){cRequirmentInteger = 500;}
            if(cLevelInteger == 2){cRequirmentInteger = 600;}
            if(cLevelInteger == 3){cRequirmentInteger = 700;}
            if(cLevelInteger == 4){cRequirmentInteger = 800;}
            if(cLevelInteger == 5){cRequirmentInteger = 900;}
            if(cLevelInteger == 6){cRequirmentInteger = 1000;}
            if(cLevelInteger == 7){cRequirmentInteger = 1100;}
            if(cLevelInteger == 8){cRequirmentInteger = 1200;}
            if(cLevelInteger == 9){cRequirmentInteger = 1300;}
            if(cLevelInteger == 10){cRequirmentInteger = 1400;}
            if(cLevelInteger >= 11){cRequirmentInteger = 1400;}
        }
        if(cId == 2){
            if(cLevelInteger == 1){cRequirmentInteger = 11 + e;}
            if(cLevelInteger == 2){cRequirmentInteger = 12 + e;}
            if(cLevelInteger == 3){cRequirmentInteger = 13 + e;}
            if(cLevelInteger == 4){cRequirmentInteger = 14 + e;}
            if(cLevelInteger == 5){cRequirmentInteger = 15 + e;}
            if(cLevelInteger == 6){cRequirmentInteger = 16 + e;}
            if(cLevelInteger == 7){cRequirmentInteger = 17 + e;}
            if(cLevelInteger == 8){cRequirmentInteger = 18 + e;}
            if(cLevelInteger == 9){cRequirmentInteger = 19 + e;}
            if(cLevelInteger == 10){cRequirmentInteger = 20+ e;}
            if(cLevelInteger >= 11){cRequirmentInteger = 21+ e;}
        }
        if(cId == 3){
            if(cLevelInteger == 1){cRequirmentInteger = 21 + e;}
            if(cLevelInteger == 2){cRequirmentInteger = 22 + e;}
            if(cLevelInteger == 3){cRequirmentInteger = 23 + e;}
            if(cLevelInteger == 4){cRequirmentInteger = 24 + e;}
            if(cLevelInteger == 5){cRequirmentInteger = 25 + e;}
            if(cLevelInteger == 6){cRequirmentInteger = 26 + e;}
            if(cLevelInteger == 7){cRequirmentInteger = 27 + e;}
            if(cLevelInteger == 8){cRequirmentInteger = 28 + e;}
            if(cLevelInteger == 9){cRequirmentInteger = 29 + e;}
            if(cLevelInteger == 10){cRequirmentInteger = 30+ e;}
            if(cLevelInteger >= 11){cRequirmentInteger = 21+ e;}
        }
        if(cId == 4){
            if(cLevelInteger == 1){cRequirmentInteger = 31 + e;}
            if(cLevelInteger == 2){cRequirmentInteger = 32 + e;}
            if(cLevelInteger == 3){cRequirmentInteger = 33 + e;}
            if(cLevelInteger == 4){cRequirmentInteger = 34 + e;}
            if(cLevelInteger == 5){cRequirmentInteger = 35 + e;}
            if(cLevelInteger == 6){cRequirmentInteger = 36 + e;}
            if(cLevelInteger == 7){cRequirmentInteger = 37 + e;}
            if(cLevelInteger == 8){cRequirmentInteger = 38 + e;}
            if(cLevelInteger == 9){cRequirmentInteger = 39 + e;}
            if(cLevelInteger == 10){cRequirmentInteger = 40+ e;}
            if(cLevelInteger >= 11){cRequirmentInteger = 41+ e;}
        }
        if(cId == 5){
            if(cLevelInteger == 1){cRequirmentInteger = 41 + e;}
            if(cLevelInteger == 2){cRequirmentInteger = 42 + e;}
            if(cLevelInteger == 3){cRequirmentInteger = 43 + e;}
            if(cLevelInteger == 4){cRequirmentInteger = 44 + e;}
            if(cLevelInteger == 5){cRequirmentInteger = 45 + e;}
            if(cLevelInteger == 6){cRequirmentInteger = 46 + e;}
            if(cLevelInteger == 7){cRequirmentInteger = 47 + e;}
            if(cLevelInteger == 8){cRequirmentInteger = 48 + e;}
            if(cLevelInteger == 9){cRequirmentInteger = 49 + e;}
            if(cLevelInteger == 10){cRequirmentInteger = 50+ e;}
            if(cLevelInteger >= 11){cRequirmentInteger = 51+ e;}
        }

        if(levelUpOrDown==+1){taskCompleted += 1; cAttempts=1;}
        if(levelUpOrDown==-1){cAttempts=1;}

        taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts));
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);
        saveData();
    }

    private void progressUpdate(TaskDetails clickedList, int i, int progress, int duration){
        int a = clickedList.getId();
        String b = clickedList.getTaskName();
        int c = clickedList.getTaskRequirementInteger();
        String d = clickedList.getTaskRequirementString();
        int e = clickedList.getTaskLevelInteger();
        int f = clickedList.getAttempts();

        int newValue = c - progress;
        taskDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f));
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);
        saveData();

        loadExcess();

        if(newValue <= 0)
        {
            int temp = newValue*-1;
            int temp2 = excessList.get(i);
            if(temp > temp2)
            {
                excessList.set(i, temp);
                saveExcess();
            }

            difficultyLevels(clickedList, i, e, +1, progress,duration);
        }
        else
        {
            if(f>=2)
            {
                int temp = excessList.get(i);
                if(temp>=2) {temp = temp/2;}
                else{temp = 0;}
                saveExcess();
                difficultyLevels(clickedList, i, e, -1, progress,duration);
            }
            else
            {
                passToJournal(a,b,newValue,d,e,f,0, progress, duration);
                taskDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f+1));
                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                taskDetails.setAdapter(adapter);
                saveData();
            }
        }
    }

    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[5]; //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] = "Walk: put one foot in front of the other";
        popUpInfo[1] = "Run: put one foot in front of the other at a fast pace";
        popUpInfo[2] = "Jog: ...";
        popUpInfo[3] = "Swim: ...";
        popUpInfo[4] = "Cycle: ...";

        return popUpInfo;
    }

    @Override   //To do with the step count sensor, might change later
    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();}
    }

    @Override   //To do with the step count sensor, might change later
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){
            //stepCounter.setText(String.valueOf(sensorEvent.values[0])); //TESTING PURPOSE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            TaskDetails a = taskDetailsListTest.get(0);
            taskDetailsListTest.set(0,new TaskDetails(a.getId(), a.getTaskName(),a.getTaskRequirementInteger()-1,a.getTaskRequirementString(),/*a.getTaskLevelString(),*/a.getTaskLevelInteger(),a.getAttempts()));
            if(a.getTaskRequirementInteger()==0) {difficultyLevels(a,0, a.getTaskLevelInteger(),1, 0, 0);}
            adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
            taskDetails.setAdapter(adapter);
            saveData();
        }}

    @Override   //To do with the step count sensor, might change later
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void passToJournal(int ID, String TaskName, int RequirmentInteger, String RequirmentString, int level, int attempts, int upOrDown, int progress, int duration){
        Intent passInfoToJournal = new Intent(this, Journal.class);
        passInfoToJournal.putExtra("ID", ID);
        passInfoToJournal.putExtra("TaskName", TaskName);
        passInfoToJournal.putExtra("RequirmentInteger", RequirmentInteger);
        passInfoToJournal.putExtra("RequirementString", RequirmentString);
        passInfoToJournal.putExtra("level", level);
        passInfoToJournal.putExtra("attempts", attempts);
        passInfoToJournal.putExtra("upOrDown", upOrDown);
        passInfoToJournal.putExtra("progress", progress);
        passInfoToJournal.putExtra("duration", duration);

        startActivity(passInfoToJournal);
    }

    public static int getTaskCompleted(){
        return taskCompleted;
    }

    private void saveExcess(){
        SharedPreferences sharedPreferences = getSharedPreferences("shareTest", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(excessList);
        editor.putString("excess list", json);
        editor.apply();
    }

    private void loadExcess(){
        SharedPreferences sharedPreferences = getSharedPreferences("shareTest", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("excess list", null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        excessList = gson.fromJson(json, type);

        if(excessList == null){
            excessList = new ArrayList<>();
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);

        }

    }






}

