package com.example.retrofit.ApiHelper;

import com.example.retrofit.Model.GetDataModel;
import com.example.retrofit.Model.InsertDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Helper {
    @POST("insertdata.php")
    Call<Void> insertData(@Body InsertDataModel insertDataModel);

    @GET("getdata.php")
    Call<List<GetDataModel>> getalldata();

}
