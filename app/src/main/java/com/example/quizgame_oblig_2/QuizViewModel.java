package com.example.quizgame_oblig_2;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.Collections;
import java.util.List;

public class QuizViewModel extends AndroidViewModel {

    private final QuizRepository repository;
    private final LiveData<List<Quiz>> allQuizzes;
    private MutableLiveData<List<Quiz>> gameQuiz;

    public QuizViewModel(Application application) {
        super(application);
        repository = new QuizRepository(application);
        allQuizzes = repository.getAllQuizzes();
        gameQuiz = new MutableLiveData<>();
    }
    public void observeQuizzes(LifecycleOwner owner) {
        allQuizzes.observe(owner, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                if (quizzes == null || quizzes.isEmpty()) {
                    startDatabase(getApplication());
                    List<Quiz> quizTest = allQuizzes.getValue();
                    if (quizTest != null) {
                        Collections.shuffle(quizTest);
                        gameQuiz.setValue(quizTest);
                    }
                } else {
                    List<Quiz> quizTest = allQuizzes.getValue();
                    if (quizTest != null) {
                        Collections.shuffle(quizTest);
                        gameQuiz.setValue(quizTest);
                    }
                }
            }
        });

    }
    public void observeGameQuiz(LifecycleOwner owner) {
        gameQuiz.observe(owner, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                List<Quiz> quizTest = allQuizzes.getValue();
                if (quizTest != null) {
                    Collections.shuffle(quizTest);
                    gameQuiz.setValue(quizTest);}
            }});
    }

    public void startGame() {
        List<Quiz> quizTest = allQuizzes.getValue();
        if (quizTest != null) {
            Collections.shuffle(quizTest);
            gameQuiz.setValue(quizTest);
        }
    }

    LiveData<List<Quiz>> getAllQuizzes(){return allQuizzes;}
    LiveData<List<Quiz>> getGameQuiz() {
        return gameQuiz;
    }
    public void deleteAllQuiz(){
        repository.deleteAllQuizzes();
    }

    public void insertQuiz(Quiz quiz) {
        repository.insertQuiz(quiz);
    }
    public void deleteQuiz(Quiz quiz) {
        repository.deleteQuiz(quiz);
    }
    private void startDatabase(Application application){


        Uri orange = Uri.parse("android.resource://" +  application.getPackageName()+ "/" + R.drawable.oranges);
        Uri fruit = Uri.parse("android.resource://" +  application.getPackageName()+ "/" + R.drawable.fruits);
        Uri banana = Uri.parse("android.resource://" +  application.getPackageName()+ "/" + R.drawable.banana);


        Quiz newQuiz = new Quiz(banana.toString(), "YOYO", "wrong1", "wrong2");
        Quiz newQuiz1 = new Quiz(orange.toString(), "Orange", "wrong1", "wrong2");
        Quiz newQuiz2 = new Quiz(fruit.toString(), "right", "wrong1", "wrong2");
        insertQuiz(newQuiz);
        insertQuiz(newQuiz1);
        insertQuiz(newQuiz2);
    }
}
