package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretches);
        gestureDetectorCompat = new GestureDetectorCompat(this, new Stretches.Gesture());

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
            stretchList.add(new StretchDetails("Stretch 1"));
            stretchList.add(new StretchDetails("Stretch 2"));
            stretchList.add(new StretchDetails("Stretch 3"));
            stretchList.add(new StretchDetails("Stretch 4"));
            stretchList.add(new StretchDetails("Stretch 5"));
            stretchList.add(new StretchDetails("Stretch 6"));
            stretchList.add(new StretchDetails("Stretch 7"));
            stretchList.add(new StretchDetails("Stretch 8"));
            stretchList.add(new StretchDetails("Stretch 9"));
            stretchList.add(new StretchDetails("Stretch 10"));
            stretchList.add(new StretchDetails("Stretch 11"));
            stretchList.add(new StretchDetails("Stretch 12"));
        }
    }

    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[12];    //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] = "\n\nStretch 1 test test test test test test test test test test test test test test test test test test";
        popUpInfo[1] = "\n\nStretch 2";
        popUpInfo[2] = "\n\nStretch 3";
        popUpInfo[3] = "\n\nStretch 4";
        popUpInfo[4] = "\n\nStretch 5";
        popUpInfo[5] = "\n\nStretch 6";
        popUpInfo[6] = "\n\nStretch 7";
        popUpInfo[7] = "\n\nStretch 8";
        popUpInfo[8] = "\n\nStretch 9";
        popUpInfo[9] = "\n\nStretch 10";
        popUpInfo[10] = "\n\nStretch 11";
        popUpInfo[11] = "\n\nStretch 12";

        return popUpInfo;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class Gesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e2.getX() > e1.getX()){
                Intent intent = new Intent(Stretches.this, Exercise.class);
                startActivity(intent);
            }
            if(e2.getX() < e1.getX()){
                Intent intent = new Intent(Stretches.this, Meditation.class);
                startActivity(intent);

            }

            return true;
            //return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
