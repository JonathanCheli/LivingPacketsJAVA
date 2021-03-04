package com.example.lp_jc_last_project.Model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class AllRecipe  extends ViewModel implements Parcelable {




    private String recipeTitle;
    private String recipeDescription;
    private List<RecipeItem> recipeItemList;



    public AllRecipe(List<RecipeItem> recipeItemList) {
        this.recipeItemList = recipeItemList;
    }


    public AllRecipe() {
    }

    public AllRecipe(String recipeTitle, String recipeDescription, List<RecipeItem> recipeItemList) {
        this.recipeTitle = recipeTitle;
        this.recipeDescription = recipeDescription;
        this.recipeItemList = recipeItemList;
    }



    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public List<RecipeItem> getRecipeItemList() {
        return recipeItemList;
    }

    public void setRecipeItemList(List<RecipeItem> recipeItemList) {
        this.recipeItemList = recipeItemList;
    }

    public AllRecipe (Parcel parcel) {

        this.recipeTitle = parcel.readString();
        this.recipeDescription = parcel.readString();
        this.recipeItemList = parcel.readArrayList(null);
    }


    public static final Creator<AllRecipe> CREATOR = new Creator<AllRecipe>() {
        @Override
        public AllRecipe createFromParcel(Parcel in) {
            return new AllRecipe(in);
        }


        @Override
        public AllRecipe[] newArray(int size) {
            return new AllRecipe[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeTitle);
        dest.writeString(recipeDescription);
        dest.writeList(recipeItemList);

    }
}

