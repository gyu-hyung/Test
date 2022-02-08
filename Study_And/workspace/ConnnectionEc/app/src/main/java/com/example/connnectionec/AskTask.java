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

//AsyncTask
//1.ectends <-
public class AskTask  extends AsyncTask<String , String , InputStream> {
    HttpClient httpClient;//접속위한 객체
    HttpPost httpPost;  //post요청을 하기위한 객체
    MultipartEntityBuilder builder;
    //Web => Web (File 빼고는 Multipart처리가 필요없음)
    //http => http 프롵토콜같음 req res
    //Android ,Iot ,ㄱ기(여러가지) => Web(Was) 요청 Multipart 처리가 필요
    //http => http 프로토콜의 바디 부분에 데이터를 써서 나눠서 보내는형태192.168.0.13
    final String HTTPIP = "http://192.168.0.13";//ipconfig
    final String SVRPATH = "/01.Middle/";
    String mapping ;
    private String postUrl;
    ArrayList<ParamDTO> params = new ArrayList<>();

    public AskTask(String mapping) { this.mapping = mapping; }

    public void addParam(String key, String value){
        params.add(new ParamDTO( key ,value ));
    }


    //    MainActivity activity;
//    ArrayList<String> params;
 //   int str ;



/*

    public AskTask(String mapping, String paramdata) {
        this.mapping = mapping;
        this.paramdata = paramdata;
    }

    public AskTask(String mapping, ArrayList<String> params, int a) {
        this.mapping = mapping;
        this.params = params;
        this.str = str;
    }

    public AskTask(String mapping, ArrayList<String> params) {
        this.mapping = mapping;
        this.params = params;
    }

    public AskTask(String mapping, MainActivity activity) {
        this.mapping = mapping;
        this.activity = activity;
    }

    public AskTask(String mapping) {
        this.mapping = mapping;
    }


*/



    /*리턴타입 : 위에 3번째 리절트 파라미터와 속성이같음*/
    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + mapping ; //url에 넣고 enter key쳤을때 (요청,주소)
        builder = MultipartEntityBuilder.create();//빌더 초기화 식(갖다쓰면댐)
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//인터넷 켜놓고 엔터치는형식
        //↑윗부분은 고청된부분


        /*
        //나중에 파라메터를 추가하는부분 (나중에 추가할것)
        //파라메터를 입력받는부분을 확장성있게 코딩을하면 계속해서 사용가능함.
        //Multipart (여러부분으로 나누어진 ) 에서 데이터를 보낸땐 String
        // => json [] 리스트 ,  {} 오브젝트
        */
        for(int i=0; i < params.size() ; i++){
            builder.addTextBody( params.get(i).getKey() , params.get(i).getValue()
            ,ContentType.create("Multipart/releated" , "UTF-8"));
        }



       /* builder.addTextBody( "param" , Integer.toString(str) ,
                ContentType.create("Multipart/related","utf-8"));

        for(int i =0; i<params.size(); i++){
            builder.addTextBody( "param"+ i , params.get(i) ,
                    ContentType.create("Multipart/related","utf-8"));

        }
        builder.addTextBody( "key" , "value" ,
                ContentType.create("Multipart/related","utf-8"));*/


        //고정된부분 (고정된 부분은 절대 외우거나 깊게 공부할필요가없음
        //대충의 흐름을 이해하는게 중요
        httpClient = AndroidHttpClient.newInstance("Android");//요청한 플랫폼(안드)고정
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());
        InputStream in = null;

        try {

             in = httpClient.execute(httpPost).getEntity().getContent();//실제 enter key <-
           // Gson gson = new Gson();
          //  MemberDTO dto = gson.fromJson(new InputStreamReader(in), MemberDTO.class);//지손바꾸기
           // String aaa = "";
           // rtnData = rtnString(in); <=초기 Servlet(미들웨어 ) 만들고 테스트용.
            //activity.setText(aa);
        //    MainActivity.tv_data.setText( rtnString(in) );
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
