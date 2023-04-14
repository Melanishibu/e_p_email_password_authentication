package com.example.e_p_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    String username1,password1;
    TextView su,fp;
    Button login;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.eusername);
        password=findViewById(R.id.epassword);
        su=findViewById(R.id.signin);
        fp=findViewById(R.id.fpass);
        login=findViewById(R.id.login);
        fauth=FirebaseAuth.getInstance();

        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 username1=username.getText().toString().trim();
                 password1=password.getText().toString().trim();
                if (TextUtils.isEmpty(username1)) {
                    Toast.makeText(MainActivity.this,"username empty",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(MainActivity.this,"password empty",Toast.LENGTH_SHORT).show();
                }
                fauth.signInWithEmailAndPassword(username1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"login succesfull",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,main_page.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,"login failed",Toast.LENGTH_SHORT).show();
            }
        });

           }
       });
       fp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,forgot_password.class);
               startActivity(intent);
           }
       });
    }
}