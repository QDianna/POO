package com.example.project;

class Answer {
    String answer;
    boolean truthValue;

    Answer(String answer, boolean answerTruthValue) {
        this.answer = answer;
        this.truthValue = answerTruthValue;
    }

    public String toString() {
        return answer + "," + truthValue;
    }

}
