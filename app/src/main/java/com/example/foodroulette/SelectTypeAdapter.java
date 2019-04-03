package com.example.foodroulette;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.recyclerview.R;

import java.util.ArrayList;

public class SelectTypeAdapter extends RecyclerView.Adapter<com.example.foodroulette.SelectTypeAdapter.MyViewHolder> {
        // declaring some fields.
        private ArrayList<Restaurant> arrayList = new ArrayList<>();
        public ArrayList<String> selected =  new ArrayList<>();

        public SelectTypeAdapter(ArrayList<Restaurant> arrayList) {
            this.arrayList = arrayList;
        }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.v("CreateViewHolder", "in onCreateViewHolder");
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.select_food_type,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name, description;
            CheckBox checkBox;
            public MyViewHolder(View itemView) {
                super(itemView);
                Log.v("ViewHolder","in View Holder");
                name = itemView.findViewById(R.id.textView);
                description = itemView.findViewById(R.id.textView2);
                checkBox = itemView.findViewById(R.id.checkBox);
            }
        }
    }

