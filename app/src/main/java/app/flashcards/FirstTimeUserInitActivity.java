package app.flashcards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstTimeUserInitActivity extends AppCompatActivity {
    private String selectedLanguage;
    private ImageButton imageButton;
    private TextView selectedLanguageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        sharedPreferences.edit();
        if(sharedPreferences.getString("currentLanguage", null) == null){
            setContentView(R.layout.first_time_init_layout);
            ListView languageList = (ListView) findViewById(R.id.languagesList);
            languageList.setAdapter(new LanguageListAdapter(getApplicationContext(), ApplicationResourceManager.getLanguageList()));
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

    private class LanguageListAdapter extends ArrayAdapter<String>{
        public LanguageListAdapter(Context context, ArrayList<String> resource) {
            super(context, 0, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            String l = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.language_list_entry_layout, parent, false);
            }
            TextView languageListTextView = (TextView) convertView.findViewById(R.id.languageListTextView);
            languageListTextView.setText(l);
            return convertView;
        }
    }
}
