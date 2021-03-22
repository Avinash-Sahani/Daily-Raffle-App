package com.samutech.dailyluck.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Referral extends Fragment {

    View view;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView refercode,reffers;
    EditText addrefer;
    Button save;
    LinearLayout l1;
    ArrayList<String> referssize = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.refers, container, false);

        l1 = view.findViewById(R.id.l1);
        refercode = view.findViewById(R.id.refercode);
        reffers = view.findViewById(R.id.refers);
        addrefer = view.findViewById(R.id.addrefer);
        save = view.findViewById(R.id.save);

        db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {



                refercode.setText("Your Referral Id : "+ task.getResult().getString("username"));
                ArrayList<String> referslsit = (ArrayList<String>) task.getResult().get("refers");
                reffers.setText(referslsit.size()+ " Referrals");



            }
        });


        db.collection("Users").whereArrayContains("refers",uid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.getResult().size() != 0){


                    l1.setVisibility(View.GONE);

                }else {

                    l1.setVisibility(View.VISIBLE);


                }

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                db.collection("Users").whereEqualTo("username",addrefer.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.getResult().size() == 0){


                            Toast.makeText(v.getContext(), "Wrong Refer Code", Toast.LENGTH_SHORT).show();

                        }else {


                            final ProgressDialog progressDialog  = new ProgressDialog(view.getContext());
                            progressDialog.setMessage("Please Wait");
                            progressDialog.show();

                            for (final DocumentSnapshot snapshot : task.getResult()){

                                db.collection("Users").document(snapshot.getId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                        ArrayList<String> ll = (ArrayList<String>) task.getResult().get("refers");
                                        ll.add(uid);
                                        HashMap<String,Object> hashMap =new HashMap();
                                        hashMap.put("refers",ll);

                                        db.collection("Users").document(snapshot.getId()).update("refers",ll).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                l1.setVisibility(View.GONE);
                                                progressDialog.dismiss();

                                            }
                                        });

                                    }
                                });

                            }

                        }

                    }
                });

            }
        });

        return view;
    }

    }
