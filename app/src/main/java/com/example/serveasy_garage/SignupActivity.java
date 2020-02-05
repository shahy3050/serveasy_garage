package com.example.serveasy_garage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email_et, password_et,confirm_et;


    public void bindviews() {
        email_et = findViewById(R.id.email_edittext);
        password_et = findViewById(R.id.password_edittext);
        confirm_et = findViewById(R.id.confirm_edittext);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("serveasy", "Current User is Signed In:" + currentUser);
    }

    public void createUser() {

        mAuth.createUserWithEmailAndPassword(email_et.toString(), password_et.toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Serveasy", "Create User With Email is Successful");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("Serveasy", "User is" + user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("Serveasy", "Error:" + task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication Failure.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindviews();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }
}
