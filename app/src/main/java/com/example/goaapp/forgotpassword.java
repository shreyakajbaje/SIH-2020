package com.example.goaapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private Button sendmail;
    private EditText emailip;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        sendmail=(Button)findViewById(R.id.send);
        emailip=(EditText)findViewById(R.id.mail);
        mAuth=FirebaseAuth.getInstance();
        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = emailip.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(forgotpassword.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(userEmail)
                            .addOnCompleteListener(forgotpassword.this, new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(forgotpassword.this, "Please check your Email Account", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(forgotpassword.this, login.class));
                                    } else {
                                        String message = task.getException().getMessage();
                                        Toast.makeText(forgotpassword.this, "Error Occured: " + message, Toast.LENGTH_SHORT).show();
                                    }
                                }


                            });


                }
            }
        });
    }
}

