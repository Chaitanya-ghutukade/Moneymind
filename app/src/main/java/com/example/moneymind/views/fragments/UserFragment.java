package com.example.moneymind.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymind.MyDBHelper;
import com.example.moneymind.databinding.FragmentUserBinding;
import com.example.moneymind.models.Userdata;
import com.example.moneymind.views.activities.LoginActivity;
import com.example.moneymind.views.activities.MainActivity;


public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    FragmentUserBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentUserBinding.inflate(inflater);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("User_name", Context.MODE_PRIVATE);
        String stored_user_name=sharedPreferences.getString("username","default_username");
        MyDBHelper db2 = new MyDBHelper(getContext());
        Userdata userdata=db2.getuserdetails(stored_user_name);
        if(userdata != null){
            String u_name=userdata.user_name;
            String u_mail=userdata.user_email;
            binding.fullname.setText(" "+u_name);
            binding.mailaddress.setText(" "+u_mail);
        }

   binding.logout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           logoutUser();
       }
   });




        return binding.getRoot();
    }
    private void logoutUser() {
        // Clear SharedPreferences
        SharedPreferences prefs= requireContext().getSharedPreferences("userlogin",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit=prefs.edit();
        edit.putBoolean("IsLoggedIn",false);
        edit.apply();

        // Redirect to Login Screen
        Intent intent = new Intent(requireActivity(), LoginActivity.class);

        startActivity(intent);

    }
}