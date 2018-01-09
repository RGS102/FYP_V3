package com.example.rossg_000.fyp_v3;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

        taskDetailsListTest.add(new TaskDetails(1, "Walk", 100, "steps", "Level:", 1,1,1, 1, 1));
        taskDetailsListTest.add(new TaskDetails(2, "Run", 1, "mile(s)", "Level:", 1,2,2,2,2));
        taskDetailsListTest.add(new TaskDetails(3, "Jog", 2, "mile(s)", "Level:", 1,3,3,3,3));
        taskDetailsListTest.add(new TaskDetails(4, "Swim", 20, "lengths", "Level:", 2,4,4,4,4));
        taskDetailsListTest.add(new TaskDetails(5, "Cycle", 10, "mile(s)", "Level:", 5, 5,5,5,5));

        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);

        taskDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                for(int f=0; f<100;f++)
                {
                    TaskDetails clickedList = taskDetailsListTest.get(i);
                    int cId = clickedList.getId();
                    String cTaskName = clickedList.getTaskName();
                    int cRequirmentInteger = clickedList.getTaskRequirementInteger();
                    String cRequirmentString = clickedList.getTaskRequirementString();
                    String cLevelString = clickedList.getTaskLevelString();
                    int cLevelInteger = clickedList.getTaskLevelInteger();
                    int cDays = clickedList.getDays();
                    int cHours = clickedList.getHours();
                    int cMinutes = clickedList.getMinutes();
                    int cSeconds = clickedList.getSeconds();

                    //Toast.makeText(getApplicationContext(), "Clicked = " + cCountDownTimer, Toast.LENGTH_LONG).show();



                    if (cHours == 0 && cMinutes == 0 && cSeconds == 0)
                    {
                        cSeconds = 60;
                        cMinutes = 59;
                        cHours = 23;
                        cDays -= 1;
                    } else if (cMinutes == 0 && cSeconds == 0) {
                        cSeconds = 60;
                        cMinutes = 59;
                        cHours -= 1;
                    } else if (cSeconds == 0) {
                        cSeconds = 60;
                        cMinutes -= 1;
                    }


                    taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelString, cLevelInteger, cDays, cHours, cMinutes, cSeconds - 1));


                    /*
                    if(cRequirmentInteger==0)
                    {
                        taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger+5, cRequirmentString, cLevelString, cLevelInteger+1, cDays, cHours, cMinutes, cSeconds-1));
                    }
                    else
                    {
                        taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger - 1, cRequirmentString, cLevelString, cLevelInteger, cDays, cHours, cMinutes, cSeconds-1));
                    }
                    */

                    adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                    taskDetails.setAdapter(adapter);
                }
            }
        });
    }
}
