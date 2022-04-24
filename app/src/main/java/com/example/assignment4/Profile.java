package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {

    TextView t1, t2, t3, t4, t5, t6;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        String userID = user.getUid();
        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.email);
        t3 = findViewById(R.id.address);
        t4 = findViewById(R.id.card);
        t5 = findViewById(R.id.date);
        t6 = findViewById(R.id.cvs);

        DocumentReference docRef = db.collection("Users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                t1.setText("   " + user.getName());
                t6.setText("   " + String.valueOf(user.getCvs()));
                t5.setText("   " + String.valueOf(user.getCardExpiry()));
                t3.setText("   " + user.getAddress());
                t4.setText("   " + user.getCardNumber());


            }
        });

        t2.setText("   " + email);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent = new Intent(Profile.this, Home.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_cat:
                    Intent intent2 = new Intent(Profile.this, Category.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_profile:
                    break;

                case R.id.navigation_bag:
                    Intent intent3= new Intent(Profile.this, ShoppingCart.class); // need to create recycler class
                    startActivity(intent3);
                    break;

            }
            return false;
        });

    }
}