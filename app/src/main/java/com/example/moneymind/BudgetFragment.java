package com.example.moneymind;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymind.databinding.FragmentBudgetBinding;
import com.example.moneymind.views.activities.Goal;


public class BudgetFragment extends Fragment {


    public BudgetFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
             FragmentBudgetBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding=FragmentBudgetBinding.inflate(inflater);
       binding.goalbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent GoalIntent=new Intent(getActivity(), Goal.class);
               startActivity(GoalIntent);

           }
       });














        return binding.getRoot();
    }
}