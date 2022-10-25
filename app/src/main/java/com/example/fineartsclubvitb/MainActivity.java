package com.example.fineartsclubvitb;


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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView tv;
    private EditText emailtextview;
    private EditText passwordtextview;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailtextview= findViewById(R.id.editTextTextEmailAddress);
        passwordtextview = findViewById(R.id.editTextTextPassword);
        tv=findViewById(R.id.textView11);
        button=findViewById(R.id.button);

        //firebase authentication instance
        mAuth = FirebaseAuth.getInstance();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Login_page.class);
                startActivity(intent);
            }
        });

        //Registration button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registernewuser();
            }
        });
            }
            private void registernewuser() {
                String email, password;
                email = emailtextview.getText().toString();
                password = passwordtextview.getText().toString();

                // Validation of entered details
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(this, "Please enter Email!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //creating new user/ registration
                mAuth
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration  successful!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), Welcome_page.class);
                                    startActivity(intent);
                                } else {

                                    // Registration failed
                                    Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }

}