package com.hb.filmquizz.pojos;

public class Question {
    private static int sID=0;
    private int id;
    private String text;
    private boolean answer;

    public Question(){

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

    public String getText() {
        return text;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
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
