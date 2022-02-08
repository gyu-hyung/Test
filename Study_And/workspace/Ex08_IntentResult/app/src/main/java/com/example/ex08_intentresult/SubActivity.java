package com.example.ex08_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        // 이화면이 띄워지기 위해서 필요했던 객체 (Intent )
        Intent intent = getIntent();//메인에서 보내준 인텐트를 가져옴 ( 데이터가 있는경우 사용하는 코드)
        TextView textView = findViewById(R.id.tv1);
        if(intent != null){
            String id = intent.getStringExtra("id");
            int pw = intent.getIntExtra("pw" , 0 );
            int testData = intent.getIntExtra("asd" , 99);
            TestDTO dto = (TestDTO) intent.getSerializableExtra("dto");
            textView.setText("id : " + id + "\n");
            textView.append("pw : " + pw + "\n");
            textView.append("testData : " + testData + "\n");
            textView.append("dto feild1 : " + dto.getField1() + "\n");
            textView.append("dto feild2 : " + dto.getField2() + "\n");
            textView.append("dto feild3 : " + dto.getField3() + "\n");
            textView.append("dto feild4 : " + dto.getField4() + "\n");
            textView.append("dto feild5 : " + dto.getField5() + "\n");

        }

        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //메인으로 다시 결과를 보내거나 어떤 데이터를 주고싶음.
                //Intent를 사용하면 됨 <- 4대 컴포넌트(액티비티)
                //간의 통신은 무조건 Intent를 통함.
                Intent intent1 = new Intent();//새로 화면을 띄우기 x
                intent1.putExtra("key", "value");
                //오류가 안났다면 결과가 성공적이라는 것을 알려주기위한 메소드
                setResult(RESULT_OK , intent1);//<-메인에있는 결과받는 메소드로 이동하겠다.
                finish();
            }
        });
    }
}