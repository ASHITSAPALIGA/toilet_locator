package com.example.livetoiletlocator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    EditText fname,lname,dob,email,pass,mobile;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RadioGroup radioGroup;
    Button button;
    private register_constructor register_constructor;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reg);
        fname = (EditText) findViewById(R.id.text1);
        dob = (EditText) findViewById(R.id.text3);
        lname = (EditText) findViewById(R.id.text2);
        mobile = (EditText) findViewById(R.id.text4);
        email = (EditText) findViewById(R.id.text5);
        pass = (EditText) findViewById(R.id.text6);
        radioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        final String fnam = fname.getText().toString();
        //final String gender = radioGroup.getTag().toString();
        final String date = dob.getText().toString();
        final String lnam = lname.getText().toString();
        final String mob = mobile.getText().toString();
        final String ema = email.getText().toString();
        final String password = pass.getText().toString();
        button = (Button) findViewById(R.id.register);
        register_constructor = new register_constructor();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Users") ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    register_constructor.setFname(fname.getText().toString());
                    register_constructor.setLname(lname.getText().toString());
                    register_constructor.setDob(dob.getText().toString());
                    register_constructor.setMobnumber(mobile.getText().toString());
                    register_constructor.setPassword(pass.getText().toString());
                    reference.child(register_constructor.getFname()).setValue(register_constructor);

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
