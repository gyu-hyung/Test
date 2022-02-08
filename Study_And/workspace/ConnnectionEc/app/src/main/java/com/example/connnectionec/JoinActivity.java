package com.example.connnectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {

    EditText id,pw,name,addr;
    Button joinbtn, canclebtn;
    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        id = findViewById(R.id.edt_id);
        pw = findViewById(R.id.edt_pw);
        name = findViewById(R.id.edt_name);
        addr = findViewById(R.id.edt_addr);

        joinbtn = findViewById(R.id.joinbtn);
        canclebtn = findViewById(R.id.canclebtn);

    findViewById(R.id.joinbtn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MemberDTO dto = new MemberDTO(
                    Integer.parseInt(id.getText()+""),
                    pw.getText()+"",
                    name.getText()+"",
                    addr.getText()+""

            );

            String json = gson.toJson(dto);
            Toast.makeText(JoinActivity.this, json, Toast.LENGTH_SHORT).show();
            AskTask task = new AskTask("aaaa.cos" );
            task.addParam("dto", json);
            try {
                InputStream in = task.execute().get();
                MemberDTO dtoRecv = gson.fromJson(new InputStreamReader(in) , MemberDTO.class);
                Toast.makeText(JoinActivity.this, dtoRecv.getAddr(), Toast.LENGTH_SHORT).show();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    });









    }//onCreate()


}