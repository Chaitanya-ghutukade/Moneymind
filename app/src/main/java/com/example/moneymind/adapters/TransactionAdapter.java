package com.example.moneymind.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymind.R;
import com.example.moneymind.databinding.RowTransactionBinding;
import com.example.moneymind.models.Category;
import com.example.moneymind.models.Transaction;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.utils.Helper;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    Context context;
    ArrayList<Transaction> transactionArrayList;

    public TransactionAdapter(Context context,ArrayList<Transaction> transactionArrayList) {
        this.context=context;
        this.transactionArrayList=transactionArrayList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(context).inflate(R.layout.row_transaction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction=transactionArrayList.get(position);
        holder.binding.transactionAmount.setText(String.valueOf(transaction.getAmount()));
        holder.binding.accountLabel.setText(transaction.getAccount());
        holder.binding.transactionDate.setText(Helper.format_date(transaction.getDate()));
        holder.binding.transactionCategory.setText(transaction.getCategory());

        Category transactionCategory=Constants.getCategoryDetails(transaction.getCategory());
        holder.binding.categoryIcon.setImageResource(transactionCategory.getCategoryImage());
        holder.binding.categoryIcon.setBackgroundTintList(context.getColorStateList(transactionCategory.getCategorycolor()));
        holder.binding.accountLabel.setBackgroundTintList(context.getColorStateList(Constants.getAccountColors(transaction.getAccount())));
        if(transaction.getType().equals(Constants.INCOME)){
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.green));
        } else if (transaction.getType().equals(Constants.EXPENSE)) {
            holder.binding.transactionAmount.setTextColor(context.getColor(R.color.red));
        }



    }

    @Override
    public int getItemCount() {
        return  transactionArrayList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        RowTransactionBinding binding;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=RowTransactionBinding.bind(itemView);
        }
    }
}
