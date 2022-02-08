package com.example.project01_background;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //progressdialog (<= Dialog 화면 위에 띄워져서 어떤 처리를 하는것
        //사용자 앱이 멈춘상태가 되면 사용자는 당황함, 어떤상태인지

        //Context <-  어떤 액티비티 (Context를 가진 클래스) 에서 보여줄것인지를 파라메터로 넘겨줘야
        ProgressDialog dialog = new ProgressDialog( SplashActivity.this);
        //Background 에서는 비정상적인 방법으로Context를 강제로 만들어 내도 화면에 보여줄 수가없음
        //dialog초기화 ( 아지 화면에안나옴)
        dialog.setTitle("지금은 프로젝트를 로딩중입니다.....");
        dialog.show();// <- 실제 dialog를 보여주는메섣

        //몇초정도 현재 액티비티 (Splash 기능이 없음 ))를 보여준 후
        //실제 기능이 있는 MainActivity로 이동하는 처리
        //Android => sevlet (Service) => DB
        // Web = > DB
        //Handler라는 메인 쓰레드를 이용해서 딜레이를 준다 . (외울필요 x, 복붙해서 사용)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , MainActivity.class );
                startActivity(intent);
                //메인으로 넘기는 처리 Intent이용 직접하기
                dialog.dismiss();//dialog를 사용자가 터치하지 않아도 안보이게 처리.
                finish();//뒤로가면 다시돌아가서
            }
        }, 3000);















    }
}