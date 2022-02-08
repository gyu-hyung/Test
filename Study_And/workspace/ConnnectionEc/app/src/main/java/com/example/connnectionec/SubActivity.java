package com.example.connnectionec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SubActivity extends AppCompatActivity {
    EditText edt_data;
    Button btn_send;
    TextView tv_recv;
    ArrayList<String> params = new ArrayList<>();
    int str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        edt_data = findViewById(R.id.edt_data);
        btn_send = findViewById(R.id.btn_send);
        tv_recv  = findViewById(R.id.tv_recv);











        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    str = Integer.parseInt( edt_data.getText().toString() );
                    for(int i =0; i < str; i++){
                        params.add("pavalues"+i);

                    }
                    AskTask task = new AskTask("vvv.cos");
                    task.execute().get();
                  //  tv_recv.setText(data);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //excute를 실행하면 비동기 작업으로(main Thread , AsyncTask는 따로작업)
                //서버랑 연동이 무조건 되고 나서 아래코드가 실행되어야할때 (결과가 무조건 필요할떄)

            }
        });






    }
}