package com.example.quizgame_oblig_2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.*;
import static androidx.test.espresso.intent.matcher.IntentMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.quizgame_oblig_2.Activites.MainActivity;
import com.example.quizgame_oblig_2.Activites.QuizActivity;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void startTest(){
        init();
    }
    @Test
    public void clickQuizButton() {
        onView(withText("Quiz")).perform(click());
        intended(hasComponent(QuizActivity.class.getName()));
    }
    @After
    public void endTest(){
        release();
    }

}