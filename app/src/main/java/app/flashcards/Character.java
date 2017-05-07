package app.flashcards;

import java.util.List;

class Character{
    private int number;
    private String character;
    private List<String> definitions;
    private boolean status; //viewed (true) or unviewed (false)

    Character(int n, String c, List<String> d, boolean s){
        number = n;
        character = c;
        definitions = d;
        status = s;
    }

    int getNumber(){
        return number;
    }

    String getCharacter(){
        return character;
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
