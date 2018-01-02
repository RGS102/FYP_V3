package com.example.rossg_000.fyp_v3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.Activity;
import android.widget.Toast;

import java.util.Random;

public class Tasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


        Integer max = 10;
        String[] taskArray = new String[10];
        Random rand = new Random();

        //Simple random number generator, replace with
        // random (no duplicate) tasks later, have random
        //selection only occur for empty places in array
        for(Integer i=0;i<taskArray.length;i++) {
            Integer next = rand.nextInt(max) + 1;
            String temp = Integer.toString(next);
            taskArray[i] = temp;
        }




        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskArray);

        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String elementClicked = String.valueOf(adapterView.getItemAtPosition(i));
                    //when item in list is clicked, do stuff to it here
                    Toast.makeText(Tasks.this, elementClicked, Toast.LENGTH_LONG).show(); //prints out at bottom of screen for a few seconds

                }
            }
        );
    }


}
