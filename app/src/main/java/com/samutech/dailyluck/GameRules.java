package com.samutech.dailyluck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class GameRules extends AppCompatActivity {



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = findViewById(R.id.listview);

        db.collection("Rules").document("2CcUpAw3vNUkhACV72m9").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                ArrayList<String> rules = (ArrayList<String>) task.getResult().get("rules");

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(GameRules.this,
                        R.layout.activity_listview, R.id.textView, rules);
                listView.setAdapter(adapter);


            }
        });

    }
}
