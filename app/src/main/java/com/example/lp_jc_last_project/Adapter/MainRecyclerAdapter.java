package com.example.lp_jc_last_project.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lp_jc_last_project.Model.RecipeItem;
import com.example.lp_jc_last_project.Model.AllRecipe;
import com.example.lp_jc_last_project.R;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    private Context context;
    private List<AllRecipe> allRecipeList;



    public MainRecyclerAdapter(Context context, List<AllRecipe> allRecipeList) {
        this.context = context;
        this.allRecipeList = allRecipeList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.main_recycler_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.recipeTitle.setText(allRecipeList.get(position).getRecipeTitle());
        holder.recipeDescription.setText(allRecipeList.get(position).getRecipeDescription());
        setRecipeItemRecycler(holder.itemRecycler, allRecipeList.get(position).getRecipeItemList());

    }


    @Override
    public int getItemCount() {
        return allRecipeList.size();
    }




    public void onRestoreRecipe( List<AllRecipe> allRecipeList){
        this.allRecipeList = allRecipeList;
        notifyDataSetChanged();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView recipeTitle;
        TextView recipeDescription;
        RecyclerView itemRecycler;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeTitle = itemView.findViewById(R.id.recipe_title);
            recipeDescription = itemView.findViewById(R.id.recipe_description);
            itemRecycler = itemView.findViewById(R.id.item_recycler);



        }




    }

    private void setRecipeItemRecycler(RecyclerView recyclerView, List<RecipeItem> categoryItemList){

        RecipeItemRecyclerAdapter itemRecyclerAdapter = new RecipeItemRecyclerAdapter(context, categoryItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(itemRecyclerAdapter);

    }
}

