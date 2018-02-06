package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class Exercise extends AppCompatActivity implements SensorEventListener {
    public static final int REQUEST_CODE_COMPLETE_OR_FAIL = 101;
    private ListView taskDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> taskDetailsListTest;
    private int dataTest = 0;
    private int compareValue = 0;
    SensorManager sensorManager;    //To do with the step count sensor, might change later
    boolean running = false;    //To do with the step count sensor, might change later
    private static int taskCompleted = 0;
    private List<Integer> excessList;

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //If adding more exercises change: loadData, difficultyLevels, popUpInfo, loadExcess
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        final String [] taskInfo = popUpInfo();
        taskDetails = (ListView) findViewById(R.id.taskListView);
        loadData();
        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);

        //stepCounter = (TextView) findViewById(R.id.ExerciseTextView);   //TESTING PURPOSE!!!!!!!!!!!!!!!!!!!!!!!!
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);   //To do with the step count sensor, might change later

        taskDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", taskInfo[i]);
                startActivity(intent);


                loadExcess();
                Toast.makeText(Exercise.this, "Excess: " +excessList.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        taskDetails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0) {
                    dataTest = i;
                    final Intent intent2 = new Intent(getApplicationContext(), CompleteOrFail.class);
                    startActivityForResult(intent2, REQUEST_CODE_COMPLETE_OR_FAIL);
                }
                    return true;

            }
        });
        saveData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_CODE_COMPLETE_OR_FAIL:
                if(resultCode== Activity.RESULT_OK) {
                    int CompleteOrFail = com.example.rossg_000.fyp_v3.CompleteOrFail.getResult(data);
                    TaskDetails clickedList = taskDetailsListTest.get(dataTest);

                    int cLevelInteger = clickedList.getTaskLevelInteger();
                    int cAttempts = clickedList.getAttempts();
                    int progressMade = data.getIntExtra("Progress", 0);
                    int duration = data.getIntExtra("Duration", 0);

                    if(CompleteOrFail == +1)
                    {
                        if(progressMade > 0) {progressUpdate(clickedList, dataTest, progressMade, duration);}
                    }
                }
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(taskDetailsListTest);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<TaskDetails>>() {}.getType();
        taskDetailsListTest = gson.fromJson(json, type);

        if(taskDetailsListTest == null){
            taskDetailsListTest = new ArrayList<>();
            taskDetailsListTest.add(new TaskDetails(1, "Walk" , 500, "step(s)"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(2, "Run"  , 11, "mile(s)"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(3, "Jog"  , 11, "mile(s)"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(4, "Swim" , 11, "length(s)",  1, 1));
            taskDetailsListTest.add(new TaskDetails(5, "Cycle", 11, "mile(s)"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(6, "Push Ups:", 11, "push up(s)",  1, 1));
            taskDetailsListTest.add(new TaskDetails(7, "Sit Ups:" , 11, "sit up(s)",  1, 1));
            taskDetailsListTest.add(new TaskDetails(8, "Crunches:"  , 11, "crunches(s)",  1, 1));
            taskDetailsListTest.add(new TaskDetails(9, "Squats:"  , 11, "squats(s)",  1, 1));
            taskDetailsListTest.add(new TaskDetails(10, "Superman" , 11, "times"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(11, "Tuck Jump"  , 11, "times"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(12, "Prone Walkout"  , 11, "times"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(13, "Burpees" , 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(14, "Plank", 11, "times"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(15, "Wall Sit", 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(16, "Lunge" , 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(17, "Clock Lunge"  , 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(18, "Single Leg Deadlift"  , 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(19, "Step-Up" , 11, "times"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(20, "Calf Raise"  , 11, "times"  , 1, 1));
            taskDetailsListTest.add(new TaskDetails(21, "Tricep Dip"  , 11, "times"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(22, "Boxer" , 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(23, "Flutter Kicks", 11, "times"  ,  1, 1));
            taskDetailsListTest.add(new TaskDetails(24, "Shoulder Bridge", 11, "times",  1, 1));
            taskDetailsListTest.add(new TaskDetails(25, "Sprinter Sit Up" , 11, "times",  1, 1));
        }}

    private void progressUpdate(TaskDetails clickedList, int i, int progress, int duration){
        int a = clickedList.getId();
        String b = clickedList.getTaskName();
        int c = clickedList.getTaskRequirementInteger();
        String d = clickedList.getTaskRequirementString();
        int e = clickedList.getTaskLevelInteger();
        int f = clickedList.getAttempts();
        int newValue = c - progress;
        compareValue = progress;

        taskDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f));



        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);
        saveData();
        loadExcess();

        if(newValue <= 0) {
            int temp = newValue*-1;
            int temp2 = excessList.get(i);
            if(temp > temp2) {
                excessList.set(i, temp);
                saveExcess();}
            difficultyLevels(clickedList, i, e, +1, progress,duration);}
        else {
            if(f>=2) {
                int temp = excessList.get(i);
                if(temp>=2) {temp = temp/2;}
                else{temp = 0;}
                excessList.set(i, temp);

                saveExcess();
                difficultyLevels(clickedList, i, e, -1, progress,duration);}
            else {
                passToJournal(a,b,newValue,d,e,f,0, progress, duration);
                taskDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f+1));
                adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
                taskDetails.setAdapter(adapter);
                saveData();}}}

    private void difficultyLevels(TaskDetails clickedList, int i, int cLevelInteger, int levelUpOrDown, int progress, int duration){
        int cId = clickedList.getId();
        String cTaskName = clickedList.getTaskName();
        int cRequirmentInteger = clickedList.getTaskRequirementInteger();
        String cRequirmentString = clickedList.getTaskRequirementString();
        int cAttempts = clickedList.getAttempts();
        passToJournal(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts, levelUpOrDown, progress, duration);
        cLevelInteger = cLevelInteger + levelUpOrDown;

        if(cLevelInteger<1){cLevelInteger = 1;}
        loadExcess();
        int e = excessList.get(i);

        if(cId == 1){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400};
                cRequirmentInteger = tempArray[cLevelInteger-1];}
            else{cRequirmentInteger = 1400;}}
        if(cId == 2){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 3){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 4){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 5){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 6){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 7){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 8){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 9){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 10){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 11){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 12){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 13){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 14){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 15){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 16){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 17){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 18){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 19){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 20){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 21){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 22){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 23){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 24){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 25){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}

        if(levelUpOrDown!=-1){
            if(compareValue > cRequirmentInteger){cRequirmentInteger = compareValue + e;}
        }
        if(levelUpOrDown==+1){taskCompleted += 1; cAttempts=1;}
        if(levelUpOrDown==-1){cAttempts=1;}

        taskDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts));


        //To put a completed task at the second position of the arraylist, need to fix popupinfo
        //Collections.swap(taskDetailsListTest, i,1);
        //Collections.swap(excessList, i, 1);
        //saveExcess();
        //String temp = popUpInfo[i];

        adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
        taskDetails.setAdapter(adapter);
        saveData();
    }

    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[25]; //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] = "Walk:" +
                "\n\nPut one foot in front of the other";
        popUpInfo[1] = "Run:" +
                "\n\nMove at a faster speed than a walk" +
                "\n\nAim to never have both or either foot on the ground at the same time";
        popUpInfo[2] = "Jog:" +
                "\n\nRun at a steady gentle pace";
        popUpInfo[3] = "Swim:" +
                "\n\nPropel body through water" +
                "\n\nStyle of swimming depends on preference";
        popUpInfo[4] = "Cycle:" +
                "\n\nRide a bicycle";
        popUpInfo[5] = "Push Ups:" +
                "\n\nStarting Position: Kneel with hands flat on the floor, feet together, and shoulders directly above your hands" +
                "\n\nDownward phase: slowly lower your body until your chest/chin touches the floor" +
                "\n\nUpward phase: Press up through your arms, while maintaining a rigid torso and keep your head aligned with your spine";
        popUpInfo[6] = "Sit Ups:" +
                "\n\nStarting position: Lie on floor with bent knees, with feet shoulder width apart and flat on floor, with your hands crossed on chest/behind head/at ears/at side, head tucked forward" +
                "\n\nUpward phase: Curl your body upwards off the floor to an upright semi-seated position, exhale as you rise" +
                "\n\nDownward phase: Return to starting position, inhaling as you do so";
        popUpInfo[7] = "Crunches:" +
                "\n\nStarting position: Lie on floor with bent knees, with feet shoulder width apart and flat on floor, arms crossed on chest or hands lightly at ears" +
                "\n\nUpward phase: Raise only your head and shoulders from the floor to feel the abdominal muscles contract, exhale while rising" +
                "\n\nAvoid pulling or flexing your head foreward" +
                "\n\nDownward phase: Return to starting position while inhaling";
        popUpInfo[8] = "Squats:" +
                "\n\nStarting position: Stand with feet hip width apart, arms down by your side, and a staight posture" +
                "\n\nDownward phase: Shift hips backwards, bend your knees and descend until your heels feel as if they are about to lift off the floor" +
                "\n\nUpwards phase: Return to starting position";
        popUpInfo[9] = "Supermans" +
                "\n\nStarting position: Lie face down with both arms and legs" +
                "\n\nUpward phase: While keeping your torso as still as possible, simultaneously raise both your arms and legs to form a small curve in your body, and hold for a few seconds" +
                "\n\nDownward phase: Slowly return to starting position";
        popUpInfo[10] = "Tuck Jumps" +
                "\n\nStarting position: Stand with knees slightly bent" +
                "\n\nUpward phase: Jump up and bring your knees in towards your chest, while extending your arms out straight" +
                "\n\nDownward phase: Land with knees slightly bent and repeat";
        popUpInfo[11] = "Prone Walkout" +
                "\n\nStarting position: Begin on all fours" +
                "\n\nOutward phase: Slowly walk your hands out forward, while staying on your toes and not moving them" +
                "\n\nInward phase: Slowly walk your hands back in to the starting position";
        popUpInfo[12] = "Burpees" +
                "\n\nStarting position: Begin in a low squat position with your hands on the floor" +
                "\n\nKick your feet back into a push up position and perform one push up" +
                "\n\nReturn to starting position" +
                "\n\nJump up as high as possible" +
                "\n\nReturn to starting position and repeat";
        popUpInfo[13] = "Plank" +
                "\n\nStarting position: Lie face down with your forearms on the floor and your hands clasped" +
                "\n\nExtend your legs behind your body and rise up on your toes" +
                "\n\nMaintain a straight back, tighten your core, and hold the position";
        popUpInfo[14] = "Wall Sit" +
                "\n\nRest your back against a wall" +
                "\n\nEnsure your thighs parallel to the ground, your knees are directly above your ankles and your back is straight" +
                "\n\nHold for 60 second or until your legs start to ache";
        popUpInfo[15] = "Lunge" +
                "\n\nStarting position: Standing, hands on hips and feet hip width apart" +
                "\n\nWith one leg step out forward, slowly lowering your body until the knee on your opposite leg is close to touching the floor and bent at least 90 degrees" +
                "\n\nReturn to starting position" +
                "\n\nRepeat with opposite leg";
        popUpInfo[16] = "Clock Lunge" +
                "\n\nSimilar to ordinary lunges" +
                "\n\nAfter each lunge take a big step to the right and perform another lunge, continue to do so until you have turned a complete 360" +
                "\n\nAfter a complete circle repeat with the opposite leg";
        popUpInfo[17] = "Single Leg Deadlift" +
                "\n\nStarting position: Stand up straight with feet together" +
                "\n\nBend your right leg slightly upwards, lower your arms and torso while raising your right leg behind your body. Keep your left knee slightly bent and reach with your arms as close as you can to the floor" +
                "\n\nRaise your torso while lowering your right leg" +
                "\n\nRepeat with your left leg";
        popUpInfo[18] = "Step-Up" +
                "\n\nMaking use of a step or bench, place your right foot on the elevated surface" +
                "\n\nStep up until your right leg is straight, then return to the start" +
                "\n\nRepeat with your other leg";
        popUpInfo[19] = "Calf Raise" +
                "\n\nStarting position: stand up straight" +
                "\n\nSlowly rise up on your toes, make sure your knees remain straight and your heels are off the floor" +
                "\n\nHold for several seconds then return to starting position";
        popUpInfo[20] = "Tricep Dip" +
                "\n\nStarting position: sit on the floor, with your knees bent, near a slightly elevated surface (step or bench), grab the edge of this elevated surface and straighten your arms" +
                "\n\nBend your arms to a 90 degree angle, and straighten them again while your heels push towards the floor";
        popUpInfo[21] = "Boxer" +
                "\n\nStarting position: Feet hip-width apart and knees bent" +
                "\n\nKeep elbows in and extend one arm forward and the other arm back" +
                "\n\nHug the arm back in and switch arms";
        popUpInfo[22] = "Flutter Kicks" +
                "\n\nStarting position: Lie on back, arms by side, palms faced down, with your legs extended out straigth" +
                "\n\nLift your heels off the floor and make quick, small up and down movement with your legs" +
                "\n\nAim to keep your core engaged";
        popUpInfo[23] = "Shoulder Bridge" +
                "\n\nStarting position: Lie on back, knees bent, feet hip width apart, with arms at your side" +
                "\n\nLift up your spine and hips, ensuring that the only parts of your body touching the ground are your head, feet, arms, and shoulders" +
                "\n\nLift one leg upwards, keep your core tight, then slowly bring it back down, then back up, repeat this several times for each leg" +
                "\n\nBring your knees into place and bring your spine back down onto the floor";
        popUpInfo[24] = "Sprinter Sit-Up" +
                "\n\nStarting position: Lie on back, legs straight, arms by side, elbows bent at 90 degree angle" +
                "\n\nSit up, bring your left knee towards your right elbow" +
                "\n\nLower your body and repeat on the other side";

        return popUpInfo;
    }

    @Override   //To do with the step count sensor, might change later
    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();}
    }

    @Override   //To do with the step count sensor, might change later
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(running){
            //stepCounter.setText(String.valueOf(sensorEvent.values[0])); //TESTING PURPOSE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            TaskDetails a = taskDetailsListTest.get(0);
            taskDetailsListTest.set(0,new TaskDetails(a.getId(), a.getTaskName(),a.getTaskRequirementInteger()-1,a.getTaskRequirementString(),/*a.getTaskLevelString(),*/a.getTaskLevelInteger(),a.getAttempts()));
            if(a.getTaskRequirementInteger()==0) {difficultyLevels(a,0, a.getTaskLevelInteger(),1, 0, 0);}
            adapter = new TaskDetailsAdapter(getApplicationContext(), taskDetailsListTest);
            taskDetails.setAdapter(adapter);
            saveData();
        }}

    @Override   //To do with the step count sensor, might change later
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void passToJournal(int ID, String TaskName, int RequirmentInteger, String RequirmentString, int level, int attempts, int upOrDown, int progress, int duration){
        Intent passInfoToJournal = new Intent(this, Journal.class);
        passInfoToJournal.putExtra("ID", ID);
        passInfoToJournal.putExtra("TaskName", TaskName);
        passInfoToJournal.putExtra("RequirmentInteger", RequirmentInteger);
        passInfoToJournal.putExtra("RequirementString", RequirmentString);
        passInfoToJournal.putExtra("level", level);
        passInfoToJournal.putExtra("attempts", attempts);
        passInfoToJournal.putExtra("upOrDown", upOrDown);
        passInfoToJournal.putExtra("progress", progress);
        passInfoToJournal.putExtra("duration", duration);

        startActivity(passInfoToJournal);
    }

    public static int getTaskCompleted(){
        return taskCompleted;
    }

    private void saveExcess(){
        SharedPreferences sharedPreferences = getSharedPreferences("shareTest", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(excessList);
        editor.putString("excess list", json);
        editor.apply();
    }

    private void loadExcess(){
        SharedPreferences sharedPreferences = getSharedPreferences("shareTest", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("excess list", null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
        excessList = gson.fromJson(json, type);

        if(excessList == null){
            excessList = new ArrayList<>();
            excessList.add(0);  //Walk
            excessList.add(0);  //Run
            excessList.add(0);  //Jog
            excessList.add(0);  //Swim
            excessList.add(0);  //Cycle
            excessList.add(0);  //Push ups
            excessList.add(0);  //Sit ups
            excessList.add(0);  //Crunches
            excessList.add(0);  //Squats
            excessList.add(0);  //10
            excessList.add(0);  //11
            excessList.add(0);  //12
            excessList.add(0);  //13
            excessList.add(0);  //14
            excessList.add(0);  //15
            excessList.add(0);  //16
            excessList.add(0);  //17
            excessList.add(0);  //18
            excessList.add(0);  //19
            excessList.add(0);  //20
            excessList.add(0);  //21
            excessList.add(0);  //22
            excessList.add(0);  //23
            excessList.add(0);  //24
            excessList.add(0);  //25
        }

    }






}

