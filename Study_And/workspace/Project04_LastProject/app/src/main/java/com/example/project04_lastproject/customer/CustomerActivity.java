package com.example.project04_lastproject.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project04_lastproject.MainActivity;
import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;

public class CustomerActivity extends AppCompatActivity {
    TextView tv_info;
    RadioButton rdo_man , rdo_woman;
    EditText edt_email , edt_tel;
    Button btn_close;
    CustomerVO vo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Intent intent = getIntent();
        vo = (CustomerVO) intent.getSerializableExtra("vo");
        //Toast.makeText(CustomerActivity.this, vo.getName() + ":이름", Toast.LENGTH_SHORT).show();
        tv_info = findViewById(R.id.tv_info);
        rdo_man = findViewById(R.id.rdo_man);
        rdo_woman = findViewById(R.id.rdo_woman);
        edt_email = findViewById(R.id.edt_email);
        edt_tel = findViewById(R.id.edt_tel);
        btn_close = findViewById(R.id.btn_close);
        boolean isEnable = intent.getBooleanExtra("enable" , false);
        //찾은 위젯에 정보를 넣어보기. ( Vo이용해서)
        //tv_info <= 선택된이름의 고객정보 ..
        //radioButton <= 선택된정보의성별 만 체크되어있게.
        tv_info.setText(vo.getName() + "의 고객정보");
        edt_email.setText(vo.getEmail());
        edt_tel.setText(vo.getPhone());
        if(vo.getGender().equals("남")){
            rdo_man.setChecked(true);
        }else{
            rdo_woman.setChecked(true);
        }

        rdo_man.setEnabled(isEnable);
        rdo_woman.setEnabled(isEnable);
        edt_email.setEnabled(isEnable);
        edt_tel.setEnabled(isEnable);

       
        rdo_man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked) {
                   rdo_woman.setChecked(false);
                   vo.setGender("남");
               }
            }
        });

        rdo_woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    rdo_man.setChecked(false);
                    vo.setGender("여");
                }
            }
        });

        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edt에 내용을 정확히 입력을 했는지 체크.
                vo.setEmail(edt_email.getText() + "");
                vo.setPhone(edt_tel.getText() + "");
                Gson gson = new Gson();
                Toast.makeText(CustomerActivity.this, gson.toJson(vo), Toast.LENGTH_SHORT).show();
                AskTask task = new AskTask("update.cu");
                task.addParam("vo" , gson.toJson(vo)); // Spring에 갈때 파라메터를 추가해서 가기.
                CommonMethod.excuteGet(task);
                finish();
            }
        });


    }


}