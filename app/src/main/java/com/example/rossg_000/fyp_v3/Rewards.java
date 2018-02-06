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
    //private List<StretchDetails> rewardList;
    private List<String> rewardList;
    private static String title = "A Rookie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rewardDetails = (ListView) findViewById(R.id.RewardsListView);
        loadData();

        //rewardList = new ArrayList<String>();
        //rewardList.add("A Rookie");



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

        if(exe >= 1 && !rewardList.contains("1")) {rewardList.add(0,"1");}
        if(exe >= 2 && !rewardList.contains("2")) {rewardList.add(0,"2");}
        if(med >= 1 && !rewardList.contains("3")) {rewardList.add(0,"3");}
        if(med >= 2 && !rewardList.contains("4")) {rewardList.add(0,"4");}
        if(task >= 3 && !rewardList.contains("5")) {rewardList.add(0,"5");}
        if(task >= 4 && !rewardList.contains("6")) {rewardList.add(0,"6");}





        /*
        if(exe >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(exe >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(exe >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(exe >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(exe >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(exe >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}

        if(med >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(med >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(med >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(med >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(med >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(med >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}

        if(task >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(task >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(task >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(task >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(task >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        if(task >= 1 && !rewardList.contains("")) {rewardList.add(0,"");}
        */




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
        }
    }


    public static String getUserTitle(){
        return title;
    }



    /*
    private ListView rewardDetails;
    private StretchDetailsAdapter adapter;
    //private List<StretchDetails> rewardList;
    private List<StretchDetails> rewardList;
    private static String title = "A Rookie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rewardDetails = (ListView) findViewById(R.id.RewardsListView);
        loadData();
        adapter = new StretchDetailsAdapter(getApplicationContext(), rewardList);
        rewardDetails.setAdapter(adapter);

        rewardDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Rewards.this, "test", Toast.LENGTH_SHORT).show();
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

        if(exe == 1) {rewardList.add(0, new StretchDetails("exe 1"));}
        if(exe == 2) {rewardList.add(0, new StretchDetails("exe 2"));}

        adapter = new StretchDetailsAdapter(getApplicationContext(), rewardList);
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
        Type type = new TypeToken<ArrayList<StretchDetails>>() {}.getType();
        rewardList = gson.fromJson(json, type);

        if(rewardList == null){
            rewardList = new ArrayList<>();
            rewardList.add(new StretchDetails("A Rookie"));
        }
    }

    public static String getUserTitle(){
        return title;
    }
    */

}
