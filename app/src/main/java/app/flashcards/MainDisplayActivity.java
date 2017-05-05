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
        //load stuff
        ArrayList<Character> characterList = new CharacterList().getCharacterList();
        //everything else
        setContentView(R.layout.activity_main_display);
//        Bundle b = getIntent().getExtras();
//        if(b != null)
//            characterList = (ArrayList<Character>) b.get("characterList");
        CharacterListAdapter characterListAdapter = new CharacterListAdapter(this, characterList);
        ListView characterListView = (ListView) findViewById(R.id.characterListView);
        characterListView.setAdapter(characterListAdapter);
    }
}
