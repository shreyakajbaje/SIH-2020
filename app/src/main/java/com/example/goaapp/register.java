package com.example.goaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {


    EditText txtEmail,txtPassword,txtConfirmPassword,txtName,txtcontact;
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
        txtcontact = (EditText) findViewById(R.id.contact);


        firebaseAuth= FirebaseAuth.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                String confirpassword=txtConfirmPassword.getText().toString().trim();
                final String name1=txtName.getText().toString().trim();
                final String contact = txtcontact.getText().toString().trim();



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

                if (contact.length() != 10) {

                    Toast.makeText(register.this, "ENTER 10 DIGIT VALID CONTACT NUMBER", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);


                if((password.equals(confirpassword)&& (contact.length()==10))){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid());
                                        UserProfile userProfile = new UserProfile(name1, email, contact);
                                        myref.setValue(userProfile);

                                        Toast.makeText(getApplicationContext(),"You are Successfully Registered...", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(register.this, login.class));
                                        finish();
                                       /* startActivity(new Intent(getApplicationContext(), login.class));
                                        Toast.makeText(register.this, "You are Successfully Registered...", Toast.LENGTH_SHORT).show();*/
                                    } else {

                                        Toast.makeText(register.this, "AUTHENTICATION FAILED", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });



                }

            }
        });

        checkDataEntered();



    }
    boolean isEmail(EditText txtEmail)
    {
        CharSequence email = txtEmail.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isContact(EditText txtcontact)
    {
        CharSequence contact = txtcontact.getText().toString();
        return (!TextUtils.isEmpty(contact) && Patterns.PHONE.matcher(contact).matches());
    }



    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(txtName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(txtEmail)) {
            txtEmail.setError("Email Id is required!");
        }
        if (isEmpty(txtcontact)) {
            txtcontact.setError("Contact is required!");
        }

        if (isEmail(txtEmail) == false) {
            txtEmail.setError("Enter valid email!");
        }
        if (isContact(txtcontact) == false) {
            txtcontact.setError("Enter valid Contact!");
        }

    }
}

