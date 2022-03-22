package com.example.campusdirecter.timetable.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campusdirecter.ServiceLocator;
import com.example.campusdirecter.common.ViewModelFactory;
import com.example.campusdirecter.databinding.FragmentTimetableBinding;

import java.util.ArrayList;

/**
 * @author Vincent Nadoll (s3003870@ba-sachsen.de)
 */
public class TimetableFragment extends Fragment {

    private TimetableModel model;
    private FragmentTimetableBinding binding;
    private LectureDataProvider dataProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = new ViewModelFactory(ServiceLocator.getInstance());
        model = new ViewModelProvider(this, factory)
                .get(TimetableModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentTimetableBinding.inflate(inflater, container, false);
        RecyclerView view = binding.getRoot();

        dataProvider = new LectureDataProvider(new ArrayList<>());
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.setAdapter(dataProvider);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.getDay().observe(getViewLifecycleOwner(), days -> {
            dataProvider.addAll(days);
            dataProvider.notifyDataSetChanged();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}