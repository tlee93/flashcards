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

public class ScreenSlideFragment extends Fragment {
    private Word word;

    // Required empty public constructor
    public ScreenSlideFragment() {}

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt("position");
        word = WordList.getWordList().get(position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.detailed_view_layout, container, false);

        TextView detailedNumberTextView = (TextView) view.findViewById(R.id.detailedNumberTextView);
        TextView detailedCharacterTextView = (TextView) view.findViewById(R.id.detailedWordTextView);
        TextView detailedDefinitionsTextView = (TextView) view.findViewById(R.id.detailedDefinitionsTextView);
        ImageView leftArrowImageView = (ImageView) view.findViewById(R.id.leftArrowImageView);
        ImageView rightArrowImageView = (ImageView) view.findViewById(R.id.rightArrowImageView);
        final ImageView detailedPlaySoundImageView = (ImageView) view.findViewById(R.id.detailedPlaySoundImageView);

        detailedNumberTextView.setText(String.valueOf(word.getNumber()));
        detailedCharacterTextView.setText(word.getWord());
        detailedDefinitionsTextView.setText(ApplicationResourceManager.formatDefinitionString(word.getDefinitions()));
        if(word.getNumber() == 1)
            leftArrowImageView.setVisibility(View.INVISIBLE);
        else if(word.getNumber() == WordList.getWordList().size()+1)
            rightArrowImageView.setVisibility(View.INVISIBLE);

        detailedPlaySoundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakText();
            }
        });
        return view;
    }

    private void speakText(){
        TextToSpeech tts = ApplicationResourceManager.getTTS();
        String s = ApplicationResourceManager.formatWordDisplayString(word.getWord()); //TODO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
        else
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);

    }
}
