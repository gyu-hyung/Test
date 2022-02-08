package com.example.connnectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {
Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener() {
            InputStream in ;
            @Override
            public void onClick(View v) {
                ArrayList<MemberDTO> list = new ArrayList<>();
                for(int i=0;i<20; i++){
                    list.add(new MemberDTO(i,"pw"+i ,"addr"+i , "addr"+i));
                }
                MainAskTask task = new MainAskTask("ad.list");
                Toast.makeText(ListActivity.this, gson.toJson(list), Toast.LENGTH_SHORT).show();
                task.addParam("list", gson.toJson(list));

                try {
                  InputStream  in = task.execute().get();//미들웨어에서  반드시 데이터를 받고나서 밑에줄 코드로
                    list = gson.fromJson(new InputStreamReader(in)
                            , new TypeToken<List<MemberDTO>>() {}.getType());
                                        //가야하는 상황은 (대부분임)반드시 get을 통해 작업한다.
                String aa = " ";


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //ArrayList<MemberDTO> list1 = gson.fromJson(new InputStreamReader(in)
                   //     ,new TypeToken<List<MemberDTO>>() {}.getType() );


            }







        });

    }
}