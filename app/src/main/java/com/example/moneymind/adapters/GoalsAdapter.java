package com.example.moneymind.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.moneymind.R;
import com.example.moneymind.databinding.GoalsListBinding;
import com.example.moneymind.models.Goals;

import java.util.ArrayList;

public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.GoalsViewHolder> {

    private ArrayList<Goals> goalsList;
    private Context context;

    public GoalsAdapter(Context context, ArrayList<Goals> goalsList) {
        this.context = context;
        this.goalsList = goalsList;
    }

    @NonNull
    @Override
    public GoalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            return null; // Handle null context gracefully
        }
        View view = LayoutInflater.from(context).inflate(R.layout.goals_list, parent, false);
        return new GoalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalsViewHolder holder, int position) {
        Goals goal = goalsList.get(position);
        holder.binding.gAmount.setText(String.valueOf(goal.getAmount()));

        // Calculate progress percentage
        int progress = (int) ((goal.getSavedAmount() / goal.getAmount()) * 100);
        holder.binding.progressBar.setProgress(progress);

        holder.binding.goalName.setText(goal.getName());
    }

    @Override
    public int getItemCount() {
        return goalsList.size();
    }

    public class GoalsViewHolder extends RecyclerView.ViewHolder {

        private GoalsListBinding binding;

        public GoalsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = GoalsListBinding.bind(itemView);
        }
    }
}
