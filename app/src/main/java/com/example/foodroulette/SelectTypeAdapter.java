package com.example.foodroulette;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectTypeAdapter extends RecyclerView.Adapter<com.example.foodroulette.SelectTypeAdapter.MyViewHolder> {
        // declaring some fields.
        private ArrayList<FoodGenre> arrayList = new ArrayList<>();
        private String[] selected;


    public SelectTypeAdapter(ArrayList<FoodGenre> arrayList) {
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
        FoodGenre type = arrayList.get(i);
        myViewHolder.name.setText(type.getGenreName());
        myViewHolder.imageView.setImageResource(type.getmImageID());

        if(Arrays.asList(selected).contains(arrayList.get(i).GenreName.toLowerCase())){
            myViewHolder.checkBox.setChecked(true);
            }
        }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            CheckBox checkBox;
            ImageView imageView;
            public MyViewHolder(View itemView) {
                super(itemView);
                Log.v("ViewHolder","in View Holder");
                name = itemView.findViewById(R.id.textView);
                checkBox = itemView.findViewById(R.id.checkBox1);
                imageView = itemView.findViewById(R.id.imageView);

            }
        }

    public String[] getSelected() {
        return selected;
    }

    public void setSelected(String[] selected) {
        this.selected = selected;
    }
    }

