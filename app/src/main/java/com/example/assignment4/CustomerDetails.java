package com.example.assignment4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetails extends AppCompatActivity {

    public static ReceiptAdpater adapter;
    RecyclerView recyclerView;
    private List<OrderReceipt> list;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.my_recycler_view);
        // To display the Recycler view linearly
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ReceiptAdpater(list);
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavAdmin);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent3 = new Intent(CustomerDetails.this, AdminHome.class); // need to create recycler class
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

        EventChangeListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_Search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }


    private void EventChangeListener() {

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();

        db.collection("OrderReceipt")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.d("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                list.add(dc.getDocument().toObject(OrderReceipt.class));
                            }
                            adapter.notifyDataSetChanged();
                        }


                    }
                });
    }

    private void filter(String newText) {
        ArrayList<OrderReceipt> filteredList = new ArrayList<>();

        // running a for loop to compare elements.
        for (OrderReceipt t : list) {
            // checking if the entered string matched with any item of our recycler view.
            if (t.getPaymentType().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            } else if (t.getUserEmail().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            } else if (t.getDate().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            adapter.filterList(filteredList);
        }
    }
}