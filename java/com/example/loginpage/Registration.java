package com.example.loginpage;
import java.lang.CharSequence;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.content.Intent;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.logging.Logger;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity {

    public static final String TAG = "TAG";
    Button Register;
    Button alreadyRegistered;
    TextInputLayout  username, email, phone, password;
    FirebaseAuth mFireBaseAuth;
  FirebaseFirestore firestore;
  String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //assign variables
        mFireBaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        firestore = FirebaseFirestore.getInstance();
        Register = findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerbutton();
            }
        });
        alreadyRegistered = (Button) findViewById(R.id.alreadyRegistered);
        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });


    }

    public void registerbutton(){
        String username01, email01, phone01, password01;
        username01 = username.getEditText().getText().toString();
        email01 = email.getEditText().getText().toString();
        phone01 = phone.getEditText().getText().toString();
        password01 = password.getEditText().getText().toString();
        if (TextUtils.isEmpty((CharSequence) username01)) {
            Toast.makeText(getApplicationContext(), "please enter your username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty((CharSequence) email01)) {
            Toast.makeText(getApplicationContext(),  "please enter your email", Toast.LENGTH_SHORT).show();
        return;
        }
        if (TextUtils.isEmpty((CharSequence) phone01)) {
            Toast.makeText(getApplicationContext(),  "please enter your phone number", Toast.LENGTH_SHORT).show();
        return;
        }
        if (TextUtils.isEmpty((CharSequence) password01)) {
            Toast.makeText(getApplicationContext(),  "please enter your password", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty((CharSequence) username01)&& TextUtils.isEmpty((CharSequence) email01) && TextUtils.isEmpty((CharSequence) phone01) && TextUtils.isEmpty((CharSequence) password01) ){
            Toast.makeText(getApplicationContext(),  "please enter all the credentials", Toast.LENGTH_SHORT).show();
        }
        else if(!(TextUtils.isEmpty((CharSequence) username01)&& TextUtils.isEmpty((CharSequence) email01) && TextUtils.isEmpty((CharSequence) phone01) && TextUtils.isEmpty((CharSequence) password01)))
        {
            mFireBaseAuth.createUserWithEmailAndPassword(email01, password01).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "sign up again", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        userID = mFireBaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = firestore.collection("users").document(userID);
                        Map<String,Object> user = new HashMap<>(1);
                        user.put("username", username01);
                        user.put("email",email01);
                        user.put("phone",phone01);
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess:user profile created for  "+userID);
                            }
                        });
                        startActivity(new Intent(Registration.this, MainActivity.class));
                    }
                }
            });
        }
        else{
            Toast.makeText(Registration.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
        }
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}



