package com.example.maheshs.firebaseauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    String email,pass;
     EditText et1;
     EditText et2;
    FirebaseAuth auth;
     FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) (findViewById(R.id.emailid));
        et2 = (EditText) (findViewById(R.id.password));
        auth=FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, Home.class));
                    finish();
                }
            }
        };


    }

    public void login(View view)
    {
         email=et1.getText().toString().trim();
        pass=et2.getText().toString().trim();
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent i=new Intent(MainActivity.this,Home.class);
                            startActivity(i);
                        }
                        else
                            {
                                Toast.makeText(MainActivity.this, "u dont have account please create a account", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void signup(View view)
    {
        Intent i=new Intent(this,Signup.class);
        startActivity(i);

    }
}