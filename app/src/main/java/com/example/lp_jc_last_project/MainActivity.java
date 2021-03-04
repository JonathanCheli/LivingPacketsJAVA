package com.example.lp_jc_last_project;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.lp_jc_last_project.Adapter.MainRecyclerAdapter;
import com.example.lp_jc_last_project.Helpers.PreConfig;
import com.example.lp_jc_last_project.Model.AllRecipe;
import com.example.lp_jc_last_project.Model.RecipeItem;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView mainRecipeRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    public AddRecipe addRecipe;
    int pos;
    int newPos;
    List<AllRecipe> allRecipeList;
    String title, description;
    List<RecipeItem> list;

    List<RecipeItem> recipeItemList;
    List<RecipeItem> recipeItemList1;
    List<RecipeItem> recipeItemList2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // here we will add some dummy data to our model class
        // here we will add data to category item model class


        // added in 2nd category
        recipeItemList2 = new ArrayList<>();
        recipeItemList2.add(new RecipeItem(0, R.drawable.pancakes));
        recipeItemList2.add(new RecipeItem(1, R.drawable.pancakes));
        recipeItemList2.add(new RecipeItem(2, R.drawable.pancakes1));
        recipeItemList2.add(new RecipeItem(3, R.drawable.pancakes3));
        recipeItemList2.add(new RecipeItem(4, R.drawable.pancakes4));
        recipeItemList2.add(new RecipeItem(5, R.drawable.pancakes5));
        recipeItemList2.add(new RecipeItem(6, R.drawable.pancakes6));

        // added in 1st category
        recipeItemList1 = new ArrayList<>();
        recipeItemList1.add(new RecipeItem(0, R.drawable.fries));
        recipeItemList1.add(new RecipeItem(1, R.drawable.fries2));
        recipeItemList1.add(new RecipeItem(2, R.drawable.fries2));
        recipeItemList1.add(new RecipeItem(3, R.drawable.fries3));
        recipeItemList1.add(new RecipeItem(4, R.drawable.fries4));
        recipeItemList1.add(new RecipeItem(5, R.drawable.fries5));
        recipeItemList1.add(new RecipeItem(6, R.drawable.fries6));

        // added in category 0
        recipeItemList = new ArrayList<>();
        recipeItemList.add(new RecipeItem(0, R.drawable.chili));
        recipeItemList.add(new RecipeItem(1, R.drawable.chilli1));
        recipeItemList.add(new RecipeItem(2, R.drawable.chilli2));
        recipeItemList.add(new RecipeItem(3, R.drawable.chilli3));
        recipeItemList.add(new RecipeItem(4, R.drawable.chilli4));
        recipeItemList.add(new RecipeItem(5, R.drawable.chilli5));
        recipeItemList.add(new RecipeItem(6, R.drawable.chilli6));


        // added in 3rd category
        allRecipeList = new ArrayList<>();
        allRecipeList.add(new AllRecipe("Chilli sin Carne", "-Tofu \n" + "-Tomatos\n" + "-Kidney Beans \n" + "-Corn", recipeItemList));
        allRecipeList.add(new AllRecipe("Pancakes", "-Flour \n" + "-Milk \n" + "-Baking Soda\n" + "-Eggs", recipeItemList2));
        allRecipeList.add(new AllRecipe("Another One", "This recipe is quit easy, \n" + "just put some fries in the oven", recipeItemList1));


        list = new ArrayList<>();
        Intent intent = getIntent();
        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        list = getIntent().getParcelableArrayListExtra("list");
        pos = getIntent().getIntExtra("myPosition", pos);

        if (title != null && description != null) {
            if ((PreConfig.readListFromPref(this)) != null) {
                allRecipeList = PreConfig.readListFromPref(this);
                allRecipeList.add(new AllRecipe(title, description, list));


            }

        }


        setMainRecipeRecycler(allRecipeList);




        

    }





    public void setMainRecipeRecycler(List<AllRecipe> allRecipeList) {

        mainRecipeRecycler = findViewById(R.id.main_recycler);
        layoutManager = new LinearLayoutManager(this);
        mainRecipeRecycler.setHasFixedSize(true);
        mainRecipeRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allRecipeList);
        mainRecipeRecycler.setAdapter(mainRecyclerAdapter);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mainRecipeRecycler);
        mainRecyclerAdapter.notifyDataSetChanged();



    }

    public void navegateToAddRecipe(View view) {
        Intent intent = new Intent(MainActivity.this, AddRecipe.class);
        PreConfig.writeListInPref(getApplicationContext(), allRecipeList);
        startActivity(intent);

    }


    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            if (viewHolder instanceof MainRecyclerAdapter.MainViewHolder) {
                allRecipeList.remove(viewHolder.getAdapterPosition());
                PreConfig.writeListInPref(getApplicationContext(), allRecipeList);
                mainRecyclerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());


                newPos = viewHolder.getAdapterPosition();


            }

        }

    };


}

