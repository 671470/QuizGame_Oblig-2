package com.example.quizgame_oblig_2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizgame_oblig_2.ViewModel.QuizViewModel;
import com.example.quizgame_oblig_2.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {

 private QuizViewModel viewModel;
 private FragmentResultBinding binding;

    public ResultFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentResultBinding.inflate(inflater, container, false);

        viewModel.getScore().observe(getViewLifecycleOwner(), integer -> binding.score.setText(String.valueOf(integer)));
        viewModel.getTotalTries().observe(getViewLifecycleOwner(), integer -> binding.totalScore.setText(String.valueOf(integer)));

        return binding.getRoot();
    }
}













