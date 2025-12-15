package com.example.portoflio1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class Screen4Activity extends BaseActivity {

    private TextView scoreTextView, highScoreTextView, strikesTextView, feedbackTextView;
    private EditText guessEditText;
    private Button submitButton, newGameButton;
    private ImageView winImageView, loseImageView;

    private int secretNumber;
    private int currentScore;
    private int highScore;
    private int strikes;

    private static final String PREFS_NAME = "GuessTheNumberPrefs";
    private static final String HIGH_SCORE_KEY = "highScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Guess the Number");
        }

        scoreTextView = findViewById(R.id.score_text_view);
        highScoreTextView = findViewById(R.id.high_score_text_view);
        strikesTextView = findViewById(R.id.strikes_text_view);
        feedbackTextView = findViewById(R.id.feedback_text_view);
        guessEditText = findViewById(R.id.guess_edit_text);
        submitButton = findViewById(R.id.submit_button);
        newGameButton = findViewById(R.id.new_game_button);
        winImageView = findViewById(R.id.win_image_view);
        loseImageView = findViewById(R.id.lose_image_view);

        submitButton.setOnClickListener(v -> checkGuess());
        newGameButton.setOnClickListener(v -> startNewGame());

        loadHighScore();
        startNewGame();
    }

    private void startNewGame() {
        secretNumber = new Random().nextInt(100) + 1;
        strikes = 10;
        currentScore = 0;

        updateUI();
        feedbackTextView.setText("Enter a number between 1 and 100");
        guessEditText.setText("");
        submitButton.setEnabled(true);
        winImageView.setVisibility(View.GONE);
        loseImageView.setVisibility(View.GONE);
        newGameButton.setVisibility(View.GONE);
    }

    private void checkGuess() {
        String guessString = guessEditText.getText().toString();
        if (guessString.isEmpty()) {
            Toast.makeText(this, "Please enter a guess", Toast.LENGTH_SHORT).show();
            return;
        }

        int guess = Integer.parseInt(guessString);

        if (guess == secretNumber) {
            handleCorrectGuess();
        } else {
            strikes--;
            if (strikes == 0) {
                handleGameOver();
            } else {
                feedbackTextView.setText(guess < secretNumber ? "Too low!" : "Too high!");
            }
        }
        updateUI();
        guessEditText.setText("");
    }

    private void handleCorrectGuess() {
        currentScore += 50; // Base points for winning
        currentScore += strikes * 5; // Bonus for remaining strikes

        if (currentScore > highScore) {
            highScore = currentScore;
            saveHighScore();
        }

        feedbackTextView.setText(String.format("You got it! It was %d. Your score: %d", secretNumber, currentScore));
        winImageView.setVisibility(View.VISIBLE);
        submitButton.setEnabled(false);
        newGameButton.setVisibility(View.VISIBLE);
    }

    private void handleGameOver() {
        feedbackTextView.setText(String.format("Game Over! The number was %d", secretNumber));
        loseImageView.setVisibility(View.VISIBLE);
        submitButton.setEnabled(false);
        newGameButton.setVisibility(View.VISIBLE);
    }

    private void updateUI() {
        scoreTextView.setText(String.format("Score: %d", currentScore));
        highScoreTextView.setText(String.format("High Score: %d", highScore));
        strikesTextView.setText(String.format("Strikes: %d", strikes));
    }

    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        highScore = prefs.getInt(HIGH_SCORE_KEY, 0);
    }

    private void saveHighScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(HIGH_SCORE_KEY, highScore);
        editor.apply();
    }
}
