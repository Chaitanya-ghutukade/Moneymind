package com.example.moneymind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerRecordsAdapter extends RecyclerView.Adapter<RecyclerRecordsAdapter.ViewHolder> {


     Context context;
     ArrayList<Record_Model> arrRecords;
     RecyclerRecordsAdapter(Context context, ArrayList<Record_Model> arrRecords){
         this.arrRecords=arrRecords;
         this.context=context;
     }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

     View view= LayoutInflater.from(context).inflate(R.layout.records_row,parent,false);
     ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         holder.record_img.setImageResource(arrRecords.get(position).img);
         holder.record_type.setText(arrRecords.get(position).record_type);
         holder.acc_name.setText(arrRecords.get(position).record_type);
         holder.payment_type.setText(arrRecords.get(position).payment_type);
         holder.amount.setText(arrRecords.get(position).amount);
         holder.date.setText(arrRecords.get(position).date);

    }

    @Override
    public int getItemCount() {

         return arrRecords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
          ImageView record_img;
          TextView record_type,acc_name,payment_type,amount,date;

         public ViewHolder(View itemView){


             super(itemView);
             record_img=itemView.findViewById(R.id.record_img);
             record_type=itemView.findViewById(R.id.record_type);
             acc_name=itemView.findViewById(R.id.acc_name);
             payment_type=itemView.findViewById(R.id.payment_type);
             amount=itemView.findViewById(R.id.amount);
             date=itemView.findViewById(R.id.record_date);
         }




    }


}
