package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class TabFragment extends Fragment {
    TabLayout tabLayout;
    FragmentManager manager;

    public TabFragment(FragmentManager manager) {
        this.manager = manager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_tab, container, false);

        changeFrag(new Fragment2());

        tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("My뷰") );
        tabLayout.addTab(tabLayout.newTab().setText("발견") );
        tabLayout.addTab(tabLayout.newTab().setText("코로나19").setId(2) );
        tabLayout.addTab(tabLayout.newTab().setText("잔여백신") );
        tabLayout.addTab(tabLayout.newTab().setText("카카오TV") );

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab객체를 이용해서 몇번째 탭이 선택되었는지 log찍어보기
                if(tab.getPosition() == 0){
                    Toast.makeText(getContext(), "My뷰", Toast.LENGTH_SHORT).show();
                }else if(tab.getText().equals("발견")){
                    Toast.makeText(getContext(), "발견", Toast.LENGTH_SHORT).show();
                }else if(tab.getId() == 2){
                    Toast.makeText(getContext(), "코로나19", Toast.LENGTH_SHORT).show();

                }
//                tab.getPosition()?
//                        tab.getText()?
//                        tab.getId()?
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return rootView;
    }


    public void changeFrag(Fragment fragment){
        //getActivity() Null 어플종료됨.
        manager.beginTransaction()
                .replace(R.id.child_container , fragment).commit();
    }
}