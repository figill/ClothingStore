package com.example.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register<email, password> extends AppCompatActivity {

    EditText addresstext;
    EditText username;
    EditText userphone;
    private Button signUpbutton;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //user information
        addresstext = findViewById(R.id.address);
        username = findViewById(R.id.name);
        userphone = findViewById(R.id.phone);
        signUpbutton = findViewById(R.id.button);


        Intent i = getIntent();
        String userID = i.getStringExtra(MainActivity.key1);

        //handle click on sign in button
        signUpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //save user's information in firebase
                String name = username.getText().toString();
                String phone = userphone.getText().toString();
                String address = addresstext.getText().toString();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                User user1 = new User(); // userIDis key of the node, got from Auth, username and user phone got from edit texts
                //User user1 = new User(name, phone, ppsn);
                user1.setName(name);
                user1.setPhone(phone);
                user1.setAddress(address);
                db.collection("Users").document(userID)
                        .set(user1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Register.this, "Write is successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Register.this, Home.class);
                                startActivity(i);
                                Log.d(TAG, "DocumentSnapshot added with ID: " + userID);
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