package app.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainDisplayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        ArrayList<Character> characterList = CharacterList.getCharacterList();

        setContentView(R.layout.activity_main_display);

        recyclerView = (RecyclerView) findViewById(R.id.characterRecycleListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CharacterListAdapter characterListAdapter = new CharacterListAdapter(this, characterList);
        recyclerView.setAdapter(characterListAdapter);
    }
}
