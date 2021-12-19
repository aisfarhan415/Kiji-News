package com.example.kijinews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    TextView headingText, headingCategory;
    ImageView headingImage;
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
        initRetrofit();
        setonClick();
        initRecycler();

        getArticle();
    }

    private void initRecycler() {
        homerecycler = findViewById(R.id.home_recyclerview);
        homerecycler.setHasFixedSize(true);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://kiji-news-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        kijiapi = retrofit.create(kijiapi.class);
    }


    private void initView(){
        headingText = findViewById(R.id.detail_textView_newstitle);
        headingCategory = findViewById(R.id.detail__textView_category);
        headingImage = findViewById(R.id.home_imageView_headline);
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
                kijiList = new ArrayList<>(Arrays.asList(Allarticles.getArticles()));
                setHeadline(kijiList.get(0));

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

    private void setHeadline(kiji kijiElem) {
        String cat = kijiElem.getCategory();
        cat = cat.substring(0, 1).toUpperCase() + cat.substring(1);

        headingText.setText(kijiElem.getTitle());
        headingCategory.setText(cat);
        Glide.with(this.getApplicationContext())
                .load(kijiElem.getPicture())
                .into(headingImage);
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
        recyclerListener = new kijiAdapter.RecyclerListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Detail_News_Activity.class);
                intent.putExtra("ARTICLE_ID", kijiList.get(position).getId());
                startActivity(intent);
            }
        };
    }


}