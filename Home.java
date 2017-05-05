package com.example.maheshs.firebaseauth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity
{
    FirebaseAuth auth;
     FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        auth=FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void forgotpassword(View view)
    {
        Intent i=new Intent(this,Forgotpassword.class);
        startActivity(i);
    }


    public void changepassword(View view)
    {
        Intent i=new Intent(this,Changepassword.class);
        startActivity(i);

    }


    public void changeemail(View view)
    {

        Intent i=new Intent(this,Changeemail.class);
        startActivity(i);
    }

    public void deletemyaccount(View view)
    {
        user.delete()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            finish();
                            Toast.makeText(Home.this, "successfully deleted", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Home.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void signout(View view)
    {
        auth.signOut();
        finish();
    }
}
