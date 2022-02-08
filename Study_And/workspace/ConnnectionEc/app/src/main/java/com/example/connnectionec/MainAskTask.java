package com.example.connnectionec;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.google.gson.Gson;

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

public class MainAskTask extends AsyncTask<String , String , InputStream> {
    HttpClient httpClient;
    HttpPost httpPost;
    MultipartEntityBuilder builder;
    final String HTTPIP = "http://192.168.0.13";//ipconfig
    final String SVRPATH = "/01.Middle/";
    String mapping ;
    private String postUrl;
    ArrayList<ParamDTO> params = new ArrayList<>();

    public MainAskTask(String mapping) { this.mapping = mapping; }
    public void addParam(String key, String value){
        params.add(new ParamDTO( key ,value ));
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + mapping ; //url에 넣고 enter key쳤을때 (요청,주소)
        builder = MultipartEntityBuilder.create();//빌더 초기화 식(갖다쓰면댐)
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//인터넷 켜놓고 엔터치는형식
        //↑윗부분은 고청된부분


        for(int i=0; i < params.size() ; i++){
            builder.addTextBody( params.get(i).getKey() , params.get(i).getValue()
            ,ContentType.create("Multipart/releated" , "UTF-8"));
        }



   httpClient = AndroidHttpClient.newInstance("Android");//요청한 플랫폼(안드)고정
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());
        InputStream in = null;
        try {
             in = httpClient.execute(httpPost).getEntity().getContent();//실제 enter key <-

        } catch (IOException e) {
            e.printStackTrace();
        }


        return in;
    }//doing


    public String rtnString(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder strbrd = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null){
            strbrd.append( line );//strbrd: "";
        }

        return strbrd.toString();
    }







}
