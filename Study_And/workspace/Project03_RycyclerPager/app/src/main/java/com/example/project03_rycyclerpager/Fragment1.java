package com.example.project03_rycyclerpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project03_rycyclerpager.recycler.RecAdapter;
import com.example.project03_rycyclerpager.recycler.RecDTO;
import com.example.project03_rycyclerpager.recycler.RecyclerActivity;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    RecyclerView rcv_act;
    ArrayList<RecDTO> list;

    public Fragment1(ArrayList<RecDTO> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);

        rcv_act = rootView.findViewById(R.id.container2);


        RecAdapter adapter = new RecAdapter(list,inflater);
        rcv_act.setAdapter(adapter);//리스트에걸기
        LinearLayoutManager manager = new LinearLayoutManager(
                getContext() , RecyclerView.VERTICAL,true
        );
        rcv_act.setLayoutManager(manager);



        return rootView;
    }
}