package com.example.project04_lastproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.customer.CustomerMainFragment;
import com.example.project04_lastproject.employees.EmployeeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btm_nav;

    Fragment nowFragment ;
    Fragment backFragment ;
    int index = 0 ;
    //SWAP을 이용하면 이전과 현재 프래그먼트를 저장해놓고 동적으로 사용하는것이 가능함.
    //addBackStackTrace등 제공하는 메소드
    // ( <= 될때가 있고 안될때가있음 , 자바코드로 직접 작성을 하면 디버깅과 안정성이 더 좋음 )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHashKey();
        btm_nav = findViewById(R.id.btm_nav);

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //
        nowFragment = new CustomerMainFragment();
        changeFragment(nowFragment);
        //AskTask askTask = new AskTask("aaa.te");
        //askTask.execute();

        btm_nav.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });
        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                if(! nowFragment.getClass().isInstance( backFragment ) ){
                    backFragment = nowFragment ; // 두개의 프래그먼트가 다르다면 .
                }


                if(item.getItemId() == R.id.menu_cus){
                    nowFragment = new CustomerMainFragment();
                }else if(item.getItemId() == R.id.menu_emp){
                    nowFragment = new EmployeeFragment();
                }
                changeFragment(nowFragment);
                return true;
            }
        });
    }
    //뒤로가기를 눌렀을때 저장해놓을 변수(시간)
    private long backTime = 0;
    @Override   
    public void onBackPressed() {
        //뒤로가기 버튼을 눌렀음을 감지.
        if(System.currentTimeMillis() >  backTime + 2000){
            backTime = System.currentTimeMillis();//뒤로가기를 누른시점에서 시간값을 저장.
            Toast.makeText(MainActivity.this, "뒤로가기 한번 더 누르면 어떤 처리를 하겠습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if(System.currentTimeMillis() <= backTime + 2000){
            //Class의 비교 ? 어떻게 해아할까? instence of 라는 비교문을 사용해야함.
            // istence of 클래스끼리 비교
            // isInstance 같은 타입의 객체를 사용해서 초기화 됐는지를 비교해서 boolean으로 리턴을해줌.
            // boolean isChecked = nowFragment.getClass().equals( CustomerMainFragment.class );
            // Log.d("gbgg", "onBackPressed: a" + nowFragment.getClass().equals( CustomerMainFragment.class ) + "");
//            Log.d("gbgg", "onBackPressed: b" + CustomerMainFragment.class.isInstance(nowFragment.getClass())  + "");
//            if(isChecked  == true){
//                String aaa = "";
//            }else if ( isChecked == false){
//                String aaa = "";
//            }
            //바텀네비게이션 == Fragment가 연동이 되어있는 상태에서는 바텀네비게이션의 아이템 선택부분을 바꿔주면 된다.
//            if(btm_nav.getSelectedItemId() == R.id.menu_cus){
//
//            }
            if(CustomerMainFragment.class.isInstance(nowFragment)){
                String aa = "";
                finish();
            }else{
                String aa = "";
            }//else 부분이 비정상적으로 실행이 안되는 블럭으로 인식이 됨.

            Toast.makeText(MainActivity.this, "종료됨.(사실종료안됨)", Toast.LENGTH_SHORT).show();
        }
        
        
        
        
    }

        // backFragment 실행 해야되는 순간이 있음.
        // nowFragment 실행 해야되는 경우도 있음
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container , fragment).commit();
    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}