package com.example.quizgame_oblig_2.Activites;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizgame_oblig_2.R;
import com.example.quizgame_oblig_2.RecyclerView.RecyclerViewAdapter;
import com.example.quizgame_oblig_2.RecyclerView.RecyclerViewInterface;
import com.example.quizgame_oblig_2.ViewModel.QuizViewModel;
import com.example.quizgame_oblig_2.databinding.ActivityGalleryBinding;
import com.example.quizgame_oblig_2.databinding.ActivityMainBinding;

public class GalleryActivity extends AppCompatActivity implements RecyclerViewInterface{

    private QuizViewModel viewModel;
    private ActivityGalleryBinding binding;
    private RecyclerViewAdapter adapter;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        viewModel.getGalleryQuizzes().observe(this, quizzes -> {

            adapter = new RecyclerViewAdapter(this, quizzes, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        });

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

    }



    @Override
    public void deleteQuiz(int pos) {

    }
//    // Opens the camera
//    public void cameraButton(View v){
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, PICTURE_REQUEST_CODE);
//    }
//    // Opens the gallery
//    public void openFile(View v){
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
//        startActivityForResult(intent, REQUEST_CODE);
//
//    }
//    // Handles the result from the camera and gallery
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
//            if(data != null){
//                Uri imageUri = data.getData();
//
//                Intent intent = new Intent(this, NewQuizActivity.class);
//                intent.putExtra("imageUri", imageUri);
//                finish();
//                startActivity(intent);
//            }
//        }
//
//        if(requestCode == PICTURE_REQUEST_CODE && resultCode == RESULT_OK){
//            if(data != null) {
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//
//                Intent intent = new Intent(this, NewQuizActivity.class);
//                intent.putExtra("bitUri",imageBitmap);
//                startActivity(intent);
//                finish();
//            }
//        }
//    }
//    //Sorts the gallery based upon the boolean sort variable
//    public void sortGallery(){
//        if(!sorted) {
//            quizModels.sort(Comparator.comparing(QuizModel::getCorrectAnswer));
//            sorted = true;
//            sortButton.setImageResource(R.drawable.a_z);
//
//        } else {
//            quizModels.sort(Comparator.comparing(QuizModel::getCorrectAnswer, Comparator.reverseOrder()));
//            sorted = false;
//            sortButton.setImageResource(R.drawable.z_a);
//
//        }
//        MyApplication app = (MyApplication) getApplication();
//        app.setSorted(sorted);
//        adapter.notifyDataSetChanged();
//    }
//    // Deletes a quiz from the RecycleView and the application state
//    @Override
//    public void deleteQuiz(int pos) {
//        QuizModel quizModel = quizModels.get(pos);
//        String name = quizModel.getCorrectAnswer();
//        MyApplication app = (MyApplication) getApplication();
//        app.removeQuiz(name);
//        adapter.notifyItemRemoved(pos);
//    }
}