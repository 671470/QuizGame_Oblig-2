package com.example.quizgame_oblig_2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuizRepository {

    private final LiveData<List<Quiz>> allQuizzes;
    private final QuizDao quizDao;
    public QuizRepository(Application application){
        QuizRoomDatabase db;
        db = QuizRoomDatabase.getDatabase(application);
        quizDao = db.quizDao();
        allQuizzes = quizDao.getAllQuizzes();
    }

    public LiveData<List<Quiz>> getAllQuizzes() {return allQuizzes;}


}
