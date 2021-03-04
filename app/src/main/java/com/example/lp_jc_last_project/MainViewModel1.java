package com.example.lp_jc_last_project;

import com.example.lp_jc_last_project.Model.AllRecipe;
import com.example.lp_jc_last_project.Model.RecipeItem;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateHandle;

import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class MainViewModel1 extends ViewModel{

    private MutableLiveData<List<AllRecipe>> mutableLiveData;


    public LiveData<List<AllRecipe>> getAllRecipeImageList() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            initImageModel();
        }

        return mutableLiveData;
    }






    private void initImageModel() {
        List<AllRecipe> allRecipeList = new ArrayList<AllRecipe>();
        List <RecipeItem> recipeItemList2 = new ArrayList<>();
        recipeItemList2.add(new RecipeItem(0, R.drawable.pancakes));
        recipeItemList2.add(new RecipeItem(1, R.drawable.pancakes));
        recipeItemList2.add(new RecipeItem(2, R.drawable.pancakes1));
        recipeItemList2.add(new RecipeItem(3, R.drawable.pancakes3));
        recipeItemList2.add(new RecipeItem(4, R.drawable.pancakes4));
        recipeItemList2.add(new RecipeItem(5, R.drawable.pancakes5));
        recipeItemList2.add(new RecipeItem(6, R.drawable.pancakes6));

        // added in 1st category
        List <RecipeItem> recipeItemList1 = new ArrayList<>();
        recipeItemList1.add(new RecipeItem(0, R.drawable.fries));
        recipeItemList1.add(new RecipeItem(1, R.drawable.fries2));
        recipeItemList1.add(new RecipeItem(2, R.drawable.fries2));
        recipeItemList1.add(new RecipeItem(3, R.drawable.fries3));
        recipeItemList1.add(new RecipeItem(4, R.drawable.fries4));
        recipeItemList1.add(new RecipeItem(5, R.drawable.fries5));
        recipeItemList1.add(new RecipeItem(6, R.drawable.fries6));

        // added in category 0
        List <RecipeItem> recipeItemList = new ArrayList<>();
        recipeItemList.add(new RecipeItem(0, R.drawable.chili));
        recipeItemList.add(new RecipeItem(1, R.drawable.chilli1));
        recipeItemList.add(new RecipeItem(2, R.drawable.chilli2));
        recipeItemList.add(new RecipeItem(3, R.drawable.chilli3));
        recipeItemList.add(new RecipeItem(4, R.drawable.chilli4));
        recipeItemList.add(new RecipeItem(5, R.drawable.chilli5));
        recipeItemList.add(new RecipeItem(6, R.drawable.chilli6));


        allRecipeList = new ArrayList<>();
        allRecipeList.add(new AllRecipe("Chilli sin Carne", "-Tofu \n" + "-Tomatos\n" + "-Kidney Beans \n" + "-Corn", recipeItemList));
        allRecipeList.add(new AllRecipe("Pancakes", "-Flour \n" + "-Milk \n" + "-Baking Soda\n" + "-Eggs", recipeItemList2));
        allRecipeList.add(new AllRecipe("Another One", "This recipe is quit easy, \n" + "just put some fries in the oven", recipeItemList1));



        mutableLiveData.setValue(allRecipeList);
    }





    public void changeRecipeImage(int position, AllRecipe allRecipe) {
        if(mutableLiveData.getValue() != null) {
            List<AllRecipe> allRecipeList = new ArrayList<>(mutableLiveData.getValue());
            allRecipeList.set(position, allRecipe);
            mutableLiveData.setValue(allRecipeList);
        }
    }

    public void deleteRecipe(int position) {
        if (mutableLiveData.getValue() != null) {
            List<AllRecipe> allRecipeList = new ArrayList<>(mutableLiveData.getValue());
            allRecipeList.remove(position);
            mutableLiveData.setValue(allRecipeList);

        }

    }


    public void addNewRecipe(AllRecipe allRecipe) {
        if (mutableLiveData.getValue() != null) {
            List<AllRecipe> allRecipeList = new ArrayList<>(mutableLiveData.getValue());
            allRecipeList.add(allRecipe);
            mutableLiveData.setValue(allRecipeList);
        }



    }



}