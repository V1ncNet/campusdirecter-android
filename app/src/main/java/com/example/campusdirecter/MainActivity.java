package com.example.campusdirecter;

import android.os.Bundle;
import android.util.Log;

import com.example.campusdirecter.api.Services;
import com.example.campusdirecter.model.Student;
import com.example.campusdirecter.model.Timetable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.campusdirecter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Services services = new Services(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        services.getStudent(new Services.StudentResponseListener() {
            @Override
            public void onResponse(Student student) {
                Log.d("SEMINARGRUPPE: ",student.getSeminarGroup());
            }
        });
        services.getTimetable(new Services.TimetableResponseListener() {
            @Override
            public void onResponse(Timetable timetable) {

                Log.d("Summary: ", timetable.getSummary());

            }
        });

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