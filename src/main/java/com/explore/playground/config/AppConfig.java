package com.explore.playground.config;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
