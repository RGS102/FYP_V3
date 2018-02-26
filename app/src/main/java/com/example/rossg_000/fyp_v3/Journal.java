package com.example.rossg_000.fyp_v3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Journal extends AppCompatActivity {
    private ListView JournalDetails;
    private JournalDetailsAdapter adapter;
    private List<JournalDetails> journalDetailsListTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        JournalDetails = (ListView) findViewById(R.id.journalListView);
        loadData();
        Bundle bundle = getIntent().getExtras();

        if(bundle!= null) {
            int ID = bundle.getInt("ID");
            String TaskName = bundle.getString("TaskName");
            int RequirmentInteger = bundle.getInt("RequirementInteger");
            String RequirmentString = bundle.getString("RequirementString");
            int level = bundle.getInt("level");
            int attempts = bundle.getInt("attempts");
            int upOrDowntemp = bundle.getInt("upOrDown");
            int progressMade = bundle.getInt("progress");
            int duration = bundle.getInt("duration");
            String formattedTime = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
            String formattedDate = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance().getTime());

            String[] taskArray = {"Walked", "Ran", "Jogged", "Swam", "Performed",
                    "Performed", "Cycled", "Performed", "Squated", "Performed",
                    "Tuck Jumped","Performed","Performed","Planked","Wall Sat",
                    "Lunged","Clock Lunged","Performed","Stepped Up","Raised Calf",
                    "Dipped Tricep","Boxed","Flutter Kicked","Performed","Performed",
                    "Performed","Performed","Performed","Performed","Performed",
                    "Performed","Performed","Performed","Performed","Slept",
                    "Performed","Performed","Performed","Performed","Performed",
                    "Performed","Performed","Performed","Performed","Listened", "Read",
                    "Performed","Performed"};

            String taskAction = taskArray[ID-1];
            String progressString="";

            if(duration == 0 && progressMade == 0){progressString = "";}
            if(duration == 0 && progressMade != 0){progressString = taskAction + " " + String.valueOf(progressMade) + " " + RequirmentString;}
            else{progressString = taskAction + " " +  String.valueOf(progressMade) + " " + RequirmentString + " for " + String.valueOf(duration) + " minutes";}

            String upOrDown = "";
            if(upOrDowntemp == 1){upOrDown = "Leveled up";}
            else if(upOrDowntemp==-1){upOrDown = "Leveled down";}

            journalDetailsListTest.add(0,new JournalDetails(ID, TaskName,RequirmentInteger, level, attempts, upOrDown, formattedTime, formattedDate, progressString));
        }

        adapter = new JournalDetailsAdapter(getApplicationContext(), journalDetailsListTest);
        JournalDetails.setAdapter(adapter);
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(journalDetailsListTest);
        editor.putString("journal list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("journal list", null);
        Type type = new TypeToken<ArrayList<JournalDetails>>() {}.getType(); //class to be implemented
        journalDetailsListTest = gson.fromJson(json, type);
        if(journalDetailsListTest == null){journalDetailsListTest = new ArrayList<>();}
    }
}
