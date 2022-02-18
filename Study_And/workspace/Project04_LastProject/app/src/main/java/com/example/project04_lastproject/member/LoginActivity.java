package com.example.project04_lastproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project04_lastproject.MainActivity;
import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.example.project04_lastproject.common.CommonVal;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.Profile;

import java.io.InputStream;
import java.io.InputStreamReader;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    EditText edt_id, edt_pw;
    Button btn_login, btn_join;
    CheckBox chk_auto;
    ImageView imgv_kakao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        KakaoSdk.init(this, "e803d6b84d8020a973ab4a276d48eeb8");

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        btn_join = findViewById(R.id.btn_join);
        chk_auto = findViewById(R.id.chk_auto);

        imgv_kakao = findViewById(R.id.imgv_kakao);
      //  if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
      //      UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->

        Function2<OAuthToken , Throwable , Unit> callBack = new
                Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                        // 소셜 : Token에 정보를 넣어서 바로 주는경우 <=o,
                        // Token을 통해서 특정 URL을 요청했을때 정보를 주는 경우.
                        if(throwable != null){
                            Toast.makeText(LoginActivity.this, "오류 발생!?" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if(oAuthToken != null){
                            Toast.makeText(LoginActivity.this, "정보를 잘받아옴!", Toast.LENGTH_SHORT).show();
                            getKakaoInfo();
                        }


                        return null;
                    }
                };


        imgv_kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    //카카오톡이 설치가 되어있는 핸드폰의 경우 카카오톡 어플을 통해서 인증하는 방법이 더편리함.
                    //카카오톡 특성상 로그인이 되어있고 2차인증을 하기만 하면됨.
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,callBack );
                }else{
                    //카카오톡이 설치가 안되어 있는경우. Web을 통해서(Redirect Uri) Activity?
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,callBack );
                }
            }
        });


        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id","--");
        String pw = preferences.getString("pw","--");
        Boolean isLogin = preferences.getBoolean("autologin" , false);
        chk_auto.setChecked(isLogin); // 자동로그인을 체크하고나서 앱을 종료해도 그대로 저장된상태를 보여줌.


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText() + "";
                String pw = edt_pw.getText() + "";
                MemberDAO dao = new MemberDAO();
                if (dao.isLogin(id , pw)) {
                    // 기존 로그인 => 하드코딩 (테스트용) id.equals("aaasa") && pw.equals("bbbb")
                    saveLoginInfo();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 확인바랍니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 추후 카메라 ( Img WAS 전송 처리 부분 )
                // Spring이 익숙해지고 나서 처리할 부분.
                Intent intent = new Intent(LoginActivity.this , JoinActivity.class);
                startActivity(intent);
            }
        });


        if(isLogin){
            edt_id.setText(id);
            edt_pw.setText(pw);
            btn_login.callOnClick(); // OnClick을 강제로 실행함.
        }
    } // onCreate

    public void saveLoginInfo() {
        //체크박스 자동로그인이 체크가 된 상태라면 임시 데이터를 저장함 ( 로그인 정보를 )
        try {
            SharedPreferences preferences  = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            if (chk_auto.isChecked()) { //로그인 정보를 저장함.
                editor.putBoolean("autologin" , true);
                editor.putString("id", edt_id.getText() + "");
                editor.putString("pw", edt_pw.getText() + "");
            } else {  // 로그인 정보를 삭제함.
                editor.remove("autologin");
                editor.remove("id");
                editor.remove("pw");
                //editor.clear();
            }
            editor.apply();
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "자동로그인 정보 저장 실패.", Toast.LENGTH_SHORT).show();
        }
    }
    //카카오로 로그인이 성공을 해도 내가 만든 앱의 사용자인지는 판단을 해야함.
    //=> select 정보 where id=>email => 회원가입 페이지로 이동시키기.
    public void getKakaoInfo(){
        UserApiClient.getInstance().me( (user, throwable) -> {
        if(throwable != null){
            // 오류임. 정보 못받아옴 ( Token이 없거나 Token을 삭제했을때(Logout)
            // KOE + 숫자
            Toast.makeText(LoginActivity.this, "오류 코드 : " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }else{
            // [ { } ] json 구조처럼 바로 데이터가 있는게 아님.
            // Account Object안에 List가 있거나 List안에 Object가 있는형식임.
            Account kakaoAcount = user.getKakaoAccount();
            if(kakaoAcount != null){
                String email = kakaoAcount.getEmail();
                Profile profile = kakaoAcount.getProfile();
                String nickName = profile.getNickname();
                AskTask task = new AskTask("kakaoLogin");
                task.addParam("id" , email);
                InputStream in =  CommonMethod.excuteGet(task);
                Gson gson = new Gson();
                MemberVO vo = gson.fromJson(new InputStreamReader(in) , MemberVO.class);
                if(vo!=null){
                    //로그인 된 회원임.
                    CommonVal.loginInfo = vo ;
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                }else{
                    //회원가입을 진행.
                    Intent intent = new Intent(LoginActivity.this , JoinActivity.class);
                    intent.putExtra("email" , email);
                    startActivity(intent);
                }


                // AsynkTask이용해서
            }

        }


            return null;
        });
    }

}