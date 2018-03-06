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
        popUpInfo[0] = "Downward Dog" +
                "\nStarting position: plank position with shoulders over waist" +
                "\nPush hips up towards ceiling to form a triangle with your body" +
                "\nKeep head between arms, straighten your legs, reach heels towards ground and spread fingers, hold for several seconds";
        popUpInfo[1] = "Side Obligue Stretch" +
                "\nStarting position: Stand with feet wider than hip distance apart" +
                "\nLift one arm overhead with your palm facing inward, and lean towards the opposite side of the raised arm" +
                "\nHold for several seconds and switch arms";
        popUpInfo[2] = "Crescent Pose" +
                "\nStand with one foot in front of you and the other behind, bend front knee to a 90 degree angle, with your back straight" +
                "\nLift your arms straight upwards, palms facing in. Lift your chest up, slightly arching your back as you press your back hip forward" +
                "\nExhale as you lower your lunge and hold for several seconds";
        popUpInfo[3] = "Single Leg Stretch" +
                "\nLie on back, lift legs towards ceiling. Lower one leg towards the floor, pull the other leg towards your head" +
                "\nHold the back of your raised legs, lift shoulders off ground. Maintain straight legs, pointed toes, and hold for several seconds";
        popUpInfo[4] = "Cat" +
                "\nOn hands and knees, with wrists in line with shoulders, and knees in line with hips" +
                "\nRound your back and tuck your pelvis as you look towards the floor, then scoop your abs upwards";
        popUpInfo[5] = "Sumo Squat" +
                "\nStand with your feet wide, toes pointed outwards, and hands just above knees" +
                "\nLean forward as you bend your knees to 90 degrees" +
                "\nBring one shoulder towards the floor as you look over the opposite shoulder" +
                "\nMaintain straight arms and keep hips aligned, hold for several seconds and switch sides";
        popUpInfo[6] = "Lying Hug Stretch" +
                "\nLie on back, tuck your knees towards your chest, grab your calves, roll your head up to meet your knees";
        popUpInfo[7] = "Crab Reach" +
                "\nSit with feet flat on floor, hip distance apart. Place hands down a few inches behind your hips, fingers facing away from you" +
                "\nBring one arm towards your chest, lift your hips upwards to form a table pose, reach your arm over your head" +
                "\nPress into your feet, rotate torso, and look down at your bottom hand. Hold for several seconds and switch sides";

        return popUpInfo;
    }
}
