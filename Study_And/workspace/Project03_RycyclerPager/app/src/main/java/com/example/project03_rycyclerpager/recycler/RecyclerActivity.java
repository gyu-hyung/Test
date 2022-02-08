package com.example.project03_rycyclerpager.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView rcv_act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rcv_act = findViewById(R.id.rcv_act);

        ArrayList<RecDTO> list = new ArrayList<>();
        list.add(new RecDTO(R.drawable.ic_launcher_background , "텍스트1"));
        list.add(new RecDTO(android.R.drawable.ic_dialog_email, "텍스트2"));
        list.add(new RecDTO(android.R.drawable.stat_sys_warning, "텍스트3"));
        list.add(new RecDTO(R.drawable.ic_launcher_foreground , "텍스트4"));
        list.add(new RecDTO(android.R.drawable.ic_menu_camera, "텍스트5"));

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        RecAdapter adapter = new RecAdapter(list, inflater);
        rcv_act.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(
                RecyclerActivity.this , RecyclerView.VERTICAL , false
        );

        rcv_act.setLayoutManager(manager);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container,new RecFragment(list)).commit();

    }
}