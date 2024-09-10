package com.example.retrofit.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.retrofit.Adapter.MyAdpater;
import com.example.retrofit.ApiHelper.Helper;
import com.example.retrofit.ApiService.Service;
import com.example.retrofit.InsertData;
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
    ImageView add;
    SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewdata);

        // Setup window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        progress = findViewById(R.id.progress);
        add = findViewById(R.id.add);
        swipeRefreshLayout = findViewById(R.id.refreshLayout);

        // Setup RecyclerView with LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Handle add button click to navigate to InsertData activity
        add.setOnClickListener(view -> {
            startActivity(new Intent(ViewData.this, InsertData.class));
            Toast.makeText(ViewData.this, "Redirecting to add data", Toast.LENGTH_SHORT).show();
        });

        // Swipe to refresh setup
        swipeRefreshLayout.setOnRefreshListener(this::loadData); // Call loadData on swipe

        // Initial data load
        loadData();
    }

    private void loadData() {
        // Show progress while loading data
        progress.setVisibility(View.VISIBLE);

        Helper helper = Service.getAPI().create(Helper.class);
        Call<List<GetDataModel>> getData = helper.getalldata();

        getData.enqueue(new Callback<List<GetDataModel>>() {
            @Override
            public void onResponse(Call<List<GetDataModel>> call, Response<List<GetDataModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    arrayList.clear(); // Clear existing data
                    arrayList.addAll(response.body()); // Add new data

                    // Set adapter or notify existing one of the data change
                    if (myAdpater == null) {
                        myAdpater = new MyAdpater(arrayList, ViewData.this);
                        recyclerView.setAdapter(myAdpater);
                    } else {
                        myAdpater.notifyDataSetChanged();
                    }

                    Toast.makeText(ViewData.this, "Data loaded: " + arrayList.size() + " items", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewData.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }

                // Hide progress and stop refreshing animation
                progress.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<GetDataModel>> call, Throwable throwable) {
                Toast.makeText(ViewData.this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
