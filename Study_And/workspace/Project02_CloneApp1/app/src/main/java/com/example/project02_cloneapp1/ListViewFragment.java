package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListViewFragment extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
        // ※ 액티비티는 find, Fragment 붙여놓은 rootView에서 find로 찾음.
        // 자바 Data => ListView에 넣기 위한 객체(Adapter)
        //기본적으로 제공해주는 어댑터는 xml을 따로가지고있음. ( TextView하나들어있는형태부터...여러가지)
        //대부분 사용할때는 커스터마이징을함 ( 이미지뷰 여러개 , 텍스트뷰 여러개 , 버튼...등등 )
        //앞으로 어플리케이션(App)을 볼때 일관된 모양으로 반복이 되는 구간은(레이아웃) 90%이상
        //ListView또는 ReyclerView같은 위젯을 묶어놓은 목록형태의 위젯이다.
        listView = rootView.findViewById(R.id.listview);
        //기본적인 형태의 어댑터 ArrayAdapter (사용 빈도 낮음 ) String값만 텍스트뷰로 보여주기때문에.
        //디자인 적으로 이쁘지 않음.
        //어댑터에 셋팅되는 데이터타입은 List구조 또는 index를 가진구조여야만 함.
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1 ; i <= 30 ; i++){
            list.add("데이터" + (i));
        }
        ArrayAdapter adapter = new ArrayAdapter(getContext() , android.R.layout.simple_list_item_1,list );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getItemAtPosition(position)+"", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}