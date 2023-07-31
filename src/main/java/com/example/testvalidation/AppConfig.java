package com.example.testvalidation;

import com.google.gson.Gson;
import com.zaxxer.hikari.util.UtilityElf;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class AppConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final String typicodeBaseUrl;
    private final boolean enableOkhttpLogs;
    private final Gson gson;


    public AppConfig(@Value("${typeicode.base.url}") String typicodeBaseUrl,
                     @Value("${enableOkhttpLogs:true}") boolean enableOkhttpLogs,
                     Gson gson) {
        this.typicodeBaseUrl = typicodeBaseUrl;
        this.enableOkhttpLogs = enableOkhttpLogs;
        this.gson = gson;
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (enableOkhttpLogs) {
            HttpLoggingInterceptor bodyLoggingInterceptor = new HttpLoggingInterceptor();
            bodyLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            bodyLoggingInterceptor.redactHeader(AUTHORIZATION_HEADER);
            okHttpClientBuilder.addInterceptor(bodyLoggingInterceptor);
        }
        return okHttpClientBuilder.build();
    }

    @Bean(name = "typeicodeRetrofit")
    public Retrofit getRetrofit(@Autowired OkHttpClient okhttpclient) {
        return createRetrofit(typicodeBaseUrl, okhttpclient);
    }

    private Retrofit createRetrofit(String baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
