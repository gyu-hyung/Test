package com.example.ex06_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imvg;
    Button btn1;
    int index = 0;
    int cnt;
    ArrayList<ImageView> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);

        imvg = findViewById(R.id.imgv);

        list.add(findViewById(R.id.imgv));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (++cnt) {
                    case 1:
                        imvg.setImageResource(R.drawable.img1);
                        break;
                    case 2:
                        imvg.setImageResource(R.drawable.img2);
                        break;
                    case 3:
                        imvg.setImageResource(R.drawable.img3);
                        cnt = 0;
                        break;
                }

            }
        });

}
}

