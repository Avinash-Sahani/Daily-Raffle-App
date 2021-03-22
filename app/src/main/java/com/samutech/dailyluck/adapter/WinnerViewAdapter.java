package com.samutech.dailyluck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.samutech.dailyluck.R;
import com.samutech.dailyluck.model.WinnersModel;

import java.util.ArrayList;

public class WinnerViewAdapter extends RecyclerView.Adapter<WinnerViewHolder> {


    Context context;
    private ArrayList<WinnersModel> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public WinnerViewAdapter(Context context, ArrayList<WinnersModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WinnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.winnersitem, parent, false);

        return new WinnerViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull final WinnerViewHolder holder, int position) {

        String uid = list.get(position).getUid();
        db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                holder.name.setText(task.getResult().getString("username"));

            }
        });

        holder.price.setText(list.get(position).getPrize()+"$");
        holder.draw.setText(list.get(position).getDraw());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
