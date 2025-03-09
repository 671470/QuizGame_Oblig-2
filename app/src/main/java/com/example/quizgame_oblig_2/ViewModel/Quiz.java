package com.example.quizgame_oblig_2.ViewModel;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz_table")
public class Quiz {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "picture")
    private String picture;
    @ColumnInfo(name = "right_answer")
    private String rightAnswer;
    @ColumnInfo(name = "alt_answer_1")
    private String altAnswer1;
    @ColumnInfo(name = "alt_answer_2")
    private String altAnswer2;

    public Quiz(@NonNull String picture, String rightAnswer, String altAnswer1, String altAnswer2){
        this.picture = picture;
        this.rightAnswer = rightAnswer;
        this.altAnswer1 = altAnswer1;
        this.altAnswer2 = altAnswer2;
    }

    @NonNull
    public String getPicture() {
        return picture;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
    public String getAltAnswer1() {
        return altAnswer1;
    }
    public String getAltAnswer2() {
        return altAnswer2;
    }
}
