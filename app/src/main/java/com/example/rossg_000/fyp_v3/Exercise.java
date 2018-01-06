package com.example.rossg_000.fyp_v3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class Exercise extends AppCompatActivity {


    private ListView taskDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> taskDetailsListTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        taskDetails = (ListView) findViewById(R.id.taskListView);
        taskDetailsListTest = new ArrayList<>();

        taskDetailsListTest.add(new TaskDetails(1, "Walk", 100, "steps", "Level:", 1 ));
        taskDetailsListTest.add(new TaskDetails(2, "Run", 1, "mile(s)", "Level:", 1 ));
        taskDetailsListTest.add(new TaskDetails(3, "Jog", 2, "mile(s)", "Level:", 1 ));
        taskDetailsListTest.add(new TaskDetails(4, "Swim", 20, "lengths", "Level:", 2 ));
        taskDetailsListTest.add(new TaskDetails(5, "Cycle", 10, "mile(s)", "Level:", 5 ));

        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);

        taskDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TaskDetails clickedList = taskDetailsListTest.get(i);
                int cId = clickedList.getId();
                String cTaskName = clickedList.getTaskName();
                int cRequirmentInteger = clickedList.getTaskRequirementInteger();
                String cRequirmentString = clickedList.getTaskRequirementString();
                String cLevelString = clickedList.getTaskLevelString();
                int cLevelInteger = clickedList.getTaskLevelInteger();

                //Toast.makeText(getApplicationContext(), "Clicked = " + cRequirmentInteger, Toast.LENGTH_LONG).show();

                if(cRequirmentInteger==0)
                {
                    taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger+5, cRequirmentString, cLevelString, cLevelInteger+1));
                }
                else
                {
                    taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger - 1, cRequirmentString, cLevelString, cLevelInteger));
                }

                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                taskDetails.setAdapter(adapter);
            }
        });
    }
}
