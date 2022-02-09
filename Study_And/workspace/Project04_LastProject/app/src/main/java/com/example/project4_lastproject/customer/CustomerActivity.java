package com.example.project4_lastproject.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project4_lastproject.R;
import com.example.project4_lastproject.common.AskTask;
import com.example.project4_lastproject.common.CommonMethod;
import com.google.gson.Gson;

public class CustomerActivity extends AppCompatActivity {

    TextView tv_info;
    RadioButton rdo_man , rdo_woman;
    EditText edt_email , edt_tel;
    Button btn_close , btn_update;
    CustomerVO vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Intent intent = getIntent();
        vo = (CustomerVO) intent.getSerializableExtra("vo");
        tv_info = findViewById(R.id.tv_info);
        rdo_man = findViewById(R.id.rdo_man);
        rdo_woman = findViewById(R.id.rdo_woman);
        edt_email = findViewById(R.id.edt_email);
        edt_tel = findViewById(R.id.edt_tel);
        btn_close = findViewById(R.id.btn_close);
        btn_update = findViewById(R.id.btn_update);
        boolean isEable = intent.getBooleanExtra("enable" , false);
        //찾은 위젯에 정보를 넣어보기 (Vo이용해서)
        //tv_info <- 선택된이름의 고객정보
        //radioButton <- 선택된정보의 성별 만 체크 되어있게
        tv_info.setText(vo.getName() + " 님의 정보");
        edt_email.setText(vo.getEmail());
        edt_tel.setText(vo.getPhone());
        if(vo.getGender().equals("남")){
            rdo_man.setChecked(true);
        }else{
            rdo_woman.setChecked(true);
        }
        rdo_man.setEnabled(isEable);
        rdo_woman.setEnabled(isEable);
        edt_email.setEnabled(isEable);
        edt_tel.setEnabled(isEable);




        //체그상태가 바뀌면....
        rdo_man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(CustomerActivity.this, "남바뀜", Toast.LENGTH_SHORT).show();
                if(isChecked) {
                    rdo_woman.setChecked(false);
                    vo.setGender("남");
                }
            }
        });

        rdo_woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(CustomerActivity.this, "여도 바뀜", Toast.LENGTH_SHORT).show();
                if(isChecked) rdo_man.setChecked(false);
                vo.setGender("여");
            }
        });

        //수정버튼눌렀을때 수정 처리
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edt에 내용을 정확이 입력을 했는지 체크정도는 해줘야
                vo.setEmail(edt_email.getText()+"");
                vo.setPhone(edt_tel.getText()+"");
                Gson gson = new Gson();
                AskTask task = new AskTask("update.cu");
                task.addParam("vo" , gson.toJson(vo));
                CommonMethod.excuteGet(task);


            }
        });








    }
}