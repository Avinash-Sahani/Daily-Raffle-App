package com.samutech.dailyluck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.adapter.HintViewAdapter;
import com.samutech.dailyluck.model.HintsModel;

import java.util.ArrayList;

public class HintsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    HintViewAdapter adapter;
    ArrayList<HintsModel> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db.collection("Hints").orderBy("date", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot : task.getResult()){

                    HintsModel model = new HintsModel();
                    model.setDraw_name(snapshot.getString("draw_name"));
                    model.setHint(snapshot.getString("hint"));

                    list.add(model);

                }

                adapter = new HintViewAdapter(HintsActivity.this,list);
                recyclerView.setAdapter(adapter);


            }
        });


    }
}
