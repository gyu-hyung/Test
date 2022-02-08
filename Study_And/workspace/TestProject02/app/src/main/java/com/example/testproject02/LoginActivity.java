package com.example.testproject02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<UserDTO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edid = findViewById(R.id.edid);
        EditText edpw = findViewById(R.id.edpw);

        UserDTO dto ;
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO dto = new UserDTO(
                        edid.getText()+"",
                        edpw.getText()+"",
                        "jyp","1972.01.13");

 //               list.add(new userDTO("admin","admin" ,"admin","admin"));
                if((edid.getText().toString()).equals("admin") && (edpw.getText().toString()).equals("admin") ){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this , ContentActivity.class);
                    intent.putExtra("dto", dto);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "로그인 실패" , Toast.LENGTH_SHORT).show();
                }
               // intent.putExtra()

            }
        });





        findViewById(R.id.join_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this , JoinActivity.class);
                startActivity(intent);
            }
        });




    }
}