package com.example.project04_lastproject.employees;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.project04_lastproject.R;

public class EmployeeDialog extends Dialog {

    public EmployeeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_emp);
        findViewById(R.id.dialog_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();//다이아로그를 안보이게 하는처리 , show();
            }
        });

    }
}
