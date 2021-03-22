package com.samutech.dailyluck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class Username extends AppCompatActivity {


    EditText username;
    Button save;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> refres = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);

        username = findViewById(R.id.username);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(username.getText().toString())){

                    Toast.makeText(Username.this, "Invalid Username", Toast.LENGTH_SHORT).show();

                }else {


                    final ProgressDialog progressDialog = new ProgressDialog(Username.this);
                    progressDialog.setMessage("Please Wait");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    db.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            for (DocumentSnapshot snapshot : task.getResult() ){

                                String usernames = snapshot.getString("username");

                                if (usernames.equals(username.getText().toString())){

                                    username.setError("Username is already taken");
                                    progressDialog.dismiss();

                                }else {


                                    HashMap<String,Object> hashMap = new HashMap<>();
                                    hashMap.put("username",username.getText().toString());
                                    hashMap.put("balance","0");
                                    hashMap.put("refers",refres);
                                    hashMap.put("payment","null");

                                    db.collection("Users").document(uid).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){

                                                progressDialog.dismiss();
                                                Intent intent = new Intent(Username.this,HomeActivity.class);
                                                startActivity(intent);
                                                finish();


                                            }else {

                                                Toast.makeText(Username.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });

                                }
                            }

                        }
                    });

                }

            }
        });

    }
}
