package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavAdmin);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    Intent intent3= new Intent(CustomerDetails.this, AdminHome.class); // need to create recycler class
                    startActivity(intent3);
                    break;

                case R.id.navigation_profile:
                    Intent intent = new Intent(CustomerDetails.this, ProfileAdmin.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_stock:
                    Intent intent2 = new Intent(CustomerDetails.this, StockItems.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_details:
                    break;

            }
            return false;
        });
    }
}