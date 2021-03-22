package com.samutech.dailyluck.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.samutech.dailyluck.FairDraw;
import com.samutech.dailyluck.R;
import com.samutech.dailyluck.model.ResultModel;

import java.util.ArrayList;

public class ResultViewAdapter extends RecyclerView.Adapter<ResultViewHolder> {

int count=0;
    Context context;
    private ArrayList<ResultModel> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> winners = new ArrayList<>();

    public ResultViewAdapter(Context context, ArrayList<ResultModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resultitem, parent, false);

        return new ResultViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ResultViewHolder holder, int position) {

        final String status = list.get(position).getStatus();
        final String luckynumber = list.get(position).getLucky_number();

        if (status.equals("Active")){

            holder.one.setText("?");
            holder.two.setText("?");
            holder.three.setText("?");
            holder.four.setText("?");
            holder.five.setText("?");
            holder.six.setText("?");

            holder.price.setText("Prize : "+list.get(position).getPrice()+"$");
            holder.hash.setText("Hash:\n"+list.get(position).getHash());
            holder.key.setText("Key:\n"+list.get(position).getKey());
            holder.key.setVisibility(View.GONE);
            holder.drawname.setText(list.get(position).getName());




        }else {

            holder.one.setText(luckynumber.substring(0,1));
            holder.two.setText(luckynumber.substring(1,2));
            holder.three.setText(luckynumber.substring(2,3));
            holder.four.setText(luckynumber.substring(3,4));
            holder.five.setText(luckynumber.substring(4,5));
            holder.six.setText(luckynumber.substring(5,6));
            holder.price.setText("Prize : "+list.get(position).getPrice()+"$");
            holder.hash.setText("Hash:\n"+list.get(position).getHash());
            holder.key.setText("Key:\n"+list.get(position).getKey());
            holder.drawname.setText(list.get(position).getName());

            db.collection("Tickets").whereEqualTo("draw",list.get(position).getName()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
count=0;

                    for (DocumentSnapshot snapshot : task.getResult()){

                        String status= snapshot.getString("status");
                        String name=snapshot.getString("draw");

                        if (status.equals("Win")){

                           count++;


                        }


                    }
                    holder.winners.setText(count+" " +"Winners");
                    holder.winners.setVisibility(View.VISIBLE);




                }


            });


        }

        holder.fairdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (holder.fairdraw.getText().toString().equals("Fair Draw")){

                    if (status.equals("Active")){

                        holder.key.setVisibility(View.GONE);
                        holder.hash.setVisibility(View.VISIBLE);
                        holder.fairdraw.setText("Why Fair Draw ?");


                    }else {


                        holder.key.setVisibility(View.VISIBLE);
                        holder.hash.setVisibility(View.VISIBLE);
                        holder.fairdraw.setText("Why Fair Draw ?");




                    }

                }else {

                    Intent intent = new Intent(context, FairDraw.class);
                    context.startActivity(intent);

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
