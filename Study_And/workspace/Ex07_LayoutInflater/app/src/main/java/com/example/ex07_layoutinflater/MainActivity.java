package com.example.ex07_layoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear; //<- sub1을 인플레이션 시키기위한 레이아웃
    RelativeLayout relative; //<- sub2를 인플레이션 시키기위한 레이아웃
    //xml (레이아웃파일) 화면 위에 떠있으려면 항상 엑티비티 또는 붙을수있는
    //어떤 도구가 필요하다, xml을 그냥 화면에 보여줄 수 있는 방법은 없음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //액티비티 화면에 붙는 과정, 레이아웃 인플레이션과정이 필요한 상태
        //즉, 화면단에(액티비티) xml 레이아웃을 붙이기 전상태 후상태에 따라서
        //어떠한 위젯을 찾아서 사용이 가능하거나 불가능한 상태가 될 수 있다.
        //레이아웃 인플레이트 시키기 라는 버튼을 눌렀을때, MainActivity의 Linear에
        //만들어놓은 sub1.xml을 붙이기 ( LayoutInflater <- xml디자인을 붙이기위한 도구)
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear = findViewById(R.id.linear); //디자인 위젯 <-> .java
                relative = findViewById(R.id.relative);
                // Context ( Os에서 제공하는 기능을 사용할 수 있는 범위 )
                // Context가 붙고 안 붙고 차이에 따라 Inflater를 사용할 수 있냐, 받아와서 사용 하냐가 나뉨
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                //안드로이드에서 제공하는 기능을 사용하기만 해서 안드로이드 프로그래밍은 어려움
                inflater.inflate(R.layout.sub1, linear , true);
                inflater.inflate(R.layout.sub2, relative, true);
                //여기에서 바로 자식레이아웃에 있는 위젯을 찾아서 사용하려고 하면 Error
                //attachToRoot : 별도로 xml과 연결 된 자바 파일이 없는경우 true, false
                //자식레이아웃을 붙인 레이아웃 안에서 위젯을 찾아야함 ▼
                //findViewById 자체가 자신이 가지고(xml) 있는 레이아웃 파일 안에서 위젯을 찾기때문에
                linear.findViewById(R.id.sub1_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Toast.makeText(MainActivity.this, "sub1눌림", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}