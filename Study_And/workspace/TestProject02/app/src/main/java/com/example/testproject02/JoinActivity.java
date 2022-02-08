package com.example.testproject02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {
    ArrayList<UserDTO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        EditText edid  = findViewById(R.id.edid);
        EditText edpw1 = findViewById(R.id.edpw1);
        EditText edpw2 = findViewById(R.id.edpw2);
        EditText edpw3 = findViewById(R.id.edpw3);
        String tedid   = edid.getText().toString();
        String tedpw1  = edpw1.getText().toString();
        String tedpw2  = edpw2.getText().toString();
        String tedpw3  = edpw3.getText().toString();/*왜 강제 형변환 안댐>?*/



         findViewById(R.id.join_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(JoinActivity.this, "회원가입버튼", Toast.LENGTH_SHORT).show();
                UserDTO dto = new UserDTO(
                        edid.getText()+"",
                        edpw1.getText()+"",
                        edpw2.getText()+"",
                        edpw3.getText()+"");


                if( dto != null && (edid.getText().toString()).equals("admin") && (edpw1.getText().toString()).equals("admin") ){
                    Toast.makeText(JoinActivity.this, "이미 가입된 아이디입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(JoinActivity.this, dto.getId()+"님 어서오세여" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                    intent.putExtra("user" , dto);
                    startActivity(intent);
                }
                /*intent.putExtra("id","아이뒤");
                intent.putExtra("pw", "뷔이번");
                userDTO dto = new userDTO("아뒤","비붠","이룸","벌뜨");
                intent.putExtra("dto", dto);*/
            }
        });
        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        
        
        
        
        
    }//onCreate()
}