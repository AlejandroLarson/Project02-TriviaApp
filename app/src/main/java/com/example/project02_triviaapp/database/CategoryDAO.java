package com.example.project02_triviaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project02_triviaapp.MainActivity;
import com.example.project02_triviaapp.database.entities.Category;
import com.example.project02_triviaapp.database.entities.User;

import java.util.List;

@Dao
public interface CategoryDAO {
    // TODO: might want to check this conflict strategy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category... category);

    @Delete
    void delete(Category category);
    @Query("DELETE FROM " + TriviaDatabase.CATEGORY_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + TriviaDatabase.CATEGORY_TABLE)
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM " + TriviaDatabase.CATEGORY_TABLE + " WHERE categoryText = :categoryText")
    LiveData<Category> getCategoryByCategoryText(String categoryText);

}
