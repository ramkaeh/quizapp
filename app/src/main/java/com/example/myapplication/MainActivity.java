package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;


public class MainActivity extends AppCompatActivity{

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button hintButton;
    private TextView textViewQuestion;
    private int currentIndex = 0;
    public static final String TAG="my_custom_message";
    public static final String KEY_CURRENT_INDEX="currentIndex";
    private static final int REQUEST_CODE_PROMPT =0;
    public static final String KEY_EXTRA_ANSWER = "pl.edu.pb.wi.quiz.correctAnswer";
    private boolean answerWasShown;

    @Override
    protected void onStart( ) {
        super.onStart();

        Log.i(TAG, "onStart");
    };
    @Override
    protected void onSaveInstanceState(Bundle savedInstaceState){
        super.onSaveInstanceState(savedInstaceState);
        Log.d(TAG,"Wywołana została metoda:onSaveInstanceState");
        savedInstaceState.putInt(KEY_CURRENT_INDEX,currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= RESULT_OK) return;
        if(requestCode == REQUEST_CODE_PROMPT){
            if (data == null) return;
            answerWasShown = data.getBooleanExtra(MainActivity2.KEY_EXTRA_ANSWER_SHOWN,false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
        if(savedInstanceState!=null){
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        textViewQuestion = findViewById(R.id.question_text_View);
        hintButton = findViewById((R.id.hint_button));

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
                answerWasShown = false;
                setNextQuestion();

            }

        });
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                boolean correctAnswer = questions[currentIndex].isTrueAnswer();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                //startActivityForResult(intent, REQUEST_CODE_PROMPT);

            }
        });
};

    @Override
    protected void onResume( ) {
        super.onResume();

        Log.i(TAG, "onResume");
    };
    @Override
    protected void onPause( ) {
        super.onPause();

        Log.i(TAG, "onPause");
    }
    @Override
    protected void onStop( ) {
        super.onStop();

        Log.i(TAG, "onStop");
    };
    @Override
    protected void onDestroy( ) {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
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
        if(answerWasShown){
            resultMessageId = R.string.answer_was_shown;
        }
        if(userAnswer == correctAnswer){
            resultMessageId = R.string.correct_answer;
        }
        else {
            resultMessageId = R.string.incorrect_answer;
        }
        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
    }

}
