package com.example.home.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int score;
    String participantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView.OnEditorActionListener editTextListener = new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                v.setCursorVisible(false);
                if (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        };

        EditText name = findViewById(R.id.name);
        name.setOnClickListener(this);
        name.setOnEditorActionListener(editTextListener);

        EditText qtn4 = findViewById(R.id.qtn4_ans);
        qtn4.setOnClickListener(this);
        qtn4.setOnEditorActionListener(editTextListener);

        EditText qtn5_ans1 = findViewById(R.id.qtn5_ans1);
        qtn5_ans1.setOnClickListener(this);
        qtn5_ans1.setOnEditorActionListener(editTextListener);

        EditText qtn5_ans2 = findViewById(R.id.qtn5_ans2);
        qtn5_ans2.setOnClickListener(this);
        qtn5_ans2.setOnEditorActionListener(editTextListener);

        EditText qtn9_ans1 = findViewById(R.id.qtn9_ans1);
        qtn9_ans1.setOnClickListener(this);
        qtn9_ans1.setOnEditorActionListener(editTextListener);

        EditText qtn9_ans2 = findViewById(R.id.qtn9_ans2);
        qtn9_ans2.setOnClickListener(this);
        qtn9_ans2.setOnEditorActionListener(editTextListener);

    }

    /**
     * This method is called when the "Take Quiz" button is clicked.
     * Displays the quiz questions.
     */

    public void takeQuiz(View view) {

        int nameLength = ((EditText) findViewById(R.id.name)).getText().toString().length();
        if (nameLength == 0)
            Toast.makeText(this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        else {

            participantName = ((EditText) findViewById(R.id.name)).getText().toString();
            findViewById(R.id.name).setVisibility(View.GONE);
            findViewById(R.id.take_quiz).setVisibility(View.GONE);
            findViewById(R.id.questions_id).setVisibility(View.VISIBLE);
        }
    }


    /**
     * This method is called on click of the "View Result" button
     * Calculates and displays the result score
     */

    public void viewScore(View view) {
        findViewById(R.id.questions_id).setVisibility(View.GONE);
        findViewById(R.id.submit_button).setVisibility(View.GONE);
        calculateScore();
        displayScore();

    }

    /**
     * Calculates the score obtained by the user
     */

    private void calculateScore() {

        //Validates the answer for the question 1
        //Adds 1 point to the score only if the correct radio button is selected

        RadioGroup radioQuestion = findViewById(R.id.question_1);

        if (radioQuestion.getCheckedRadioButtonId() == R.id.qtn1_ans4)
            score++;

        //Validates the answer for the question 2
        //Adds 1 point to the score only if the correct radio button is selected

        radioQuestion = findViewById(R.id.question_2);
        if (radioQuestion.getCheckedRadioButtonId() == R.id.qtn2_ans4)
            score++;

        //Validates the answer for the question 3
        //Adds 1 point to the score only if all of the correct checkboxes are checked and the wrong checkboxes are unchecked

        CheckBox option = findViewById(R.id.qtn3_ans1);
        if (option.isChecked()) {
            option = findViewById(R.id.qtn3_ans2);

            if (option.isChecked()) {
                option = findViewById(R.id.qtn3_ans3);

                if (option.isChecked()) {
                    option = findViewById(R.id.qtn3_ans4);

                    if (!option.isChecked()) {
                        score++;
                    }

                }
            }
        }

        //Validates the answer for the question 4
        //Adds 1 point to the score only if the correct answer is entered

        String userAns = ((EditText) findViewById(R.id.qtn4_ans)).getText().toString();
        String crctAns = getString(R.string.answer_4);
        userAns = userAns.replaceAll("\\s", "").trim();
        crctAns = crctAns.replaceAll("\\s", "").trim();
        if (userAns.equalsIgnoreCase(crctAns))
            score++;

        //Validates the answer for the question 5
        //Adds 1 point to the score if and only if the correct answer is entered for all the images.
        //No point will be added if any of the text boxes is left unanswered or wrongly answered

        userAns = ((EditText) findViewById(R.id.qtn5_ans1)).getText().toString();
        crctAns = getString(R.string.question_5_answer_1);
        userAns = userAns.replaceAll("\\s", "").trim();
        crctAns = crctAns.replaceAll("\\s", "").trim();
        if (userAns.equalsIgnoreCase(crctAns)) {

            userAns = ((EditText) findViewById(R.id.qtn5_ans2)).getText().toString();
            crctAns = getString(R.string.question_5_answer_2);
            userAns = userAns.replaceAll("\\s", "").trim();
            crctAns = crctAns.replaceAll("\\s", "").trim();
            if (userAns.equalsIgnoreCase(crctAns))
                score++;

        }

        //Validates the answer for the question 6
        //Adds 1 point to the score only if the correct radio button is selected

        radioQuestion = findViewById(R.id.question_6);

        if (radioQuestion.getCheckedRadioButtonId() == R.id.qtn6_ans1)
            score++;

        //Validates the answer for the question 7
        //Adds 1 point to the score only if the correct radio button is selected

        radioQuestion = findViewById(R.id.question_7);
        if (radioQuestion.getCheckedRadioButtonId() == R.id.qtn7_ans3)
            score++;

        //Validates the answer for the question 8
        //Adds 1 point to the score only if all of the correct checkboxes are checked and the wrong checkboxes are unchecked

        option = findViewById(R.id.qtn8_ans1);
        if (option.isChecked()) {
            option = findViewById(R.id.qtn8_ans2);

            if (option.isChecked()) {
                option = findViewById(R.id.qtn8_ans3);

                if (!option.isChecked()) {
                    option = findViewById(R.id.qtn8_ans4);

                    if (!option.isChecked())
                        score++;

                }
            }
        }

        //Validates the answer for the question 9
        //Adds 1 point to the score if and only if the correct answer is entered for all the images.
        //No point will be added if any of the text boxes is left unanswered or wrongly answered

        userAns = ((EditText) findViewById(R.id.qtn9_ans1)).getText().toString();
        crctAns = getString(R.string.question_9_answer_1);
        userAns = userAns.replaceAll("\\s", "").trim();
        crctAns = crctAns.replaceAll("\\s", "").trim();
        if (userAns.equalsIgnoreCase(crctAns)) {

            userAns = ((EditText) findViewById(R.id.qtn9_ans2)).getText().toString();
            crctAns = getString(R.string.question_9_answer_2);
            userAns = userAns.replaceAll("\\s", "").trim();
            crctAns = crctAns.replaceAll("\\s", "").trim();
            if (userAns.equalsIgnoreCase(crctAns))
                score++;

        }

        //Validates the answer for the question 10
        //Adds 1 point to the score only if all of the correct checkboxes are checked and the wrong checkboxes are unchecked

        option = findViewById(R.id.qtn10_ans1);
        if (!option.isChecked()) {
            option = findViewById(R.id.qtn10_ans2);

            if (!option.isChecked()) {
                option = findViewById(R.id.qtn10_ans3);

                if (option.isChecked()) {
                    option = findViewById(R.id.qtn10_ans4);

                    if (option.isChecked())
                        score++;

                }
            }
        }


    }

    /**
     * Displays the score of the quiz
     */

    private void displayScore() {

        Toast toast = Toast.makeText(this, "Hello " + participantName + ",\nYour Score is " + score + ".", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.name:
                ((EditText) findViewById(R.id.name)).setCursorVisible(true);
                break;

            case R.id.qtn4_ans:
                ((EditText) findViewById(R.id.qtn4_ans)).setCursorVisible(true);
                break;

            case R.id.qtn5_ans1:
                ((EditText) findViewById(R.id.qtn5_ans1)).setCursorVisible(true);
                break;

            case R.id.qtn5_ans2:
                ((EditText) findViewById(R.id.qtn5_ans2)).setCursorVisible(true);
                break;

            case R.id.qtn9_ans1:
                ((EditText) findViewById(R.id.qtn9_ans1)).setCursorVisible(true);
                break;

            case R.id.qtn9_ans2:
                ((EditText) findViewById(R.id.qtn9_ans2)).setCursorVisible(true);
                break;

            default:
                break;
        }

    }
}
