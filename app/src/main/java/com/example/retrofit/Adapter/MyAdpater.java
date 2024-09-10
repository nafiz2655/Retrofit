package com.example.retrofit.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit.ApiHelper.Helper;
import com.example.retrofit.ApiService.Service;
import com.example.retrofit.Model.DeleteDataModel;
import com.example.retrofit.Model.GetDataModel;
import com.example.retrofit.Model.UpdateDataModel;
import com.example.retrofit.R;
import com.example.retrofit.View.Update;
import com.example.retrofit.View.ViewData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAdpater extends RecyclerView.Adapter<MyAdpater.ViewHolder> {

    ArrayList<GetDataModel> arrayList;
    Context context;

    public MyAdpater() {
    }


    public MyAdpater(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = layoutInflater.inflate(R.layout.layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GetDataModel dataModel = arrayList.get(position);

        String id = dataModel.getId();
        String sname = dataModel.getName();
        String sroll = dataModel.getRoll();
        String srej = dataModel.getRej();
        String sphone = dataModel.getPhone();
        String ssub = dataModel.getSub();
        String sadd = dataModel.getAddress();

        holder.name.setText(sname);
        holder.roll.setText(sroll);
        holder.reg.setText(srej);
        holder.phone.setText(sphone);
        holder.subject.setText(ssub);
        holder.address.setText(sadd);

        String url = "https://maltinamax.quillncart.com/appsdta/test/retrofit/images/"+dataModel.getImage();

        String uri = dataModel.getImage();


        Glide.with(context).load(url)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(holder.profile_image);

        holder.tv_uptade.setOnClickListener( view -> {

            Update.ID = id;
            Update.ADD= sadd;
            Update.NAME = sname;
            Update.ROLL = sroll;
            Update.REG = srej;
            Update.PHONE = sphone;
            Update.SUB = ssub;
            Update.URL = uri;
            context.startActivity(new Intent(context, Update.class));
        });

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer i_id = Integer.parseInt(dataModel.getId());

                Helper helper = Service.getAPI().create(Helper.class);
                helper.deleteData(new DeleteDataModel(i_id)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context, "Data Update success", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        Toast.makeText(context, "Data Update Failed", Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,roll,reg,phone,subject,address,tv_uptade,tv_delete;
        CircleImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            roll = itemView.findViewById(R.id.roll);
            reg = itemView.findViewById(R.id.reg);
            phone = itemView.findViewById(R.id.phone);
            subject = itemView.findViewById(R.id.subject);
            address = itemView.findViewById(R.id.address);
            profile_image = itemView.findViewById(R.id.profile_image);
            tv_uptade = itemView.findViewById(R.id.tv_uptade);
            tv_delete = itemView.findViewById(R.id.tv_delete);
        }
    }
}
