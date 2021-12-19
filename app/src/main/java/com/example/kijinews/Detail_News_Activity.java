package com.example.kijinews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Detail_News_Activity extends AppCompatActivity {
    TextView title, source, description, category;
    ImageView image;
    kijiapi Kijiapi;
    Bundle bundle;
    Retrofit retrofit;
    onearticle singleArticleResponse;
    kiji singleArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        bundle = getIntent().getExtras();

        initView();
        initRetrofit();
        setOnClick();
        getkijiDetail();

    }

    private void initView() {
        title = findViewById(R.id.detail_textView_newstitle);
        source = findViewById(R.id.detail_textView_source);
        description = findViewById(R.id.detail_textView_description);
        image = findViewById(R.id.detail_imageView_image);
        category = findViewById(R.id.detail__textView_category);
    }

    private void setOnClick(){

    }

    private String getStringBundle(Bundle bundle) {
        return bundle.getString("ARTICLE_ID");
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://kiji-news-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Kijiapi = retrofit.create(kijiapi.class);
    }

    private void getkijiDetail() {
        Call<onearticle> call = Kijiapi.getArticle(
                getStringBundle(bundle)
        );

        call.enqueue(new Callback<onearticle>() {
            @Override
            public void onResponse(Call<onearticle> call, Response<onearticle> response) {
                singleArticleResponse = response.body();
                singleArticle = singleArticleResponse.getArticle();

                setArticleDetail(singleArticle);
            }

            @Override
            public void onFailure(Call<onearticle> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void setArticleDetail(kiji singleKiji) {
        String cat = singleKiji.getCategory();
        cat = cat.substring(0, 1).toUpperCase() + cat.substring(1);
        title.setText(singleKiji.getTitle());
        source.setText(singleKiji.getSource());
        description.setText(singleKiji.getContent());
        category.setText(cat);

        Glide.with(this.getApplicationContext())
                .load(singleKiji.getPicture())
                .into(image);
    }

}