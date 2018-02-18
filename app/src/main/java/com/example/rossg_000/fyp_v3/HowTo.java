package com.example.rossg_000.fyp_v3;

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

public class HowTo extends AppCompatActivity {

    private ListView howToDetails;
    private StretchDetailsAdapter adapter;
    private List<StretchDetails> howToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        final String [] howToInfo = popUpInfo();
        howToDetails = (ListView) findViewById(R.id.HowToListView);
        loadData();
        adapter = new StretchDetailsAdapter(getApplicationContext(), howToList);
        howToDetails.setAdapter(adapter);

        howToDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", howToInfo[i]);
                startActivity(intent);
            }
        });
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences how to", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(howToList);
        editor.putString("how to list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences how to", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("how to list", null);
        Type type = new TypeToken<ArrayList<StretchDetails>>() {}.getType();
        howToList = gson.fromJson(json, type);

        if(howToList == null){
            howToList = new ArrayList<>();
            howToList.add(new StretchDetails("Page: Main"));
            howToList.add(new StretchDetails("Page: Journal"));
            howToList.add(new StretchDetails("Page: Task"));
            howToList.add(new StretchDetails("Page: Stretches"));
            howToList.add(new StretchDetails("Page: Exercise"));
            howToList.add(new StretchDetails("Page: Meditation"));
            howToList.add(new StretchDetails("Page: Rewards"));
            howToList.add(new StretchDetails("Activity Info"));
            howToList.add(new StretchDetails("Making Progress"));
            howToList.add(new StretchDetails("Activity Attempts"));
            howToList.add(new StretchDetails("Tracked Activities"));
            howToList.add(new StretchDetails("Trophys and Reward"));
            howToList.add(new StretchDetails("Adaptivity"));
        }
    }

    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[13];    //Dont forget to change size of array to match amount of elements in it
        //Need to tab them down a bit
        popUpInfo[0] = "\n\njdhjdjhkddhjksdhjs";
        popUpInfo[1] = "\n\n2";
        popUpInfo[2] = "\n\n3";
        popUpInfo[3] = "\n\n4";
        popUpInfo[4] = "\n\n5";
        popUpInfo[5] = "\n\n6";
        popUpInfo[6] = "\n\n7";
        popUpInfo[7] = "\n\n8";
        popUpInfo[8] = "\n\n9";
        popUpInfo[9] = "\n\n10";
        popUpInfo[10] = "\n\n11";
        popUpInfo[11] = "\n\n12";
        popUpInfo[12] = "\n\n13";

        return popUpInfo;
    }



}
