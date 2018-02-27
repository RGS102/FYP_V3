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

public class Stretches extends AppCompatActivity {
    private ListView stretchDetails;
    private StretchDetailsAdapter adapter;
    private List<StretchDetails> stretchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretches);
        final String [] stretchInfo = popUpInfo();
        stretchDetails = (ListView) findViewById(R.id.StretchesListView);
        loadData();
        adapter = new StretchDetailsAdapter(getApplicationContext(), stretchList);
        stretchDetails.setAdapter(adapter);

        stretchDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", stretchInfo[i]);
                startActivity(intent);
            }
        });

        saveData();
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(stretchList);
        editor.putString("stretch list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("stretch list", null);
        Type type = new TypeToken<ArrayList<StretchDetails>>() {}.getType();
        stretchList = gson.fromJson(json, type);

        if(stretchList == null){
            stretchList = new ArrayList<>();
            stretchList.add(new StretchDetails("Downward Dog"));
            stretchList.add(new StretchDetails("Side Oblique Stretch"));
            stretchList.add(new StretchDetails("Crescent Pose"));
            stretchList.add(new StretchDetails("Single Leg Stretch"));
            stretchList.add(new StretchDetails("Cat"));
            stretchList.add(new StretchDetails("Sumo Squat"));
            stretchList.add(new StretchDetails("Lying Hug Stretch"));
            stretchList.add(new StretchDetails("Crab Reach"));
        }
    }

    public String[] popUpInfo(){
        String[] popUpInfo = new String[8];    //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] = "\n\nDownward Dog" +
                "\n\nStarting position: plank position with shoulders over waist" +
                "\n\nPush hips up towards ceiling to form a triangle with your body" +
                "\n\nKeep head between arms, straighten your legs, reach heels towards ground and spread fingers, hold for several seconds";
        popUpInfo[1] = "\n\nSide Obligue Stretch" +
                "\n\nStarting position: Stand with feet wider than hip distance apart" +
                "\n\nLift one arm overhead with your palm facing inward, and lean towards the opposite side of the raised arm" +
                "\n\nHold for several seconds and switch arms";
        popUpInfo[2] = "\n\nCrescent Pose" +
                "\n\nCrescent Pose" +
                "\n\nStand with one font infront of you and the other behind, bend front knee to a 90 degree angle, with your back straight" +
                "\n\nLift your arms straight upwards, palms facing in. Lift your chest up, slightly arching your back as you press your back hip forward" +
                "\n\nExhale as you lower your lunge and hold for several seconds";
        popUpInfo[3] = "\n\nSingle Leg Stretch" +
                "\n\nLie on back, lift legs towards ceiling. Lower one leg towards the floor, pull the other leg towards your head" +
                "\n\nHold the back of your rasied legs, lift shoulders off ground. Maintain straight legs, pointed toes, and hold for several seconds";
        popUpInfo[4] = "\n\nCat" +
                "\n\nOn hands and knees, with wrists in line with shoulders, and knees in line with hips" +
                "\n\nRound your back and tuck your pelvis as you look towards the floor, then scoop your abs upwars";
        popUpInfo[5] = "\n\nSumo Squat" +
                "\n\nStand with your feet wide, toes pointed outwards, and hands just above knees" +
                "\n\nLean forward as you bend your knees to 90 degrees" +
                "\n\nBring one shoulder towards the floor as you look over the opposite shoulder" +
                "\n\nMaintain straight arms and keep hips aligned, hold for several seconds and switch sides";
        popUpInfo[6] = "\n\nLying Hug Stretch" +
                "\n\nLie on back, tuck your knees towards your chest, grab your calves, roll your head up to meet your knees";
        popUpInfo[7] = "\n\nCrab Reach" +
                "\n\nSit with feet flat on floor, hip distance apart. Place hands down a few inches behind your hips, fingers facing away from you" +
                "\n\nBring one arm towards your chest, lift your hips upwards to form a table pose, reach your arm over your head" +
                "\n\nPress into your feet, rotate torso, and look down at your bottom hand. Hold for several seconds and switch sides";

        return popUpInfo;
    }
}
