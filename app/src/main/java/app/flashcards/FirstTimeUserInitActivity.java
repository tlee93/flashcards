package app.flashcards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstTimeUserInitActivity extends AppCompatActivity {
    //TODO create the layout to ask user to pick the language they want, save it in sharedpref and then start main activity
    String selectedLanguage;
    ImageButton imageButton;
    ListView languageList;
    TextView selectedLanguageView;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        sharedPreferences.edit();
        if(sharedPreferences.contains("currentLanguage")){
            setContentView(R.layout.first_time_init_layout);
            languageList = (ListView) findViewById(R.id.languagesList);
            languageList.setOnItemClickListener(new LanguageListHandler());
            imageButton = (ImageButton) findViewById(R.id.continueButton);
            imageButton.setOnClickListener(new ContinueButtonHandler());
        }else{
            startNextActivity();
        }
    }

    private void startNextActivity(){
        WordList.loadList();
        Intent intent = new Intent(this, MainDisplayActivity.class);
        startActivity(intent);
        finish();
    }

    private class ContinueButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (selectedLanguage == null)
                Toast.makeText(getApplicationContext(), "Choose a language first.", Toast.LENGTH_SHORT).show();
            else{
                imageButton.setEnabled(false);
                ApplicationResourceManager.setLanguage(selectedLanguage);
                startNextActivity();
            }
        }
    }

    private class LanguageListHandler implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(selectedLanguageView != null)
                selectedLanguageView.setBackgroundColor(0x00000000);
            selectedLanguageView = (TextView) view;
            selectedLanguage = selectedLanguageView.getText().toString();
            selectedLanguageView.setBackgroundColor(0xFFAAAAAA);
        }
    }
}
