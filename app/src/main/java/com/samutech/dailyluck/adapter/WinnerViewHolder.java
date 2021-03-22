package com.samutech.dailyluck.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samutech.dailyluck.R;

public class WinnerViewHolder extends RecyclerView.ViewHolder {


    TextView name,draw,price;

    public WinnerViewHolder(@NonNull View itemView) {
        super(itemView);


        name = itemView.findViewById(R.id.name);
        draw = itemView.findViewById(R.id.draw);
        price = itemView.findViewById(R.id.price);

    }
}
