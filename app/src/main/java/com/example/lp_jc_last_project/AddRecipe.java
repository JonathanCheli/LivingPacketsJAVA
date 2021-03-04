package com.example.lp_jc_last_project;


import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;


import com.example.lp_jc_last_project.Adapter.AddRecipeAdapter;
import com.example.lp_jc_last_project.Adapter.RecipeItemRecyclerAdapter;
import com.example.lp_jc_last_project.Model.AllRecipe;
import com.example.lp_jc_last_project.Model.RecipeItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class AddRecipe extends AppCompatActivity {

        private static final int PICK_IMAGE = 1;
        Uri uri;
        FloatingActionButton floatingView2, floatingView3;
        RecipeItem recipeI;
        RecyclerView recyclerView;
        EditText editTitle;
        EditText editDescription;
        Button saveData;
        AddRecipeAdapter mAdapter;
        private MainViewModel mainViewModel;
        int pos;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_recipe);





            floatingView2 = findViewById(R.id.item_image2);
            floatingView3 = findViewById(R.id.add_new_image);
            recyclerView = findViewById(R.id.item_recycler2);
            editTitle = findViewById(R.id.edit_title);
            editDescription = findViewById(R.id.edit_description);
            mAdapter = new AddRecipeAdapter();
            recyclerView.setAdapter(mAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


            mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
            mainViewModel.getImageModelList().observe(this, new Observer<List<RecipeItem>>() {
                @Override
                public void onChanged(List<RecipeItem> recipeItems) {
                    mAdapter.setAddRecipeList(recipeItems);
                    mAdapter.notifyDataSetChanged();



                    mAdapter.setOnItemClickListener(new AddRecipeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            pickImageFromGallery();
                            pos = position;

                        }

                        @Override
                        public void onDeleteClick(int position) {
                            recipeI = new RecipeItem();
                            mainViewModel.replacePicture(position, recipeI);
                            mAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onLongItemClick(int position) {
                            recipeI = new RecipeItem();
                            mainViewModel.deletePicture(position);
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(AddRecipe.this, "The image has been deleted", Toast.LENGTH_SHORT).show();


                        }
                    });

                    saveData = findViewById(R.id.save_data_id);
                    saveData.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String title = editTitle.getText().toString();
                            String description = editDescription.getText().toString();

                            if(title.trim().isEmpty() || description.trim().isEmpty() ){
                                Toast.makeText(AddRecipe.this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Intent intent = new Intent(AddRecipe.this, MainActivity.class);


                            intent.putExtra("myPosition",pos);
                            intent.putExtra("title", title);
                            intent.putExtra("description", description);
                            intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) recipeItems);




                            setResult(RESULT_OK, intent);
                            startActivity(intent);

                            finish();
                            onBackPressed();


                        }
                    });

                }
            });



        floatingView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeI = new RecipeItem();
                mainViewModel.addRecipeImage(recipeI);
                Toast.makeText(AddRecipe.this, "a new image has been added!", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
            }
        });
        }
        private void pickImageFromGallery() {
            Intent pickImage = new Intent();
            pickImage.setType("image/*");
            pickImage.setAction(pickImage.ACTION_GET_CONTENT);
            startActivityForResult(pickImage, PICK_IMAGE);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

                uri = data.getData();

                if(uri !=null) {
                    recipeI = new RecipeItem(0, uri);
                    mainViewModel.replacePicture(pos, recipeI);
                    mAdapter.notifyDataSetChanged();

                }

            }
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position",pos);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pos = savedInstanceState.getInt("position");
    }


}


