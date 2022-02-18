package com.example.project04_lastproject.member;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JoinActivity extends AppCompatActivity {
    ImageView img_profile;
    EditText edt_id , edt_name , edt_pw ;
    //RadioButton rdo_man , rdo_woman;
    RadioGroup rdo_grp ; // <= 버튼을 따로 따로 사용을 하면 메소드나 if문 공부(알고리즘)가 되나 불편함.
    Button btn_join , btn_cancel;
    MemberVO vo = new MemberVO(); //비어있는 상태의 vo를 하나 만듬.
    String[] spn_item = { "카메라" , "갤러리"  };

    public final int CAMERA_CODE = 1004; //카메라 액티비티 실행 ( 암시,묵시 ) 제어권을 x
    public final int GELLARY_CODE = 1005;//갤러리 액티비티 실행 ( 암시,묵시 ) 제어권을 x

    File imgFile = null;
    String imgFilePath = null;
    //EditText[] edt_arr = new EditText[3]; //어떤 타입의 클래스든 대부분은 배열,List 자료구조형으로 만들수있음.
    //ArrayList<EditText> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        checkDangerousPermissions();
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        rdo_grp = findViewById(R.id.rdo_grp);
        img_profile = findViewById(R.id.img_profile);
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_pw = findViewById(R.id.edt_pw);
        //rdo_man = findViewById(R.id.rdo_man);
        //rdo_woman = findViewById(R.id.rdo_woman);
        btn_join = findViewById(R.id.btn_join);
        btn_cancel = findViewById(R.id.btn_cancel);

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // [ Editext ] [ EditText ] [ EditText ].
                if(edt_id.getText().length() == 0 && edt_pw.getText().length() == 0 && edt_name.getText().length() == 0 )
                {
                    Toast.makeText(JoinActivity.this, "데이터를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                vo.setId(edt_id.getText()+"");
                vo.setPw(edt_pw.getText()+"");
                vo.setName(edt_name.getText()+"");
                AskTask task = new AskTask("join");
                Gson gson = new Gson();
                task.addParam("vo", gson.toJson( vo ) );// Vo => json(String)으로 바꿔서 추가
                if(imgFilePath != null){
                    task.addFileParam("file" , imgFilePath);
                }
                CommonMethod.excuteGet(task);

                finish();

            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        rdo_grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.rdo_man == checkedId){
                    vo.setGender("남");
                }else{
                    vo.setGender("여");
                }
            }
        });

        if (email != null){
            edt_id.setText(email);
            edt_id.setEnabled(false);
        }

    }

    public void showDialog(){
        // context( this , JoinActivity.this )
        //현재위치가 Activity.class바디부분인지.
        // ↑ 인터페이스나 익명의 객체의 바디부분{}에서 작업하는 경우.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("업로드 방법 선택")
                .setSingleChoiceItems(spn_item, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int index) {
                        if(spn_item[index].equals("카메라")){
                            go_Camera();
                        }else{
                            go_gallery();
                        }
                       dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void go_gallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser( intent , "Select Picture"   ) , GELLARY_CODE );
    }



    public void go_Camera(){
        //명시적 x , 암시,묵시적 인텐트 실행 => Camera기능을 호출.
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //인텐트가 사용이 가능한지 ( MediaStore기능이 사용가능한지 체크 )
        if(intent.resolveActivity( getPackageManager() ) != null){
            //이미지 파일을 만들고 저장하는 처리가 필요함.
            imgFile = null ;
            imgFile = createFile();

            if(imgFile != null){
                // API 24이상 부터는 FileProvider를 제공해야함
                // Context <=
                Uri imgUri = FileProvider.getUriForFile(
                            getApplicationContext(),
                        getApplicationContext().getPackageName()+".fileprovider",
                        imgFile
                );
                //버전 분기를 위한 처리.
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    intent.putExtra(MediaStore.EXTRA_OUTPUT , imgUri); // imgUri를 통해서 카메라로 찍은사진을 받음.
                }else{
                    intent.putExtra(MediaStore.EXTRA_OUTPUT , Uri.fromFile(imgFile));
                }

                startActivityForResult(intent , CAMERA_CODE);

            }


        }
    }

    public String getGalleryRealPath(Uri contentUri){
        String rtnPath = null;
        String[] paths = {MediaStore.Images.Media.DATA};//<=
        Cursor cursor = getContentResolver().query(contentUri , paths , null , null , null);
        if(cursor.moveToFirst()){
            int columns_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            rtnPath = cursor.getString(columns_index);
        }
        cursor.close();
        return rtnPath;
    }



    // requestCode <= 여러가지 액티비티를 실행하고 (하나의액티비티) 결과를 하나의 메소드에서 처리하기때문에.
    // 어떤 액티비티가 끝났는지를 구별해야함. ( CameraActivity 1004 , GalleryActivity 1005 .... )
    // 카메라로 사진을 찍게 되면 임시파일을 우리가 만들고 그파일에 실제 사진을 넣고 파일은 임시파일이 아닌상태가 됨.
    // 갤러리 <= 이미 있는 사진을 넘겨 받아야함 ( Intent <= Null이 아닌상태 )
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_CODE && resultCode == RESULT_OK){
            Toast.makeText(JoinActivity.this, "사진을 잘찍었음.", Toast.LENGTH_SHORT).show();
            Glide.with(JoinActivity.this).load(imgFilePath).into(img_profile);
        }else if(requestCode == GELLARY_CODE && resultCode == RESULT_OK){
            Toast.makeText(JoinActivity.this, "갤러리 사진 가져옴", Toast.LENGTH_SHORT).show();
            //getContentResolver.query <= 경로를 받아오는 처리. 실제 저장경로 Uri를 알아옴.
            Uri selectImageUri = data.getData();
            imgFilePath = getGalleryRealPath(selectImageUri);
            Glide.with(JoinActivity.this).load(imgFilePath).into(img_profile);
        }
    }

    //파일 생성을 위한처리 .
    public File createFile(){
        //파일 이름이 중복되거나 하는것을 방지하기 위한 처리.
        String timeTemp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "My" + timeTemp ;
        // 이름만들기 끝 , 경로를 만들기 시작
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//경로생성을 위한 처리
        File rtnFile = null ;

        //임시파일이 생성 됨.

        try {
            rtnFile = File.createTempFile(imageFileName , ".jpg" , storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgFilePath = rtnFile.getAbsolutePath();
        return rtnFile;

    }


    // 위험권한
    private void checkDangerousPermissions() {
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_MEDIA_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }




}//class