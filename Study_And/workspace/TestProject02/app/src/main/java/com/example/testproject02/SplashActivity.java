package com.example.testproject02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //ProgressDialog dialog = new ProgressDialog(SplashActivity.this);//dialog초기화
        //dialog.setTitle("로딩중");
       // dialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , MainActivity.class);
                startActivity(intent);
                //메인으로넘김
                //dialog.dismiss();//dialog를 터치안해도 안보이게처리
                finish();
            }
        },1000);


    }
}