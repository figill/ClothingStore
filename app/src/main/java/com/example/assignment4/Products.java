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
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    public static ProductAdapter adapter;
    RecyclerView recyclerView;
    private List<StockModel> list;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String selectedFilter = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        list = new ArrayList<>();

        recyclerView = findViewById(R.id.my_recycler_view);
        // To display the Recycler view linearly
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter(list);
        recyclerView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Intent intent4 = new Intent(Products.this, Home.class);
                    startActivity(intent4);
                    break;

                case R.id.navigation_cat:
                    Intent intent = new Intent(Products.this, Category.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_profile:
                    Intent intent2 = new Intent(Products.this, Profile.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_bag:
                    Intent intent3= new Intent(Products.this, ShoppingCart.class); // need to create recycler class
                    startActivity(intent3);
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

        db.collection("Stock")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.d("Firestore error", error.getMessage());
                            return;
                        }

                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(StockModel.class));
                            }
                            adapter.notifyDataSetChanged();
                        }


                    }
                });
    }

    private void filter(String newText) {
        ArrayList<StockModel> filteredList = new ArrayList<>();

        // running a for loop to compare elements.
        for (StockModel t : list) {
            // checking if the entered string matched with any item of our recycler view.
            if (t.getCategory().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            }
            else if (t.getManufacturer().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            }
            else if (t.getTitle().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            adapter.filterList(filteredList);
        }
    }

    private void filterList(String string){
        selectedFilter = string;

        ArrayList<StockModel> filteredList = new ArrayList<StockModel>();

        for (StockModel t : list) {
            if (t.getCategory().toLowerCase().contains(string.toLowerCase())) {
                filteredList.add(t);
            }

        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            adapter.filterList(filteredList);
        }
    }

    public void allFilter(View view) {
        list = new ArrayList<>();

        recyclerView = findViewById(R.id.my_recycler_view);
        // To display the Recycler view linearly
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ProductAdapter(list);
        recyclerView.setAdapter(adapter);
        EventChangeListener();
    }

    public void clothesFilter(View view) {
        filterList("Clothing");
    }

    public void shoesFilter(View view) {
        filterList("Shoes");
    }

    public void bagsFilter(View view) {
        filterList("Bags");
    }

    public void jewelleryFilter(View view) {
        filterList("Jewellery");
    }

    public void addToBasket(View view) {
        Toast.makeText(this, "Item has been added to your basket.", Toast.LENGTH_LONG).show();
    }
}