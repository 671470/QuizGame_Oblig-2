
Skriv tester ved bruk av Espresso rammeverket

Test 1: Klikk på knapp i main-menu, sjekk om det tar deg til riktig sub-activity

Test 2: Sjekk om poengsum oppdateres riktig når feil/riktig svar blir trykt på

Test 3: Test om nummer registrerte bilder oppdaterer seg når en legger til / fjerner bilde (Bruk Intent stubbing for returnere image data)




1. Save your data (and load it when the app starts again later)! Decide on how you want store the data (names & pictures) that we add from the app. We'll use Android Room DAOsLenker til eit ekstern område. (also see Ch. 68 "An Android Room Database..." in Smyth's book/the lecture). Encapsulate the data necessary for the quiz (i.e. all images, the current image and correct answer and alternative wrong choices for the current question) in a subclass of a ViewModel. Make sure that when rotating your phone during a quiz i) the current question and ii) score are not lost!
   Don't forget to load the three or more build-in questions at the right stage in your code. You can simplify your code by addressing both images on the phone and those in the resource folder through URIs. I do not recommend trying to store the images as bitmaps!
   See (Room, LiveData ,and ViewModel for extra reading).Lenker til eit ekstern område.

2. Use Fragments for the actual quiz: in portrait-mode, we want the image on top and the answer-buttons below. In landscape mode, they should be side-by-side.

3. Write test-cases using the Espresso-framework for your app! At least have the following test cases:

    - clicking a button in the main-menu (if you have one) takes you to the right sub-activity (i.e. to the Quiz or the Gallery; testing one button is enough);

    - is the score updated correctly in the quiz (the test submits at least one right/wrong answer each and you check if the score is correct afterwards);

    - a test that checks that the number of registered pictures/persons is correct after adding/deleting an entry. For adding, use Intent Stubbing to return some image data (e.g. from the resource-folder) without any user interaction.
      Write your Espresso test classes (do not use the Espresso "Test Recorder") so that they directly address each activity under test. In other words, don't write all tests for the main activity and then have your test case click the main menu buttons to reach an activity. Note that you may have to change the internal structure of your app quite a bit so that your test cases actually have access to internal state of your app (e.g., the current score and where the right answer is; or starting the Quiz directly without going through the main menu) from the unit test.

4. Document your test cases with results in the README in your repo (HTML or Markdown). That means there should be i) a detailed description in natural language of what the test should ... test (use the same "language" we would use to describe e.g. a use case), the expected result, and which class/method implements the test, ii) whether the test passed failed. If you haven't been able to get a test to pass, describe your explanation on what is going wrong and what you would have to do to fix it.
   Note that the main goal is to have proper test-cases, so it is okay if in the end you have some test-cases that still fail, where you haven't been able to make them "green". In this case, a failing test-case can still serve as documentation.