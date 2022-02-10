package com.example.project4_lastproject.employees;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project4_lastproject.R;
public class EmployeeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.fragment_employee, container, false);



        return itemView;
    }
}