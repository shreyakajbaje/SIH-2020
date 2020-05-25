package com.example.goaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText txtemail,txtPassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;
    private TextView forgotpasswordlink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(login.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        txtemail=(EditText)findViewById(R.id.txt_email);
        txtPassword=(EditText)findViewById(R.id.txt_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        forgotpasswordlink=(TextView)findViewById(R.id.textView2);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=txtemail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this, "PLEASE ENTER EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this, "PLEASE ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.length()<6){

                    Toast.makeText(login.this, "PASSWORD TOO SHORT", Toast.LENGTH_SHORT).show();
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    SharedPrefs.saveSharedSetting(login.this, "CaptainCode", "false");
                                    Intent ImLoggedIn = new Intent(login.this, MainActivity.class);
                                    startActivity(ImLoggedIn);
                                    Toast.makeText(getApplicationContext(), "Welcome To Goa App !!",Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {

                                    Toast.makeText(login.this, "LOGIN FAILED OR USER NOT AVAILABLE", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });


            }
        });
        forgotpasswordlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, forgotpassword.class));

            }
        });
    }

    public void btn_signupForm(View view) {

        startActivity(new Intent(getApplicationContext(),register.class));
    }
}
