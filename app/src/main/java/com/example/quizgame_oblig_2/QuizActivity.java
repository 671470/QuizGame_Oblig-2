package com.example.quizgame_oblig_2;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import com.example.quizgame_oblig_2.databinding.ActivityQuizBinding;
import com.google.android.material.color.utilities.Score;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private QuizViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        setContentView(view);

        if(savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();


            ft.add(binding.pictureFragment.getId(), new PictureFragment());
            ft.add(binding.buttonFragment.getId(), new ButtonFragment());
            ft.add(binding.scoreFragment.getId(), new ScoreFragment());

            ft.commit();

        }




        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    }
    public void getResultScreen(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ScoreFragment scoreFragment = (ScoreFragment) fm.findFragmentById(binding.scoreFragment.getId());
        ButtonFragment buttonFragment = (ButtonFragment) fm.findFragmentById(binding.buttonFragment.getId());
        PictureFragment pictureFragment = (PictureFragment) fm.findFragmentById(binding.pictureFragment.getId());
        ResultFragment resultFragment = new ResultFragment();

        if(pictureFragment != null && buttonFragment != null && scoreFragment != null) {
            ft.remove(pictureFragment);
            ft.remove(buttonFragment);
            ft.remove(scoreFragment);
            ft.add(binding.resultFragment.getId(), resultFragment);

            ft.commit();
        }

    }
}