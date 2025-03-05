package com.example.quizgame_oblig_2.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizgame_oblig_2.ViewModel.Quiz;
import com.example.quizgame_oblig_2.ViewModel.QuizViewModel;
import com.example.quizgame_oblig_2.databinding.ActivityGalleryBinding;
import com.example.quizgame_oblig_2.databinding.ActivityNewQuizBinding;

public class NewQuizActivity extends AppCompatActivity {

    private QuizViewModel viewModel;
    private ActivityNewQuizBinding binding;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);

        binding = ActivityNewQuizBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(binding.newQuiz, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        image = getIntent().getStringExtra("imageUri");
        Uri imageUri = Uri.parse(image);
        binding.quizPicture.setImageURI(imageUri);

        binding.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitButton();
            }
        });

    }
    public void submitButton() {
        EditText name = findViewById(binding.correctAnswer.getId());
        EditText alt1 = findViewById(binding.alt1.getId());
        EditText alt2 = findViewById(binding.alt2.getId());

        String correctName = name.getText().toString();
        String alternative1 = alt1.getText().toString();
        String alternative2 = alt2.getText().toString();


        if (correctName.isEmpty()) {
            name.setError("This field is required");
        }
        if (alternative1.isEmpty()) {
            alt1.setError("This field is required");
        }
        if (alternative2.isEmpty()) {
            alt2.setError("This field is required");
        }

        if (!(correctName.isEmpty() || alternative1.isEmpty() || alternative2.isEmpty())) {
           Quiz newQuiz;

                newQuiz= new Quiz(image, correctName, alternative1, alternative2);
                viewModel.addQuiz(newQuiz);

            finish();
        }
    }
}