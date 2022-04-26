package com.example.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Date;

public class ShoppingCart extends AppCompatActivity {

    int minteger = 1;
    public static CartAdapter adapter;
    RecyclerView recyclerView;
    private List<Cart> list;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    public TextView t1;
    public String totalPrice;
    public Button b1, b2;
    Cart cart = new Cart();
    public static final String TAG = "TAG";

    private static HashMap<Cart.OnStateChangeListener, View> viewListeners = new LinkedHashMap<>();
    private static HashMap<ShoppingCart, ArrayList<Cart.OnStateChangeListener>> productListeners = new LinkedHashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        totalPrice = getIntent().getStringExtra("Total Price");
        t1 = findViewById(R.id.textView3);


        list = new ArrayList<>();
        recyclerView = findViewById(R.id.my_recycler_view);
        // To display the Recycler view linearly
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CartAdapter(list);
        recyclerView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(ShoppingCart.this, Home.class);
                    startActivity(intent);
                    break;

                case R.id.navigation_cat:
                    Intent intent2 = new Intent(ShoppingCart.this, Category.class); // need to create recycler class
                    startActivity(intent2);
                    break;

                case R.id.navigation_profile:
                    Intent intent3 = new Intent(ShoppingCart.this, Profile.class); // need to create recycler class
                    startActivity(intent3);
                    break;

                case R.id.navigation_bag:
                    break;

            }
            return false;
        });

        EventChangeListener();

        b1 = findViewById(R.id.card);
        b2 = findViewById(R.id.paypal);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseFirestore.getInstance();
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String email = user.getEmail();
                String userID = user.getUid();
                String paymentType = cart.pay(new CardStrategy());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                OrderReceipt orderReceipt = new OrderReceipt(userID, email, paymentType, totalPrice, new Date().toString());

                db.collection("OrderReceipt").document()
                        .set(orderReceipt)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ShoppingCart.this, "Write is successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(ShoppingCart.this, ConfirmedPurchase.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseFirestore.getInstance();
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String email = user.getEmail();
                String userID = user.getUid();
                String paymentType = cart.pay(new PaypalStrategy());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                OrderReceipt orderReceipt = new OrderReceipt(userID, email, paymentType, totalPrice, new Date().toString());

                db.collection("OrderReceipt").document()
                        .set(orderReceipt)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ShoppingCart.this, "Write is successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(ShoppingCart.this, ConfirmedPurchase.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });


            }
        });

        //Get all the listeners for this specific product
        ArrayList<Cart.OnStateChangeListener> x = productListeners.get(minteger);
        //For each listener
        for (Cart.OnStateChangeListener listener : x) {

            //Get the view that owns this listener
            View v = viewListeners.get(listener);

            //Trigger update on the subscribed view
            listener.onStateChange(v);
        }
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

        db.collection("Users").document(userID).collection("Cart")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.d("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                list.add(dc.getDocument().toObject(Cart.class));
                            }
                            adapter.notifyDataSetChanged();
                        }


                    }
                });
    }

    private void filter(String newText) {
        ArrayList<Cart> filteredList = new ArrayList<>();

        // running a for loop to compare elements.
        for (Cart t : list) {
            // checking if the entered string matched with any item of our recycler view.
            if (t.getTitle().toLowerCase().contains(newText.toLowerCase())) {

                filteredList.add(t);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            adapter.filterList(filteredList);
        }
    }


    public void increaseInteger(View view) {
        minteger = minteger + 1;
        //display(minteger);
        //productView gets updated here
        Cart.OnStateChangeListener mCartListener = new Cart.OnStateChangeListener() {
            @Override
            public void onStateChange(View productView) {
                TextView displayInteger = (TextView) findViewById(
                        R.id.integer_number);
                displayInteger.setText("" + minteger);
            }

        };
    }

    public void decreaseInteger(View view) {
        minteger = minteger - 1;
        //display(minteger);
        //productView gets updated here
        Cart.OnStateChangeListener mCartListener = new Cart.OnStateChangeListener() {
            @Override
            public void onStateChange(View productView) {
                TextView displayInteger = (TextView) findViewById(
                        R.id.integer_number);
                displayInteger.setText("" + minteger);
            }

        };
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);
        displayInteger.setText("" + number);
    }

    public void pay(View view) {
    }
}