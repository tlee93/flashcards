package app.flashcards;

public class Character {
    private int number;
    private String character;
    private String[] definitions;
    private int status; //viewed or unviewed

    public Character(int n, String c, String[] d, int s){
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

    public String[] getDefinitions(){
        return definitions;
    }

    public int getStatus(){
        return status;
    }
}
