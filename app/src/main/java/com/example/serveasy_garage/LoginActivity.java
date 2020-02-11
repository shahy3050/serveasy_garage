package com.example.serveasy_garage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText email_et, password_et;
    private TextView signupTV;
    private Button login_bt;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;


    public void bindViews() {
        email_et = findViewById(R.id.email_edit_text);
        password_et = findViewById(R.id.password_edit_text);
        login_bt = findViewById(R.id.login_button);
        signupTV = findViewById(R.id.register_text_view);
        inputLayoutEmail = findViewById(R.id.email_input_layout);
        inputLayoutPassword = findViewById(R.id.password_input_layout);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("serveasy", "User :" + user);
    }

    // attempt login when clicked on login button
    public void attemptLogin() {
        boolean isValid = true;
        if (email_et.getText().toString().isEmpty()) {
            inputLayoutEmail.setError("Email Address is Empty");
            isValid = false;
        } else if (!email_et.getText().toString().contains("@")) {
            inputLayoutEmail.setError("@ symbol missing ");


        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        if (password_et.getText().toString().isEmpty()) {
            inputLayoutPassword.setError("Password is Empty");
            isValid = false;
        } else if (password_et.getText().toString().trim().length() < 8) {
            inputLayoutPassword.setError("Password should have more than 8 characters");

        } else if (isValid) {

//            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            inputLayoutPassword.setErrorEnabled(false);
            logIn();

        }


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

                    showErrorDialog("Failure Logging In, Please Try again");

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
