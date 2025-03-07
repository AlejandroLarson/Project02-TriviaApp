package com.example.project02_triviaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project02_triviaapp.database.entities.Scores;
import com.example.project02_triviaapp.database.entities.TopScoreWithUser;
import com.example.project02_triviaapp.database.entities.User;

import java.util.List;

@Dao
public interface ScoresDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Scores score);

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE + " WHERE userOwnerId = :userId")
    LiveData<List<Scores>> getScoresByUser(long userId);

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE + " WHERE categoryOwnerId = :categoryId")
    LiveData<List<Scores>> getScoresByCategory(long categoryId);

    @Query("SELECT * FROM " + TriviaDatabase.SCORES_TABLE)
    LiveData<List<Scores>> getAllScores();

    @Query("DELETE FROM " + TriviaDatabase.SCORES_TABLE)
    void deleteAll();

        // Query to get the top 3 scores for a category, including the username of the user
        @Query("SELECT " + TriviaDatabase.SCORES_TABLE + ".*, " + TriviaDatabase.USER_TABLE + ".username " +
                "FROM " + TriviaDatabase.SCORES_TABLE + " " +
                "INNER JOIN " + TriviaDatabase.USER_TABLE + " ON " + TriviaDatabase.SCORES_TABLE + ".userOwnerId = " + TriviaDatabase.USER_TABLE + ".userid " +
                "WHERE " + TriviaDatabase.SCORES_TABLE + ".categoryOwnerId = :categoryId " +
                "ORDER BY " + TriviaDatabase.SCORES_TABLE + ".score DESC LIMIT 3")
        LiveData<List<TopScoreWithUser>> getTopScoresForCategory(long categoryId);




}
