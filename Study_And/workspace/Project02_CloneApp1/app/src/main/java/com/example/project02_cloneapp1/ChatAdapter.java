package com.example.project02_cloneapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {
    //ArrayList <- xml로 만들어놓은 아이템을 몇개정도 붙일건지
    ArrayList<KakaoDTO> list;
    LayoutInflater inflater;

    public ChatAdapter(ArrayList<KakaoDTO> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int index) {
        return list.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }
    //실제 화면을 만드는 부분.
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ChatViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_chat_item, parent , false);
            viewHolder = new ChatViewHolder();
            viewHolder.imgv = convertView.findViewById(R.id.list_imgv);
            viewHolder.tv1 = convertView.findViewById(R.id.list_tv1);
            viewHolder.tv2 = convertView.findViewById(R.id.list_tv2);
            viewHolder.tv3 = convertView.findViewById(R.id.list_tv3);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ChatViewHolder) convertView.getTag();
        }

        viewHolder.imgv.setImageResource(list.get(i).getImgId());
        viewHolder.tv1.setText(list.get(i).getName());
        viewHolder.tv2.setText(list.get(i).getMsg());
        viewHolder.tv3.setText(list.get(i).getDate());

        return convertView;
    }

    public class ChatViewHolder{
        public ImageView imgv;
        TextView tv1 , tv2 , tv3;//tv1=이름 , tv2=메세지 , tv3=시간
    }

}
