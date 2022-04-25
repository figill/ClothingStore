package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfirmedPurchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_purchase);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    Intent intent4 = new Intent(ConfirmedPurchase.this, Home.class);
                    startActivity(intent4);
                    break;

                case R.id.navigation_cat:
                    Intent intent = new Intent(ConfirmedPurchase.this, Category.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_profile:
                    Intent intent2 = new Intent(ConfirmedPurchase.this, Profile.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_bag:
                    Intent intent3= new Intent(ConfirmedPurchase.this, ShoppingCart.class); // need to create recycler class
                    startActivity(intent3);
                    break;

            }
            return false;
        });
    }

    public void contShopping(View view) {
        Intent i = new Intent(ConfirmedPurchase.this, Category.class);
        startActivity(i);
    }
}