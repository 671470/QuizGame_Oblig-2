package com.example.quizgame_oblig_2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.*;
import static androidx.test.espresso.intent.matcher.IntentMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static com.example.quizgame_oblig_2.RecyclerViewItemCountAssertion.withItemCount;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.quizgame_oblig_2.Activites.GalleryActivity;

@RunWith(AndroidJUnit4.class)
public class NewQuizActivityTest {

    @Rule
    public ActivityScenarioRule<GalleryActivity> activityRule =
            new ActivityScenarioRule<>(GalleryActivity.class);

    @Before
    public void startTest(){
        GalleryActivity.setTesting(true);
        init();
    }

    @Test
    public void testSelectImageFromGallery() {

        Intent resultData = new Intent();
        Uri imageUri = Uri.parse("content://media/external/images/media/1");
        resultData.setData(imageUri);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        intending(hasAction(Intent.ACTION_OPEN_DOCUMENT)).respondWith(result);

        onView(withId(R.id.galleryButton)).perform(click());
        onView(withId(R.id.correctAnswer)).perform(click()).perform(typeText("Bla"), closeSoftKeyboard());

        onView(withId(R.id.alt1)).perform(click()).perform(typeText("Himmel"), closeSoftKeyboard());
        onView(withId(R.id.alt2)).perform(click()).perform(typeText("Cola"), closeSoftKeyboard());

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.recyclerView)).check(withItemCount(4));
    }

    @After
    public void endTest(){
        release();
    }



}