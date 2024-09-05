package com.example.retrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Model.GetDataModel;
import com.example.retrofit.R;

import java.util.ArrayList;

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


        holder.name.setText(dataModel.getName());
        holder.roll.setText(dataModel.getRoll());
        holder.reg.setText(dataModel.getRej());
        holder.phone.setText(dataModel.getPhone());
        holder.subject.setText(dataModel.getSub());
        holder.address.setText(dataModel.getAddress());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,roll,reg,phone,subject,address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            roll = itemView.findViewById(R.id.roll);
            reg = itemView.findViewById(R.id.reg);
            phone = itemView.findViewById(R.id.phone);
            subject = itemView.findViewById(R.id.subject);
            address = itemView.findViewById(R.id.address);
        }
    }
}
