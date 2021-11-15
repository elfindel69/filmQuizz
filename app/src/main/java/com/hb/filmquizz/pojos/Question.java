package com.hb.filmquizz.pojos;

import java.io.Serializable;

public class Question implements Serializable {
    private static int sID = 0;
    private int id;
    private String text;
    private boolean answer;

    public Question() {

    }

    public Question(String text, boolean answer) {
        this.id = ++sID;
        this.text = text;
        this.answer = answer;
    }

    public static int getsID() {
        return sID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answer=" + answer +
                '}';
    }

}
