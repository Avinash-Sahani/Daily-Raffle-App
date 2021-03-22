package com.samutech.dailyluck.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.R;
import com.samutech.dailyluck.adapter.WinnerViewAdapter;
import com.samutech.dailyluck.model.WinnersModel;

import java.util.ArrayList;
import java.util.Collections;

public class Winners extends Fragment {


    RecyclerView recyclerView;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<WinnersModel> arrayList = new ArrayList<>();
    private ArrayList<WinnersModel> temp=new ArrayList<>();

    View view;
    WinnerViewAdapter adapter;
    String drawname=null;
    String draw=null;
    Boolean status=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tickets, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Winners");

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));

        db.collection("Draws").whereEqualTo("status", "Active").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot snapshot : task.getResult()) {

                    drawname = snapshot.getString("name");

                }


                viewTicketsss();




            }
        });





        return view;
    }

    private void viewTicketsss() {


        db.collection("Tickets").whereEqualTo("status", "Win").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot snapshot : task.getResult()) {
//Toast.makeText(getActivity(),drawname,Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getActivity(),"database Tickers"+drawname,Toast.LENGTH_SHORT).show();
                    draw = snapshot.getString("draw");

                    if (!(draw.equals(drawname))) {

//                        Toast.makeText(getActivity(), "IT doesnt WORKS \n \n" +
  //                              "draw= " + draw + "\n \n Draw= " + drawname, Toast.LENGTH_SHORT).show();


                        String uii = snapshot.getString("uid");

                        String status = snapshot.getString("status");


                        WinnersModel model = new WinnersModel();
                        model.setDraw(snapshot.getString("draw"));
                        model.setUid(snapshot.getString("uid"));
                        // Toast.makeText(getActivity(),snapshot.getString("price"),Toast.LENGTH_SHORT).show();
                        model.setPrize(snapshot.getString("amount"));

                        temp.add(model);

                    } else {
                        // Toast.makeText(getActivity(),"database Tickers"+draw,Toast.LENGTH_SHORT).show();

//    Toast.makeText(getActivity(),"\n database draw"+drawname,Toast.LENGTH_SHORT).show();


                    }


                }


                for (int i = 0; i < temp.size(); i++) {

                    if (!(temp.get(i).getDraw().equals(drawname))) {

                        arrayList.add(temp.get(i));


                    }

                    Collections.sort(arrayList,new SortByDrawNo());




                }


                adapter = new WinnerViewAdapter(view.getContext(), arrayList);
                recyclerView.setAdapter(adapter);

            }
        });





    }


}
