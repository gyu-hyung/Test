package com.example.ex05_scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //사용할 것을 먼저 찾음(전역변수)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        for (int i=0; i<60; i++){
            //tv1.setText(""); 마지막 글자를 덮어쓰기때문에 아무리 반복해도 마지막 반복횟수때 넣은 글자만 유지가됨
            tv1.append("\n안녕하세요.안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요");
            //글씨 붙이기
        }



    }
}