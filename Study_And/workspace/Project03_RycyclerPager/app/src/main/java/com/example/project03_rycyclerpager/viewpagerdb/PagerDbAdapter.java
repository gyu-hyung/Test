package com.example.project03_rycyclerpager.viewpagerdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;

public class PagerDbAdapter extends RecyclerView.Adapter<PagerDbAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<Integer> imgs;

    public PagerDbAdapter(LayoutInflater inflater, ArrayList<Integer> imgs) {
        this.inflater = inflater;
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.pager_item,parent , false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pagerImgv.setImageResource(imgs.get(position));
    }



    @Override
    public int getItemCount() {
        return imgs.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pagerImgv;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pagerImgv = itemView.findViewById(R.id.pager_imgv);
        }
    }




}
