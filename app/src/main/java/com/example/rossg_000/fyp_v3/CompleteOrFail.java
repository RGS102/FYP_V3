package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class CompleteOrFail extends AppCompatActivity {
    private EditText Progress;
    private EditText Duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_or_fail);

        Button Complete = (Button) findViewById(R.id.Complete);
        Button Fail = (Button) findViewById(R.id.Fail);

        Complete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Progress = (EditText) findViewById(R.id.NumberInput);
                Duration = (EditText) findViewById(R.id.DurationInput);
                int finalValue = 0;
                int finalDuration = 0;

                if(Progress.getText().toString().trim().length()>0){
                    String value = Progress.getText().toString();
                    finalValue = Integer.parseInt(value);
                }
                if(Duration.getText().toString().trim().length()>0){
                    String durationValue = Duration.getText().toString();
                    finalDuration = Integer.parseInt(durationValue);
                }

                Intent intent = new Intent();
                intent.putExtra("CompleteOrFail", 1);
                intent.putExtra("Progress", finalValue);
                intent.putExtra("Duration", finalDuration);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        Fail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Toast.makeText(CompleteOrFail.this, "Fail", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("CompleteOrFail", -1);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    public static int getResult(Intent intent){
        return intent.getIntExtra("CompleteOrFail", 0);
    }
}
