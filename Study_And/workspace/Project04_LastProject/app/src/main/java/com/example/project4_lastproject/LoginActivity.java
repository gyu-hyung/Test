package com.example.project4_lastproject;

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

    CheckBox chk_auto;
    Button btn_join , btn_login ;
    EditText edt_id , edt_pw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_join = findViewById(R.id.btn_join);
        btn_login = findViewById(R.id.btn_login);
        chk_auto = findViewById(R.id.chk_auto);

       SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id" , "--");
        String pw = preferences.getString("pw" , "--");
        Boolean login = preferences.getBoolean("autologin" ,false);

       /* if(login == true && id.equals("aaa") && pw.equals("bbb") ) {
            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
            startActivity(intent);
        }*/
        chk_auto.setChecked(login); //자동로그인을 체크하고나서 앱을종료해도 그대로 저장된 상태를 보여줌


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText()+"";
                String pw = edt_pw.getText()+"";
                if( id.equals("aaa") && pw.equals("bbbb") ){
                    saveLoginInfo();
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "다시입력하세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(login){
            edt_id.setText(id);
            edt_pw.setText(pw);
            //btn_login.callOnClick();
        }


    }//oncreate()

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