package com.samutech.dailyluck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samutech.dailyluck.R;
import com.samutech.dailyluck.model.HintsModel;

import java.util.ArrayList;

public class HintViewAdapter extends RecyclerView.Adapter<HintViewHolder> {


    Context context;
    private ArrayList<HintsModel> list = new ArrayList<>();

    public HintViewAdapter(Context context, ArrayList<HintsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hintsitem, parent, false);

        return new HintViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull HintViewHolder holder, int position) {


        holder.name.setText(list.get(position).getDraw_name());
        holder.hint.setText(list.get(position).getHint());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
