package app.flashcards;

import java.util.List;

public class Character {
    private int number;
    private String character;
    private List<String> definitions;
    private int status; //viewed or unviewed

    public Character(int n, String c, List<String> d, int s){
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

    public int getStatus(){
        return status;
    }

    public String toString(){
        return number + " " + character + " " + definitions.get(0) + " " + status;
    }
}
