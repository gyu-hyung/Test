package com.example.project02_cloneapp1.recyclerview;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_cloneapp1.R;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {
RecyclerView recviewHo , recviewVe ;
ArrayList<RecDTO> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recycler, container, false);
        recviewHo = rootView.findViewById(R.id.recview_ho);
        recviewVe = rootView.findViewById(R.id.recview_ve);
        for(int i = 0; i<50 ; i++){
            list.add(new RecDTO( R.drawable.ic_launcher_background , "텍스트뷰 글씨 " + (i)));
        } //<- 데이터베이스와 연동해서 실제 데이터를 가지고오면 됨.(DTO<=>DB컬럼)
        //1.리사이클러뷰를 붙일 레이아웃(java)을 준비 ( Activity , Fragment )
        //2.한칸마다 보여질 데이터 타입(DTO) , 보여질 뷰를 준비(rec_item)
        //3.Adapter를 만들기.!
        //LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RecAdapter adapter = new RecAdapter(list , inflater);
        recviewHo.setAdapter(adapter);
        recviewVe.setAdapter(adapter);
        //4.Adatper <===> RecyclerView랑 연결 setAdapter
        //5.LayoutManager <- 라는것을 사용해서 가로 또는 세로 ,지그재그 인지를 지정해줘야함※
        //(ListView,GridView )
        LinearLayoutManager manager
                = new LinearLayoutManager(getContext() , RecyclerView.HORIZONTAL , false);
        LinearLayoutManager manager2
                = new LinearLayoutManager(getContext() , RecyclerView.VERTICAL , false);
        recviewHo.setLayoutManager(manager);
        recviewVe.setLayoutManager(manager2);
        //목록 형태를 가진 데이터는 우리가 원하는 모양으로 데이터를 보여주기 위해서는 반드시 xml , Adapter<-※
        return rootView;
    }
}