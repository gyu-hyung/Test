package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class GridFragment extends Fragment {
    GridView gridView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_grid, container, false);
       gridView = rootView.findViewById(R.id.gridview);
        ArrayList<KakaoDTO> list = new ArrayList<>();
        for(int i = 0 ; i< 50 ; i ++){
            list.add(new KakaoDTO(R.drawable.shopping , "name" + i ,"msg" + i, "123" + i));
        }
       GridAdapter adapter = new GridAdapter(list , inflater);
        gridView.setAdapter(adapter);
       return rootView;
    }
}