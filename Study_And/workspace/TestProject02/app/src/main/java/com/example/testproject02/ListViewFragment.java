package com.example.testproject02;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewFragment extends Fragment {

    ListView listView ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_list, container, false);

        listView = rootview.findViewById(R.id.listview);
        ArrayList<ListDTO> list = new ArrayList<>();
        for(int i=0;i<30;i++){
            list.add(new ListDTO("이름" +  i , "상태 메세지" + i , R.drawable.talk , R.drawable.gear));
        }









        return rootview;
    }
}