package com.example.campusdirecter;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.campusdirecter.databinding.ActivityMainBinding;
import com.example.campusdirecter.student.model.StudentRepository;
import com.example.campusdirecter.timetable.model.TimetableRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private final StudentRepository studentRepository;
    private final TimetableRepository timetableRepository;
    private ActivityMainBinding binding;

    public MainActivity() {
        ContextHolder contextHolder = ContextHolder.getInstance();
        this.studentRepository = contextHolder.getStudentRepository(this);
        this.timetableRepository = contextHolder.getTimetableRepository(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentRepository.retrieve(student -> Log.d("SEMINARGRUPPE: ", student.getSeminarGroup()));
        timetableRepository.retrieve(timetable -> Log.d("Summary: ", timetable.getSummary()));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}