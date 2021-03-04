package com.example.lp_jc_last_project.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.example.lp_jc_last_project.Model.AllRecipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
public class PreConfig {

    private static final String LIST_KEY= "list_Key";

    public static void writeListInPref(Context context, List<AllRecipe> list){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriInOut())
                .create();
        String jsonString = gson.toJson(list);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();

    }

    public static  List<AllRecipe>  readListFromPref(Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = prefs.getString(LIST_KEY, "");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Uri.class, new UriInOut())
                .create();
        Type type = new TypeToken<ArrayList<AllRecipe>>(){}.getType();
        List<AllRecipe> list = gson.fromJson(jsonString,type);
        return list;

    }
}
