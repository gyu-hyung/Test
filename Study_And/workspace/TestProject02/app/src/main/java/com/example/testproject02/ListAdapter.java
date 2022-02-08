package com.example.testproject02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    ArrayList<ListDTO> list;
    LayoutInflater inflater;

    public ListAdapter(ArrayList<ListDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        return null;
    }




}
