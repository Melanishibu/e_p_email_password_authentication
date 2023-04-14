package com.example.e_p_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText name,email,password;
    String name2,email2,password2;
    Button register;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.rname);
        email=findViewById(R.id.remail);
        password=findViewById(R.id.rpassword);
        register=findViewById(R.id.register);
        fauth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name2=name.getText().toString().trim();
                email2=email.getText().toString().trim();
                password2=password.getText().toString().trim();
                fauth.createUserWithEmailAndPassword(email2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                          FirebaseUser Fuser=fauth.getCurrentUser();
                          Fuser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  Toast.makeText(Register.this,"email registered",Toast.LENGTH_SHORT).show();
                              }
                          }).addOnFailureListener(new OnFailureListener() {
                              @Override
                              public void onFailure(@NonNull Exception e) {
                              Toast.makeText(Register.this,"email not registered",Toast.LENGTH_SHORT).show();
                              }
                          });

                        }
                        Intent intent=new Intent(Register.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "registration failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}