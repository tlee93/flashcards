package app.flashcards;

import java.util.List;

class Word {
    private int number;
    private String word;
    private List<String> definitions;
    private boolean status; //viewed (true) or unviewed (false)

    Word(int n, String w, List<String> d, boolean s){
        number = n;
        word = w;
        definitions = d;
        status = s;
    }

    int getNumber(){
        return number;
    }

    String getWord(){
        return word;
    }

    List<String> getDefinitions(){
        return definitions;
    }

    boolean getStatus(){
        return status;
    }

    public void setStatus(boolean s){
        status = s;
    }
}
