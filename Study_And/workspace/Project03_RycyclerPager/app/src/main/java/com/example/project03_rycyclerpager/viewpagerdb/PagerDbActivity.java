package com.example.project03_rycyclerpager.viewpagerdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.project03_rycyclerpager.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class PagerDbActivity extends AppCompatActivity {
    ArrayList<Integer> imgs = new ArrayList<>();
    ViewPager2 pager2;
    DotsIndicator dotsIndicator ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_db);
        imgs.add(R.drawable.ic_launcher_background);
        imgs.add(R.drawable.ic_launcher_foreground);
        imgs.add(android.R.drawable.ic_dialog_email);
        imgs.add(android.R.drawable.ic_delete);
        imgs.add(android.R.drawable.ic_menu_camera);

        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        pager2 = findViewById(R.id.pager2db);
        // Indicator <- 이미지슬라이드
        //1.순수 자바코드와 xml 하나씩 직접만들어서 이벤트랑 어댑터 등등을 직접구현 <- 오래걸림,안정성
        //2.만들어져있는 API를 이용해서 사용하는 방법 ※
        //↑ DB에 img url주소를 넣어둠 => Glide API를 사용해서 웹에있는 이미지를 불러올수있게 수정.
        // Adapter추가 ..> setAdapter까지의 과정 해보기.
        //

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        PagerDbAdapter adapter = new PagerDbAdapter(inflater,imgs );
        pager2.setAdapter(adapter);
        dotsIndicator.setViewPager2(pager2);
    }
}