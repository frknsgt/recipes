package com.example.recipes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment homepageFragment= new Homepage();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.add(R.id.content, homepageFragment);
        transaction.commit();


        Button homeButton= (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment homepageFragment = new Homepage();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, homepageFragment);
                transaction.commit();
            }
        });
        Button categoryButton= (Button) findViewById(R.id.categoryButton);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment blankFragment = new BlankFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, blankFragment);
                transaction.commit();
            }
        });
        Button aboutButton= (Button) findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment aboutFragment = new About();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, aboutFragment);
                transaction.commit();
            }
        });

        Button todayButton= (Button) findViewById(R.id.todayButton);
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealDay todayFragment = new MealDay();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, todayFragment);
                transaction.commit();
            }
        });
    }
}