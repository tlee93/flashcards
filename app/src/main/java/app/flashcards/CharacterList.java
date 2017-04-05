package app.flashcards;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.TextView;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CharacterList {
    private Context mcontext;
    protected ArrayList<Character> chlist;

    public CharacterList(Context c){
        mcontext = c;
        chlist = new ArrayList<Character>();
        //read in the data
        //readData();
    }

    public String readData(){
        AssetManager am = mcontext.getAssets();
        String s = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open("ch.json")));
            while(!br.ready());
            s= br.toString();
            //JSONTokener jst = new JSONTokener(br.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
