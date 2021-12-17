package com.example.kijinews;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface kijiapi {
    @GET("articles/index")
    Call<allarticles> getArticles();

    @GET("/articles/search?category={category}")
    Call<articlebycategory> getArticlec(@Path("category") String category);

    @GET("/articles/show/{id}")
    Call<onearticle> getArticle(@Path("id") String id);

    @POST("/comments/store")
    Call<comment> postcomment(@Body comment Comment);
}
