package com.example.project02_cloneapp1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment {
    //Fragment를 만들게 되면 불필요한 코드가 매우 많이 많이 존재한다. final상태로 인자값을 한번만 입력받게하는
    //변수, OnCreate() 메소드 등등.
    //실제로 제일 중요하고 필요한 코드는 onCreateView이다
    //onCreate<- Activity ( Context를 가진 )
    //onCreateView<- Fragment,View객체 ( Context를 가지지 않은 , 화면에 혼자 떠있을수 없는 )
    //=> inflater.inflate 화면을 붙이는 행위가 onCreateView에 있기 때문. (find)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_3, container, false);
    }
}