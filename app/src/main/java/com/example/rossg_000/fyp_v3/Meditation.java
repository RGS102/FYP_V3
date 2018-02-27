package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private Sensor temperature;
    private Sensor humidity;
    private Sensor light;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        TextView temperatureTextView = (TextView) findViewById(R.id.TemperatureSensor);
        TextView humidityTextView = (TextView) findViewById(R.id.HumiditySensor);
        TextView lightTextView = (TextView) findViewById(R.id.LightSensor);

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light != null){sensorManager.registerListener(Meditation.this, light, SensorManager.SENSOR_DELAY_NORMAL);}
        else{lightTextView.setText("Light Sensor Not Supported");}

        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(temperature != null){sensorManager.registerListener(Meditation.this, temperature, SensorManager.SENSOR_DELAY_NORMAL);}
        else{temperatureTextView.setText("Temperature Sensor Not Supported");}

        humidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(humidity != null){sensorManager.registerListener(Meditation.this, humidity, SensorManager.SENSOR_DELAY_NORMAL);}
        else{humidityTextView.setText("Humidity Sensor Not Supported");}

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
            meditationDetailsListTest.add(new TaskDetails(27,"Power Nap",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(28,"Muscle Relaxation",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(29,"Body Scan",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(30,"Open Monitoring",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(31,"Focused Attention",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(32,"Walking Meditation",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(33,"Yoga",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(34,"Tai Chi",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(35,"Sleep",8, "hour(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(36,"Mindful Eating",10, "bite(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(37,"Visualisation",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(38,"Mantra Meditation",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(39,"Metta Meditation",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(40,"Self Enquiry",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(41,"Scalp Soother",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(42,"Easy on the Eyes",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(43,"Sinus Pressure Relief",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(44,"Shoulder Tension Relief",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(45,"Listen to Music",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(46,"Reading",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(47,"Effortless Presence",10, "minute(s)",1,1));
            meditationDetailsListTest.add(new TaskDetails(48,"Zen Meditation",10, "minute(s)",1,1));
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
                int[] tempArray = {1,2,3,4,5,6,7,8,9,10};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 27){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 28){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 29){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 30){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 31){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 32){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 33){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 34){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 35){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {8,8,8,9,9,9,10,10,10,11};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 36){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {1,2,3,4,5,6,7,8,9,10};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 37){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 38){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 39){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 40){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 41){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 42){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 43){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 44){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 45){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 46){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 47){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}
        if(cId == 48){
            if(cLevelInteger >= 1 && cLevelInteger <= 10) {
                int[] tempArray = {10,12,14,16,18,20,22,24,26,28};
                cRequirmentInteger = tempArray[cLevelInteger-1]+e;}
            else{cRequirmentInteger = 21+e;}}

        if(levelUpOrDown!=-1) {if (compareValue > cRequirmentInteger) {cRequirmentInteger = compareValue + e;}}

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

        if(newValue <= 0) {
            int temp = newValue*-1;
            int temp2 = excessList.get(i);
            if(temp > temp2) {
                excessList.set(i, temp);
                saveExcess();
            }

            difficultyLevels(clickedList, i, e, +1, progress,duration);
        }
        else {
            if(f>=2) {
                int temp = excessList.get(i);
                if(temp>=2) {temp = temp/2;}
                else{temp = 0;}
                excessList.set(i, temp);
                saveExcess();
                difficultyLevels(clickedList, i, e, -1, progress,duration);
            }
            else {
                passToJournal(a,b,newValue,d,e,f+1,0, progress, duration);
                meditationDetailsListTest.set(i, new TaskDetails(a,b,newValue,d,e,f));
                adapter = new TaskDetailsAdapter(getApplicationContext(), meditationDetailsListTest);
                meditationDetails.setAdapter(adapter);
                saveData();
            }
        }
    }

    public String[] popUpInfo(){
        String[] popUpInfo = new String[23]; //Dont forget to change size of array to match amount of elements in it
        popUpInfo[0] =
                "Deep Breathing" +
                "\n\nSit comfortably with your back straight, one hand on your chest and one on your stomach" +
                "\nBreath in through your nose, the hand on your stomach should rise, the hand on your chest should move very little" +
                "\nExhale as much air as possible out through your mouth, the hand on your stomach should move in, the hand on your chest should move very little" +
                "\nRepeat";    //26

        popUpInfo[1] =
                "Power Nap" +
                "\n\nTaking just a few minutes a day to have a quick nap will be of great benefit"; //27

        popUpInfo[2] =
                "Progressive Muscular Relaxation" +
                "\n\nGet comfortable and perform a few deep breathing exercises" +
                "\nStarting with your right foot, focus on how it feels, then slowly tense its muscles as hard as you can for 10 seconds" +
                "\nRelax your foot, focus on the release of tension and how your foot feels" +
                "\nRemain in a relaxed state for a moment, while performing deep breathing exercises" +
                "\nRepeat with other parts of your body"; //28

        popUpInfo[3] =
                "Body Scan" +
                "\n\nSimilar to progressive muscular relaxation, but instead of tensing your muscles you simply you simply focus on how each part of your body feels individually" +
                "\n\nRefrain from labelling these feelings as 'good' or 'bad'"; //29

        popUpInfo[4] =
                "Open Monitoring Meditation" +
                "\n\nThroughout your meditation monitor all aspects of your experience, without judgment or attachment" +
                "\n\nRecognise all forms of perception, internal (thoughts, memory, feeling, etc) or external (sound, smell, etc)" +
                "\n\nAttempt to monitor these things without response, and simply experience them on a moment to moment basis";    //30

        popUpInfo[5] =
                "Focused Attention Meditation" +
                "\n\nFocus your attention entirely on a single object throughout your meditation" +
                "\n\nThis object can be your breath, a part of your body, a mantra, a visualisation, as well as many other things" +
                "\n\nAim to ignore distractions and maintain your focus";  //31

        popUpInfo[6] =
                "Walking Meditation" +
                "\n\nFocus on the feeling of each step as it touches the ground, the rhythm your breathing and the sensation caused by the environment around you"; //32

        popUpInfo[7] =
                "Yoga" +
                "\n\nInvolves performing a series of poses as well as deep breathing" +
                "\n\nMany examples can be easily found"; //33

        popUpInfo[8] =
                "Tai Chi" +
                "\n\nInvolves performing slow moving body movements as well as deep breathing" +
                "\n\nFocus your mind on your bodily movements and your breathing"; //34

        popUpInfo[9] =
                "Sleep" +
                "\n\nThe body and mind can not function properly with inadequate levels of sleep"; //35

        popUpInfo[10] =
                "Mindful Eating" +
                "\n\nEat slowly and concentrate on every single bite you take"; //36

        popUpInfo[11] =
                "Visualisation" +
                "\n\nImagine a scene where you feel at most peace and calm";    //37

        popUpInfo[12] =
                "Mantra Meditation" +
                "\n\nThroughout your meditation repeat a simple word or phrase with no meaning" +
                "\n\nRepeat the mantra either within your mind or gently whisper it";    //38

        popUpInfo[13] =
                "Metta Meditation" +
                "\n\nWhile meditating with closed eyes, generate both in your mind and in your heart feelings of kindness and benevolence" +
                "\n\nBegin by generating these feelings about yourself, then a close friend, someone you know, someone who is difficult, all four of them equally, and finally the universe";    //39

        popUpInfo[14] =
                "Self enquiry" +
                "\n\nAsk yourself 'Who am I?', then question what this 'I' is" +
                "\n\nReject any verbal answers and delve deeper, focusing on your feelings of 'I'" +
                "\n\nReveal to yourself your true 'I'"; //40

        popUpInfo[15] =
                "Scalp Massage" +
                "\n\nPlace your thumb behind your ears, with your fingers on top of your head" +
                "\n\nMove your fingers in circular motions, moving your scalp back and forth slightly" +
                "\n\nRepeat for 20 seconds";    //41

        popUpInfo[16] =
                "Eye Soother" +
                "\n\nWith closed eyes and your ring finger directly under your eyebrow near the bridge of your nose" +
                "\n\nSlowly increase pressure pressure for 10 seconds";

        popUpInfo[17] =
                "Sinus Relief" +
                "\n\nWith your fingertips on the bridge of your nose, slowly slide them outwards across the top of your cheekbones to the outside of your eyes";

        popUpInfo[18] =
                "Shoulder Tension Relief" +
                "\n\nReach one arm over to the opposite shoulder" +
                "\n\nPress firmly and make a circular motion on the muscle above your shoulder blade" +
                "\n\nRepeat on the other side";

        popUpInfo[19] =
                "Listen to Music" +
                "\n\nAllow your mind to focus primarily on the music";

        popUpInfo[20] =
                "Read" +
                "\n\nAllow yourself to become lost among the words on each and every page";    //46

        popUpInfo[21] =
                "Effortless Presence" +
                "\n\nMeditation without focus or attention" +
                "\n\nSimply embrace the quiet";

        popUpInfo[22] =
                "Zen Meditation" +
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
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            TextView lightSensor = (TextView) findViewById(R.id.LightSensor);
            if(sensorEvent.values[0] >= 0 && sensorEvent.values[0] < 50){lightSensor.setText("Too Dark");}
            else if(sensorEvent.values[0] >= 50 && sensorEvent.values[0] < 100){lightSensor.setText("Dark");}
            else if(sensorEvent.values[0] >= 100 && sensorEvent.values[0] < 150){lightSensor.setText("Tranquil");}
            else if(sensorEvent.values[0] >= 150 && sensorEvent.values[0] < 250){lightSensor.setText("Bright");}
            else if(sensorEvent.values[0] >= 250){lightSensor.setText("Too Bright");}
            else{lightSensor.setText("Error");}
        }

        else if(sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            TextView temperatureTextView = (TextView) findViewById(R.id.TemperatureSensor);
            if(sensorEvent.values[0] <= 0){temperatureTextView.setText("Too Cold");}
            else if(sensorEvent.values[0] >= 1 && sensorEvent.values[0] < 20){temperatureTextView.setText("Cold");}
            else if(sensorEvent.values[0] >= 20 && sensorEvent.values[0] < 26){temperatureTextView.setText("Perfect 20-25 C");}
            else if(sensorEvent.values[0] >= 26 && sensorEvent.values[0] < 46){temperatureTextView.setText("Warm");}
            else if(sensorEvent.values[0] >= 46){temperatureTextView.setText("Too Warm");}
            else{temperatureTextView.setText("Error");}
        }

        else if(sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            TextView humidityTextView = (TextView) findViewById(R.id.HumiditySensor);
            if(sensorEvent.values[0] >= 0 && sensorEvent.values[0] < 10){humidityTextView.setText("Humidity: 0-9%  Too Low");}
            else if(sensorEvent.values[0] >= 10 && sensorEvent.values[0] < 19){humidityTextView.setText("Humidity: 10-19%  Too Low");}
            else if(sensorEvent.values[0] >= 20 && sensorEvent.values[0] < 29){humidityTextView.setText("Humidity: 20-29%  Increase");}
            else if(sensorEvent.values[0] >= 30 && sensorEvent.values[0] < 39){humidityTextView.setText("Humidity: 30-39%  Increase");}
            else if(sensorEvent.values[0] >= 40 && sensorEvent.values[0] < 49){humidityTextView.setText("Humidity: 40-49%  Perfect");}
            else if(sensorEvent.values[0] >= 50 && sensorEvent.values[0] < 59){humidityTextView.setText("Humidity: 50-59%  Lower");}
            else if(sensorEvent.values[0] >= 60 && sensorEvent.values[0] < 69){humidityTextView.setText("Humidity: 60-69%  Lower");}
            else if(sensorEvent.values[0] >= 70 && sensorEvent.values[0] < 79){humidityTextView.setText("Humidity: 70-79%  Lower");}
            else if(sensorEvent.values[0] >= 80 && sensorEvent.values[0] < 89){humidityTextView.setText("Humidity: 80-89%  Lower");}
            else if(sensorEvent.values[0] >= 90 && sensorEvent.values[0] < 99){humidityTextView.setText("Humidity: 90-99%  Too High");}
            else if(sensorEvent.values[0] == 100){humidityTextView.setText("Humidity: 100%  Too High");}
            else {humidityTextView.setText("Error");}
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}
}
