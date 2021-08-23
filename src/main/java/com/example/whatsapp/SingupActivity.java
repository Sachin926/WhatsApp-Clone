package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsapp.Models.Users;
import com.example.whatsapp.databinding.ActivitySingupBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SingupActivity extends AppCompatActivity{

    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog loading;
    TextView alreadyAccount;
    GoogleSignInClient mGoogleSignInClient;
    Button butsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        EditText etemail = findViewById(R.id.etemail);
        EditText etpassword = findViewById(R.id.etpassword);
        EditText etusername = findViewById(R.id.userName);
        alreadyAccount = findViewById(R.id.alreadyaccount);
        database = FirebaseDatabase.getInstance();

        loading = new ProgressDialog(SingupActivity.this);  //temporary loading screen while signing up
        loading.setTitle("Creating Account");
        loading.setMessage("Please wait");

        //action of signup button
        Button but = findViewById(R.id.btnsignup);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                auth.createUserWithEmailAndPassword(etemail.getText().toString(),
                        etpassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loading.dismiss();
                        if (task.isSuccessful())
                        {
                            Toast ts = Toast.makeText(SingupActivity.this,
                                    "User Created successfully",
                                    Toast.LENGTH_SHORT);
                            ts.show();
                            String uid = task.getResult().getUser().getUid();
                            Users user = new Users(etusername.getText().toString(), etemail.getText().toString(), etpassword.getText().toString());
                            database.getReference().child("Users").child(uid).setValue(user);
                        }
                        else
                        {
                            Toast ts2 = Toast.makeText(SingupActivity.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_SHORT);
                            ts2.show();
                        }

                    }
                });
            }
        });
        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SingupActivity.this, signinActivity.class);
                startActivity(i);
            }
        });

    }

}