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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity<email, password> extends AppCompatActivity {
    public static final String key1 = "USER_ID";

    private EditText useremailedittext;
    private EditText passwordedittext;
    private Button signUpbutton, signInbutton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        useremailedittext = findViewById(R.id.email);
        passwordedittext = findViewById(R.id.password);
        signUpbutton = findViewById(R.id.button2);
        signInbutton = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();

        //handle click on sign up button
        signUpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = useremailedittext.getText().toString();
                String password = passwordedittext.getText().toString();

                mAuth.createUserWithEmailAndPassword(useremail, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myActivity", "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Sign up successful", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid();
                            Intent i = new Intent(MainActivity.this, Register.class);
                            i.putExtra(key1, userID);
                            startActivity(i);
                            //here you can add code to start another activity to add more details of the signed in user
                        } else {// If sign in fails, display a message to the user.
                            Log.w("myActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //handle click on sign in button
        signInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = useremailedittext.getText().toString();
                String password = passwordedittext.getText().toString();

                mAuth.signInWithEmailAndPassword(useremail, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myActivity", "createUserWithEmail:success");
                            Intent i = new Intent(MainActivity.this, Home.class);
                            startActivity(i);
                            Toast.makeText(MainActivity.this, "Sign In successfully !", Toast.LENGTH_LONG).show();
                        } else {// If sign in fails, display a message to the user.
                            Log.w("myActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}