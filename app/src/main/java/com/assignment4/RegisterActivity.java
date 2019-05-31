package com.assignment4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.UserApi;
import model.Users;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etPassword;
    private Button btnSave;
//    private ImageView imgPhoto;
//    String imagepath,imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPass);
        btnSave = findViewById(R.id.btnRegister);
//        imgPhoto = findViewById(R.id.imgPhoto);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           save();
            }
        });
//
//        imgPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                browseImage();
//            }
//        });
   }
//
//    private void browseImage(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,0);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK){
//            if (data == null){
//                Toast.makeText(RegisterActivity.this,"please select an image",Toast.LENGTH_LONG).show();
//            }
//        }
//        Uri uri = data.getData();
//        imagepath = getRealPathFromUri(uri);
//        previewImage(imagepath);
//    }
//
//    private String getRealPathFromUri(Uri uri){
//        String[] projection = {MediaStore.Images.Media.DATA};
//        CursorLoader loader = new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
//        Cursor cursor = loader.loadInBackground();
//        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(colIndex);
//        cursor.close();
//        return result;
//    }
//
//    private void previewImage(String imgaepath){
//        File imgFile = new File(imagepath);
//        if (imgFile.exists()){
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//            imgPhoto.setImageBitmap(myBitmap);
//        }
//    }
//    private void StrictMode(){
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//    }
//    private void SaveImageOnly(){
//        File file = new File(imagepath);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",file.getName(),requestBody);
//
//        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);
//        Call<ImageResponse> responseBodyCall = heroesAPI.uploadImage(body);
//
//        StrictMode();
//
//        try{
//            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
//            imageName = imageResponseResponse.body().getFilename();
//
//        }catch (IOException e){
//            Toast.makeText(ImageActivity.this,"error",Toast.LENGTH_LONG).show();
//            e.printStackTrace();
//        }
//    }

    private void save(){
//        SaveImageOnly();
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();

        Map<String,String> map = new HashMap<>();
        map.put("username",name);
        map.put("password",password);
//        map.put("image",imageName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);
        Call<Void> userCall =  userApi.addUser1(map);

        userCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"code " +response.isSuccessful(),Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this,"Added successfully",Toast.LENGTH_LONG).show();
            }



            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"error " +t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

  }
}
