package com.example.connnectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
//1.Manifest 확인 (clearTrafic , Permission , lib 등 ) 기존에 되었던 소스가있으면 복붙
    //Permission(사용 권한을 부여받는것, 별도로 코드가 ㅣ필요한경우도있고 아닌것도)
    //(Ex. 인터넷은 그냥 Manifest에 기재만해도 사용이가능 카메라 저장소 등은 사용자동의
    //clearTraffic = http, https 로 요청할수있게처리
//2.Gradle (라이브러리추가 sync now통해 연결해주기
//3.AskTask를 사용해서 Eclipse 까지 접근 (was) Mapping을 통해서
// service <- 비동기 처리 .(메인쓰레드 액티비티)멈추지않고 어떤 동작을 하기위해서
    EditText editText ; // EditText ( xml ) <=> java ( 그릇 )
    public TextView tv_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//xml

        tv_data = findViewById(R.id.tv_data);
        editText = findViewById(R.id.edt_mapping);


        findViewById(R.id.bt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText editText = findViewById(R.id.edt_mapping);쌤하신거
                AskTask task = new AskTask(editText.getText().toString()+".cos" );
                task.execute();
            }
        });

    }

    public void setText (String data){
        /*MainActivity activity = new MainActivity();*/
        tv_data.setText(data);
    }


}