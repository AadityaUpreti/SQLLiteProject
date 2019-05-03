package model;

public class Word {

    private int wordId;
    private String wordName;
    private String wordMeaning;

    public Word(int wordId, String wordName, String wordMeaning) {
        this.wordId = wordId;
        this.wordName = wordName;
        this.wordMeaning = wordMeaning;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }
}
