package com.example.rossg_000.fyp_v3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CompleteOrFail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_or_fail);

        Button Complete = (Button) findViewById(R.id.Complete);
        Button Fail = (Button) findViewById(R.id.Fail);

        Complete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Toast.makeText(CompleteOrFail.this, "Complete", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("CompleteOrFail", 1);
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
