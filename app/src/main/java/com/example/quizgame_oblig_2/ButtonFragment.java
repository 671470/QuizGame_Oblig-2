package com.example.quizgame_oblig_2;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizgame_oblig_2.databinding.FragmentButtonsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonFragment extends Fragment {

    private FragmentButtonsBinding binding;
    private QuizViewModel viewModel;
    private String rightAnswer;

    private final String defaultColor = "#6f3b96";
    private final String wrongColor = "#d13434";
    private final String rightColor = "#439936";

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
                        binding.button2.setBackgroundColor(android.graphics.Color.parseColor(viewModel.getButtonColor(String.valueOf(binding.button2.getId()))));
                        binding.button2.setClickable(false);
                    } else {
                        binding.button2.setBackgroundColor(android.graphics.Color.parseColor(defaultColor));
                        binding.button2.setClickable(true);
                    }
                    binding.button3.setText(answers.get(1));
                    if(viewModel.getButtonColor(String.valueOf(binding.button3.getId())) != null){
                        binding.button3.setBackgroundColor(android.graphics.Color.parseColor(viewModel.getButtonColor(String.valueOf(binding.button3.getId()))));
                        binding.button3.setClickable(false);
                    } else {
                        binding.button3.setBackgroundColor(android.graphics.Color.parseColor(defaultColor));
                        binding.button3.setClickable(true);
                    }
                    binding.button4.setText(answers.get(2));
                    if(viewModel.getButtonColor(String.valueOf(binding.button4.getId())) != null) {
                        binding.button4.setBackgroundColor(android.graphics.Color.parseColor(viewModel.getButtonColor(String.valueOf(binding.button4.getId()))));
                        binding.button4.setClickable(false);
                    } else {
                        binding.button4.setBackgroundColor(android.graphics.Color.parseColor(defaultColor));
                        binding.button4.setClickable(true);
                    }

                }
            }
        });


        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosenAnswer = ((android.widget.Button) v).getText().toString();
                if (chosenAnswer.equals(rightAnswer)) {
                    v.setBackgroundColor(android.graphics.Color.parseColor(rightColor));
                    viewModel.saveButtonColor(String.valueOf(v.getId()),rightColor);
                    viewModel.incrementScore();
                    viewModel.incrementTotalTries();
                    if(viewModel.getShuffledQuizzes().getValue().size() == 1){
                        if (getActivity() instanceof QuizActivity) {
                            ((QuizActivity) getActivity()).getResultScreen();
                        }
                    }
                    if(viewModel.getShuffledQuizzes().getValue().size() > 1) {
                        List<String> buttons =
                                Arrays.asList(
                                        String.valueOf(binding.button2.getId()),
                                        String.valueOf(binding.button3.getId()),
                                        String.valueOf(binding.button4.getId()));
                        viewModel.delayAfterAnswer(buttons);
                    }

                } else {
                    v.setBackgroundColor(android.graphics.Color.parseColor(wrongColor));
                    viewModel.saveButtonColor(String.valueOf(v.getId()),wrongColor);
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
