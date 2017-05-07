package app.flashcards;

import android.content.res.AssetManager;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//singleton for global data access
class CharacterList {
    private static CharacterList characterListInstance = new CharacterList();
    private ArrayList<Character> characterList;

    private CharacterList(){
        AssetManager am = new GlobalApplicationContext().getContext().getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open("ch.json")));
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
                sb.append('\n');
            }
            br.close();
            JSONArray jsa = new JSONObject(sb.toString()).getJSONArray("l"); //start of the list
            int size = jsa.length();
            characterList = new ArrayList<>(size);
            br = new BufferedReader(new InputStreamReader(am.open("statuslist.dat")));
            for(int i = 0; i < size; i++){
                JSONObject current = jsa.getJSONObject(i);
                String ch = current.getString("c"); //get chara
                JSONArray definitions = current.getJSONArray("d"); //get definitions
                List<String> list = new ArrayList<>();
                for (int j = 0; j < definitions.length(); j++)
                    list.add( definitions.getString(j) );
                characterList.add(new Character(i+1, ch, list, Integer.parseInt(br.readLine()) != 0));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Character> getCharacterList(){
        return characterListInstance.characterList;
    }
}
