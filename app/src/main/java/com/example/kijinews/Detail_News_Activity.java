package com.example.kijinews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_News_Activity extends AppCompatActivity {
    TextView title, source, description;
    kijiapi Kijiapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        bundle = getIntent().getExtras();

        initView();
        initRetrofit();
        setOnClick();
//        getkijiDetail();
        Retrofit retrofit;

    }

    private void initView() {
        title = findViewById(R.id.detail_textView_newstitle);
        source = findViewById(R.id.detail_textView_source);
        description = findViewById(R.id.detail_textView_description);
    }

    private void setOnClick(){

    }

    private String getStringBundle(Bundle bundle) {
        return bundle.getString("RESTAURANT_ID");
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kiji-news-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Kijiapi = retrofit.create(kijiapi.class);
    }


}