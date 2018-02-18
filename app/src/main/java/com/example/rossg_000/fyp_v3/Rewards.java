package com.example.rossg_000.fyp_v3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Rewards extends AppCompatActivity {
    private ListView rewardDetails;
    private RewardsDetailsAdapter adapter;
    private List<String> rewardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rewardDetails = (ListView) findViewById(R.id.RewardsListView);
        loadData();
        adapter = new RewardsDetailsAdapter(getApplicationContext(), rewardList);
        rewardDetails.setAdapter(adapter);

        rewardDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String title = rewardList.get(i);
                MainActivity.setTitleDisplay(title);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int i = MainActivity.getExercisesCompleted();
        int j = MainActivity.getMeditationsCompleted();
        int k = MainActivity.getTasksCompleted();

        updateProgressBars();
        unlockRewards(k,i,j);
    }


    private void unlockRewards(int task, int exe, int med){
        loadData();

        int exeRewards = MainActivity.getExerciseRewardsEarned();
        int medRewards = MainActivity.getMeditationRewardsEarned();
        int taskRewards = MainActivity.getTaskRewardsEarned();

        if(exeRewards > 0){
            if(!rewardList.contains("A Couch Potato")) {rewardList.add(0,"A Couch Potato");}
            else if(!rewardList.contains("A Try Hard")) {rewardList.add(0,"A Try Hard");}
            else if(!rewardList.contains("Destined For Greatness")) {rewardList.add(0,"Destined For Greatness");}
            else if(!rewardList.contains("Determined")) {rewardList.add(0,"Determined");}
            else if(!rewardList.contains("Pretty Good")) {rewardList.add(0,"Pretty Good");}
            else if(!rewardList.contains("Fit")) {rewardList.add(0,"Fit");}
            else if(!rewardList.contains("Strong Like Bull")) {rewardList.add(0,"Strong Like Bull");}
            else if(!rewardList.contains("Unbreakable")) {rewardList.add(0,"Unbreakable");}
            else if(!rewardList.contains("Unstoppable")) {rewardList.add(0,"Unstoppable");}
            else if(!rewardList.contains("The Champion")) {rewardList.add(0,"The Champion");}

            exeRewards-=1;
            MainActivity.setExerciseRewardsEarned(exeRewards);
        }

        if(medRewards > 0){
            if(!rewardList.contains("A Hippie Wannabe")) {rewardList.add(0,"A Hippie Wannabe");}
            else if(!rewardList.contains("Enjoying Yourself")) {rewardList.add(0,"Enjoying Yourself");}
            else if(!rewardList.contains("Enjoying Life")) {rewardList.add(0,"Enjoying Life");}
            else if(!rewardList.contains("Rested")) {rewardList.add(0,"Rested");}
            else if(!rewardList.contains("Relaxing")) {rewardList.add(0,"Relaxing");}
            else if(!rewardList.contains("Peaceful")) {rewardList.add(0,"Peaceful");}
            else if(!rewardList.contains("Powerful")) {rewardList.add(0,"Powerful");}
            else if(!rewardList.contains("A Great Person")) {rewardList.add(0,"A Great Person");}
            else if(!rewardList.contains("The Best You")) {rewardList.add(0,"The Best You");}
            else if(!rewardList.contains("At One")) {rewardList.add(0,"At One");}

            medRewards-=1;
            MainActivity.setMeditationRewardsEarned(medRewards);
        }

        if(taskRewards > 0){
            if(!rewardList.contains("A Long Way From The Top")) {rewardList.add(0,"A Long Way From The Top");}
            else if(!rewardList.contains("Moving On Up")) {rewardList.add(0,"Moving On Up");}
            else if(!rewardList.contains("Heroic")) {rewardList.add(0,"Heroic");}
            else if(!rewardList.contains("Legendary")) {rewardList.add(0,"Legendary");}
            else if(!rewardList.contains("The Task Master")) {rewardList.add(0,"The Task Master");}
            else if(!rewardList.contains("Still Going?")) {rewardList.add(0,"Still Going?");}
            else if(!rewardList.contains("Improbable")) {rewardList.add(0,"Improbable");}
            else if(!rewardList.contains("Impossible")) {rewardList.add(0,"Impossible");}
            else if(!rewardList.contains("The One In The Prophecy")) {rewardList.add(0,"The One In The Prophecy");}
            else if(!rewardList.contains("The Chosen One")) {rewardList.add(0,"The Chosen One");}

            taskRewards-=1;
            MainActivity.setTaskRewardsEarned(taskRewards);
        }

        adapter = new RewardsDetailsAdapter(getApplicationContext(), rewardList);
        rewardDetails.setAdapter(adapter);
        saveData();



        /*

        //SOME OF THIS WILL BE USED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int checkedTasks = MainActivity.getCheckedTasks();
        int checkedExercise = MainActivity.getCheckedExercises();
        int checkedMeditation = MainActivity.getCheckedMeditations();

        if((exe % 10 == 0) && checkedExercise != exe){
            if(!rewardList.contains("tester")) {rewardList.add(0,"tester");}
            else if(!rewardList.contains("2")) {rewardList.add(0,"2");}
            else if(!rewardList.contains("3")) {rewardList.add(0,"3");}

            MainActivity.setCheckedExercises(exe);
        }

        if((med % 10 == 0) && checkedMeditation != med){
            if(!rewardList.contains("4")) {rewardList.add(0,"4");}
            else if(!rewardList.contains("5")) {rewardList.add(0,"5");}
            else if(!rewardList.contains("6")) {rewardList.add(0,"6");}

            MainActivity.setCheckedMeditations(med);
        }

        if((task % 20 == 0) && checkedTasks != task){
            if(!rewardList.contains("7")) {rewardList.add(0,"7");}
            else if(!rewardList.contains("8")) {rewardList.add(0,"8");}
            else if(!rewardList.contains("9")) {rewardList.add(0,"9");}

            MainActivity.setCheckedTasks(task);
        }

        adapter = new RewardsDetailsAdapter(getApplicationContext(), rewardList);
        rewardDetails.setAdapter(adapter);
        saveData();
        */
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(rewardList);
        editor.putString("reward list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("reward list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        rewardList = gson.fromJson(json, type);

        if(rewardList == null){
            rewardList = new ArrayList<String>();
            rewardList.add("A Rookie");
            rewardList.add("A Beta Tester");
        }
    }

    public void updateProgressBars(){
        ProgressBar taskProgressBar = (ProgressBar) findViewById(R.id.TaskProgressBar);
        ProgressBar exerciseProgressBar = (ProgressBar) findViewById(R.id.ExerciseProgressBar);
        ProgressBar meditationProgressBar = (ProgressBar) findViewById(R.id.MeditationProgressBar);
        ImageView trophy = (ImageView) findViewById(R.id.Trophy);
        ImageView notTrophy = (ImageView) findViewById(R.id.NotTrophy);

        int i = MainActivity.getExercisesCompleted();
        int j = MainActivity.getMeditationsCompleted();
        int k = MainActivity.getTasksCompleted();

        //int tempi = Main.getAvailableExerciseRewards
        //if tempi > 0{
        //Display trophy, give reward, setAvailableExerciseRewards-1



        Boolean taskAlmostComplete = MainActivity.isTaskAlmostComplete();//MainActivity.taskAlmostComplete;
        Boolean exeAlmostComplete = MainActivity.isExeAlmostComplete();//MainActivity.exeAlmostComplete;
        Boolean medAlmostComplete = MainActivity.isMedAlmostComplete();//MainActivity.medAlmostComplete;

        if(((k % 20 == 0) && taskAlmostComplete == true) ||
                ((i % 10 == 0) && exeAlmostComplete == true) ||
                ((j % 10 == 0) && medAlmostComplete == true)){
            trophy.setVisibility(View.VISIBLE);
            notTrophy.setVisibility(View.INVISIBLE);
        }
        else{
            trophy.setVisibility(View.INVISIBLE);
            notTrophy.setVisibility(View.VISIBLE);
        }


        if((k % 20 == 0) && taskAlmostComplete == false){taskProgressBar.setProgress(0);}
        if(k % 20 == 1){taskProgressBar.setProgress(5);}
        if(k % 20 == 2){taskProgressBar.setProgress(10);}
        if(k % 20 == 3){taskProgressBar.setProgress(15);}
        if(k % 20 == 4){taskProgressBar.setProgress(20);}
        if(k % 20 == 5){taskProgressBar.setProgress(25);}
        if(k % 20 == 6){taskProgressBar.setProgress(30);}
        if(k % 20 == 7){taskProgressBar.setProgress(35);}
        if(k % 20 == 8){taskProgressBar.setProgress(40);}
        if(k % 20 == 9){taskProgressBar.setProgress(45);}
        if(k % 20 == 10){taskProgressBar.setProgress(50);}
        if(k % 20 == 11){taskProgressBar.setProgress(55);}
        if(k % 20 == 12){taskProgressBar.setProgress(60);}
        if(k % 20 == 13){taskProgressBar.setProgress(65);}
        if(k % 20 == 14){taskProgressBar.setProgress(70);}
        if(k % 20 == 15){taskProgressBar.setProgress(75);}
        if(k % 20 == 16){taskProgressBar.setProgress(80);}
        if(k % 20 == 17){taskProgressBar.setProgress(85);}
        if(k % 20 == 18){taskProgressBar.setProgress(90);}
        if(k % 20 == 19){taskProgressBar.setProgress(95); MainActivity.setTaskAlmostComplete(true);}
        if((k % 20 == 0) && taskAlmostComplete == true){taskProgressBar.setProgress(100); MainActivity.setTaskAlmostComplete(false);}


        if((i % 10 == 0) && exeAlmostComplete == false){exerciseProgressBar.setProgress(0);}
        if(i % 10 == 1){exerciseProgressBar.setProgress(10);}
        if(i % 10 == 2){exerciseProgressBar.setProgress(20);}
        if(i % 10 == 3){exerciseProgressBar.setProgress(30);}
        if(i % 10 == 4){exerciseProgressBar.setProgress(40);}
        if(i % 10 == 5){exerciseProgressBar.setProgress(50);}
        if(i % 10 == 6){exerciseProgressBar.setProgress(60);}
        if(i % 10 == 7){exerciseProgressBar.setProgress(70);}
        if(i % 10 == 8){exerciseProgressBar.setProgress(80); }
        if(i % 10 == 9){exerciseProgressBar.setProgress(90); MainActivity.setExeAlmostComplete(true);}
        if((i % 10 == 0) && exeAlmostComplete == true){exerciseProgressBar.setProgress(100); MainActivity.setExeAlmostComplete(false);}

        if((j % 10 == 0) && medAlmostComplete == false){meditationProgressBar.setProgress(0);}
        if(j % 10 == 1){meditationProgressBar.setProgress(10);}
        if(j % 10 == 2){meditationProgressBar.setProgress(20);}
        if(j % 10 == 3){meditationProgressBar.setProgress(30);}
        if(j % 10 == 4){meditationProgressBar.setProgress(40);}
        if(j % 10 == 5){meditationProgressBar.setProgress(50);}
        if(j % 10 == 6){meditationProgressBar.setProgress(60);}
        if(j % 10 == 7){meditationProgressBar.setProgress(70);}
        if(j % 10 == 8){meditationProgressBar.setProgress(80);}
        if(j % 10 == 9){meditationProgressBar.setProgress(90); MainActivity.setMedAlmostComplete(true);}
        if((j % 10 == 0) && medAlmostComplete == true){meditationProgressBar.setProgress(100); MainActivity.setMedAlmostComplete(false);}

    }



}
