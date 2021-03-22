package com.samutech.dailyluck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samutech.dailyluck.R;
import com.samutech.dailyluck.model.TicketsModel;

import java.util.ArrayList;

public class TicketsDrawAdapter extends RecyclerView.Adapter<TicketsViewHolder> {



    Context context;
    private ArrayList<TicketsModel> list = new ArrayList<>();


    public TicketsDrawAdapter(Context context, ArrayList<TicketsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawsitem, parent, false);

        return new TicketsViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder holder, int position) {

        String num = list.get(position).getLuckynumber();

        holder.one.setText(num.substring(0,1));
        holder.two.setText(num.substring(1,2));
        holder.three.setText(num.substring(2,3));
        holder.four.setText(num.substring(3,4));
        holder.five.setText(num.substring(4,5));
        holder.six.setText(num.substring(5,6));

        holder.id.setText(String.valueOf(position+1));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
