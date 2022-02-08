package com.example.project03_rycyclerpager.common;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.example.project03_rycyclerpager.common.ParamDTO;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AskTask extends AsyncTask<String , String , InputStream> {
    HttpClient httpClient;
    HttpPost httpPost;
    MultipartEntityBuilder builder;
    final String HTTPIP = "http://192.168.0.13";
    final String SVRPATH = "/02.Mid/";
    String mapping;
    private String postUrl ;
    ArrayList<ParamDTO> params = new ArrayList<>();
    public AskTask(String mapping) {
        this.mapping = mapping;
    }

    public void addParam(String key , String value){
        params.add(new ParamDTO(key , value));
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + mapping ;//url에 넣고 enter key쳤을때 (요청,주소)
        builder = MultipartEntityBuilder.create();//빌더 초기화식.(가져다가쓰면됨)
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//인터넷 켜놓고 엔터치는 형식

        for(int i=0 ; i<params.size() ; i++){
                builder.addTextBody( params.get(i).getKey()  ,  params.get(i).getValue()
                , ContentType.create("Multipart/related" , "UTF-8") );
        }
        httpClient = AndroidHttpClient.newInstance("Android");//<=요청한 플랫폼(Android고정)
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());
        InputStream in = null ;
        try {
             in = httpClient.execute(httpPost).getEntity().getContent();//실제 enter key <-
        } catch (IOException e) {
            e.printStackTrace();
        }


        return in;
    }//do



}
