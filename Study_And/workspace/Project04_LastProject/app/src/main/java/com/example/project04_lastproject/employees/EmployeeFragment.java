package com.example.project04_lastproject.employees;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class EmployeeFragment extends Fragment {
    RecyclerView emp_rcv;
    Gson gson = new Gson();
    SwipeRefreshLayout swipe ;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.fragment_employee, container, false);
        emp_rcv = itemView.findViewById(R.id.emp_rcv);
        searchView = itemView.findViewById(R.id.emp_srchview);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //검색 버튼을 눌렀을때 까지의 텍스트를 그대로 가져옴
                Toast.makeText(getContext(),"서치뷰 : " + query, Toast.LENGTH_SHORT).show();
                //String query<= 값이용해서 employees에 where조건에 사용해보기.
                search_employee(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //텍스트가 바뀔때 마다 바뀌는 텍스트를 그대로 가져옴.
                search_employee(query);
                return true;
            }
        });

        swipe = itemView.findViewById(R.id.emp_swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refresh가 스와이프 되서 동작하는 상태가 되면 처리할 이벤트.
                Toast.makeText(getContext(), "새로 고침 완료.", Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);//새로고침 화살표를 없애는 처리.
            }
        });
        search_employee();


        //1.어싱크 이용해서 List(목록데이터 먼저 가져오기 )
        //2.Adapter , 2.emp_item.xml , 2.전체목록 조회하기.
        return itemView ;
    }
    //나중에 DAO만들어서 이동시키면 편함.
    public void search_employee(){
        AskTask task = new AskTask("list.hr");
        InputStream in = CommonMethod.excuteGet(task);
        List<EmployeeVO> list = gson.fromJson(new InputStreamReader(in) ,
                new TypeToken<List<EmployeeVO>>(){}.getType());
        EmployeeAdapter adapter = new EmployeeAdapter(list,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(
                getContext() , RecyclerView.VERTICAL , false
        );
        emp_rcv.setAdapter(adapter);
        emp_rcv.setLayoutManager(manager);
    }
    public void search_employee(String search){
        AskTask task = new AskTask("list.hr");
        task.addParam("search" , search);
        InputStream in = CommonMethod.excuteGet(task);
        List<EmployeeVO> list = gson.fromJson(new InputStreamReader(in) ,
                new TypeToken<List<EmployeeVO>>(){}.getType());
        EmployeeAdapter adapter = new EmployeeAdapter(list,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(
                getContext() , RecyclerView.VERTICAL , false
        );
        emp_rcv.setAdapter(adapter);
        emp_rcv.setLayoutManager(manager);
    }
}