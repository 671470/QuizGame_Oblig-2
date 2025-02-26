package com.example.quizgame_oblig_2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizRepository {


    private final QuizDao quizDao;
    private final LiveData<List<Quiz>> allQuizzes;
    public QuizRepository(Application application){
        QuizRoomDatabase db;
        db = QuizRoomDatabase.getDatabase(application);
        quizDao = db.quizDao();
        allQuizzes = quizDao.getAllQuizzes();

    }
    public void deleteQuiz(Quiz quiz){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> quizDao.deleteQuiz(quiz));
        executor.shutdown();
    }
    public void insertQuiz(Quiz quiz){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> quizDao.insertQuiz(quiz));
        executor.shutdown();
    }
    public void deleteAllQuizzes(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(quizDao::deleteAll);
        executor.shutdown();
    }

    public LiveData<List<Quiz>> getAllQuizzes() {return allQuizzes;}


}
