package app.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainDisplayActivity extends AppCompatActivity {
    //load json data. display as list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        //store charlist in application context
        CharacterList characterList = new CharacterList(this);
        CharacterListAdapter characterListAdapter = new CharacterListAdapter(this, characterList.getCharacterList());
        ListView characterListView = (ListView) findViewById(R.id.characterListView);
        characterListView.setAdapter(characterListAdapter);
    }
}
