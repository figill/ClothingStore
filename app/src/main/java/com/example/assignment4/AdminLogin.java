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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    public static final String key1 = "USER_ID";

    private EditText useremailedittext;
    private EditText passwordedittext;
    private FirebaseAuth mAuth;
    private Button signInbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        useremailedittext = findViewById(R.id.email);
        passwordedittext = findViewById(R.id.password);
        signInbutton = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();

        //handle click on sign in button
        signInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = useremailedittext.getText().toString();
                String password = passwordedittext.getText().toString();

                mAuth.signInWithEmailAndPassword(useremail, password).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myActivity", "createUserWithEmail:success");
                            Intent i = new Intent(AdminLogin.this, AdminHome.class);
                            startActivity(i);
                            Toast.makeText(AdminLogin.this, "Sign In successfully !", Toast.LENGTH_LONG).show();
                        } else {// If sign in fails, display a message to the user.
                            Log.w("myActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AdminLogin.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}