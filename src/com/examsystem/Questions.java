package com.examsystem;
import java.io.Serializable;

public class Questions implements Serializable {
    String question;
    String correctAnswer;
    String a;
    String b;
    String c;
    String d;
    public Questions(String question, String correctAnswer, String a, String b, String c, String d) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

}
