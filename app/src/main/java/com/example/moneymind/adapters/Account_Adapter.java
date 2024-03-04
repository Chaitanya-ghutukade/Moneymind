package com.example.moneymind.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymind.R;
import com.example.moneymind.databinding.RowAccountBinding;
import com.example.moneymind.models.Account;

import java.util.ArrayList;

public class Account_Adapter extends RecyclerView.Adapter<Account_Adapter.AccountViewHolder>{

    Context context;
    ArrayList<Account> accountArrayList;

    public interface AccountClickListner{

        void onAccountClick(Account account);

    }
    AccountClickListner accountClickListner;

    public Account_Adapter(Context context, ArrayList<Account> accountArrayList,AccountClickListner accountClickListner) {
        this.accountArrayList=accountArrayList;
        this.context=context;
        this.accountClickListner=accountClickListner;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountViewHolder(LayoutInflater.from(context).inflate(R.layout.row_account,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account=accountArrayList.get(position);
        holder.binding.accountname.setText(account.getAccountName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    accountClickListner.onAccountClick(account);
            }
        });


    }

    @Override
    public int getItemCount() {
        return accountArrayList.size();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder{

        RowAccountBinding binding;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=RowAccountBinding.bind(itemView);
        }
    }
}
