 package com.example.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.retrofit.ApiHelper.Helper;
import com.example.retrofit.ApiService.Service;
import com.example.retrofit.Model.InsertDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class MainActivity extends AppCompatActivity {

    EditText tv_name,tv_roll,tv_reg,tv_subject,tv_phone,tv_address;
    TextView submit;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv_name = findViewById(R.id.tv_name);
        tv_roll = findViewById(R.id.tv_roll);
        tv_reg = findViewById(R.id.tv_reg);
        tv_subject = findViewById(R.id.tv_subject);
        tv_phone = findViewById(R.id.tv_phone);
        tv_address = findViewById(R.id.tv_address);
        submit = findViewById(R.id.tv_submit);
        progressbar = findViewById(R.id.progressbar);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                String sName =tv_name.getText().toString();
                String sRoll =tv_roll.getText().toString();
                String sReg =tv_reg.getText().toString();
                String sSub =tv_subject.getText().toString();
                String sPhone =tv_phone.getText().toString();
                String sAddress =tv_address.getText().toString();
                String sImage ="Image";
                InsertData(sName,sRoll,sReg,sSub,sPhone,sAddress,sImage);


            }
        });




    }

    private void InsertData(String sName, String sRoll, String sReg, String sSub, String sPhone, String sAddress, String sImage) {


        Helper helper = Service.getAPI().create(Helper.class);
        helper.insertData(new InsertDataModel(sName,sRoll,sReg,sSub,sPhone,sImage,sAddress)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(MainActivity.this, "Data Insert success", Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Data Insert Failed", Toast.LENGTH_SHORT).show();
                progressbar.setVisibility(View.GONE);

            }
        });

    }
}