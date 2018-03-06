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
        if(position == 0){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 1){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 2){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 3){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 4){ theImage.setImageResource(R.drawable.cat);}
        if(position == 5){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 6){ theImage.setImageResource(R.drawable.lyinghug);}
        if(position == 7){ theImage.setImageResource(R.drawable.crabreach);}
        if(position == 8){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 9){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 10){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 11){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 12){ theImage.setImageResource(R.drawable.cat);}
        if(position == 13){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 14){ theImage.setImageResource(R.drawable.lyinghug);}
        if(position == 15){ theImage.setImageResource(R.drawable.crabreach);}
        if(position == 16){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 17){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 18){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 19){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 20){ theImage.setImageResource(R.drawable.cat);}
        if(position == 21){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 22){ theImage.setImageResource(R.drawable.lyinghug);}
        if(position == 23){ theImage.setImageResource(R.drawable.crabreach);}
        if(position == 24){ theImage.setImageResource(R.drawable.downwarddog);}
        //to 24
    }

    public void setImageMeditation(int position){
        //0 to
        ImageView theImage = (ImageView) findViewById(R.id.TheImage);
        if(position == 0){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 1){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 2){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 3){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 4){ theImage.setImageResource(R.drawable.cat);}
        if(position == 5){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 6){ theImage.setImageResource(R.drawable.lyinghug);}
        if(position == 7){ theImage.setImageResource(R.drawable.crabreach);}
        if(position == 8){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 9){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 10){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 11){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 12){ theImage.setImageResource(R.drawable.cat);}
        if(position == 13){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 14){ theImage.setImageResource(R.drawable.lyinghug);}
        if(position == 15){ theImage.setImageResource(R.drawable.crabreach);}
        if(position == 16){ theImage.setImageResource(R.drawable.downwarddog);}
        if(position == 17){ theImage.setImageResource(R.drawable.sideoblique);}
        if(position == 18){ theImage.setImageResource(R.drawable.crescentpose);}
        if(position == 19){ theImage.setImageResource(R.drawable.singleleg);}
        if(position == 20){ theImage.setImageResource(R.drawable.cat);}
        if(position == 21){ theImage.setImageResource(R.drawable.sumosquat);}
        if(position == 22){ theImage.setImageResource(R.drawable.lyinghug);}
        //to 22
    }
}
