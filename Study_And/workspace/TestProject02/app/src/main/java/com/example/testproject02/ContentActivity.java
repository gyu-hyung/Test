package com.example.testproject02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContentActivity extends AppCompatActivity {

    Fragment fragment;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        nav = findViewById(R.id.nav);



        Intent intent = getIntent();//메인에서 보내준 인텐트를 가져옴 ( 데이터가 있는경우 사용하는 코드)
        UserDTO dto = (UserDTO) intent.getSerializableExtra("dto");
        String aa = null ;
       /* TextView tvid = findViewById(R.id.tvid);
        TextView tvname = findViewById(R.id.tvname);
        TextView tvbirth = findViewById(R.id.tvbirth);


        if(intent != null){// 로그인창에서 입력한 정보를 뿌려줌
            userDTO dto = (userDTO) intent.getSerializableExtra("dto");
            tvid.setText(dto.getId());
            tvname.setText(dto.getName());
            tvbirth.setText(dto.getBirth());
        }*/

        fragment = new MainFragment(dto);
        changeFrag(fragment);
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.getItemId() // ? Resoureces 에 등록되어있는 인트로 되어있는 id를 확인가능
                //item.getTitle() // ?  Resources 에 등록되어있는 텍스트( tittle글씨)를 확인가능
                if(item.getItemId() == R.id.tab1){
                    fragment = new MainFragment(dto);
                    changeFrag(fragment);
                }else if (item.getItemId() == R.id.tab2){
                    fragment = new ListFragment();
                    changeFrag(fragment);
                }else if (item.getItemId() == R.id.tab3){

                }
                // id를 부여하면 자동으로 int로 id를 채번해서 R에서 사용할수있게 됨.
                //문제 ) 1.몇번 메뉴가 선택되었는지 Toast창으로 띄우기
                //       2.log로찍어보기
                //       3.로그와 Toast가 둘다 됐다면 디버깅모드로도 확인해보기.

                return true;
            }
        });











        }
        public void changeFrag(Fragment fragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container , fragment).commit();
            String aa= "";
        }

    }
