package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavAdmin);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    break;

                case R.id.navigation_profile:
                    Intent intent = new Intent(AdminHome.this, ProfileAdmin.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_stock:
                    Intent intent2 = new Intent(AdminHome.this, StockItems.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_details:
                    Intent intent3= new Intent(AdminHome.this, CustomerDetails.class); // need to create recycler class
                    startActivity(intent3);
                    break;

            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public void logOut(MenuItem menuItem){
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(AdminHome.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}