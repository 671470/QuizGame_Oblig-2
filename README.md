
# Test cases

- **MainActivityTest**: 
  - This test checks if the quiz button on the Main Activity works correctly 
  - ActivityScenarioRule ensures that the test starts in the Main Activity
  - clickQuizButton() clicks the button with the text  "Quiz" and verifies that an intent was sent to start the Quiz Activity
  - Test passes successfully

- **QuizActivityTest**:
  - This test class checks if the score and total score are updated correctly when the user selects right or wrong answers
  - ActivityScenarioRule ensures that the test starts in the Quiz Activity
  - CountingIdlingResourceis implemented because Espresso was pressing buttons too quickly and didn't wait for the UI to update because LiveData updates are delayed by 600ms after answering (handled in delayAfterAnswer).
  - CountingIdlingResource is incremented and decremented in the delayAfterAnswer() method in QuizViewModel to synchronize the UI updates
  - checkScoreUpdate() presses one wrong answer and one correct answer and checks if score and total score match. (Expects score to be 1 and total score to be 2)
  - checkScoreUpdate2() presses one wrong answer and two correct answers and checks if score and total score match (Expects score to be 2 and total score to be 3)
  - Had to change the way buttons are implented in ButtonFragment and assign the Button with the correct answer the tag ("Right answer") and the two other Buttons ("Wrong answer 1") and ("Wrong answer 2")
  - All test pass successfully

- **GalleryActivity**: 
  - This test class checks if the gallery updates correctly when a quiz is added or removed.
  - ActivityScenarioRule ensures that the test starts in the Gallery Activity
  - deleteQuiz() locates and presses the remove button on the first item in the gallery, then checks how many items are left. Since the gallery is initialized with 3 quizzes, 2 should remain after deletion. (Expects 2 quizzes to be in recycleview)
  - A helper method, clickChildViewWithId(), was used to click the button on the first item in the RecyclerView. This method was found on StackOverflow after having difficulty interacting with buttons on individual RecyclerView items.
  - A SystemClock.sleep() was used to allow the RecyclerView to update, as the test failed without it. While a CountingIdlingResource was attempted, it didn’t succeed in synchronizing the UI updates.
  - The test passes successfully with SystemClock.sleep(), but this is not an ideal solution, the answer most likely lies in implementing an IdlingResource fix to handle the delay in RecyclerView updates.

- **NewQuizActivityTest**:
  - This test class simulates pressing the gallery button to add a new quiz and checks if the items in the gallery are updated correctly.
  - ActivityScenarioRule ensures that the test starts in the Gallery Activity
  - addQuizTest() first creates an intent stub that returns a URI and ACTION_OK when an intent with ACTION_OPEN_DOCUMENT is fired.
  - intending() returns the intent stub when the ACTION_OPEN_DOCUMENT is fired, simulating the intent interaction.
  - The Espresso test presses the gallery button, which normally opens the phone gallery and gives the user an option to pick a picture, after picture is picked NewQuizActivity is started with results from the gallery. Instead new NewQuizActivity is started with data from the intent stub, then Espresso fills in the three EditText fields, and submits the form. After submission, the test checks if the RecyclerView item count has been updated. (Expects 4 quizzes to be in recycleview)
  - A boolean flag SetTesting is set to true before the test starts to prevent certain lines in GalleryActivity from running: 
```java
if (imageUri != null) {
    if (!isTesting) {
        getContentResolver().takePersistableUriPermission(
            imageUri,
            Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        );
    }
}
```
  - Couldn’t find a way to grant the URI_PERMISSION using the intent stub, which caused the test to fail when those lines were run. The test runs successfully without those lines executing.
  - The test passes successfully

