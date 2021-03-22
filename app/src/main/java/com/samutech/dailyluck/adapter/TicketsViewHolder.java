package com.samutech.dailyluck.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samutech.dailyluck.R;

public class TicketsViewHolder extends RecyclerView.ViewHolder {


    TextView one,two,three,four,five,six,id;


    public TicketsViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.ids);
        one = itemView.findViewById(R.id.one);
        two = itemView.findViewById(R.id.two);
        three = itemView.findViewById(R.id.three);
        four = itemView.findViewById(R.id.four);
        five = itemView.findViewById(R.id.five);
        six = itemView.findViewById(R.id.six);

    }


}
