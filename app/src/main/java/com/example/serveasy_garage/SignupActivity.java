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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email_et, password_et, confirm_et;
    private Button signup_bt;


    public void bindviews() {
        email_et = findViewById(R.id.email_edittext);
        password_et = findViewById(R.id.password_edittext);
        confirm_et = findViewById(R.id.confirm_edittext);
        signup_bt = findViewById(R.id.signup_button);

    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("serveasy", "Current User is Signed In:" + currentUser);
    }


    // Create User Method For Registration of New User


    public void attemptRegistration() {
        email_et.setError(null);
        password_et.setError(null);
        confirm_et.setError(null);

        String email = email_et.getText().toString();
        String password = password_et.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.

        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            password_et.setError("Password Doesn't Match or is Empty!!! ");
            focusView = confirm_et;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            email_et.setError("");
            focusView = email_et;
            cancel = true;
        } else if (!isEmailValid(email)) {
            email_et.setError("");
            focusView = email_et;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            createUser();
        }
    }

    private boolean isEmailValid(String email) {
        // You can add more checking logic here.
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        String confirmPassword = confirm_et.getText().toString();
        return confirmPassword.equals(password) && confirmPassword.length() > 4;

    }

    public void createUser() {

        final String email = email_et.getText().toString();
        final String password = password_et.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Serveasy", "Create User With Email is Successful");
                            Log.d("Serveasy", "Email:" + email + " Password: " + password);
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("Serveasy", "User is" + user);

                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            finish();
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("Serveasy", "Error:" + task.getException());
                            showErrorDialog("User Registration Failed");

                        }

                    }
                });

    }

    // TODO: Create an alert dialog to show in case registration failed
    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("OOPS")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }


    //Sign In Method For Existing User
    /*public void signIn() {

        email = email_et.getText().toString();
        password = password_et.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Log.d("serveasy", "Sign in Successful");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d("serveasy", "USER IS: " + user);
                } else {
                    Log.d("serveasy", "Failure in Sign In" + task.getException());
                    Toast.makeText(SignupActivity.this, "Cant Log In", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bindviews();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        signup_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration();
            }
        });


    }
}
