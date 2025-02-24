package com.example.quizgame_oblig_2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;
@Dao
public interface QuizDao {
    @Query("SELECT * FROM quizzes")
    LiveData<List<Quiz>>getAllQuizzes();

}
