package app.flashcards;

import java.util.List;

public class Character{
    private int number;
    private String character;
    private List<String> definitions;
    private boolean status; //viewed (true) or unviewed (false)

    public Character(int n, String c, List<String> d, boolean s){
        number = n;
        character = c;
        definitions = d;
        status = s;
    }

    public int getNumber(){
        return number;
    }

    public String getCharacter(){
        return character;
    }

    public List<String> getDefinitions(){
        return definitions;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean s){
        status = s;
    }
}
