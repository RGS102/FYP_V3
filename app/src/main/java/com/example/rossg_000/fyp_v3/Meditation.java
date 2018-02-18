package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Meditation extends AppCompatActivity implements SensorEventListener{
    public static final int REQUEST_CODE_COMPLETE_OR_FAIL = 101;
    private ListView meditationDetails;
    private TaskDetailsAdapter adapter;
    private List<TaskDetails> meditationDetailsListTest;
    private int dataTest = 0;
    private int compareValue = 0;
    private List<Integer> excessList;
    private GestureDetectorCompat gestureDetectorCompat;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        gestureDetectorCompat = new GestureDetectorCompat(this, new Meditation.Gesture());
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        final String [] meditationInfo = popUpInfo();
        meditationDetails = (ListView) findViewById(R.id.MeditationListView);
        loadData();
        adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
        meditationDetails.setAdapter(adapter);

        meditationDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", meditationInfo[i]);
                startActivity(intent);
            }
        });

        meditationDetails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataTest = i;
                final Intent intent2 = new Intent(getApplicationContext(), CompleteOrFail.class);
                startActivityForResult(intent2, REQUEST_CODE_COMPLETE_OR_FAIL);
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
                    TaskDetails clickedList = meditationDetailsListTest.get(dataTest);
                    int cLevelInteger = clickedList.getTaskLevelInteger();
                    int cAttempts = clickedList.getAttempts();
                    int progressMade = data.getIntExtra("Progress", 0);
                    int duration = data.getIntExtra("Duration", 0);

                    if(CompleteOrFail == +1){if(progressMade > 0) {progressUpdate(clickedList, dataTest, progressMade, duration);}}
                }}}

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(meditationDetailsListTest);
        editor.putString("meditation list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meditation list", null);
        Type type = new TypeToken<ArrayList<TaskDetails>>() {}.getType();
        meditationDetailsListTest = gson.fromJson(json, type);

        if(meditationDetailsListTest == null){
            meditationDetailsListTest = new ArrayList<>();

            meditationDetailsListTest.add(new TaskDetails(26,"Deep Breathing",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(27,"Power Nap",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(28,"Muscle Relaxation",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(29,"Body Scan",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(30,"Open Monitoring",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(31,"Focused Attention",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(32,"Walking Meditation",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(33,"Yoga",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(34,"Tai Chi",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(35,"Sleep",1, "hour(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(36,"Mindful Eating",1, "times",1,1));
            meditationDetailsListTest.add(new TaskDetails(37,"Visualisation",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(38,"Mantra Meditation",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(39,"Metta Meditation",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(40,"Self Enquiry",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(41,"Scalp Soother",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(42,"Easy on the Eyes",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(43,"Sinus Pressure Relief",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(44,"Shoulder Tension Relief",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(45,"Listen to Music",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(46,"Reading",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(47,"Effortless Presence",1, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(48,"Zen Meditation",1, "minute(s)",1,1));
        }
    }

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

        if(cId == 26){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 27){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 28){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 29){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 30){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 31){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 32){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 33){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 34){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 35){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 36){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 37){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 38){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 39){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 40){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 41){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 42){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 43){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 44){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 45){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 46){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 47){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 48){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {11,12,13,14,15,16,17,18,19,20};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}

        if(levelUpOrDown!=-1) {
            if (compareValue > cRequirmentInteger) {
                cRequirmentInteger = compareValue + e;
            }
        }

        int meditationsCompleted = MainActivity.getMeditationsCompleted();
        int tasksCompleted = MainActivity.getTasksCompleted();

        if(levelUpOrDown!=-1){if(compareValue > cRequirmentInteger){cRequirmentInteger = compareValue + e;}}
        if(levelUpOrDown==+1){cAttempts=1; meditationsCompleted+=1; tasksCompleted +=1;}
        if(levelUpOrDown==-1){cAttempts=1;}

        MainActivity.setMeditationsCompleted(meditationsCompleted);
        MainActivity.setTasksCompleted(tasksCompleted);

        meditationDetailsListTest.set(i, new TaskDetails(cId, cTaskName, cRequirmentInteger, cRequirmentString, cLevelInteger, cAttempts));
        adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
        meditationDetails.setAdapter(adapter);
        saveData();
    }

    private void progressUpdate(TaskDetails clickedList, int i, int progress, int duration){
        int a = clickedList.getId();
        String b = clickedList.getTaskName();
        int c = clickedList.getTaskRequirementInteger();
        String d = clickedList.getTaskRequirementString();
        int e = clickedList.getTaskLevelInteger();
        int f = clickedList.getAttempts();
        int newValue = c - progress;
        compareValue = progress;
        meditationDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f));
        adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
        meditationDetails.setAdapter(adapter);
        saveData();
        loadExcess();

        if(newValue <= 0)
        {
            int temp = newValue*-1;
            int temp2 = excessList.get(i);
            if(temp > temp2)
            {
                excessList.set(i, temp);
                saveExcess();
            }

            difficultyLevels(clickedList, i, e, +1, progress,duration);
        }
        else
        {
            if(f>=2)
            {
                int temp = excessList.get(i);
                if(temp>=2) {temp = temp/2;}
                else{temp = 0;}
                excessList.set(i, temp);
                saveExcess();
                difficultyLevels(clickedList, i, e, -1, progress,duration);
            }
            else
            {
                passToJournal(a,b,newValue,d,e,f+1,0, progress, duration);
                meditationDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f));
                adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
                meditationDetails.setAdapter(adapter);
                saveData();
            }
        }

    }

    public String[] popUpInfo(){
        //Fill this in later, position in array should correspond to position of list view
        String[] popUpInfo = new String[23]; //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] =
                "\n\nDeep Breathing" +
                "\n\nSit comfortably with your back straight, one hand on your chest and one on your stomach" +
                "\nBreath in through your nose, the hand on your stomach should rise, the hand on your chest should move very little" +
                "\nExhale as much air as possible out through your mouth, the hand on your stomach should move in, the hand on your chest should move very little" +
                "\nRepeat";    //26

        popUpInfo[1] =
                "\n\nPower Nap" +
                "\n\nTaking just a few minutes a day to have a quick nap will be of great benefit"; //27

        popUpInfo[2] =
                "\n\nProgressive Muscular Relaxation" +
                "\n\nGet comfortable and perform a few deep breathing exercises" +
                "\nStarting with your right foot, focus on how it feels, then slowly tense its muscles as hard as you can for 10 seconds" +
                "\nRelax your foot, focus on the release of tension and how your foot feels" +
                "\nRemain in a relaxed state for a moment, while performing deep breathing exercises" +
                "\nRepeat with other parts of your body"; //28

        popUpInfo[3] =
                "\n\nBody Scan" +
                "\n\nSimilar to progressive muscular relaxation, but instead of tensing your muscles you simply you simply focus on how each part of your body feels individually" +
                "\n\nRefrain from labelling these feelings as 'good' or 'bad'"; //29

        popUpInfo[4] =
                "\n\nOpen Monitoring Meditation" +
                "\n\nThroughout your meditation monitor all aspects of your experience, without judgment or attachment" +
                "\n\nRecognise all forms of perception, internal (thoughts, memory, feeling, etc) or external (sound, smell, etc)" +
                "\n\nAttempt to monitor these things without response, and simply experience them on a moment to moment basis";    //30

        popUpInfo[5] =
                "\n\nFocused Attention Meditation" +
                "\n\nFocus your attention entirely on a single object throughout your meditation" +
                "\n\nThis object can be your breath, a part of your body, a mantra, a visualisation, as well as many other things" +
                "\n\nAim to ignore distractions and maintain your focus";  //31

        popUpInfo[6] =
                "\n\nWalking Meditation" +
                "\n\nFocus on the feeling of each step as it touches the ground, the rhythm your breathing and the sensation caused by the environment around you"; //32

        popUpInfo[7] =
                "\n\nYoga" +
                "\n\nInvolves performing a series of poses as well as deep breathing" +
                "\n\nMany examples can be easily found"; //33

        popUpInfo[8] =
                "\n\nTai Chi" +
                "\n\nInvolves performing slow moving body movements as well as deep breathing" +
                "\n\nFocus your mind on your bodily movements and your breathing"; //34

        popUpInfo[9] =
                "\n\nSleep" +
                "\n\nThe body and mind can not function properly with inadequate levels of sleep"; //35

        popUpInfo[10] =
                "\n\nMindful Eating" +
                "\n\nEat slowly and concentrate on every single bite you take"; //36

        popUpInfo[11] =
                "\n\nVisualisation" +
                "\n\nImagine a scene where you feel at most peace and calm";    //37

        popUpInfo[12] =
                "\n\nMantra Meditation" +
                "\n\nThroughout your meditation repeat a simple word or phrase with no meaning" +
                "\n\nRepeat the mantra either within your mind or gently whisper it";    //38

        popUpInfo[13] =
                "\n\nMetta Meditation" +
                "\n\nWhile meditating with closed eyes, generate both in your mind and in your heart feelings of kindness and benevolence" +
                "\n\nBegin by generating these feelings about yourself, then a close friend, someone you know, someone who is difficult, all four of them equally, and finally the universe";    //39

        popUpInfo[14] =
                "\n\nSelf enquiry" +
                "\n\nAsk yourself 'Who am I?', then question what this 'I' is" +
                "\n\nReject any verbal answers and delve deeper, focusing on your feelings of 'I'" +
                "\n\nReveal to yourself your true 'I'"; //40

        popUpInfo[15] =
                "\n\nScalp Massage" +
                "\n\nPlace your thumb behind your ears, with your fingers on top of your head" +
                "\n\nMove your fingers in circular motions, moving your scalp back and forth slightly" +
                "\n\nRepeat for 20 seconds";    //41

        popUpInfo[16] =
                "\n\nEye Soother" +
                "\n\nWith closed eyes and your ring finger directly under your eyebrow near the bridge of your nose" +
                "\n\nSlowly increase pressure pressure for 10 seconds";

        popUpInfo[17] =
                "\n\nSinus Relief" +
                "\n\nWith your fingertips on the bridge of your nose, slowly slide them outwards across the top of your cheekbones to the outside of your eyes";

        popUpInfo[18] =
                "\n\nShoulder Tension Relief" +
                "\n\nReach one arm over to the opposite shoulder" +
                "\n\nPress firmly and make a circular motion on the muscle above your shoulder blade" +
                "\n\nRepeat on the other side";

        popUpInfo[19] =
                "\n\nListen to Music" +
                "\n\nAllow your mind to focus primarily on the music";

        popUpInfo[20] =
                "\n\nRead" +
                "\n\nAllow yourself to become lost among the words on each and every page";    //46

        popUpInfo[21] =
                "\n\nEffortless Presence" +
                "\n\nMeditation without focus or attention" +
                "\n\nSimply embrace the quiet";

        popUpInfo[22] =
                "\n\nZen Meditation" +
                "\n\nWhile seated on the floor with your legs crossed and your back straight" +
                "\n\nWith your mouth closed and eyes lowered, focus your gaze on the ground a few feet in front of you" +
                "\n\nFocus on your breathing";

        return popUpInfo;
    }

    public void passToJournal(int ID, String TaskName, int RequirmentInteger, String RequirmentString, int level, int attempts, int upOrDown, int progress, int duration) {
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
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);

            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);

            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);

            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);

            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
            excessList.add(0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            TextView lightSensor = (TextView) findViewById(R.id.LightSensor);
            if(sensorEvent.values[0] >= 0 && sensorEvent.values[0] < 50){
                lightSensor.setText("Too Dark " + sensorEvent.values[0]);
            }
            if(sensorEvent.values[0] >= 50 && sensorEvent.values[0] < 100){
                lightSensor.setText("Dark " + sensorEvent.values[0]);
            }
            if(sensorEvent.values[0] >= 100 && sensorEvent.values[0] < 150){
                lightSensor.setText("Medium " + sensorEvent.values[0]);
            }
            if(sensorEvent.values[0] >= 150 && sensorEvent.values[0] < 200){
                lightSensor.setText("Bright " + sensorEvent.values[0]);
            }
            if(sensorEvent.values[0] >= 200 && sensorEvent.values[0] < 250){
                lightSensor.setText("Too Bright " + sensorEvent.values[0]);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class Gesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e2.getX() > e1.getX()){
                Intent intent = new Intent(Meditation.this, Stretches.class);
                startActivity(intent);
            }
            if(e2.getX() < e1.getX()){
                Intent intent = new Intent(Meditation.this, Exercise.class);
                startActivity(intent);

            }

            return true;
            //return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
