package com.example.project03_rycyclerpager.viewpager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project03_rycyclerpager.R;

// Adapter가 완전히 똑같은 형태로 사용이 가능함.
// RecyclerView와 완전히 똑같음.
public class Pager2Adapter extends RecyclerView.Adapter<Pager2Adapter.ViewHolder>{

    LayoutInflater inflater;
    // inflater를 만들수있는 Context를 받아오거나 , LayoutInflater자체를 받아오기.
    public Pager2Adapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    // 칸마다 보여줄 데이터를 연결해서 ViewHolder라는 클래스 형태로 묶어둠.
    // LayoutInflater ↓
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rec_item , parent , false);
       // ViewHolder viewHolder = new ViewHolder(itemView);
        return new ViewHolder(itemView);
    }

    //묶어진 데이터가 정상적으로 View(Recycler,Pager2 ..등) 붙고나서 처리.
    //이벤트 처리 ( Onclick 등등 )
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }
    //몇개의 데이터를 보여줄건지를 정의 . ArrayList나 인덱스(크기)를 가진 데이터를 기준으로
    //정함.
    @Override
    public int getItemCount() {
        return 20;
    }

    //1. InnerClass로 VH ( ViewHolder ) 클래스 만들기.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
