package com.example.quizgame_oblig_2;

import android.os.Bundle;
import android.net.Uri;
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


        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);


        viewModel.observeQuizzes(this);

        viewModel.getGameQuiz().observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {

                if (quizzes != null && !quizzes.isEmpty()) {


                    binding.imageView2.setImageURI(Uri.parse(quizzes.get(0).getPicture()));
                }}

        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
