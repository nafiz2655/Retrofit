package com.example.retrofit.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.ref.Cleaner;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    static String TOKEN = "DIPTI";
    static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            .header("Authorization", TOKEN)
                            .build();

                    return chain.proceed(newRequest);
                }
            })
            .build();


    public static Retrofit getAPI(){
        return new Retrofit.Builder()
                .client(client)
                .baseUrl("https://maltinamax.quillncart.com/appsdta/test/retrofit/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}