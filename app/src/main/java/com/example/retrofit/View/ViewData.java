package com.example.retrofit.View;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Adapter.MyAdpater;
import com.example.retrofit.ApiHelper.Helper;
import com.example.retrofit.ApiService.Service;
import com.example.retrofit.Model.GetDataModel;
import com.example.retrofit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewData extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdpater myAdpater;
    ArrayList<GetDataModel> arrayList;
    ProgressBar progress;
    EditText edxx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewdata);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         arrayList = new ArrayList<>();

        edxx = findViewById(R.id.tv_roll);
        recyclerView = findViewById(R.id.recyclerView);
        progress = findViewById(R.id.progress);



        Helper helper = Service.getAPI().create(Helper.class);
        Call<List<GetDataModel>> getData = helper.getalldata();

        getData.enqueue(new Callback<List<GetDataModel>>() {
            @Override
            public void onResponse(Call<List<GetDataModel>> call, Response<List<GetDataModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    arrayList.addAll(response.body());
                    progress.setVisibility(View.GONE);
                    Toast.makeText(ViewData.this, "jjjj"+arrayList.size(), Toast.LENGTH_SHORT).show();
                    myAdpater = new MyAdpater(arrayList,ViewData.this);
                    recyclerView.setAdapter(myAdpater);

                } else {
                    progress.setVisibility(View.GONE);

                    Toast.makeText(ViewData.this, "Data Null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GetDataModel>> call, Throwable throwable) {
                Toast.makeText(ViewData.this, "Data Failed"+throwable, Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
                edxx.setText(""+throwable);


            }
        });





    }
}