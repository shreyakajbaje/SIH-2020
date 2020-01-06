package com.example.goaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {


    EditText txtEmail,txtPassword,txtConfirmPassword,txtName;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtPassword=(EditText)findViewById(R.id.txt_password);
        txtConfirmPassword=(EditText)findViewById(R.id.txt_confirm_password);
        btn_register=(Button) findViewById(R.id.buttonRegister);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        txtName=(EditText) findViewById(R.id.name);
        txtEmail=(EditText)findViewById(R.id.txt_email);


        firebaseAuth= FirebaseAuth.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                String confirpassword=txtConfirmPassword.getText().toString().trim();
                String name1=txtName.getText().toString().trim();




                if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this, "PLEASE ENTER EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(password)){
                    Toast.makeText(register.this, "PLEASE ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(confirpassword)){
                    Toast.makeText(register.this, "PLEASE ENTER CONFIRM PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(name1)){
                    Toast.makeText(register.this, "PLEASE ENTER NAME", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(password.length()<6){

                    Toast.makeText(register.this, "PASSWORD TOO SHORT", Toast.LENGTH_SHORT).show();
                }


                progressBar.setVisibility(View.VISIBLE);


                if(password.equals(confirpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        Toast.makeText(register.this, "REGISTRATION COMPLETE", Toast.LENGTH_SHORT).show();
                                    } else {

                                        Toast.makeText(register.this, "AUTHENTICATION FAILED", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });



                }

            }
        });



    }
}

