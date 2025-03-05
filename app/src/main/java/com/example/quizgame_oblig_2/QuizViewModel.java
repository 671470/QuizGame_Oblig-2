package com.example.quizgame_oblig_2;

import android.app.Application;
import android.os.Handler;


import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import java.util.ArrayList;


public class QuizViewModel extends AndroidViewModel {
    private final LiveData<List<Quiz>> allQuizzes;
    private final MutableLiveData<List<Quiz>> _shuffledQuizzes = new MutableLiveData<>();
    private final LiveData<List<Quiz>> shuffledQuizzes;
    private final MutableLiveData<Integer> _score = new MutableLiveData<>(0);
    private final LiveData<Integer> score = _score;

    private final MutableLiveData<Integer> _totalTries = new MutableLiveData<>(0);
    private final LiveData<Integer> totalTries = _totalTries;


    private  ArrayList<String> answers;

    private SavedStateHandle handle;
    private boolean shuffledAnswers = false;

    public QuizViewModel(Application application, SavedStateHandle savedStateHandle) {
        super(application);
        QuizRepository repository = new QuizRepository(application);
        allQuizzes = repository.getAllQuizzes();
        this.handle = savedStateHandle;

        shuffledQuizzes = Transformations.switchMap(allQuizzes, quizzes -> {
            MutableLiveData<List<Quiz>> shuffledLiveData = new MutableLiveData<>();
            if (quizzes != null && !quizzes.isEmpty()) {
                List<Quiz> shuffledList = new ArrayList<>(quizzes);
                Collections.shuffle(shuffledList);
                shuffledLiveData.setValue(shuffledList);
            }
            return shuffledLiveData;
        });
    }
    public LiveData<Integer> getScore() {
        return score;
    }

    public LiveData<Integer> getTotalTries() {
        return totalTries;
    }
    public void goToNextQuiz(){
        List<Quiz> newQuiz = shuffledQuizzes.getValue();
        newQuiz.remove(0);
        _shuffledQuizzes.setValue(newQuiz);

    }


    public void incrementScore() {
        if(_score.getValue() != null) {
            _score.setValue(_score.getValue() + 1);
        }
    }
    public void incrementTotalTries() {
        if(_totalTries.getValue() != null) {
            _totalTries.setValue(_totalTries.getValue() + 1);
        }
    }
    public void delayAfterAnswer(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextQuiz();
            }
    }, 2000);
        }

    public ArrayList<String> getAnswers(){

        if(!shuffledAnswers && shuffledQuizzes.getValue() != null && !shuffledQuizzes.getValue().isEmpty()) {
            ArrayList<String> answers = new ArrayList<>(Arrays.asList(
                    shuffledQuizzes.getValue().get(0).getAltAnswer1(),
                    shuffledQuizzes.getValue().get(0).getAltAnswer2(),
                    shuffledQuizzes.getValue().get(0).getRightAnswer()
            ));

            Collections.shuffle(answers);

            this.shuffledAnswers = true;
            this.answers = answers;
        }
        return answers;

    }

    public LiveData<List<Quiz>> getShuffledQuizzes() {
        return shuffledQuizzes;
    }
    public void saveButtonColor(String buttonKey, int color) {
        handle.set(buttonKey, color);
    }

    public Integer getButtonColor(String buttonKey) {
        return handle.get(buttonKey);
    }
}
