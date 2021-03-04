package com.example.lp_jc_last_project;


import android.app.Application;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;


import com.example.lp_jc_last_project.Model.RecipeItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class MainViewModel extends ViewModel{



    private MutableLiveData<List<RecipeItem>> mutableLiveData;

    public LiveData<List<RecipeItem>> getImageModelList() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            initImageModel();
        }

        return mutableLiveData;
    }

    private void initImageModel() {
        List<RecipeItem> recipeItemList = new ArrayList<RecipeItem>();
        recipeItemList.add(new RecipeItem());
        recipeItemList.add(new RecipeItem());
        recipeItemList.add(new RecipeItem());
        recipeItemList.add(new RecipeItem());
        recipeItemList.add(new RecipeItem());
        recipeItemList.add(new RecipeItem());
        recipeItemList.add(new RecipeItem());



        mutableLiveData.setValue(recipeItemList);
    }


    public void changeRecipeImage(int position, RecipeItem recipeItem) {
        if(mutableLiveData.getValue() != null) {
            List<RecipeItem> recipeItemList = new ArrayList<>(mutableLiveData.getValue());
            recipeItemList.set(position, recipeItem);
            mutableLiveData.setValue(recipeItemList);
        }
    }

    public void replacePicture(int position, RecipeItem recipeItem) {
        if (mutableLiveData.getValue() != null) {
            List<RecipeItem> recipeItemList = new ArrayList<>(mutableLiveData.getValue());
            recipeItemList.set(position,recipeItem);
            mutableLiveData.setValue(recipeItemList);
        }

    }

    public void deletePicture(int position) {
        if (mutableLiveData.getValue() != null) {
            List<RecipeItem> recipeItemList = new ArrayList<>(mutableLiveData.getValue());
            recipeItemList.remove(position);
            mutableLiveData.setValue(recipeItemList);

        }

    }


    public void addRecipeImage(RecipeItem recipeItem) {
        if (mutableLiveData.getValue() != null) {
            List<RecipeItem> recipeItemList = new ArrayList<>(mutableLiveData.getValue());
            recipeItemList.add(recipeItem);
            mutableLiveData.setValue(recipeItemList);
        }



    }

}

