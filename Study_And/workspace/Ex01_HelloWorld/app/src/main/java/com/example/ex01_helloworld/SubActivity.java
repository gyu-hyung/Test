package com.example.ex01_helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //위젯들은 전체적으로 View를 상속받았기때문에
        //SetOnclickListner가 전부 있는 구조다.
        //Text gettext settext
        findViewById(R.id.btn_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, Sub2Activity.class);
                startActivity(intent);
            }
        });
        Button btn_sub = findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener((View.OnClickListener) this);
    }



}