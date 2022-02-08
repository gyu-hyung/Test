package com.example.project03_rycyclerpager.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;

public class PagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        //Pager2 == RecyclerView는 거의 유사함 UI적인 요소를 더 추가하거나 상황에 따라서
        //다른 위젯을 선택을 함 ( 기능이 거의 똑같음 )
        //RecyclerView 사용 - id를 찾아서 자바코드에서 사용할수있게함.
        //                  -Item준비(ArrayList) = xml도 포함
        //                  -Adapter준비 ( setAdapter로 연결 )
        //                  -LayoutManager준비 ( setLayoutManager 연결)
        //Viewpager2 사용 - ReyclerView와 거의 똑같음. LayoutManager가 별도로 필요없음
        ViewPager2 pager2 = findViewById(R.id.pager2);
//        ArrayList<String> items = new ArrayList<>();
//        for(int i = 0 ; i < 20 ; i ++){
//            items.add("item" + i);
//        }
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        Pager2Adapter adapter = new Pager2Adapter(inflater);
        pager2.setAdapter(adapter);


    }
}