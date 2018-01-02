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

public class Tasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        String[] taskArray = {"a", "b", "c", "d", "e"};
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskArray);

        ListView taskListView = (ListView) findViewById(R.id.taskListView);
        taskListView.setAdapter(adapter);

        taskListView.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String test = String.valueOf(adapterView.getItemAtPosition(i));
                    //when item in list is clicked, do stuff to it here
                    Toast.makeText(Tasks.this, test, Toast.LENGTH_LONG).show(); //prints out at bottom of screen for a few seconds

                }
            }
        );
    }


}
