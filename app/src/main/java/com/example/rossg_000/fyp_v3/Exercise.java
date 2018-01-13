package com.example.rossg_000.fyp_v3;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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


    public static final int REQUEST_CODE_COMPLETE_OR_FAIL = 101;
    private ListView taskDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> taskDetailsListTest;
    private int dataTest = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        final String [] taskInfo = popUpInfo();
        taskDetails = (ListView) findViewById(R.id.taskListView);
        loadData();
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);

        taskDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", taskInfo[i]);
                startActivity(intent);
            }
        });


        taskDetails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataTest = i;
                final Intent intent2 = new Intent(getApplicationContext(), CompleteOrFail.class);
                startActivityForResult(intent2, REQUEST_CODE_COMPLETE_OR_FAIL);
                return true;
            }
        });
        saveData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_CODE_COMPLETE_OR_FAIL:
                if(resultCode== Activity.RESULT_OK){
                    int CompleteOrFail = com.example.rossg_000.fyp_v3.CompleteOrFail.getResult(data);
                    TaskDetails clickedList = taskDetailsListTest.get(dataTest);

                    int cLevelInteger = clickedList.getTaskLevelInteger();
                    int levelTest = cLevelInteger + CompleteOrFail;

                    if(levelTest < 1){levelTest=1;}
                    if(levelTest > 10){levelTest=10;}

                    difficultyLevels(clickedList, dataTest, levelTest);
                }
        }
    }

    /*
    private void countDownTimer(final TaskDetails clickedList, final int i)
    {
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
                                long fullTime = clickedList.getTimeRemaining();

                                TextView timeRemaining = (TextView) findViewById(R.id.timeRemaining);
                                fullTime = fullTime - count;
                                //SimpleDateFormat timeRemainingFormat = new SimpleDateFormat("dd:HH:mm:ss");
                                //String fullString = timeRemainingFormat.format(fullTime);
                                //timeRemaining.setText(fullString);

                                taskDetailsListTest.set(i, new TaskDetails(clickedList.getId(), clickedList.getTaskName(),clickedList.getTaskRequirementInteger(),clickedList.getTaskRequirementString(),clickedList.getTaskLevelString(),clickedList.getTaskLevelInteger(), fullTime));
                                */
                                /*
                                int testSeconds = (int)fullTime/1000;
                                int testMinutes = testSeconds/60;
                                int testHours = testMinutes/60;
                                int testDays = testHours/60;

                                taskDetailsListTest.set(i, new TaskDetails(1, clickedList.getTaskName(), clickedList.getTaskRequirementInteger()+9, clickedList.getTaskRequirementString(), clickedList.getTaskLevelString(), clickedList.getTaskLevelInteger(), testDays, testHours%24, testMinutes%60, testSeconds%60));
                                */
                                /*
                                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                                taskDetails.setAdapter(adapter);
                                saveData();



                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    */

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

            taskDetailsListTest.add(new TaskDetails(1, "Walk" , 1, "step(s)"  , "Level:", 0, 90061000));
            taskDetailsListTest.add(new TaskDetails(2, "Run"  , 1, "mile(s)"  , "Level:", 0, 90062000));
            taskDetailsListTest.add(new TaskDetails(3, "Jog"  , 1, "mile(s)"  , "Level:", 0, 90063000));
            taskDetailsListTest.add(new TaskDetails(4, "Swim" , 1, "length(s)", "Level:", 0, 90064000));
            taskDetailsListTest.add(new TaskDetails(5, "Cycle", 1, "mile(s)"  , "Level:", 0, 90065000));
        }
    }

    private void difficultyLevels(TaskDetails clickedList, int i, int cLevelInteger){
        int cId = clickedList.getId();
        String cTaskName = clickedList.getTaskName();
        int cRequirmentInteger = clickedList.getTaskRequirementInteger();
        String cRequirmentString = clickedList.getTaskRequirementString();
        String cLevelString = clickedList.getTaskLevelString();
        long cTimeRemaining = clickedList.getTimeRemaining();
        /*
        int cDays = clickedList.getDays();
        int cHours = clickedList.getHours();
        int cMinutes = clickedList.getMinutes();
        int cSeconds = clickedList.getSeconds();
        */
        if(cId == 1){
            if(cLevelInteger == 1){cRequirmentInteger = 1; cTimeRemaining = 90061000;}
            if(cLevelInteger == 2){cRequirmentInteger = 2; cTimeRemaining = 90062000;}
            if(cLevelInteger == 3){cRequirmentInteger = 3; cTimeRemaining = 90063000;}
            if(cLevelInteger == 4){cRequirmentInteger = 4;}
            if(cLevelInteger == 5){cRequirmentInteger = 5;}
            if(cLevelInteger == 6){cRequirmentInteger = 6;}
            if(cLevelInteger == 7){cRequirmentInteger = 7;}
            if(cLevelInteger == 8){cRequirmentInteger = 8;}
            if(cLevelInteger == 9){cRequirmentInteger = 9;}
            if(cLevelInteger == 10){cRequirmentInteger = 10;}
        }
        if(cId == 2){
            if(cLevelInteger ==1){cRequirmentInteger = 11; cTimeRemaining = 90064000;}
            if(cLevelInteger ==2){cRequirmentInteger = 12; cTimeRemaining = 90065000;}
            if(cLevelInteger ==3){cRequirmentInteger = 13; cTimeRemaining = 90066000;}
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


        taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelString, cLevelInteger, cTimeRemaining));
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);
        saveData();
    }


    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[5];
        popUpInfo[0] = "Walk: put one foot in front of the other";
        popUpInfo[1] = "Run: put one foot in front of the other at a fast pace";
        popUpInfo[2] = "Jog: ...";
        popUpInfo[3] = "Swim: ...";
        popUpInfo[4] = "Cycle: ...";

        return popUpInfo;



    }




}

                /*
                //Just below the first on click
                TaskDetails clickedList = taskDetailsListTest.get(i);
                int cLevelInteger = clickedList.getTaskLevelInteger();

                if(cLevelInteger<10)
                {
                    difficultyLevels(clickedList, i, cLevelInteger + 1);
                }

                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                taskDetails.setAdapter(adapter);
                saveData();
                */
