package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView textViewQuestion;
    private int currentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        textViewQuestion = findViewById(R.id.question_text_View);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerCorrectness(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1)%questions.length;
                setNextQuestion();

            }
        });

};
private void setNextQuestion(){
    textViewQuestion.setText(questions[currentIndex].getQuestionId());
}
    private Question[] questions = new Question[] {
            new Question(R.string.a, true),
            new Question(R.string.b, false),
            new Question(R.string.c, true),
            new Question(R.string.d, true),
            new Question(R.string.e, false)


    };
    private void checkAnswerCorrectness(boolean userAnswer){
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        }
        else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

}
