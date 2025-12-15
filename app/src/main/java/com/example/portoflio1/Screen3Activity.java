package com.example.portoflio1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class Screen3Activity extends BaseActivity {

    private EditText firstNameEditText, lastNameEditText, cityEditText, schoolEditText, petFoodEditText, siblingMythicalEditText;
    private Button generateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SciFi Name Generator");
        }

        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        cityEditText = findViewById(R.id.city_edit_text);
        schoolEditText = findViewById(R.id.school_edit_text);
        petFoodEditText = findViewById(R.id.pet_food_edit_text);
        siblingMythicalEditText = findViewById(R.id.sibling_mythical_edit_text);
        generateButton = findViewById(R.id.generate_button);
        resultTextView = findViewById(R.id.result_text_view);

        generateButton.setOnClickListener(v -> generateSciFiName());
    }

    private void generateSciFiName() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String school = schoolEditText.getText().toString().trim();
        String petFood = petFoodEditText.getText().toString().trim();
        String siblingMythical = siblingMythicalEditText.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || city.isEmpty() || school.isEmpty() || petFood.isEmpty() || siblingMythical.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate SciFi First Name
        String sciFiFirstName = getSubString(lastName, 3) + getSubString(firstName, 2);

        // Generate SciFi Last Name
        String sciFiLastName = getSubString(school, 3) + getSubString(city, 2);

        // Determine SciFi Origin with randomization
        String[] originParts = {petFood, siblingMythical};
        Random random = new Random();
        String part1 = originParts[random.nextInt(2)];
        String part2 = originParts[random.nextInt(2)];
        String sciFiOrigin = getSubString(part1, random.nextInt(part1.length())) + getSubString(part2, random.nextInt(part2.length()));

        // Display the result
        String result = String.format("%s %s from the planet %s", sciFiFirstName, sciFiLastName, sciFiOrigin);
        resultTextView.setText(result);
        resultTextView.setVisibility(View.VISIBLE);
    }

    private String getSubString(String text, int length) {
        if (text.length() < length) {
            return text;
        }
        return text.substring(0, length);
    }
}
