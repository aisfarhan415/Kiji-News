package com.example.kijinews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    RecyclerView homerecycler;
    Retrofit retrofit;
    kijiapi kijiapi;
    kijiAdapter.RecyclerListener recyclerListener;
    ArrayList <kiji> kijiList;
    allarticles Allarticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setonClick();
        initRecycler();

        getArticle();
    }

    private void initRecycler() {
        homerecycler = findViewById(R.id.home_recyclerview);
        homerecycler.setHasFixedSize(true);
    }


    private void initView(){
        initRecycler();
    }

    private void setonClick(){

    }

    //RecylerView

    private void getArticle() {
        Call<allarticles> call = kijiapi.getArticles();

        call.enqueue(new Callback<allarticles>() {
            @Override
            public void onResponse(Call<allarticles> call, Response<allarticles> response) {
                Allarticles = response.body();
                kijiList = new ArrayList<>(Arrays.asList(allarticles.getArticles()));

                showRecycler(kijiList);
            }

            @Override
            public void onFailure(Call<allarticles> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

            private void showRecycler(ArrayList<kiji> kijis) {
        setRecyclerOnClick();
        homerecycler.setLayoutManager(new LinearLayoutManager(
                getApplicationContext()
        ));
        kijiAdapter kijiAdapter = new kijiAdapter(kijis, recyclerListener);
        homerecycler.setAdapter(kijiAdapter);
    }

//    private void setRecyclerOnClick(){
//    recyclerListener = new kijiAdapter() {
//            public void onClick(View v, int position) {
//                Intent intent = new Intent(getApplicationContext(), Detail_News_Activity.class);
//                intent.putExtra("", kijiList.get(position).getId());
//                startActivity(intent);
//            }
//        };
//    }


    private void setRecyclerOnClick() {
        recyclerListener = new kijiAdapter() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Detail_News_Activity.class);
                intent.putExtra("", kijiList.get(position).getId());
                startActivity(intent);
            }
        };
    }


}