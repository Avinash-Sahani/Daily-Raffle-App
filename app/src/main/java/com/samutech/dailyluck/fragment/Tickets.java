package com.samutech.dailyluck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.Config;
import com.samutech.dailyluck.R;
import com.samutech.dailyluck.adapter.TicketsDrawAdapter;
import com.samutech.dailyluck.model.TicketsModel;

import java.util.ArrayList;

public class Tickets extends Fragment {


    RecyclerView recyclerView;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<TicketsModel> arrayList = new ArrayList<>();
    View view;
    TicketsDrawAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tickets, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(Config.draw+" Tickets");

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));



        db.collection("Tickets").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot : task.getResult()){


                    String draw = snapshot.getString("draw");
                    String uii = snapshot.getString("uid");
                    if (draw!=null && draw.equals(Config.draw)){

                        if  ( uii!=null  && uid!=null && uii.equals(uid) )
                        {


                        TicketsModel model = new TicketsModel();
                        model.setLuckynumber(snapshot.getString("luckynumber"));

                        arrayList.add(model);
                    }
                    }


                }

                adapter = new TicketsDrawAdapter(view.getContext(),arrayList);
                recyclerView.setAdapter(adapter);

            }
        });


        return view;
    }
}
