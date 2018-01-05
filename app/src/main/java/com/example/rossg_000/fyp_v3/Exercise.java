package com.example.rossg_000.fyp_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Exercise extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);


        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        final HashMap<String, Integer> testHash = getHashMapTest();
        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_items, new String[]{"First Line", "Second Line"}, new int[]{R.id.text1, R.id.text2});
        Iterator iterator = testHash.entrySet().iterator();

        while(iterator.hasNext())
        {
            HashMap<String, String> newMap = new HashMap<>();
            Map.Entry pairs = (Map.Entry)iterator.next();
            newMap.put("First Line", pairs.getKey().toString());
            newMap.put("Second Line", pairs.getValue().toString());
            listItems.add(newMap);
        }

        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener
        (
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        String elementClicked = String.valueOf(adapterView.getItemAtPosition(i));
                        //when item in list is clicked, do stuff to it here
                        Toast.makeText(Exercise.this, elementClicked, Toast.LENGTH_LONG).show(); //prints out at bottom of screen for a few seconds


                    }
                }
        );

    }

    public HashMap<String, Integer> getHashMapTest()
    {
        final HashMap<String, Integer> testHash = new HashMap<>();
        testHash.put("Walk (miles):", 8);
        testHash.put("Jog (miles):", 5);
        testHash.put("Swim (Lengths):", 10);
        testHash.put("Push Ups:", 50);
        testHash.put("Meditation (minutes):", 20);
        testHash.put("Deep Breathes:", 4);
        testHash.put("Hike (minutes):", 40);
        return testHash;

    }

}
