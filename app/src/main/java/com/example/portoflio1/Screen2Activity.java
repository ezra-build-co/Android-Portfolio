package com.example.portoflio1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Screen2Activity extends BaseActivity {

    private LinearLayout storySelectionView, inputView, outputView;
    private RadioGroup storyChoices;
    private Button generateButton, startOverButton;
    private TextView storyTextView;
    private List<TextInputLayout> inputLayouts;
    private List<EditText> editTexts;

    private int selectedStory = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Mad Libs");
        }

        storySelectionView = findViewById(R.id.story_selection_view);
        inputView = findViewById(R.id.input_view);
        outputView = findViewById(R.id.output_view);
        storyChoices = findViewById(R.id.story_choices);
        generateButton = findViewById(R.id.generate_button);
        startOverButton = findViewById(R.id.start_over_button);
        storyTextView = findViewById(R.id.story_text_view);

        storyChoices.setOnCheckedChangeListener((group, checkedId) -> {
            storySelectionView.setVisibility(View.GONE);
            inputView.setVisibility(View.VISIBLE);
            if (checkedId == R.id.story1_button) {
                selectedStory = 1;
                createInputFields(getHintsForStory(1));
            } else if (checkedId == R.id.story2_button) {
                selectedStory = 2;
                createInputFields(getHintsForStory(2));
            } else if (checkedId == R.id.story3_button) {
                selectedStory = 3;
                createInputFields(getHintsForStory(3));
            } else if (checkedId == R.id.random_story_button) {
                selectedStory = 0; // 0 for random
                createInputFields(getHintsForStory(0));
            }
        });

        generateButton.setOnClickListener(v -> generateStory());
        startOverButton.setOnClickListener(v -> resetGame());
    }

    private void createInputFields(List<String> hints) {
        inputView.removeViews(0, inputView.getChildCount() - 1);
        editTexts = new ArrayList<>();
        inputLayouts = new ArrayList<>();

        for (String hint : hints) {
            TextInputLayout textInputLayout = new TextInputLayout(this);
            textInputLayout.setHint(hint);
            textInputLayout.setHintTextColor(ColorStateList.valueOf(Color.parseColor("#A7AFB6")));
            textInputLayout.setBoxBackgroundColorResource(android.R.color.transparent);
            EditText editText = new EditText(this);
            editText.setTextColor(Color.parseColor("#A7AFB6"));
            editText.setBackgroundResource(R.drawable.radio_button_outline);
            editText.setPadding(35, 35, 35, 35);
            textInputLayout.addView(editText);
            inputView.addView(textInputLayout, 0);
            editTexts.add(editText);
            inputLayouts.add(textInputLayout);
        }
    }

    private void generateStory() {
        List<String> inputs = new ArrayList<>();
        for (EditText editText : editTexts) {
            String input = editText.getText().toString().trim();
            if (input.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            inputs.add(input);
        }

        int storyToGenerate = selectedStory;
        if (storyToGenerate == 0) { // Random
            storyToGenerate = new Random().nextInt(3) + 1;
        }

        String story = "";
        switch (storyToGenerate) {
            case 1:
                story = String.format(
                        "On a %s day, I went to the %s. I saw a %s %s over a giant %s. It was so %s that I nearly dropped my %s. I will never forget the %s of that day.",
                        inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4), inputs.get(5), inputs.get(6), inputs.get(7)
                );
                break;
            case 2:
                story = String.format(
                        "The zoo was a strange place. I saw a %s that could %s. It ate %s and lived in a %s. The zookeeper said its name was %s and it loved to %s with the visitors. It even stole my %s! I had to get a new one for %s dollars.",
                        inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4), inputs.get(5), inputs.get(6), inputs.get(7)
                );
                break;
            case 3:
                story = String.format(
                        "My vacation to %s was a disaster. The hotel was a %s, and the food tasted like %s. I decided to go %s, but I fell into a %s. A friendly %s helped me out. I was so %s that I gave them my %s.",
                        inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4), inputs.get(5), inputs.get(6), inputs.get(7)
                );
                break;
        }

        storyTextView.setText(story);
        inputView.setVisibility(View.GONE);
        outputView.setVisibility(View.VISIBLE);
    }

    private void resetGame() {
        outputView.setVisibility(View.GONE);
        storySelectionView.setVisibility(View.VISIBLE);
        storyChoices.clearCheck();
    }

    private List<String> getHintsForStory(int storyNumber) {
        switch (storyNumber) {
            case 1:
                return Arrays.asList("Adjective", "Place", "Noun", "Verb (ending in -ing)", "Plural Noun", "Adjective", "Noun", "Noun");
            case 2:
                return Arrays.asList("Animal", "Verb", "Food", "Adjective", "Name", "Verb", "Noun", "Number");
            case 3:
                return Arrays.asList("Place", "Adjective", "Food", "Verb (ending in -ing)", "Noun", "Animal", "Adjective", "Noun");
            default: // Random
                return Arrays.asList("Noun", "Adjective", "Verb", "Adverb", "Plural Noun", "Place", "Part of the Body", "Number");
        }
    }
}