package com.example.livetoiletlocator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText username,password;
    Button button;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ahit);
        username = (EditText) findViewById(R.id.text1);
        password = (EditText) findViewById(R.id.text2);
        button = (Button) findViewById(R.id.b1);
        final String pass = password.getText().toString();
        final String usernam = username.getText().toString();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reference.child(usernam) != null && reference.child(pass) != null) {

                    reference.child(usernam).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            register_constructor registerConstructor = dataSnapshot.getValue(register_constructor.class);
                            if (usernam.equals(registerConstructor.getMobnumber())) {
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, complaint.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(login.this, "Enter correc details", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else {
                    Toast.makeText(login.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
