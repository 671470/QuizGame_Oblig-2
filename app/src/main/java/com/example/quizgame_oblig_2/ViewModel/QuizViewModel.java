package com.example.quizgame_oblig_2.ViewModel;

import android.app.Application;
import android.os.Handler;


import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import java.util.ArrayList;


public class QuizViewModel extends AndroidViewModel {
    private final LiveData<List<Quiz>> allQuizzes;

    private final LiveData<List<Quiz>> galleryQuizzes;
    private final MutableLiveData<List<Quiz>> _allQuizzes = new MutableLiveData<>();

    private final MutableLiveData<List<Quiz>> _shuffledQuizzes = new MutableLiveData<>();
    private final LiveData<List<Quiz>> shuffledQuizzes;
    private final MutableLiveData<Integer> _score = new MutableLiveData<>(0);
    private final LiveData<Integer> score = _score;

    private final MutableLiveData<Integer> _totalTries = new MutableLiveData<>(0);
    private final LiveData<Integer> totalTries = _totalTries;


    private  ArrayList<String> answers;

    private SavedStateHandle handle;
    private boolean shuffledAnswers = false;
    private boolean newQuiz = false;

    public boolean isNewQuiz() {
        return newQuiz;
    }

    public void setNewQuiz(boolean newQuiz) {
        this.newQuiz = newQuiz;
    }

    public QuizViewModel(Application application, SavedStateHandle savedStateHandle) {
        super(application);
        QuizRepository repository = new QuizRepository(application);
        this.handle = savedStateHandle;


        allQuizzes = repository.getAllQuizzes();

        galleryQuizzes = Transformations.switchMap(allQuizzes, quizzes -> {
            if (quizzes != null && !quizzes.isEmpty()) {
                List<Quiz> shuffledList = new ArrayList<>(quizzes);
                _allQuizzes.setValue(shuffledList);
            }
            return _allQuizzes;
                });

        shuffledQuizzes = Transformations.switchMap(allQuizzes, quizzes -> {

            if (quizzes != null && !quizzes.isEmpty()) {
                List<Quiz> shuffledList = new ArrayList<>(quizzes);
                Collections.shuffle(shuffledList);
                _shuffledQuizzes.setValue(shuffledList);
            }
            return _shuffledQuizzes;
        });
    }
    public LiveData<List<Quiz>> getGalleryQuizzes(){
        return galleryQuizzes;
    }

    public void sortZtoA(){
        List<Quiz> newQuiz = allQuizzes.getValue();
        newQuiz.sort(Comparator.comparing(Quiz::getAltAnswer1).reversed());
        _allQuizzes.setValue(newQuiz);
    }

    public void sortAtoZ(){
        List<Quiz> newQuiz = allQuizzes.getValue();
        newQuiz.sort(Comparator.comparing(Quiz::getAltAnswer1));
        _allQuizzes.setValue(newQuiz);
    }

    public LiveData<List<Quiz>> getAllQuizzes() {
        return allQuizzes;
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
    public void delayAfterAnswer(List<String> buttons){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                shuffledAnswers = false;
                setNewQuiz(true);
                removeButtonColor(buttons);
                goToNextQuiz();

            }
    }, 1200);
        }

    public ArrayList<String> getAnswers(){

        if(!shuffledAnswers) {
            ArrayList<String> answers = new ArrayList<>(Arrays.asList(
                    shuffledQuizzes.getValue().get(0).getAltAnswer1(),
                    shuffledQuizzes.getValue().get(0).getAltAnswer2(),
                    shuffledQuizzes.getValue().get(0).getRightAnswer()
            ));
            Collections.shuffle(answers);

            shuffledAnswers = true;
            this.answers = answers;
        }
        return answers;

    }

    public LiveData<List<Quiz>> getShuffledQuizzes() {
        return shuffledQuizzes;
    }
    public void saveButtonColor(String buttonKey, String color) {
        handle.set(buttonKey, color);
    }

    public String getButtonColor(String buttonKey) {
        return handle.get(buttonKey);
    }

    public void removeButtonColor(List<String> buttonKey) {
        buttonKey.forEach(v -> handle.remove(v));
    }
}
