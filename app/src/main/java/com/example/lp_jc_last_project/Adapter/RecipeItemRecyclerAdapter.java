package com.example.lp_jc_last_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lp_jc_last_project.Model.RecipeItem;
import com.example.lp_jc_last_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeItemRecyclerAdapter extends RecyclerView.Adapter<RecipeItemRecyclerAdapter.RecipeItemViewHolder> {

    private Context context;
    private List<RecipeItem> recipeItemList;



    public RecipeItemRecyclerAdapter() {
    }

    public RecipeItemRecyclerAdapter(Context context, List<RecipeItem> recipeItemList) {
        this.context = context;
        this.recipeItemList = recipeItemList;


    }






    @NonNull
    @Override
    public RecipeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recipe_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemViewHolder holder, int position) {
        RecipeItem item = recipeItemList.get(position);




        if(item.getImageUrl() !=null){
            Picasso.get().load(item.getImageUrl()).resize(400,400).centerCrop().into(holder.itemImage);

        }else{
            Picasso.get().load(item.getUriImg()).resize(400,400).centerCrop().into(holder.itemImage);
        }











    }

    public void setAddRecipeList(List<RecipeItem> RecipeItemList){
        this.recipeItemList = RecipeItemList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return recipeItemList.size();

    }

    public static final class RecipeItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;


        public RecipeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);


        }
    }

}

