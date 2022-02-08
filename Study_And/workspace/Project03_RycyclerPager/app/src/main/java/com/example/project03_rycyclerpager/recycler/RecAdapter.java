package com.example.project03_rycyclerpager.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;

//2.↓ 일반 클래스 => 리사이클러뷰 어댑터를 상속받은 객체로 만듬.
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    // 필드부분은 두가지가 필요함 ( LayoutInflater ) Layout파일을 뷰(목록) 한칸마다 붙일수있는
    //기능을 가진 객체

    // 몇개의 아이템을 보여줄지를 결정할 Collection , Index , Length , size
    //구조를 가진 데이터 타입. ( ArrayList , List , LinkedList , Vector , 배열 , 등등 )

    //LayoutInflater <- 어떻게 하면 RecAdapter에서 사용하게 만들수있을까.
    // 디버깅으로 확인해보기 ↑
    ArrayList<RecDTO> list ;
    LayoutInflater inflater;

    public RecAdapter(ArrayList<RecDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }


    //3.뷰홀더를 만들어내는 과정 ( infalter객체를 이용해서 칸마다 아이템을 붙여주는 처리를 함)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rec_item , parent ,false);
      //  ViewHolder v = new ViewHolder(itemView);
        return new ViewHolder(itemView);
    }

    // 4. 아이템이 세팅되고나서 (Inflater되고나서의 처리 )
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.rec_imgv.setImageResource(list.get(i).getRedId());
        holder.rec_tv.setText(list.get(i).getTextStr());
    }
    // 5. 몇개의 아이템을 가진 RecyclerView인지를 정하는 부분.
    @Override
    public int getItemCount() {
        return list.size();
    }

    //1.LayoutInflater과정을 통해서 붙여놓은 ItemView(ViewGroup)를 통해 위젯의 아이디를
    //찾아서 담아놓을수있는 클래스를 미리만든다 ( RecyclerView에서는 강제함 )
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rec_imgv;
        TextView rec_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rec_imgv = itemView.findViewById(R.id.rec_imgv);
            rec_tv = itemView.findViewById(R.id.rec_tv);
        }
    }

}
