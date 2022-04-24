package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoryAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_admin);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavAdmin);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent3= new Intent(CategoryAdmin.this, AdminHome.class); // need to create recycler class
                    startActivity(intent3);
                    break;

                case R.id.navigation_cat:
                    break;

                case R.id.navigation_stock:
                    Intent intent = new Intent(CategoryAdmin.this, StockItems.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_details:
                    Intent intent2 = new Intent(CategoryAdmin.this, CustomerDetails.class); // need to create recycler class
                    startActivity(intent2);
                    break;

            }
            return false;
        });
    }
}