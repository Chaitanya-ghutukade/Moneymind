package com.example.moneymind.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymind.R;
import com.example.moneymind.databinding.SampleCategoryItemBinding;
import com.example.moneymind.models.Category;

import java.util.ArrayList;


public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category> categories;

    public interface CategoryClickedListner {

        void onCategoryClicked(Category category);
    }

    CategoryClickedListner categoryClickedListner;

    public Category_Adapter(Context context, ArrayList<Category> categories, CategoryClickedListner categoryClickedListner) {
        this.context = context;
        this.categories = categories;
        this.categoryClickedListner = categoryClickedListner;

    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.binding.categoryText.setText(category.getCategoryName());
        holder.binding.categoryicon.setImageResource(category.getCategoryImage());
        holder.binding.categoryicon.setBackgroundTintList(context.getColorStateList(category.getCategorycolor()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickedListner.onCategoryClicked(category);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        SampleCategoryItemBinding binding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleCategoryItemBinding.bind(itemView);
        }
    }


}
