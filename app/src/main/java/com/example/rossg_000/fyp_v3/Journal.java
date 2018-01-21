package com.example.rossg_000.fyp_v3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Journal extends AppCompatActivity{

    private ListView JournalDetails;
    private JournalDetailsAdapter adapter;
    private List<JournalDetails> journalDetailsListTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        JournalDetails = (ListView) findViewById(R.id.journalListView);

        loadData();
        //journalDetailsListTest = new ArrayList<>();
        //journalDetailsListTest.add(new JournalDetails(0,"a", 2, "b", 0, 0));
        adapter = new JournalDetailsAdapter(getApplicationContext(), journalDetailsListTest);
        JournalDetails.setAdapter(adapter);



        Bundle bundle = getIntent().getExtras();
        if(bundle!= null) {
            int ID = bundle.getInt("ID");
            String TaskName = bundle.getString("TaskName");
            int RequirmentInteger = bundle.getInt("RequirementInteger");
            String RequirmentString = bundle.getString("RequirementString");
            int level = bundle.getInt("level");
            int attempts = bundle.getInt("attempts");
            int upOrDowntemp = bundle.getInt("upOrDown");

            String formattedTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            String formattedDate = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance().getTime());



            String upOrDown = "";
            if(upOrDowntemp == 1){
                upOrDown = "up";
            }else if(upOrDowntemp==-1){
                upOrDown = "down";
            }


            journalDetailsListTest.add(new JournalDetails(ID, TaskName,RequirmentInteger,RequirmentString, level, attempts, upOrDown, formattedTime, formattedDate));
            JournalDetails.setAdapter(adapter);
        }

        saveData();
    }



    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(journalDetailsListTest);
        editor.putString("journal list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("journal list", null);
        Type type = new TypeToken<ArrayList<JournalDetails>>() {}.getType(); //class to be implemented
        journalDetailsListTest = gson.fromJson(json, type);

        if(journalDetailsListTest == null){
            journalDetailsListTest = new ArrayList<>();
        //    journalDetailsListTest.add(new JournalDetails(0,"a", 2, "b", 0, 0, "", "", ""));
        }
    }



}
