package com.example.project02_cloneapp1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Fragment1 extends Fragment {

    Context context;// 90%이상 오류 안남. (제일 안전한 방법)

    public Fragment1(Context context) {
        this.context = context;
    }

    //Fragment를 만들게 되면 불필요한 코드가 매우 많이 많이 존재한다. final상태로 인자값을 한번만 입력받게하는
    //변수, OnCreate() 메소드 등등.
    //실제로 제일 중요하고 필요한 코드는 onCreateView이다
    //onCreate<- Activity ( Context를 가진 )
    //onCreateView<- Fragment,View객체 ( Context를 가지지 않은 , 화면에 혼자 떠있을수 없는 )
    //=> inflater.inflate 화면을 붙이는 행위가 onCreateView에 있기 때문. (find)
    TextView frag1_tv;
    TextView frag1_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        //↑ fragment_1이라는 xml에 있는 모든 위젯들이 묶여서 ViewGroup에 담김.
        //context를 받아오는 방식.

        Toast.makeText(rootView.getContext(), "메세지1", Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "메세지2", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "메세지3", Toast.LENGTH_SHORT).show();
        frag1_tv = rootView.findViewById(R.id.frag1_tv);
        frag1_btn = rootView.findViewById(R.id.frag_btn1);
        frag1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag1_tv.setText("버튼 눌림.");
                //화면이 엑티비티에 붙고나서 상위 화면에 있는 context를 받아오는 메소드.
                //화면이 붙기전에 사용을 하면 어플이 강제종료가됨.
                TestClass testClass = new TestClass();
                testClass.toastShow(rootView.getContext() , "메세지1");
                testClass.toastShow(context , "메세지2");
                testClass.toastShow(getContext() , "메세지3");
                testClass.toastShow(getActivity() , "메세지4");

                //Toast.makeText(rootView.getContext(), "메세지1", Toast.LENGTH_SHORT).show();
               // Toast.makeText(context, "메세지222221", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getContext(), "메세지2", Toast.LENGTH_SHORT).show();
               // Toast.makeText(getActivity(), "메세지3", Toast.LENGTH_SHORT).show();
            }
        });
        frag1_tv.setText("글씨를 바꿔 봅니다.");
        //fragmnet1<- 버튼 추가하고 온클릭리스너 통해서 frag1_tv에 있는 글씨를 버튼클릭됨으로 바꾸기.


        return rootView;
    }
}