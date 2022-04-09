package com.example.campusdirecter;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.campusdirecter.common.ViewModelFactory;
import com.example.campusdirecter.databinding.ActivityMainBinding;
import com.example.campusdirecter.ui.profile.ProfileFragment;
import com.example.campusdirecter.ui.profile.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ProfileFragment profileFragment;
    private ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        ViewModelFactory factory  = new ViewModelFactory(ServiceLocator.getInstance());
        profileViewModel = new ViewModelProvider(this, factory).get(ProfileViewModel.class);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        profileViewModel.getStudent().observe(this, student -> {
            binding.initialsBtn.setText(student.getName().getInitials());
        });

        binding.initialsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileFragment = new ProfileFragment();
                profileFragment.show(getSupportFragmentManager(),"DialogFragment");
            }
        });
    }
}