package com.example.project4_lastproject.employees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4_lastproject.R;

import java.util.List;


public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{

    LayoutInflater inflater;
    List<EmployeeVO2> list;


    public EmployeeAdapter(LayoutInflater inflater, List<EmployeeVO2> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.emp_item , parent ,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i ) {
        holder.tv_id.setText(list.get(i).getEmployee_id()+"");
        holder.tv_name.setText(list.get(i).getName());
        holder.tv_part.setText(list.get(i).getDepartment_name());
        holder.tv_city.setText(list.get(i).getCity());
        holder.tv_country.setText(list.get(i).getCountry_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id , tv_name , tv_part , tv_city , tv_country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id= itemView.findViewById(R.id.tv_id);
            tv_name= itemView.findViewById(R.id.tv_name);
            tv_part= itemView.findViewById(R.id.tv_part);
            tv_city= itemView.findViewById(R.id.tv_city);
            tv_country= itemView.findViewById(R.id.tv_country);

        }
    }
}
