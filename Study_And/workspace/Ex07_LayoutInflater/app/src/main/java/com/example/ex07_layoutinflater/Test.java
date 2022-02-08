package com.example.ex07_layoutinflater;

import android.content.Context;
import android.view.LayoutInflater;

public class Test {
    public void onCreate(Context context) {
        //일반 Class에서는 Context를 받아와야함 (제어권 같은 개념)
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //일반 클래스와 Acivity또는 디자인단의 기능을 상속받은 클래스의 차이는
        //Os에서 제공하는 기능을 사용할 수 있는지 없는지 차이가 가장 크다
    }
}
