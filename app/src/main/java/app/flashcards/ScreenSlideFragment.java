package app.flashcards;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class ScreenSlideFragment extends Fragment {
    private Character character;
    private TextToSpeech tts;

    // Required empty public constructor
    public ScreenSlideFragment() {}

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt("position");
        character = CharacterList.getCharacterList().get(position);
        tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    //try to set locale
                    int result = tts.setLanguage(Locale.CHINESE);
                    if(result != TextToSpeech.LANG_AVAILABLE)
                        Toast.makeText(getContext(), "Language is not supported.", Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(getContext(), "Error. Text-to-speech may be disabled.", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.detailed_view_layout, container, false);

        TextView detailedNumberTextView = (TextView) view.findViewById(R.id.detailedNumberTextView);
        TextView detailedCharacterTextView = (TextView) view.findViewById(R.id.detailedCharacterTextView);
        TextView detailedDefinitionsTextView = (TextView) view.findViewById(R.id.detailedDefinitionsTextView);
        final ImageView detailedPlaySoundImageView = (ImageView) view.findViewById(R.id.detailedPlaySoundImageView);

        detailedNumberTextView.setText(String.valueOf(character.getNumber()));
        detailedCharacterTextView.setText(character.getCharacter());
        detailedDefinitionsTextView.setText(formatDefinitionString(character.getDefinitions()));
        detailedPlaySoundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playText();
            }
        });
        return view;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        tts.shutdown();
    }

    private void playText(){
        String s = character.getCharacter().charAt(0) + "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);

    }

    private String formatDefinitionString(List<String> definitionList){
        String def = "";
        for(String s: definitionList)
            def += (s + '\n');
        return def;
    }
}
