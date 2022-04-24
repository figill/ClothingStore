package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppingCart extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    Intent intent = new Intent(ShoppingCart.this, Home.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_cat:
                    Intent intent2 = new Intent(ShoppingCart.this, Category.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_profile:
                    Intent intent3= new Intent(ShoppingCart.this, Profile.class); // need to create recycler class
                    startActivity(intent3);
                    break;

                case R.id.navigation_bag:
                    break;

            }
            return false;
        });
    }
}