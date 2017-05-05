package app.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedViewActivity extends AppCompatActivity {
    int position;
    ArrayList<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);
        android.app.ActionBar actionBar = getActionBar();
        if(actionBar != null)
            actionBar.hide();

        characterList = CharacterList.getCharacterList();
        Bundle b = getIntent().getExtras();
        position = 0;
        if(b != null)
            position = b.getInt("position");

        TextView detailedNumberTextView = (TextView) findViewById(R.id.detailedNumberTextView);
        TextView detailedCharacterTextView = (TextView) findViewById(R.id.detailedCharacterTextView);
        TextView detailedDefinitionsTextView = (TextView) findViewById(R.id.detailedDefinitionsTextView);
        Character character = characterList.get(position);
        detailedNumberTextView.setText(String.valueOf(character.getNumber()));
        detailedCharacterTextView.setText(character.getCharacter());
        detailedDefinitionsTextView.setText(character.getDefinitions().toString());
    }
}
