package com.example.project02_cloneapp1.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project02_cloneapp1.R;

import java.util.ArrayList;

//Recycler Adapter (마스터) ==> ViewPager2 (똑같은 어댑터를 사용하는 목록뷰를 사용가능)
//RecyclerView의 특성(list,grid) ViewHolder 강제 한다. ( 미리 만들어놔야만 어댑터를 상속받을수있음)
//ViewHolder ( 개발자가 만들어놓은 위젯들을 묶어놓은 클래스 ) == 필드의 갯수는 xml에 있는 위젯갯수와 대부분 같음
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{


    ArrayList<RecDTO> list;
    LayoutInflater inflater;
    Context context;

    //list == null , inflater == null ( 선언만 해둔 상태는 항상 null )

    public RecAdapter(ArrayList<RecDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }


    // xml을 인플레이트 시키는 처리 (listview) tag를 통해서 ViewHolder를 담아두는 행위 등↓
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.rec_item , parent , false);
        return new ViewHolder(itemView);
    }

    // on↑ 처리가 끝나고 나서 ViewHolder 가 null이 아닐때 이벤트 처리를 하기위해 만들어주는 메소드
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder , position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //1.ViewHolder를 만든다.(Inner Class)
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_image;
        TextView tv_str;
        //↓ Viewholder에 있는 변수(객체)와 디자인에 있는 위젯 연결부 ( getView)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            tv_str = itemView.findViewById(R.id.tv_str);
        }
        //Nullness Annotations
        //@NonNull <= null을 허용하지 않는 경우 ( 변수에 Null이 들어오면 Critical한 오류가 발생할수있음 주의)
        //@Nullable <= null을 허용하는 경우 ( Null이 들어와도 알아서 처리를 하거나 오류가 작은 경우 )
        public void bind(@NonNull ViewHolder holder, int position) {
            holder.profile_image.setImageResource(list.get(position).getResId());
            holder.tv_str.setText(list.get(position).getTextStr());
        }
    }
}
