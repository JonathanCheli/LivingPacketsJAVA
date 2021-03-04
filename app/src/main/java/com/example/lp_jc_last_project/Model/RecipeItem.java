package com.example.lp_jc_last_project.Model;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class RecipeItem extends ViewModel implements Parcelable {


// here i am taking only image url. and this is as integer because i am using it from drawable file.

    Integer itemId;
    Integer imageUrl;
    Uri uriImg;
    Drawable imageDrawable;

    public RecipeItem(Integer itemId, Drawable imageDrawable) {
        this.itemId = itemId;
        this.imageDrawable = imageDrawable;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    private List<RecipeItem> RecipeItemList;


    public RecipeItem(Integer itemId, List<RecipeItem> recipeItemList) {
        this.itemId = itemId;
        RecipeItemList = recipeItemList;
    }

    public RecipeItem(List<RecipeItem> list) {
        this.RecipeItemList = list;

    }


    public List<RecipeItem> getRecipeItemList() {
        return RecipeItemList;
    }

    public void setRecipeItemList(List<RecipeItem> recipeItemList) {
        RecipeItemList = recipeItemList;
    }

    public RecipeItem() {
    }

    public RecipeItem(Integer itemId, Integer imageUrl) {
        this.itemId = itemId;
        this.imageUrl = imageUrl;
    }

    public RecipeItem(Integer itemId, Uri uriImg) {
        this.itemId = itemId;
        this.uriImg = uriImg;
    }


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Uri getUriImg() {
        return uriImg;
    }

    public void setUriImg(Uri uriImg) {
        this.uriImg = uriImg;
    }


    public RecipeItem(Parcel in) {
        this.uriImg = in.readParcelable(Uri.class.getClassLoader());

    }


    public static final Creator<RecipeItem> CREATOR = new Creator<RecipeItem>() {
        @Override
        public RecipeItem createFromParcel(Parcel in) {
            return new RecipeItem(in);
        }


        @Override
        public RecipeItem[] newArray(int size) {
            return new RecipeItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(uriImg, flags);







    }
}

