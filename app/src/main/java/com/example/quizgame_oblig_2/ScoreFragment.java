package com.example.quizgame_oblig_2;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizgame_oblig_2.databinding.FragmentQuizBinding;
import com.example.quizgame_oblig_2.databinding.FragmentScoreBinding;

import java.util.List;

public class ScoreFragment extends Fragment {

    private QuizViewModel viewModel;

    private FragmentScoreBinding binding;

    public ScoreFragment(){

    }
    public static ScoreFragment newInstance() {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
