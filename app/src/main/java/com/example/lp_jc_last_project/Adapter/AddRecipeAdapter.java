package com.example.lp_jc_last_project.Adapter;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;


import com.example.lp_jc_last_project.AddRecipe;
import com.example.lp_jc_last_project.Model.RecipeItem;
import com.example.lp_jc_last_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddRecipeAdapter extends RecyclerView.Adapter<AddRecipeAdapter.ExampleViewHolder> {

    private List<RecipeItem> mAddRecList; /* = new ArrayList<>(); */



    private Context context;
    private OnItemClickListener mListener;

    /*
    public AddRecipeAdapter() {
    }

     */

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onLongItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView2;
        public FloatingActionButton floatingactionbutton;


        public ExampleViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            imageView2 = itemView.findViewById(R.id.item_image2);
            floatingactionbutton = itemView.findViewById(R.id.image_3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);







                        }



                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int which_item = getAdapterPosition();
                    new AlertDialog.Builder(v.getContext())
                            .setIcon(R.drawable.ic_clear_black_24)
                            .setTitle("Are you sure ?")
                            .setMessage("Do you want to delete this picture ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    listener.onLongItemClick(which_item);



                                }


                            })
                            .setNegativeButton("No",null)
                            .show();

                    return true;
                }
            });

            floatingactionbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(getAdapterPosition());


                }
            });

        }

    }

    public void setAddRecipeList(List<RecipeItem> addRecipeItemList){
        this.mAddRecList = addRecipeItemList;
        notifyDataSetChanged();

    }

  /*  public AddRecipeAdapter(ArrayList<AddRecipeItem> addRecipeItemList) {
        mAddRecList = addRecipeItemList;
    }


   */



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_recipe_row_items, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }


    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        RecipeItem currentAddRecipeItem = mAddRecList.get(position);


            Picasso.get().load(currentAddRecipeItem.getUriImg())
                    .resize(400,400).centerCrop()
                    .into(holder.imageView2);



    }
    @Override
    public int getItemCount() {
        return  mAddRecList.size();
    }
}

