package com.example.quizgame_oblig_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizgame_oblig_2.databinding.FragmentButtonsBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonFragment extends Fragment {

    private FragmentButtonsBinding binding;
    private QuizViewModel viewModel;


    public ButtonFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);



        // Observing changes in the list of quizzes
        viewModel.getAllQuizzes().observe(this, new Observer<List<Quiz>>() {
            @Override
            public void onChanged(List<Quiz> quizzes) {
                // Check if the list is not empty
                if (quizzes != null && !quizzes.isEmpty()) {
                    // If the list is not empty, set the image
                    binding.button2.setText(quizzes.get(0).getAltAnswer1());
                    binding.button3.setText(quizzes.get(0).getAltAnswer2());
                    binding.button4.setText(quizzes.get(0).getRightAnswer());
                }}
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentButtonsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}