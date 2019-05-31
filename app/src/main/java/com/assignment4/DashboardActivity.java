package com.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import adapter.ItemsAdapter;
import model.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnAdd;
    private ImageView refresh;
    private List<Items> heroesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerview);
        btnAdd = findViewById(R.id.btnAdd);
        refresh = findViewById(R.id.refresh);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        API.UserAPI heroesAPI = retrofit.create(API.UserAPI.class);
        Call<List<Items>> listCall = heroesAPI.getAllItems();

        listCall.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {

                if (response.isSuccessful()){
                    heroesList = response.body();
                    ItemsAdapter adapter = new ItemsAdapter(DashboardActivity.this,heroesList);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
