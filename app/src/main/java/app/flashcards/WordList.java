package app.flashcards;

import org.json.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

//singleton for global data access
class WordList {
    private static WordList wordListInstance = new WordList();
    private static ArrayList<Word> wordList;

    private WordList(){}

    static ArrayList<Word> getWordList(){
        return wordListInstance.wordList;
    }

    static void loadList(){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = ApplicationResourceManager.getLanguageResourceFile();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
                sb.append('\n');
            }
            br.close();
            JSONArray jsa = new JSONObject(sb.toString()).getJSONArray("l"); //start of the list
            int size = jsa.length();
            wordList = new ArrayList<>(size);
            br = ApplicationResourceManager.getLanguageStatusFile();
            for(int i = 0; i < size; i++){
                JSONObject current = jsa.getJSONObject(i);
                String ch = current.getString("c"); //get chara/word
                JSONArray definitions = current.getJSONArray("d"); //get definitions
                List<String> list = new ArrayList<>();
                for (int j = 0; j < definitions.length(); j++)
                    list.add( definitions.getString(j) );
                wordList.add(new Word(i+1, ch, list, Integer.parseInt(br.readLine()) != 0));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
