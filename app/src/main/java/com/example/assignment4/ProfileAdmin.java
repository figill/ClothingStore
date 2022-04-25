package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileAdmin extends AppCompatActivity {

    TextView t1, t2, t3;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String userID = user.getUid();
        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.email);
        t3 = findViewById(R.id.phone);

        DocumentReference docRef = db.collection("Admin").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Admin admin = documentSnapshot.toObject(Admin.class);
                t1.setText("   " + admin.getName());
                t3.setText("   " + admin.getPhone());
            }
        });

        t2.setText("   " + email);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavAdmin);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    Intent intent3= new Intent(ProfileAdmin.this, AdminHome.class); // need to create recycler class
                    startActivity(intent3);
                    break;

                case R.id.navigation_cat:
                    break;

                case R.id.navigation_stock:
                    Intent intent = new Intent(ProfileAdmin.this, StockItems.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_details:
                    Intent intent2 = new Intent(ProfileAdmin.this, CustomerDetails.class); // need to create recycler class
                    startActivity(intent2);
                    break;

            }
            return false;
        });
    }


}