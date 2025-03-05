package com.example.quizgame_oblig_2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizgame_oblig_2.databinding.FragmentButtonsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ButtonFragment extends Fragment {

    private FragmentButtonsBinding binding;
    private QuizViewModel viewModel;
    private String rightAnswer;

    public ButtonFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentButtonsBinding.inflate(inflater, container, false);

        viewModel.getShuffledQuizzes().observe(getViewLifecycleOwner(), new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                if (quizzes != null && !quizzes.isEmpty()) {
                    Quiz currentQuiz = quizzes.get(0);
                    rightAnswer = currentQuiz.getRightAnswer();

                  ArrayList<String> answers = viewModel.getAnswers();

                    binding.button2.setText(answers.get(0));
                    if(viewModel.getButtonColor(String.valueOf(binding.button2.getId())) != null){
                        binding.button2.setBackgroundColor(viewModel.getButtonColor(String.valueOf(binding.button2.getId())));
                        binding.button2.setClickable(false);
                    }
                    binding.button3.setText(answers.get(1));
                    if(viewModel.getButtonColor(String.valueOf(binding.button3.getId())) != null){
                        binding.button3.setBackgroundColor(viewModel.getButtonColor(String.valueOf(binding.button3.getId())));
                        binding.button3.setClickable(false);
                    }
                    binding.button4.setText(answers.get(2));
                    if(viewModel.getButtonColor(String.valueOf(binding.button4.getId())) != null){
                        binding.button4.setBackgroundColor(viewModel.getButtonColor(String.valueOf(binding.button4.getId())));
                        binding.button4.setClickable(false);

                    }
                }
            }
        });


        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosenAnswer = ((android.widget.Button) v).getText().toString();
                if (chosenAnswer.equals(rightAnswer)) {
                    v.setBackgroundColor(Color.GREEN);
                    viewModel.saveButtonColor(String.valueOf(v.getId()), Color.GREEN);
                    viewModel.incrementScore();
                    viewModel.delayAfterAnswer();


                } else {
                    v.setBackgroundColor(Color.RED);
                    viewModel.saveButtonColor(String.valueOf(v.getId()), Color.RED);
                    viewModel.incrementTotalTries();


                }
               v.setClickable(false);
            }
        };

        binding.button2.setOnClickListener(answerButtonClickListener);
        binding.button3.setOnClickListener(answerButtonClickListener);
        binding.button4.setOnClickListener(answerButtonClickListener);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
