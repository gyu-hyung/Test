package com.example.project04_lastproject.member;

import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.example.project04_lastproject.common.CommonVal;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

public class MemberDAO {
    Gson gson = new Gson();
    public boolean isLogin(String id , String pw){
        AskTask task = new AskTask("andLogin");
        task.addParam("id",id);
        task.addParam("pw",pw);
        InputStream in = CommonMethod.excuteGet(task);
        try{
            CommonVal.loginInfo = gson.fromJson(new InputStreamReader(in),MemberVO.class);
            if (CommonVal.loginInfo != null) return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
