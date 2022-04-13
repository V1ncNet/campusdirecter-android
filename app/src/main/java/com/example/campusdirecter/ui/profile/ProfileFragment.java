package com.example.campusdirecter.ui.profile;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import java.util.Optional;


public class ProfileFragment extends DialogFragment {


    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = new ViewModelFactory(ServiceLocator.getInstance());
        profileViewModel = new ViewModelProvider(getActivity(), factory).get(ProfileViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setWidth(98);

        profileViewModel.getStudent().observe(getViewLifecycleOwner(), student -> {
            binding.name.setText(student.getName().toString());
            binding.snumber.setText("s" + student.getId());
            binding.seminarGroup.setText(student.getSeminarGroup());

        });
        binding.btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * @apiNote Adjusted for Java syntax
     * @see <a href="https://stackoverflow.com/a/41495370">How to set DialogFragment's width and height?</a>
     */
    private void setWidth(int percentage) {
        float percent = (float) percentage / 100;
        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        Rect rect = new Rect(0, 0, dm.widthPixels, dm.heightPixels);
        float width = rect.width() * percent;

        Optional.ofNullable(getDialog())
                .map(Dialog::getWindow)
                .ifPresent(window -> window.setLayout((int) width, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}