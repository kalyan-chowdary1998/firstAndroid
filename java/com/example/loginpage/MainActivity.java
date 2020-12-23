package com.example.loginpage;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button newUser;
    TextInputLayout email, password;
    Button login;
    FirebaseAuth mFireBaseAuth;
    Button forgotPassword;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Assign Variables
        mFireBaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUserAccount();
            }
        });
        newUser = (Button) findViewById(R.id.newUser);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistrationPage();
            }
        });
        forgotPassword = (Button) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void loginUserAccount() {
        String email1, password1;
        email1 = email.getEditText().getText().toString();
        password1 = password.getEditText().getText().toString();
        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(getApplicationContext(), "please enter your username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(email1)&& TextUtils.isEmpty(password1)) {
            Toast.makeText(MainActivity.this, "Fields Are Empty!", Toast.LENGTH_SHORT).show();
        } else if (!(TextUtils.isEmpty(email1) && TextUtils.isEmpty(password1))) {
            mFireBaseAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intToHome = new Intent(MainActivity.this, Home.class);
                        startActivity(intToHome);
                    }
                }
            });
        }
        else{
            Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

        }

    }
        public void openRegistrationPage () {
            Intent intent = new Intent(this, Registration.class);
            startActivity(intent);
        }

    }

