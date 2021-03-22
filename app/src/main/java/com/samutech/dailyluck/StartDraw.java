package com.samutech.dailyluck;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class StartDraw extends AppCompatActivity {
    String drawnamestring;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String isstatus="Active";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


  db.collection("Draws").whereEqualTo("status", "Active").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
      @Override
      public void onComplete(@NonNull Task<QuerySnapshot> task) {

          for (DocumentSnapshot snapshot : task.getResult()) {


              drawnamestring = snapshot.getString("name");
              db.collection("Draws").document(drawnamestring).update("status",isstatus);

          }

      }
      });










    }

}






