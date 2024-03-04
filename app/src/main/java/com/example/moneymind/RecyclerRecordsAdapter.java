package com.example.moneymind;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerRecordsAdapter extends RecyclerView.Adapter<RecyclerRecordsAdapter.ViewHolder> {


    Context context;
    ArrayList<Record_Model> arrRecords;

    RecyclerRecordsAdapter(Context context, ArrayList<Record_Model> arrRecords) {
        this.arrRecords = arrRecords;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.records_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView record_img;
        TextView record_type, acc_name, payment_type, amount, date;

        public ViewHolder(View itemView) {


            super(itemView);
            record_img = itemView.findViewById(R.id.record_img);
            record_type = itemView.findViewById(R.id.record_type);
            acc_name = itemView.findViewById(R.id.acc_name);
            payment_type = itemView.findViewById(R.id.payment_type);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.record_date);
        }


    }


    public static class Records extends AppCompatActivity {

        Toolbar Records_toolbar;
        RecyclerView recyclerView;
        ArrayList<Record_Model> arr_records = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_records);
            Records_toolbar = findViewById(R.id.Records_toolbar);
            recyclerView = findViewById(R.id.recyclerview);
            setSupportActionBar(Records_toolbar);
            getSupportActionBar().setTitle("Records");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.record_toolbar_color));
            }


            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "100rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "110rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "200rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "150rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "189rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "135rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "102rs", "today"));
            arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "12rs", "today"));


            RecyclerRecordsAdapter adapter = new RecyclerRecordsAdapter(this, arr_records);
            recyclerView.setAdapter(adapter);

        }
    }
}
