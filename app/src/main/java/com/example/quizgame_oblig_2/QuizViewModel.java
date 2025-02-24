package com.example.quizgame_oblig_2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {

    final private QuizRepository repository;
    final private LiveData<List<Quiz>> allQuizzes;

    public QuizViewModel (Application application){
        super(application);
        repository = new QuizRepository(application);
        allQuizzes = repository.getAllQuizzes();
    }

    LiveData<List<Quiz>> getAllQuizzes(){return allQuizzes;}

}
