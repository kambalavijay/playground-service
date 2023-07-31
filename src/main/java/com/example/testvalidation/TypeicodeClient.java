package com.example.testvalidation;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import java.util.concurrent.CompletionStage;

@Component
public class TypeicodeClient {

    private final TypicodeInterface typicodeInterface;

    public TypeicodeClient(@Qualifier("typeicodeRetrofit") Retrofit retrofit) {
        typicodeInterface = retrofit.create(TypicodeInterface.class);
    }

    public CompletionStage<Post> getPost(Long postID) {
        return HttpUtil.toCompletableFuture(typicodeInterface.getPostByID(postID));
    }

    private interface TypicodeInterface {

        @GET("posts/{postID}")
        @Headers({"Content-Type: application/json", "Accept: application/json"})
        Call<Post> getPostByID(@Path("postID") Long postID);
    }
}