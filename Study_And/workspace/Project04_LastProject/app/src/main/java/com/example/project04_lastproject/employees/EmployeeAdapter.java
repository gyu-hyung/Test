package com.example.project04_lastproject.employees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project04_lastproject.R;

import java.util.List;
import java.util.Random;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    List<EmployeeVO> list ;
    Context context ;
    LayoutInflater inflater ;
    int cnt = 0 ;
    public EmployeeAdapter(List<EmployeeVO> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(cnt == 0 ){
            View itemView = inflater.inflate(R.layout.emp_item , parent,false);
        }
        View itemView = inflater.inflate(R.layout.emp_item , parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder , position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_empid , tv_deptname , tv_city , tv_ctrname , tv_name;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_empid = itemView.findViewById(R.id.tv_empid);
            tv_deptname = itemView.findViewById(R.id.tv_deptname);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_ctrname = itemView.findViewById(R.id.tv_ctrname);
            tv_name = itemView.findViewById(R.id.tv_name);
            layout = itemView.findViewById(R.id.emp_lnlayout);
        }

        public void bind(@NonNull ViewHolder holder, int i) {
            if(list.get(i).getEmployee_id() == 100){
                layout.setBackgroundResource(R.drawable.card_bg2);
            }else if (list.get(i).getEmployee_id() % 2 == 0){
                layout.setBackgroundResource(R.drawable.card_bg3);
            }
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmployeeDialog dialog = new EmployeeDialog(context);
                    dialog.show();
                }
            });

            holder.tv_empid.setText("( no : " + list.get(i).getEmployee_id()+")");
            holder.tv_deptname.setText(list.get(i).getDepartment_name());
            holder.tv_city.setText(list.get(i).getCity());
            holder.tv_ctrname.setText(list.get(i).getCountry_name());
            holder.tv_name.setText(list.get(i).getName());

        }
    }
}
