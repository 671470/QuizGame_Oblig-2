package com.example.quizgame_oblig_2;

import android.os.Bundle;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizgame_oblig_2.databinding.FragmentQuizBinding;

import java.util.List;

public class PictureFragment extends Fragment {

    private QuizViewModel viewModel;
    private FragmentQuizBinding binding;

    public PictureFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);

        viewModel.getShuffledQuizzes().observe(getViewLifecycleOwner(), quizzes -> {

                binding.imageView2.setImageURI(Uri.parse(quizzes.get(0).getPicture()));

        });

        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


