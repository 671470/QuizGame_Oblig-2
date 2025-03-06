package com.example.quizgame_oblig_2.Activites;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizgame_oblig_2.R;
import com.example.quizgame_oblig_2.RecyclerView.RecyclerViewAdapter;
import com.example.quizgame_oblig_2.RecyclerView.RecyclerViewInterface;
import com.example.quizgame_oblig_2.ViewModel.Quiz;
import com.example.quizgame_oblig_2.ViewModel.QuizViewModel;
import com.example.quizgame_oblig_2.databinding.ActivityGalleryBinding;
import com.example.quizgame_oblig_2.databinding.ActivityMainBinding;

import java.io.File;

public class GalleryActivity extends AppCompatActivity implements RecyclerViewInterface{

    private ActivityResultLauncher<Intent> launcher;
    private QuizViewModel viewModel;
    private ActivityGalleryBinding binding;
    private RecyclerViewAdapter adapter;

    private Uri cameraUri;

    // Sets up the RecycleView and loads sort attribute and quiz models from the application state
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityGalleryBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(binding.gallery, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(binding.recyclerView.getId());

        viewModel.getGalleryQuizzes().observe(this, quizzes -> {

            adapter = new RecyclerViewAdapter(this, quizzes, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Intent data = result.getData();
                        Uri imageUri = data.getData();

                        if (imageUri != null) {
                            getContentResolver().takePersistableUriPermission(
                                    imageUri,
                                    Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                            );

                            Intent intent = new Intent(this, NewQuizActivity.class);
                            intent.putExtra("imageUri", imageUri.toString());
                            startActivity(intent);
                        } else {

                            Intent intent = new Intent(this, NewQuizActivity.class);
                            intent.putExtra("imageUri", cameraUri.toString());
                            startActivity(intent);
                        }
                    }
                }
        );




        binding.sortAZButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sortAtoZ();
            }
    });
        binding.sortZAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sortZtoA();
            }
        });

        binding.galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoneGallery();

            }

        });
        binding.cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();

            }

        });


    }
    @Override
    public void deleteQuiz(int pos) {
        Quiz quiz = viewModel.getGalleryQuizzes().getValue().get(pos);
       viewModel.deleteQuiz(quiz);
    }

    public void openPhoneGallery(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        launcher.launch(intent);

    }


    public void openCamera() {


        File imageFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), SystemClock.currentThreadTimeMillis()+ "temp_image.jpg");
        cameraUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", imageFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);


            launcher.launch(intent);

    }

}