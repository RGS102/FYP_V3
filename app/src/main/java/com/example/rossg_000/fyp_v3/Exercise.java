package com.example.rossg_000.fyp_v3;


import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Exercise extends AppCompatActivity {


    private ListView taskDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> taskDetailsListTest;
    int count=0;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


        thread = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count+=1000;
                                long startSeconds = 12;
                                long startMinutes = 13;
                                long startHours = 23;
                                long startDay = 14;

                                long startSecondsInMilli = startSeconds*1000;
                                long startMinutesInMilli = startMinutes*60000;
                                long startHoursInMilli = (startHours-1)*3600000;
                                long startDayInMilli = (startDay-1)*86400000;
                                long fullTime = startSecondsInMilli+startMinutesInMilli+startHoursInMilli+startDayInMilli;

                                TextView timeRemaining = (TextView) findViewById(R.id.timeRemaining);
                                fullTime = fullTime - count;
                                SimpleDateFormat timeRemainingFormat = new SimpleDateFormat("dd:HH:mm:ss");
                                String fullString = timeRemainingFormat.format(fullTime);
                                timeRemaining.setText(fullString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();




        taskDetails = (ListView) findViewById(R.id.taskListView);
        loadData();
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);

        taskDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskDetails clickedList = taskDetailsListTest.get(i);
                int cLevelInteger = clickedList.getTaskLevelInteger();

                if(cLevelInteger<10)
                {
                    difficultyLevels(clickedList, i, cLevelInteger+1);
                }
                //thread.start();

                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                taskDetails.setAdapter(adapter);
                saveData();
            }
        });


        taskDetails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskDetails clickedList = taskDetailsListTest.get(i);
                int cLevelInteger = clickedList.getTaskLevelInteger();

                if(cLevelInteger>1)
                {
                    difficultyLevels(clickedList, i, cLevelInteger-1);
                }

                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                taskDetails.setAdapter(adapter);
                saveData();

                return true;
            }
        });
        saveData();
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

            taskDetailsListTest.add(new TaskDetails(1, "Walk" , 1, "step(s)"  , "Level:", 0,1,1,1,1));
            taskDetailsListTest.add(new TaskDetails(2, "Run"  , 1, "mile(s)"  , "Level:", 0,1,1,1,1));
            taskDetailsListTest.add(new TaskDetails(3, "Jog"  , 1, "mile(s)"  , "Level:", 0,1,1,1,1));
            taskDetailsListTest.add(new TaskDetails(4, "Swim" , 1, "length(s)", "Level:", 0,1,1,1,1));
            taskDetailsListTest.add(new TaskDetails(5, "Cycle", 1, "mile(s)"  , "Level:", 0,1,1,1,1));
        }
    }

    private void difficultyLevels(TaskDetails clickedList, int i, int cLevelInteger){
        int cId = clickedList.getId();
        String cTaskName = clickedList.getTaskName();
        int cRequirmentInteger = clickedList.getTaskRequirementInteger();
        String cRequirmentString = clickedList.getTaskRequirementString();
        String cLevelString = clickedList.getTaskLevelString();
        int cDays = clickedList.getDays();
        int cHours = clickedList.getHours();
        int cMinutes = clickedList.getMinutes();
        int cSeconds = clickedList.getSeconds();

        if(cId == 1){
            if(cLevelInteger ==1){cRequirmentInteger = 1;}
            if(cLevelInteger ==2){cRequirmentInteger = 2;}
            if(cLevelInteger ==3){cRequirmentInteger = 3;}
            if(cLevelInteger ==4){cRequirmentInteger = 4;}
            if(cLevelInteger ==5){cRequirmentInteger = 5;}
            if(cLevelInteger ==6){cRequirmentInteger = 6;}
            if(cLevelInteger ==7){cRequirmentInteger = 7;}
            if(cLevelInteger ==8){cRequirmentInteger = 8;}
            if(cLevelInteger ==9){cRequirmentInteger = 9;}
            if(cLevelInteger ==10){cRequirmentInteger = 10;}
        }
        if(cId == 2){
            if(cLevelInteger ==1){cRequirmentInteger = 11;}
            if(cLevelInteger ==2){cRequirmentInteger = 12;}
            if(cLevelInteger ==3){cRequirmentInteger = 13;}
            if(cLevelInteger ==4){cRequirmentInteger = 14;}
            if(cLevelInteger ==5){cRequirmentInteger = 15;}
            if(cLevelInteger ==6){cRequirmentInteger = 16;}
            if(cLevelInteger ==7){cRequirmentInteger = 17;}
            if(cLevelInteger ==8){cRequirmentInteger = 18;}
            if(cLevelInteger ==9){cRequirmentInteger = 19;}
            if(cLevelInteger ==10){cRequirmentInteger = 20;}
        }
        if(cId == 3){
            if(cLevelInteger ==1){cRequirmentInteger = 21;}
            if(cLevelInteger ==2){cRequirmentInteger = 22;}
            if(cLevelInteger ==3){cRequirmentInteger = 23;}
            if(cLevelInteger ==4){cRequirmentInteger = 24;}
            if(cLevelInteger ==5){cRequirmentInteger = 25;}
            if(cLevelInteger ==6){cRequirmentInteger = 26;}
            if(cLevelInteger ==7){cRequirmentInteger = 27;}
            if(cLevelInteger ==8){cRequirmentInteger = 28;}
            if(cLevelInteger ==9){cRequirmentInteger = 29;}
            if(cLevelInteger ==10){cRequirmentInteger = 30;}
        }
        if(cId == 4){
            if(cLevelInteger ==1){cRequirmentInteger = 31;}
            if(cLevelInteger ==2){cRequirmentInteger = 32;}
            if(cLevelInteger ==3){cRequirmentInteger = 33;}
            if(cLevelInteger ==4){cRequirmentInteger = 34;}
            if(cLevelInteger ==5){cRequirmentInteger = 35;}
            if(cLevelInteger ==6){cRequirmentInteger = 36;}
            if(cLevelInteger ==7){cRequirmentInteger = 37;}
            if(cLevelInteger ==8){cRequirmentInteger = 38;}
            if(cLevelInteger ==9){cRequirmentInteger = 39;}
            if(cLevelInteger ==10){cRequirmentInteger = 40;}
        }
        if(cId == 5){
            if(cLevelInteger ==1){cRequirmentInteger = 41;}
            if(cLevelInteger ==2){cRequirmentInteger = 42;}
            if(cLevelInteger ==3){cRequirmentInteger = 43;}
            if(cLevelInteger ==4){cRequirmentInteger = 44;}
            if(cLevelInteger ==5){cRequirmentInteger = 45;}
            if(cLevelInteger ==6){cRequirmentInteger = 46;}
            if(cLevelInteger ==7){cRequirmentInteger = 47;}
            if(cLevelInteger ==8){cRequirmentInteger = 48;}
            if(cLevelInteger ==9){cRequirmentInteger = 49;}
            if(cLevelInteger ==10){cRequirmentInteger = 50;}
        }


        taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelString, cLevelInteger, cDays, cHours, cMinutes, cSeconds));

        /*
        //Save for later
        if (cHours == 0 && cMinutes == 0 && cSeconds == 0) {cSeconds = 60; cMinutes = 59; cHours = 23; cDays -= 1;}
        else if (cMinutes == 0 && cSeconds == 0) {cSeconds = 60; cMinutes = 59; cHours -= 1;}
        else if (cSeconds == 0) {cSeconds = 60; cMinutes -= 1;}
        */

    }



}
