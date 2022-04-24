package com.example.assignment4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Date;


public class AddStock extends AppCompatActivity {

    EditText t1, t2, t3, t4, t5;
    Button button;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        t1 = findViewById(R.id.title);
        t2 = findViewById(R.id.manufacturer);
        t3 = findViewById(R.id.category);
        t4 = findViewById(R.id.quantity);
        t5 = findViewById(R.id.price);
        button = findViewById(R.id.addStock);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String title = t1.getText().toString();
                String manufacturer = t2.getText().toString();
                String category = t3.getText().toString();
                String quantity = t4.getText().toString();
                String price = t5.getText().toString();

                StockModel stockModel = new StockModel(title, manufacturer, category, quantity, price);

                db.collection("Stock").document()
                        .set(stockModel)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddStock.this, "Write is successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(AddStock.this, StockItems.class);
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
    }
}