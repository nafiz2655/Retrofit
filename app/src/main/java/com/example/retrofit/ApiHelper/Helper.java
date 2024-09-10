package com.example.retrofit.ApiHelper;

import com.example.retrofit.Model.DeleteDataModel;
import com.example.retrofit.Model.GetDataModel;
import com.example.retrofit.Model.InsertDataModel;
import com.example.retrofit.Model.UpdateDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Helper {
    @POST("insertimage.php")
    Call<Void> insertData(@Body InsertDataModel insertDataModel);

    @GET("getdata.php")
    Call<List<GetDataModel>> getalldata();


    // if no image select
    @POST("updatedata.php")
    Call<Void> updatedata(@Body UpdateDataModel updateDataModel);

    // if new image select
    @POST("update2..php")
    Call<Void> updatedataimage(@Body UpdateDataModel updateDataModel);

    @POST("deletedata.php")
    Call<Void> deleteData(@Body DeleteDataModel deleteDataModel);
}
