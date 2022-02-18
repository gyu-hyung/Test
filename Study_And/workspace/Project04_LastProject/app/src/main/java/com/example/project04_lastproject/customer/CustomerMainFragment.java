package com.example.project04_lastproject.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CustomerMainFragment extends Fragment {
    RecyclerView rcv_cus ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_customer_main, container, false);
        //↑ inflate 된 화면에서 위젯을 찾기위한 처리.
        rcv_cus = rootView.findViewById(R.id.rcv_cus);
        refresh();
        return rootView;
    }//onCreateView

    public void refresh(){
        AskTask task = new AskTask("list.cu");
        InputStream in = CommonMethod.excuteGet(task);
        Gson gson = new Gson();
        List<CustomerVO> list= gson.fromJson(
                new InputStreamReader(in),
                new TypeToken<List<CustomerVO>>(){}.getType()
        );
        // 리사클러뷰는 어댑터 외에도 한가지 필요한게 있음. ( LayoutManager 아이템을 어떤 방향으로 보여줄지를 결정 )
        RecyclerView.LayoutManager manager
                = new LinearLayoutManager( getContext() , RecyclerView.VERTICAL , false );
        CustomerAdapter adapter = new CustomerAdapter( getContext() , list , this ); // <= getContext 오류가 난다면 (null)Context 받아오면됨
        rcv_cus.setLayoutManager(manager);
        rcv_cus.setAdapter(adapter);
    }
}