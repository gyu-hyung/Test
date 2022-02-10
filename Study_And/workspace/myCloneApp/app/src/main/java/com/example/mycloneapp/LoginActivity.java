package com.example.mycloneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edt_id , edt_pw;
    Button btn_login , btn_join , btn_cancle , btn_forget;
    CheckBox chk_auto ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        chk_auto = findViewById(R.id.chk_auto);

        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id" , "--");
        String pw = preferences.getString("pw" , "--");
        Boolean login = preferences.getBoolean("autologin" ,false);

        chk_auto.setChecked(login); //자동로그인을 체크하고나서 앱을종료해도 그대로 저장된 상태를 보여줌

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO dto = new UserDTO(
                        "a",
                        "a",
                        "jyp","1972.01.13");
                if((edt_id.getText().toString()).equals(dto.getId()) && edt_pw.getText().toString().equals(dto.getPw())){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    saveLoginInfo();
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    intent.putExtra("dto" , dto );
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(login){
            edt_id.setText(id);
            edt_pw.setText(pw);
            //btn_login.callOnClick();
        }

    }//onCreate()

    public void saveLoginInfo(){
        try {
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if(chk_auto.isChecked()){
                editor.putBoolean("autologin" , true);
                editor.putString("id" , edt_id.getText() + "");
                editor.putString("pw" , edt_pw.getText() + "");
                editor.apply();
            }else{//로그인 정보를 삭제
                editor.remove("id");
                editor.remove("pw");
                //editor.putString("id" , null); 이방법도있ㅎ는데ㅇㅇ
                editor.apply();
            }
        }catch (Exception e){
            Toast.makeText(LoginActivity.this, "자동로그인 정보 저장 실패", Toast.LENGTH_SHORT).show();
        }

    }


}