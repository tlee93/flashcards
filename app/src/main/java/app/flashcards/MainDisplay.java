package app.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainDisplay extends AppCompatActivity {
    //load json data. display as list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        CharacterList characterList = new CharacterList(this);
        CharacterListAdapter characterListAdapter = new CharacterListAdapter(this, characterList.getCharacterList());
        ListView characterListView = (ListView) findViewById(R.id.characterListView);
        characterListView.setAdapter(characterListAdapter);
    }
}
