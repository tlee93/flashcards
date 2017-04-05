package app.flashcards;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CharacterList {
    protected ArrayList<Character> chlist;
    String a = null;
    Context mcontext;

    public CharacterList(Context c){
        chlist = new ArrayList<Character>();
        mcontext = c;
        //read in the data
        readData();
    }

    public void readData() {
        AssetManager am = mcontext.getAssets();
        StringBuilder text = new StringBuilder();
        try {
            File f = new File("ch.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open("example.csv")));
            String line;
            while ((line = br.readLine()) != null){
                text.append(line);
                text.append('\n');
            }
            br.close();
            a = br.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
