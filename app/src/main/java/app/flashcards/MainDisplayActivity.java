package app.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainDisplayActivity extends AppCompatActivity {
    //load json data. display as list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        //load the data
        ArrayList<Character> characterList = CharacterList.getCharacterList();
        //everything else
        setContentView(R.layout.activity_main_display);
        CharacterListAdapter characterListAdapter = new CharacterListAdapter(this, characterList);
        ListView characterListView = (ListView) findViewById(R.id.characterListView);
        characterListView.setAdapter(characterListAdapter);
    }
}
