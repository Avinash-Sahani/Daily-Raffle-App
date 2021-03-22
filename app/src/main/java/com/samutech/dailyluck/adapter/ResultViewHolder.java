package com.samutech.dailyluck.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samutech.dailyluck.R;

public class ResultViewHolder extends RecyclerView.ViewHolder {


    TextView one,two,three,four,five,six,drawname,price,winners,key,hash;
    Button fairdraw;

    public ResultViewHolder(@NonNull View itemView) {
        super(itemView);


        one = itemView.findViewById(R.id.one);
        two = itemView.findViewById(R.id.two);
        three = itemView.findViewById(R.id.three);
        four = itemView.findViewById(R.id.four);
        five = itemView.findViewById(R.id.five);
        six = itemView.findViewById(R.id.six);
        drawname = itemView.findViewById(R.id.drawname);
        price = itemView.findViewById(R.id.price);
        winners = itemView.findViewById(R.id.winners);
        key = itemView.findViewById(R.id.key);
        hash = itemView.findViewById(R.id.hash);
        fairdraw = itemView.findViewById(R.id.fairdraw);

    }
}
