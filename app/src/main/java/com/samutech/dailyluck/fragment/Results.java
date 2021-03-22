package com.samutech.dailyluck.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.GameRules;
import com.samutech.dailyluck.R;
import com.samutech.dailyluck.adapter.ResultViewAdapter;
import com.samutech.dailyluck.model.ResultModel;

import java.util.ArrayList;
import java.util.Collections;

public class Results extends Fragment {



    RecyclerView recyclerView;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<ResultModel> arrayList = new ArrayList<>();
    View view;
    ResultViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tickets, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Results");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));



        db.collection("Draws").whereEqualTo("status","Complete").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot : task.getResult()){




                        ResultModel model = new ResultModel();
                     //   model.setHash(snapshot.getString("hash"));
                       // model.setKey(snapshot.getString("key"));
                        model.setLucky_number(snapshot.getString("luckynumber"));
                        model.setName(snapshot.getString("name"));
                        model.setPrice(snapshot.getString("1stprice"));
                        model.setStatus(snapshot.getString("status"));
                        model.setHash(snapshot.getString("hashkey"));
                    //Toast.makeText(getActivity(),snapshot.getString("hashkey"),Toast.LENGTH_SHORT).show();
                        model.setKey(snapshot.getString("key"));
                        arrayList.add(model);



                }

                Collections.sort(arrayList,new SortByDraw());
                adapter = new ResultViewAdapter(view.getContext(),arrayList);
                recyclerView.setAdapter(adapter);

            }
        });


        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO add your menu :
        inflater.inflate(R.menu.rules, menu);
        //TODO call super
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.rules:

                Intent intent = new Intent(view.getContext(), GameRules.class);
                startActivity(intent);

                break;

        }

        return super.onOptionsItemSelected(item);
    }


}

