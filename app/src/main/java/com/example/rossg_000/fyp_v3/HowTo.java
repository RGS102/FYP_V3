package com.example.rossg_000.fyp_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HowTo extends AppCompatActivity {
    private ListView howToDetails;
    private StretchDetailsAdapter adapter;
    private List<StretchDetails> howToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        final String [] howToInfo = popUpInfo();
        howToDetails = (ListView) findViewById(R.id.HowToListView);
        loadData();
        adapter = new StretchDetailsAdapter(getApplicationContext(), howToList);
        howToDetails.setAdapter(adapter);

        howToDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Intent intent = new Intent(getApplicationContext(), PopUpInfo.class);
                intent.putExtra("details", howToInfo[i]);
                intent.putExtra("ImageFrom", "HowTo");
                intent.putExtra("Position", i);
                startActivity(intent);
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences how to", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(howToList);
        editor.putString("how to list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences how to", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("how to list", null);
        Type type = new TypeToken<ArrayList<StretchDetails>>() {}.getType();
        howToList = gson.fromJson(json, type);

        if(howToList == null)
        {
            howToList = new ArrayList<>();
            howToList.add(new StretchDetails("Page: Homepage"));
            howToList.add(new StretchDetails("Page: Journal"));
            howToList.add(new StretchDetails("Page: Task"));
            howToList.add(new StretchDetails("Page: Stretches"));
            howToList.add(new StretchDetails("Page: Exercise"));
            howToList.add(new StretchDetails("Page: Meditation"));
            howToList.add(new StretchDetails("Page: Rewards"));
            howToList.add(new StretchDetails("Page: FAQ"));
            howToList.add(new StretchDetails("Activity Info"));
            howToList.add(new StretchDetails("Making Progress"));
            howToList.add(new StretchDetails("Levelling Up and Down"));
            howToList.add(new StretchDetails("Activity Attempts"));
            howToList.add(new StretchDetails("Tracked Activities"));
            howToList.add(new StretchDetails("Rewards and Titles"));
            howToList.add(new StretchDetails("Adaptivity"));
            howToList.add(new StretchDetails("Sit Up Detector"));
            howToList.add(new StretchDetails("Step Counter"));
            howToList.add(new StretchDetails("Light Sensor"));
            howToList.add(new StretchDetails("Temperature Sensor"));
            howToList.add(new StretchDetails("Humidity Sensor"));
            howToList.add(new StretchDetails("Send Journal Info via Email"));
        }
    }

    public String[] popUpInfo() {
        String[] popUpInfo = new String[21];
        popUpInfo[0] = "Homepage" +
                "\n\nThe homepage is the first page you see upon starting the app, it provides easy navigation to other pages, as well as allowing you to display your favourite reward title at the top of the screen";

        popUpInfo[1] = "Journal" +
                "\n\nThe journal automatically updates each time you make submit your progress towards completing a goal, it will record things such as the progress you made, the time you spent, if you leveled up or down, and much more";

        popUpInfo[2] = "Task Page" +
                "\n\nEach day the task page will recommend four new tasks for you to complete, two exercises and two meditations, it will also keep track of the amount of tasks you have already completed" +
                "\n\nIf the recommended tasks are not to your liking then you can press the 'New Tasks' button to get a new set of tasks";

        popUpInfo[3] = "Stretches Page" +
                "\n\nThe stretches page provides you with several stretches that can be used during warm ups and cool downs before and after performing tasks" +
                "\n\nTo view more information on each of the stretches simply tap on the name of the stretch you would like to learn more about";

        popUpInfo[4] = "Exercises Page" +
                "\n\nThe exercises page provides you with several exercises for you to carry out" +
                "\n\nEach exercise will have a certain goal, relating to the amount that exercise is performed" +
                "\n\nAchieving a goal will level you up and give you a harder goal, whereas failing your second attempt will level you down and give you an easier goal";

        popUpInfo[5] = "Meditation Page" +
                "\n\nThe meditation page provides you with several meditations for you to carry out" +
                "\n\nEach meditation will have a certain goal, relating to the time spent on that meditation" +
                "\n\nAchieving a goal will level you up and give you a harder goal, whereas failing your second attempt will level you down and give you an easier goal";

        popUpInfo[6] = "Rewards Page" +
                "\n\nThe rewards page keeps track of your progress towards your next reward" +
                "\n\nRewards are acquired upon completing a certain amount of exercises and meditations" +
                "\n\nThe red bars indicate your progress towards the next reward, the display image will change if you have achieved a reward" +
                "\n\nClicking on one of the rewards will set it as your title back on the homepage";

        popUpInfo[7] = "FAQ Page" +
                "\n\nThe FAQ page contains several pieces of information about the app, tap on each topic to learn more about it" +
                "\n\nThe FAQ page can be reached from most pages by pressing the question mark button in the top right corner of the page";

        popUpInfo[8] = "Activity Info" +
                "\n\nOn the exercise, meditation and stretches page you will be able to tap on each activity in order to receive more information about how to carry out that activity" +
                "\n\nInformation will include an image of the activity accompanied by text describing the steps involved" +
                "\n\nScrolling up or down will allow you to see more information on the activity";

        popUpInfo[9] = "Making Progress" +
                "\n\nHolding down on a particular exercise or meditation will allow you to enter in the amount of progress you have made and the time you hae spent on that activity" +
                "\n\nThis information will automatically be stored in the journal along with a few other pieces of information" +
                "\n\nAfter submitting your progress, the goal for that activity will be changed accordingly";

        popUpInfo[10] = "Levelling Up and Down" +
                "\n\nAchieving a goal for a particular activity will cause it to level up and give you a more challenging goal" +
                "\n\nNot achieving a goal after two attempts will cause that activity to level down and give you an easier goal" +
                "\n\nAlways remember, just because you leveled down doesn't mean you have failed, you are simply perfecting your ability to perform at this level before moving onto the next one";

        popUpInfo[11] = "Activity Attempts" +
                "\n\nAfter submitting your progress for an activity your attempts for that activity will increase (if you have not completely achieved the goal and leveled up)" +
                "\n\nHaving not achieved the goal for a particular activity after two attempts will cause that activity to level down and set you an easier goal" +
                "\n\nYour goal will change depending on how much effort you put in on your first attempt (e.g. performing 95% on your first attempt leaves you with the remaining 5% for your second attempt)";

        popUpInfo[12] = "Sensor Tracked Activities" +
                "\n\nSome activities can be automatically tracked through the use of sensors e.g. walking and sit ups";

        popUpInfo[13] = "Rewards and Titles" +
                "\n\nRewards are unlocked after completing a certain number of activities" +
                "\n\nA list of unlocked rewards can be found on the rewards page" +
                "\n\nSimply click (no confirmation required) your favourite reward in the rewards list to set it as your title" +
                "\n\nYour title can be found on the homepage" +
                "\n\nTry to unlock every reward and wear them proudly as your title";

        popUpInfo[14] = "Adaptivity" +
                "\n\nIf you exceed the amount required to complete a goal, the next goal will adapt to this and provide you with a bigger challenge (e.g. current goal is 10 and the next goal is 20, reaching 12 will make the next goal 22)" +
                "\n\nLikewise, leveling down will adapt the difficulty of the lower level goal";

        popUpInfo[15] = "Sit Up Detector" +
                "\n\nTurning on the sit up detector (on the exercise page) will monitor how many sit ups you do and will update info accordingly, Can be simply turned off again by flipping the switch";

        popUpInfo[16] = "Step Counter" +
                "\n\nEvery step you take will be recorded and will update the walking activity automatically";

        popUpInfo[17] = "Light Sensor" +
                "\n\nThe light sensor (on the meditation page) will indicate the current light level, allowing you to adjust your surroundings to experience a tranquil meditation" +
                "\n\nThe display will not appear if the sensor is not supported by your phone";

        popUpInfo[18] = "Temperature Sensor" +
                "\n\nThe temperature sensor (on the meditation page) will indicate the current temperature, allowing you to adjust your surroundings to experience a tranquil meditation" +
                "\n\nThe display will not appear if the sensor is not supported by your phone";

        popUpInfo[19] = "Humidity Sensor" +
                "\n\nThe humidity sensor (on the meditation page) will indicate the current humidity, allowing you to adjust your surroundings to experience a tranquil meditation" +
                "\n\nThe display will not appear if the sensor is not supported by your phone";

        popUpInfo[20] = "Sending Journal Data via Email" +
                "\n\nMainly for beta testing purposes" +
                "\n\nAllows you to send on the journal data (your progress) via email" +
                "\n\nSimply press the 'Send Email' button on the journal page, select the method you would like to use to send the data, then send the data (based on the method you chose)";

        return popUpInfo;
    }
}
