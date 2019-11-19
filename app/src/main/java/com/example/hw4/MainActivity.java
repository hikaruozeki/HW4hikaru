package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonRegister;
    EditText editTextBirdName, editTextYourName;
    EditText editTextNewZipCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextYourName = findViewById(R.id.editTextYourName);
        editTextNewZipCode = findViewById(R.id.editTextNewZipCode);


        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);


    }
    



    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (buttonRegister == v) {
            String BirdName = editTextBirdName.getText().toString();
            String YourName = editTextYourName.getText().toString();
            String NewZipCode = editTextNewZipCode.getText().toString();


            Bird myBird = new Bird(BirdName, YourName, NewZipCode);

            myRef.push().setValue(myBird);

            Intent mainIntent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(mainIntent);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        return super.onCreateOptionsMenu(menu);








    }







}