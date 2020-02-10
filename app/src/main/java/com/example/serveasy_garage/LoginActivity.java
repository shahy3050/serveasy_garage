package com.example.serveasy_garage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText email_et, password_et;
    private TextView signupTV;
    private Button login_bt;


    public void bindViews() {
        email_et = findViewById(R.id.email_edit_text);
        password_et = findViewById(R.id.password_edit_text);
        login_bt = findViewById(R.id.login_button);
        signupTV = findViewById(R.id.register_text_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("serveasy", "User :" + user);
    }

    // attempt login when clicked on login button
    public void attemptLogin() {
        email_et.setError(null);
        password_et.setError(null);

        String email = email_et.getText().toString();
        String password = password_et.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a empty password.
        if (TextUtils.isEmpty(password)) {
            password_et.setError("Password is Empty!!! ");
            focusView = password_et;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_et.setError("Email ID is Empty");
            focusView = email_et;
            cancel = true;
        } else if (!isEmailValid(email)) {
            email_et.setError("Enter a valid Email ID");
            focusView = email_et;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            logIn();
        }
    }

    private boolean isEmailValid(String email) {
        // You can add more checking logic here.
        return email.contains("@");
    }


    public void logIn() {

        final String email = email_et.getText().toString();
        final String password = password_et.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Log.d("serveasy", "Sign in Successful");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("serveasy", "USER IS: " + user);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);

                } else {
                    Log.d("serveasy", "Failure in Sign In" + task.getException());

                    showErrorDialog("Failure Loggin In, Please Try again");

                }

            }
        });
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("OOPS")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        signupTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }


    //Sign In Method For Existing User
    /**/


}
