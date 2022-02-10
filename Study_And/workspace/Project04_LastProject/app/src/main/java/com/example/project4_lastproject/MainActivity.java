package com.example.project4_lastproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project4_lastproject.common.AskTask;
import com.example.project4_lastproject.customer.CustomerMainFragment;
import com.example.project4_lastproject.employees.EmployeeFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container , new EmployeeFragment()).commit();





    }

    }