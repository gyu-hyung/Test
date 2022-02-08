package com.example.ex04_framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    ImageView imgv1, imgv2, imgv3;
    ImageView[] arrImage = new ImageView[3];
    ArrayList<ImageView> list = new ArrayList<>();
    int index = 0;
    int cnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);

        arrImage[0] = findViewById(R.id.imgv1);
        arrImage[1] = findViewById(R.id.imgv2);
        arrImage[2] = findViewById(R.id.imgv3);

        list.add(findViewById(R.id.imgv1));
        list.add(findViewById(R.id.imgv2));
        list.add(findViewById(R.id.imgv3));

       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               imgv3.setVisibility(View.INVISIBLE);
               imgv2.setVisibility(View.INVISIBLE);
               imgv1.setVisibility(View.INVISIBLE);
            //   if(index == 3) {
              //     imgv1.setVisibility(View.VISIBLE);
              //     index = 2;
             //  }else if...
               //파이널 상수를 넣어줌
                  //  if(imgv3.getVisibility() == View.VISIBLE) {
                   // }
               for (int i = 0; i<list.size(); i++) {
                   list.get(i).setVisibility(View.GONE);
               }
               list.get(index).setVisibility(View.VISIBLE);
               index++;
               if(list.size() <= index ) index = 0;

               //list.get(0).setImageResource(R.drawable.img1);
               //아이디로 이미지를 찾아서 바꿈
               // ImageView세개를 중첩시켜놓는다는것은 리소스 낭비.

               if (cnt == 1) {
                   imgv3.setVisibility(View.VISIBLE);
               } else if (cnt == 2) {
                   imgv2.setVisibility(View.VISIBLE);
               } else if (cnt == 3) {
                   imgv1.setVisibility(View.VISIBLE);
                   cnt = 0;
               }

                   switch (cnt) {
                       case 1 : imgv3.setVisibility(View.VISIBLE); break;
                       case 2 : imgv2.setVisibility(View.VISIBLE); break;
                       case 3 : imgv1.setVisibility(View.VISIBLE); cnt=0; break;


                   }

           }
       });



    }

}