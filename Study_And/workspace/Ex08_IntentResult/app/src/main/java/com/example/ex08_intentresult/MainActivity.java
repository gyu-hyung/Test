package com.example.ex08_intentresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:";
    Button btn_new ;
    final int SUBACTCODE = 1000; // 이코드는 어떠한 액티비티를 실행하고나서 어떤 액티비티를 실행했는지
                                  //알아보기위한 변수(코드)이기때문에 초기화 빼고는 수정이 되면 안됨.


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: !!!!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: !");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:!");
        btn_new = findViewById(R.id.btn_new);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //액티비티 또는 컴포넌트(4대 컴포넌트 , 서비스 , 브로드캐스트,내용제공자).
                //간의 통신은 Intent라는 객체를 통해서 한다.
                //인텐트의 두가지 방식 , 암시적(묵시) , 명시적
                //암시적 -> 어떤 기능이나 액션 또는 외부의 기능을 사용하게 하는것(제어권이 없음).카메라,전화걸기,인터넷
                //명시적 -> 어떤 클래스나 또는 우리가 사용할수있는 컴포넌트에 통신을 하는것
                // ( Intent로 새로운 액티비티 띄우기 , 액티비티는 안드로이드 스튜디오에 코드로 존재하기때문에
                // 제어권을 가지고 있다고 생각하면 된다.)
                Intent intent = new Intent(MainActivity.this , SubActivity.class);
                //1. 서브창으로 데이터를 넘기는 처리 : 인텐트가 가져갈수있게 추가.
                intent.putExtra("id" , "KYM");//인텐트에 String 추가.
                intent.putExtra("pw" , 1234); //int 추가
                TestDTO dto = new TestDTO("a","b","c","d","f");
                intent.putExtra("dto",dto);
                //※ 통신간에 객체를 이동시키기 위한 implements Serializable이 반드시 필요함. ※

                //startActivity(intent); <- 일반적으로 새로운 액티비티를 시작하는 메소드.
                startActivityForResult(intent , SUBACTCODE);//<- 액티비티를 시작하고 해당하는 액티비티가
                                                            //종료되고 나서 결과를 가지고오기위한 메소드
                                                            //ex)카메라로 사진을 찍는 액션(기능)
                                                            //수행하고나서 그사진파일을 결과로 받아올수가있음.
            }
        });
    }//onCreate();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SUBACTCODE && resultCode == RESULT_OK){// 한 화면에서 액티비티 또는 인텐트로 여러 기능을 사용했을때
                                      //그결과는 하나의 메소드인 onActivityResult에 전부다 옴.
                                      //따라서 내가 어떤 기능을 사용했는지에 따라 다른 처리를 하기위해서는
                                      //보내줬던 requestCode가 필요함.
            Toast.makeText(MainActivity.this, data.getStringExtra("key"), Toast.LENGTH_SHORT).show();
        }else if(requestCode == 1001){//<- ex) 카메라 기능을 사용하고나서의 결과를 처리.

        }
    }
}