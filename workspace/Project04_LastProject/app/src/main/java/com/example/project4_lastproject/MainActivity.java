package com.example.project4_lastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project4_lastproject.common.AskTask;

public class MainActivity extends AppCompatActivity {

    EditText editText ; // EditText ( xml ) <=> java ( 그릇 )
    public TextView tv_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_data = findViewById(R.id.tv_data);
        editText = findViewById(R.id.edt_mapping);


        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText editText = findViewById(R.id.edt_mapping);쌤하신거
                AskTask task = new AskTask(editText.getText().toString()+".Last" );
                task.execute();
            }
        });

    }

    }