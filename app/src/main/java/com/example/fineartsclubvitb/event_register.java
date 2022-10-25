package com.example.fineartsclubvitb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class event_register extends AppCompatActivity {
    private EditText name_text, regisration_no_text,email_text;
    private Button submit_but;
    private TextView retrivedata;
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;

    // creating a variable for
    // our object class
    User UserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_register);

       name_text=findViewById(R.id.editTextTextPersonName);
       regisration_no_text=findViewById(R.id.editTextTextPersonName2);
       email_text=findViewById(R.id.editTextTextEmailAddress3);
       submit_but=findViewById(R.id.button3);
       retrivedata = findViewById(R.id.textView11);

        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("UserInfo");

        UserInfo = new User();

        submit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= name_text.getText().toString();
                String registration_no= regisration_no_text.getText().toString();
                String email= email_text.getText().toString();
                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(registration_no) && TextUtils.isEmpty(email)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(event_register.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(name, registration_no, email);
                }


            }
        });
    }
    private void addDatatoFirebase(String name,String registration_no, String email){
        UserInfo.setUserName(name);
        UserInfo.setUserRegitrationNumber(registration_no);
        UserInfo.setemailAddress(email);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(UserInfo);
                Toast.makeText(event_register.this, "Registration successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(event_register.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();

            }
        });
        getdata();

    }
    private  void  getdata(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value= snapshot.getValue(String.class);
                retrivedata.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(event_register.this, "Failed to get data", Toast.LENGTH_SHORT).show();

            }
        });
    }


}