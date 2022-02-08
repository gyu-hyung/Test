package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.UUID;


public class ChatFragment extends Fragment {
    ArrayList<KakaoDTO> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);
        ListView listView = rootView.findViewById(R.id.listview);

        //인터넷에서 고유 식별자로 사용하는 데이터.
        //(100%로 중복을 방지하는건 아님 ) 년월일시분초+UUID
        for(int i = 0 ; i < 30 ; i++){
            UUID random = UUID.randomUUID();
            list.add(new KakaoDTO(R.drawable.gear , random.toString() , "MSG" + i , "15:0" + i));
        }
        ChatAdapter adapter = new ChatAdapter(list , inflater);
        listView.setAdapter(adapter);
        return rootView;
    }
}