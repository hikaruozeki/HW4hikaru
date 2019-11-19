package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonSearch;
    EditText editTextInputZipCode;
    TextView textViewListOfBirds;
    TextView textViewListOfName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextInputZipCode = findViewById(R.id.editTextInputZipCode);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);
        textViewListOfBirds = findViewById(R. id.textViewListOfBirds);
        textViewListOfName = findViewById(R. id. textViewListOfName);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemRegister) {
            Intent MainIntent = new Intent(this, MainActivity.class);
            startActivity(MainIntent);
        } else if (item.getItemId() == R.id.itemSearch) {
            Toast.makeText(this, "You are already in Search page, you fool!", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (buttonSearch == view) {
            String findbird = editTextInputZipCode.getText().toString();
            myRef.orderByChild("zipcode").equalTo(findbird).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //String findKey = dataSnapshot.getKey();//
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBird = foundBird.bird;
                    String findName = foundBird.you;

                    textViewListOfBirds.setText(findBird);
                    textViewListOfName.setText(findName);
                }


                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

        });

         }

    }
}
