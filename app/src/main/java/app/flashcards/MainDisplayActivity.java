package app.flashcards;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

    private void initActivity(){
        ApplicationResourceManager.initTTS(this);
        setContentView(R.layout.activity_main_display);
        ArrayList<Word> wordList = WordList.getWordList();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Flashcards/" + ApplicationResourceManager.getLanguage());
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.wordRecycleListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        WordListAdapter wordListAdapter = new WordListAdapter(this, wordList);
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.addOnScrollListener(new MainDisplayScrollListener());
        recyclerView.scrollToPosition(ApplicationResourceManager.getCurrentPosition());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivity();
    }

    @Override
    protected void onResume(){
        super.onResume();
        recyclerView.scrollToPosition(ApplicationResourceManager.getCurrentPosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder alertDialogBuilder;
        AlertDialog alertDialog;
        switch (item.getItemId()) {
            case R.id.action_help:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Help");
                alertDialogBuilder.setMessage(R.string.help_message).setCancelable(true);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            case R.id.action_info:
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("About");
                alertDialogBuilder.setMessage(R.string.info_message).setCancelable(true);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            case R.id.action_switch_language:
                ArrayList<String> languageList = new ArrayList<>(ApplicationResourceManager.getLanguageList());
                languageList.remove(ApplicationResourceManager.getLanguage());
                final String[] ss = new String[languageList.size()];
                for(int i = 0; i < ss.length; i++)
                    ss[i] = languageList.get(i);

                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle(R.string.switch_language_action_title);
                alertDialogBuilder.setItems(ss , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String language = ss[which];
                                ApplicationResourceManager.setLanguage(language);
                                WordList.loadList();
                                initActivity();
                            }
                        });
                alertDialogBuilder.setCancelable(true);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class MainDisplayScrollListener extends RecyclerView.OnScrollListener {
        int actionBarHeight = R.attr.actionBarSize;
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy){
            if(dy > actionBarHeight)
                getActionBar().hide();
            else if(dy < actionBarHeight * -1)
                getActionBar().show();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState){
            if (newState == RecyclerView.SCROLL_STATE_IDLE)
                ApplicationResourceManager.setCurrentPosition(layoutManager.findFirstVisibleItemPosition());
        }
    }
}
