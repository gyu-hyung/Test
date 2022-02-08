package com.example.project02_cloneapp1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class ListViewFragment2 extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
        listView = rootView.findViewById(R.id.listview);
        ArrayList<KakaoDTO> list = new ArrayList<>();
        for(int i = 0 ; i < 30 ; i ++){

            list.add(new KakaoDTO(R.drawable.music,"이름 " + i,"상태 메세지"  + i ));
        }

        KakaoAdapter adapter = new KakaoAdapter(list,getContext());
        listView.setAdapter(adapter);

        return rootView;
    }
}