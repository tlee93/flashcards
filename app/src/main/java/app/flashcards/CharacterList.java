package app.flashcards;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CharacterList {
    protected List<Character> chlist;
    String a = null;
    private Context mcontext;

    public CharacterList(Context c){
        chlist = new ArrayList<Character>();
        mcontext = c;
        //read in the data
        readData();
    }

    public void readData() {
        AssetManager am = mcontext.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            File f = new File("ch.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open("ch.json")));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
                sb.append('\n');
            }
            br.close();
            JSONArray jsa = new JSONObject(sb.toString()).getJSONArray("list");
            int size = jsa.length();
            br = new BufferedReader(new InputStreamReader(am.open("statuslist.dat")));
            for(int i = 0; i < size; i++){
                JSONObject current = jsa.getJSONObject(i);
                String ch = current.getString("character");
                JSONArray definitions = current.getJSONArray("definitions");
                List<String> list = new ArrayList<String>();
                for (int j = 0; j < definitions.length(); j++) {
                    list.add( definitions.getString(j) );
                }
                chlist.add(new Character(i+1, ch, list, Integer.parseInt(br.readLine())));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Character> getChlist(){
        return chlist;
    }
}
