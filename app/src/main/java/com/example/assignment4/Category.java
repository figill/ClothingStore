package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Category extends AppCompatActivity {
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent = new Intent(Category.this, Home.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_cat:
                    break;

                case R.id.navigation_profile:
                    Intent intent2 = new Intent(Category.this, Profile.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_bag:
                    Intent intent3= new Intent(Category.this, ShoppingCart.class); // need to create recycler class
                    startActivity(intent3);
                    break;

            }
            return false;
        });
    }

    public void clothes(View view){

    }

    public void shoes(View view){

    }

    public void bags(View view){

    }

    public void jewellery(View view){

    }

    public void products(View view){
        Intent i = new Intent(Category.this, Products.class);
        startActivity(i);
    }
}