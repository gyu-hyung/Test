package com.example.project03_rycyclerpager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project03_rycyclerpager.common.AskTask;
import com.example.project03_rycyclerpager.recycler.RecyclerActivity;
import com.example.project03_rycyclerpager.viewglide.GlideActivity;
import com.example.project03_rycyclerpager.viewglide.GlidePagerActivity;
import com.example.project03_rycyclerpager.viewpager.PagerActivity;
import com.example.project03_rycyclerpager.viewpagerdb.PagerDbActivity;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{
Button btn_rycycler , btn_viewpager , btn_viewpager2 , btn_glide , btn_glide2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_rycycler = findViewById(R.id.btn_recycler);
        btn_viewpager = findViewById(R.id.btn_viewpager);
        btn_viewpager2 = findViewById(R.id.btn_viewpager2);
        btn_glide = findViewById(R.id.btn_glide);
        btn_rycycler.setOnClickListener(this);
        btn_viewpager.setOnClickListener(this);
        btn_viewpager2.setOnClickListener(this);
        btn_glide.setOnClickListener(this);

        btn_glide2 = findViewById(R.id.btn_glide2);
        btn_glide2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_recycler){
            Intent intent = new Intent(MainActivity.this , RecyclerActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btn_viewpager){
            Intent intent = new Intent(MainActivity.this , PagerActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btn_viewpager2){
            Intent intent = new Intent(MainActivity.this , PagerDbActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btn_glide){
            Intent intent = new Intent(MainActivity.this , GlideActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btn_glide2){
            Intent intent = new Intent(MainActivity.this , GlidePagerActivity.class);
            startActivity(intent);
        }


    }
}