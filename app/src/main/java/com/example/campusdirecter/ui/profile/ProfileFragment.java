package com.example.campusdirecter.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.campusdirecter.R;
import com.example.campusdirecter.databinding.FragmentNotificationsBinding;
import com.example.campusdirecter.ui.notifications.NotificationsViewModel;


public class ProfileFragment extends Fragment {


    private ProfileViewModel profileViewModel;
    private FragmentNotificationsBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}