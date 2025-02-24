package com.example.quizgame_oblig_2;

public class QuizModel {

    private String picture;
    private String rightAnswer, altAnswer1, altAnswer2;

    public QuizModel(String picture, String rightAnswer, String altAnswer1, String altAnswer2){
        this.picture = picture;
        this.rightAnswer = rightAnswer;
        this.altAnswer1 = altAnswer1;
        this.altAnswer2 = altAnswer2;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAltAnswer1() {
        return altAnswer1;
    }

    public void setAltAnswer1(String altAnswer1) {
        this.altAnswer1 = altAnswer1;
    }

    public String getAltAnswer2() {
        return altAnswer2;
    }

    public void setAltAnswer2(String altAnswer2) {
        this.altAnswer2 = altAnswer2;
    }
}
