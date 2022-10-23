package com.example.myapplication;

public class Question {
    private int questionid;
    private boolean trueAnswer;

    public Question(int questionid, boolean trueAnswer){
        this.questionid = questionid;
        this.trueAnswer = trueAnswer;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }
    public int getQuestionId(){
        return this.questionid;
    }
}
