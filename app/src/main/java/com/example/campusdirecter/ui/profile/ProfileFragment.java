package com.example.campusdirecter.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.campusdirecter.ServiceLocator;
import com.example.campusdirecter.common.ViewModelFactory;
import com.example.campusdirecter.databinding.FragmentProfileBinding;


public class ProfileFragment extends DialogFragment {


    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = new ViewModelFactory(ServiceLocator.getInstance());
        profileViewModel = new ViewModelProvider(this, factory).get(ProfileViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileViewModel.getStudent().observe(getViewLifecycleOwner(), student -> {
            binding.studentnameText.setText(student.getName().getFullName());
            binding.snummerText.setText("s"+student.getId());
            binding.seminargroupText.setText(student.getSeminarGroup());

        });
        binding.btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}