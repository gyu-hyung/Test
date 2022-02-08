package com.example.testproject02;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {


    UserDTO dto;

    TextView tvid , tvname , tvbirth;
    public MainFragment(UserDTO dto) {
        this.dto = dto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        tvid = rootview.findViewById(R.id.tvid);
        tvname = rootview.findViewById(R.id.tvname);
        tvbirth = rootview.findViewById(R.id.tvbirth);

        /*tvid.findViewById(R.id.tvid);
        tvname.findViewById(R.id.tvname);
        tvbirth.findViewById(R.id.tvbirth);*/

        tvid.setText(dto.getId());
        tvname.setText(dto.getName());
        tvbirth.setText(dto.getBirth());
















        return rootview ;
    }
}