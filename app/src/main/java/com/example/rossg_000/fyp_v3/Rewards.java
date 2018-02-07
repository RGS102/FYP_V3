package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rewards extends AppCompatActivity {
    private ListView rewardDetails;
    private RewardsDetailsAdapter adapter;
    private List<String> rewardList;
    private static String title = "A Rookie";
    //private static int exerciseProgress = 0;
    private static int checkedExercise = 0;
    private static int checkedMeditation = 0;
    private static int checkedTasks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rewardDetails = (ListView) findViewById(R.id.RewardsListView);
        loadData();
        adapter = new RewardsDetailsAdapter(getApplicationContext(), rewardList);
        rewardDetails.setAdapter(adapter);

        rewardDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Rewards.this, "test", Toast.LENGTH_SHORT).show();
                title = rewardList.get(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int i = Exercise.getTaskCompleted();
        int j = Meditation.getTaskCompleted();
        int k = i + j;


        unlockRewards(k,i,j);
    }


    private void unlockRewards(int task, int exe, int med){
        loadData();

        if((exe % 10 == 0) && checkedExercise != exe){
            if(!rewardList.contains("tester")) {rewardList.add(0,"tester");}
            else if(!rewardList.contains("2")) {rewardList.add(0,"2");}
            else if(!rewardList.contains("3")) {rewardList.add(0,"3");}

            checkedExercise = exe;
        }

        if((med % 10 == 0) && checkedMeditation != med){
            if(!rewardList.contains("4")) {rewardList.add(0,"4");}
            else if(!rewardList.contains("5")) {rewardList.add(0,"5");}
            else if(!rewardList.contains("6")) {rewardList.add(0,"6");}

            checkedMeditation = med;
        }

        if((task % 20 == 0) && checkedTasks != task){
            if(!rewardList.contains("7")) {rewardList.add(0,"7");}
            else if(!rewardList.contains("8")) {rewardList.add(0,"8");}
            else if(!rewardList.contains("9")) {rewardList.add(0,"9");}

            checkedTasks = task;
        }




        adapter = new RewardsDetailsAdapter(getApplicationContext(), rewardList);
        rewardDetails.setAdapter(adapter);
        saveData();
    }



    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(rewardList);
        editor.putString("reward list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("reward list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        rewardList = gson.fromJson(json, type);

        if(rewardList == null){
            rewardList = new ArrayList<String>();
            rewardList.add("A Rookie");
            rewardList.add("A Beta Tester");
        }
    }


    public static String getUserTitle(){
        return title;
    }

    //public static int getExerciseProgress(){return exerciseProgress;}
}
