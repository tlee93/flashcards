package app.flashcards;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainDisplayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        ArrayList<Character> characterList = CharacterList.getCharacterList();

        setContentView(R.layout.activity_main_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.characterRecycleListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CharacterListAdapter characterListAdapter = new CharacterListAdapter(this, characterList);
        recyclerView.setAdapter(characterListAdapter);
        recyclerView.scrollToPosition(getPosition());
        recyclerView.addOnScrollListener(new MainDisplayScrollListener());
    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.scrollToPosition(getPosition());
    }

    private int getPosition(){
        SharedPreferences sharedPreferences = getSharedPreferences("currentPosition", MODE_PRIVATE);
        return sharedPreferences.getInt("position", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                return true;
            case R.id.action_info:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private class MainDisplayScrollListener extends RecyclerView.OnScrollListener {
        SharedPreferences.Editor editor = getSharedPreferences("currentPosition", MODE_PRIVATE).edit();
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy){
            editor.putInt("position", layoutManager.findFirstVisibleItemPosition());
            editor.commit();
        }
    }
}
