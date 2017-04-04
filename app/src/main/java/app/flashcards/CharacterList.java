package app.flashcards;

import android.util.JsonReader;

import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterList {
    private String dataFilePath = "appdata/";
    private String characterDataFileName;
    private String characterStatusFileName;
    protected ArrayList<Character> chlist;

    public CharacterList(String datafn, String statusfn){
        characterDataFileName = dataFilePath + datafn;
        characterStatusFileName = dataFilePath + statusfn;
        chlist = new ArrayList<Character>();
        //read in the data
    }

    public void readData(){
        try {
            JsonReader jsr = new JsonReader(new FileReader(new File(characterDataFileName)));
            System.out.print(jsr.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
