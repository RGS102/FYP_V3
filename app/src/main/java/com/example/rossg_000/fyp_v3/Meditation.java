package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Meditation extends AppCompatActivity {
    public static final int REQUEST_CODE_COMPLETE_OR_FAIL = 101;
    private ListView meditationDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> meditationDetailsListTest;
    private int dataTest = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        final String [] meditationInfo = popUpInfo();
        meditationDetails = (ListView) findViewById(R.id.MeditationListView);
        loadData();
        adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
        meditationDetails.setAdapter(adapter);

        meditationDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", meditationInfo[i]);
                startActivity(intent);
            }
        });

        meditationDetails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                if(resultCode== Activity.RESULT_OK) {
                    int CompleteOrFail = com.example.rossg_000.fyp_v3.CompleteOrFail.getResult(data);
                    TaskDetails clickedList = meditationDetailsListTest.get(dataTest);
                    int cLevelInteger = clickedList.getTaskLevelInteger();
                    int cAttempts = clickedList.getAttempts();
                    int progressMade = data.getIntExtra("Progress", 0);
                    int duration = data.getIntExtra("Duration", 0);

                    if(CompleteOrFail == +1){if(progressMade > 0) {progressUpdate(clickedList, dataTest, progressMade, duration);}}
                    else if(CompleteOrFail == -1) {
                        if(cAttempts >= 2){difficultyLevels(clickedList, dataTest, cLevelInteger, CompleteOrFail, progressMade, duration);}
                        else {difficultyLevels(clickedList, dataTest, cLevelInteger, 0, progressMade, duration);}
                    }}}}

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(meditationDetailsListTest);
        editor.putString("meditation list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meditation list", null);
        Type type = new TypeToken<ArrayList<TaskDetails>>() {}.getType();
        meditationDetailsListTest = gson.fromJson(json, type);

        if(meditationDetailsListTest == null){
            meditationDetailsListTest = new ArrayList<>();
            meditationDetailsListTest.add(new TaskDetails(1, "a" , 500, "step(s)"  , 1, 1));
            meditationDetailsListTest.add(new TaskDetails(2, "b"  , 1, "mile(s)"  , 1, 1));
            meditationDetailsListTest.add(new TaskDetails(3, "c"  , 1, "mile(s)"  ,  1, 1));
            meditationDetailsListTest.add(new TaskDetails(4, "d" , 1, "length(s)",  1, 1));
            meditationDetailsListTest.add(new TaskDetails(5, "e", 1, "mile(s)"  ,  1, 1));
        }
    }

    private void difficultyLevels(TaskDetails clickedList, int i, int cLevelInteger, int levelUpOrDown, int progress, int duration){
        int cId = clickedList.getId();
        String cTaskName = clickedList.getTaskName();
        int cRequirmentInteger = clickedList.getTaskRequirementInteger();
        String cRequirmentString = clickedList.getTaskRequirementString();
        int cAttempts = clickedList.getAttempts();

        cLevelInteger = cLevelInteger + levelUpOrDown;
        passToJournal(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts, levelUpOrDown, progress, duration);
        cAttempts = 1;

        if(cLevelInteger > 10){cLevelInteger = 1;}//JUST FOR TESTING PURPOSES - REMOVE/MODIFY LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(cLevelInteger<1){cLevelInteger = 1;}//JUST FOR TESTING PURPOSES - REMOVE/MODIFY LATER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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

        meditationDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts));
        adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
        meditationDetails.setAdapter(adapter);
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

        if(newValue <= 0){difficultyLevels(clickedList, i, e, +1, progress,duration);}
        else{
            passToJournal(a,b,newValue,d,e,f,0,progress,duration);
            meditationDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f));
            adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
            meditationDetails.setAdapter(adapter);
            saveData();
        }}

    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[5]; //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] = "111111111111111111111111111111111111111111111111111111111";
        popUpInfo[1] = "222222 222222 222222 222222 22222 2222222 222222 22222222";
        popUpInfo[2] = "3";
        popUpInfo[3] = "4";
        popUpInfo[4] = "5";

        return popUpInfo;
    }

    public void passToJournal(int ID, String TaskName, int RequirmentInteger, String RequirmentString, int level, int attempts, int upOrDown, int progress, int duration) {
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
}
