package com.example.quizgame_oblig_2.Activites;

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
import com.example.quizgame_oblig_2.databinding.ActivityNewQuizBinding;

public class NewQuizActivity extends AppCompatActivity {

    private QuizViewModel viewModel;
    private ActivityNewQuizBinding binding;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityNewQuizBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
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

        binding.submitButton.setOnClickListener(v -> submitButton());

    }

    private void submitButton() {
        EditText rightAns = findViewById(binding.correctAnswer.getId());
        EditText alt1 = findViewById(binding.alt1.getId());
        EditText alt2 = findViewById(binding.alt2.getId());

        String rightAnswer = rightAns.getText().toString();
        String altAnswer1 = alt1.getText().toString();
        String altAnswer2 = alt2.getText().toString();

        if (rightAnswer.isEmpty()) {
            rightAns.setError("This field is required");
        }
        if (altAnswer1.isEmpty()) {
            alt1.setError("This field is required");
        }
        if (altAnswer2.isEmpty()) {
            alt2.setError("This field is required");
        }

        if (!(rightAnswer.isEmpty() || altAnswer1.isEmpty() || altAnswer2.isEmpty()))
        {
            Quiz newQuiz= new Quiz(image, rightAnswer, altAnswer1, altAnswer2);
            viewModel.addQuiz(newQuiz);
            finish();
        }
    }
}