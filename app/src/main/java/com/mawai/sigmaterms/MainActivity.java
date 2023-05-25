package com.mawai.sigmaterms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(MainActivity.this, R.id.main);
//      NavigationUI.setupActionBarWithNavController(MainActivity.this, navController);
//      NavigationUI.setupWithNavController(toolbar, navController);


    }
}