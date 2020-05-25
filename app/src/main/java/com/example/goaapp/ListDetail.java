package com.example.goaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class ListDetail extends AppCompatActivity {
    TextView title,location, exptext;
    EditText fillexp, fillsuggest;
    RatingBar ratingBar;
    float rateValue;
    String temp;
    TextView ratings;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);
        title=(TextView) findViewById(R.id.title);
        location=(TextView)findViewById(R.id.location);
        exptext=(TextView) findViewById(R.id.exptext);
        fillexp=(EditText)findViewById(R.id.fillexp);
        fillsuggest=(EditText)findViewById(R.id.fillsuggest);
        ratings=(TextView)findViewById(R.id.fillrating);
        submit=(Button)findViewById(R.id.submit);
        ratingBar=findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();
                if(rateValue<=1 && rateValue>0)
                    ratings.setText("Bad " + rateValue + "/5");
                else if(rateValue<=2 && rateValue>1)
                    ratings.setText("OK " +rateValue + "/5");
                else if(rateValue<=3 && rateValue>2)
                    ratings.setText("Good " +rateValue + "/5");
                else if(rateValue<=4 && rateValue>3)
                    ratings.setText("Very Good " +rateValue + "/5");
                else if(rateValue<=5 && rateValue>4)
                    ratings.setText("Best " +rateValue + "/5");
            }

        });
        ratingBar.setRating(0);
        ratings.setText("");



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String exp=fillexp.getText().toString();
                String sug=fillsuggest.getText().toString();
                String rate=ratings.getText().toString();

                if(TextUtils.isEmpty(exp))
                {
                    Toast.makeText(ListDetail.this, "PLEASE ENTER YOUR EXPERIENCE", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(rate))
                {
                    Toast.makeText(ListDetail.this, "PLEASE GIVE RATINGS", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if((TextUtils.isEmpty(rate)) && (TextUtils.isEmpty(exp)))
                {
                    Toast.makeText(ListDetail.this, "PLEASE ENTER YOUR FEEDBACK", Toast.LENGTH_SHORT).show();
                    return;
                }

                Feedback fb=new Feedback(

                        title.getText().toString(),location.getText().toString(),fillexp.getText().toString(),fillsuggest.getText().toString(),ratings.getText().toString()

                );

                String myCurrentDateTime= DateFormat.getDateTimeInstance()
                        .format(Calendar.getInstance().getTime());

                FirebaseDatabase.getInstance().getReference("Feedback")
                        .child(myCurrentDateTime).setValue(fb).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(ListDetail.this, "Feedback Successfully Submitted", Toast.LENGTH_SHORT).show();

                            finish();
                        }



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListDetail.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                });



            }


        });

        Bundle mBundle=getIntent().getExtras();

        if(mBundle!=null) {
            //foodImage.setImageResource(mBundle.getInt("Image"));
            title.setText(mBundle.getString("title"));
            location.setText(mBundle.getString("location"));
        }



       /* Intent intent = getIntent();

        String place = intent.getStringExtra("tpname");
      //  String loc= intent.getStringExtra("tplname");


        title.setText("Tourist Place: " + place);*/
    }
}
