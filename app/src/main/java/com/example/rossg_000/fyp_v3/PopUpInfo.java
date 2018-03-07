package com.example.rossg_000.fyp_v3;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUpInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_info);

        Intent intent = getIntent();
        String value = intent.getStringExtra("details");
        String imageFrom = intent.getStringExtra("ImageFrom");
        int position = intent.getIntExtra("Position",0);

        if(imageFrom.matches("Stretches")){setImageStretches(position);}
        if(imageFrom.matches("Exercise")){setImageExercise(position);}
        if(imageFrom.matches("Meditation")){setImageMeditation(position);}
        if(imageFrom.matches("HowTo")){setImageHowTo(position);}




        TextView PopUp = (TextView) findViewById(R.id.TaskPopUpInfo);
        PopUp.setText(value);
    }

    public void setImageStretches(int position){
        ImageView theImage = (ImageView) findViewById(R.id.TheImage);
        if(position == 0){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 1){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 2){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 3){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 4){ theImage.setImageResource(R.drawable.cat);}
        if(position == 5){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 6){ theImage.setImageResource(R.drawable.lyinghug);}
        if(position == 7){ theImage.setImageResource(R.drawable.crabreach);}
    }

    public void setImageExercise(int position){
        //0 to
        ImageView theImage = (ImageView) findViewById(R.id.TheImage);
        if(position == 0){ theImage.setImageResource(R.drawable.walk);}
        if(position == 1){ theImage.setImageResource(R.drawable.situps);}
        if(position == 2){ theImage.setImageResource(R.drawable.jog);}
        if(position == 3){ theImage.setImageResource(R.drawable.swim);}
        if(position == 4){ theImage.setImageResource(R.drawable.run);}
        if(position == 5){ theImage.setImageResource(R.drawable.pushups);}
        if(position == 6){ theImage.setImageResource(R.drawable.bike);}
        if(position == 7){ theImage.setImageResource(R.drawable.crunches);}
        if(position == 8){ theImage.setImageResource(R.drawable.squats);}
        if(position == 9){ theImage.setImageResource(R.drawable.superman);}
        if(position == 10){ theImage.setImageResource(R.drawable.tuckjump);}
        if(position == 11){ theImage.setImageResource(R.drawable.pronewalkout);}
        if(position == 12){ theImage.setImageResource(R.drawable.burpee);}
        if(position == 13){ theImage.setImageResource(R.drawable.plank);}
        if(position == 14){ theImage.setImageResource(R.drawable.wallsit);}
        if(position == 15){ theImage.setImageResource(R.drawable.lunge);}
        if(position == 16){ theImage.setImageResource(R.drawable.clocklunge);}
        if(position == 17){ theImage.setImageResource(R.drawable.singlelegdeadlift);}
        if(position == 18){ theImage.setImageResource(R.drawable.stepup);}
        if(position == 19){ theImage.setImageResource(R.drawable.calfraise);}
        if(position == 20){ theImage.setImageResource(R.drawable.tricepdip);}
        if(position == 21){ theImage.setImageResource(R.drawable.boxer);}
        if(position == 22){ theImage.setImageResource(R.drawable.flutterkicks);}
        if(position == 23){ theImage.setImageResource(R.drawable.shoulderbridge);}
        if(position == 24){ theImage.setImageResource(R.drawable.sprintersitup);}
        //to 24
    }

    public void setImageMeditation(int position){
        //0 to
        ImageView theImage = (ImageView) findViewById(R.id.TheImage);
        if(position == 0){ theImage.setImageResource(R.drawable.deepbreathing);}
        if(position == 1){ theImage.setImageResource(R.drawable.powernap);}
        if(position == 2){ theImage.setImageResource(R.drawable.musclerelaxation);}
        if(position == 3){ theImage.setImageResource(R.drawable.bodyscan);}
        if(position == 4){ theImage.setImageResource(R.drawable.openmonitoring);}
        if(position == 5){ theImage.setImageResource(R.drawable.focusedattention);}
        if(position == 6){ theImage.setImageResource(R.drawable.walkingmeditation);}
        if(position == 7){ theImage.setImageResource(R.drawable.yoga);}
        if(position == 8){ theImage.setImageResource(R.drawable.taichi);}
        if(position == 9){ theImage.setImageResource(R.drawable.sleep);}
        if(position == 10){ theImage.setImageResource(R.drawable.mindfuleating);}
        if(position == 11){ theImage.setImageResource(R.drawable.visualisation);}
        if(position == 12){ theImage.setImageResource(R.drawable.mantra);}
        if(position == 13){ theImage.setImageResource(R.drawable.metta);}
        if(position == 14){ theImage.setImageResource(R.drawable.selfenquiry);}
        if(position == 15){ theImage.setImageResource(R.drawable.scalpmassage);}
        if(position == 16){ theImage.setImageResource(R.drawable.eyesoother);}
        if(position == 17){ theImage.setImageResource(R.drawable.sinusrelief);}
        if(position == 18){ theImage.setImageResource(R.drawable.shouldertension);}
        if(position == 19){ theImage.setImageResource(R.drawable.music);}
        if(position == 20){ theImage.setImageResource(R.drawable.read);}
        if(position == 21){ theImage.setImageResource(R.drawable.effortlesspresence);}
        if(position == 22){ theImage.setImageResource(R.drawable.zenmeditation);}
        //to 22
    }

    public void setImageHowTo(int position){
        ImageView theImage = (ImageView) findViewById(R.id.TheImage);
        //0 to

        if(position == 0){ theImage.setImageResource(R.drawable.downwarddog);}

        //to ?? depends how many explanations i have at end
    }



}
