package com.example.danciben3;

public class Items {
    private String word;
    private String sentence;
    private String mean;

    public Items()
    {
        word="";
        mean="";
        sentence="";
    }
    public Items(String w,String m,String s)
    {
        this.word=w;
        this.mean=m;
        this.sentence=s;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public void setMean(String mean){this.mean=mean;}
    public String getSentence() {
        return sentence;
    }
    public String getMean(){return mean;}

    public String getWord() {
        return word;
    }
}
