package com.example.android.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View view) {
        int score = 9;
        String correctTranslation = "shukran";
        RadioButton secondOption = (RadioButton) findViewById(R.id.two);
        boolean isSecondOptionChecked = secondOption.isChecked();
        RadioButton fourthOption = (RadioButton) findViewById(R.id.four);
        boolean isFourthOptionChecked = fourthOption.isChecked();
        RadioButton fifthOption = (RadioButton) findViewById(R.id.five);
        boolean isFifthOptionChecked = fifthOption.isChecked();
        RadioButton eightOption = (RadioButton) findViewById(R.id.eight);
        boolean isEighthOptionChecked = eightOption.isChecked();
        RadioButton tenthOption = (RadioButton) findViewById(R.id.ten);
        boolean isTenthOptionChecked = tenthOption.isChecked();
        RadioButton twelfthOption = (RadioButton) findViewById(R.id.twelve);
        boolean isTwelfthOptionChecked = twelfthOption.isChecked();
        CheckBox cb = (CheckBox) findViewById(R.id.yes);
        boolean yesChecked = cb.isChecked();
        CheckBox cb2 = (CheckBox) findViewById(R.id.no_doubt);
        boolean noDoubtChecked = cb2.isChecked();
        EditText et = (EditText) findViewById(R.id.your_answer);
        String answer = et.getText().toString();

        if (isSecondOptionChecked)
            score -= 1;

        if (isFourthOptionChecked)
            score -= 1;

        if (isFifthOptionChecked)
            score -= 1;

        if (isEighthOptionChecked)
            score -= 1;

        if (isTenthOptionChecked)
            score -= 1;

        if (isTwelfthOptionChecked)
            score -= 1;

        if (answer.equalsIgnoreCase(correctTranslation) || answer.equalsIgnoreCase(correctTranslation + " "))
            score -= 1;

        if (yesChecked)
            score -= 1;

        if (noDoubtChecked)
            score -= 1;

        if (score == 0)
            Toast.makeText(this, R.string.congratsText, Toast.LENGTH_SHORT).show();
        else {
            score = 9 - score;
            Toast.makeText(this, "Your score is: " + "" + score + "" + " out of 9", Toast.LENGTH_SHORT).show();
        }
    }
}
