package com.samutech.dailyluck.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samutech.dailyluck.R;

public class HintViewHolder extends RecyclerView.ViewHolder {

    TextView hint,name;

    public HintViewHolder(@NonNull View itemView) {
        super(itemView);

        hint = itemView.findViewById(R.id.hint);
        name = itemView.findViewById(R.id.name);

    }
}
