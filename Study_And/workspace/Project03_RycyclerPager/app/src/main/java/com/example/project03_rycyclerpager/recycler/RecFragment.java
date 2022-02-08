package com.example.project03_rycyclerpager.recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;

public class RecFragment extends Fragment {
    RecyclerView rcv_frg;
    ArrayList<RecDTO> list;

    public RecFragment(ArrayList<RecDTO> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_rec, container, false);

        rcv_frg = rootView.findViewById(R.id.rcv_frg);

        RecAdapter adapter = new RecAdapter(list, inflater );
        rcv_frg.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(
                getContext() , RecyclerView.VERTICAL , false
        );

        rcv_frg.setLayoutManager(manager);

        return rootView;
    }
}