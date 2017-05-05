package app.flashcards;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

//probably not needed
public class LoadingScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        if(getActionBar() != null)
            getActionBar().hide();
        ArrayList<Character> characterList = new CharacterList().getCharacterList();
        Intent intent = new Intent(this, MainDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("characterList", characterList);
        intent.putExtras(bundle);
        startActivity(intent);
        //if(loadDataTask.getStatus() == AsyncTask.Status.FINISHED)
        finish();
    }
}

class AsyncLoadDataTask extends AsyncTask<Void, Void, ArrayList<Character>>{

    @Override
    protected ArrayList<Character> doInBackground(Void... params) {
        ArrayList<Character> characterList = new CharacterList().getCharacterList();
        return characterList;
    }
}