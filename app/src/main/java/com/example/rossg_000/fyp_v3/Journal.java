package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

            String[] taskArray = {"Walked", "Performed", "Jogged", "Swam", "Ran",
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
            if(upOrDowntemp == 1){upOrDown = "Levelled up";}
            else if(upOrDowntemp==-1){upOrDown = "Levelled down";}

            journalDetailsListTest.add(0,new JournalDetails(ID, TaskName,RequirmentInteger, level, attempts, upOrDown, formattedTime, formattedDate, progressString));
        }

        adapter = new JournalDetailsAdapter(getApplicationContext(), journalDetailsListTest);
        JournalDetails.setAdapter(adapter);
        saveData();
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

    public void emailButtonClicked(View view){sendEmail();}

    public void sendEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "ross.gs95@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "APP TEST");
        loadData();

        StringBuilder stringBuilder = new StringBuilder();
        for(JournalDetails journalDetails: journalDetailsListTest){

            int taskId = journalDetails.getId();
            String taskName = journalDetails.getTaskName();
            int level = journalDetails.getTaskLevelInteger();
            int attempts = journalDetails.getAttempts();
            String upOrDown = journalDetails.getUpOrDown();
            String progress = journalDetails.getProgressString();
            String time = journalDetails.getTime();
            String date = journalDetails.getDate();

            String full = "ID: " + Integer.toString(taskId) +
                    ", Task: " + taskName +
                    ", Progress: " + progress +
                    ", Time: " + time +
                    ", Date: " + date +
                    ", Level: " + Integer.toString(level) +
                    ", Attempts: " + Integer.toString(attempts) +
                    ", Level_Change: " + upOrDown;
            stringBuilder.append(full);
            stringBuilder.append("\n\n");
        }
        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
        startActivity(Intent.createChooser(intent, "Choose app to send:"));


    }


}
